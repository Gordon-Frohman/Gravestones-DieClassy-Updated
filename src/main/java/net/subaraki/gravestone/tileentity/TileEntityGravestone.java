



package net.subaraki.gravestone.tileentity;

import net.minecraft.tileentity.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.subaraki.gravestone.*;
import net.subaraki.gravestone.util.GraveUtility;
import net.minecraft.nbt.*;
import net.minecraft.world.*;
import net.minecraft.entity.item.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.network.play.server.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.*;

public class TileEntityGravestone extends TileEntity implements IInventory
{
    public ItemStack[] list;
    public ItemStack[] slots;
    public int tab;
    public String playername;
    public GameProfile profile;
    public int modelType;
    public float ModelRotation;
    public EntityPlayer entityPlayerStub;
    public String message1;
    public String message2;
    public boolean isDecorativeGrave;
    public boolean hasItems;
    public String locked;
    public boolean otherPlayerHasTakenItemStack;
    public ResourceLocation skinLocation = null;
    Random rand;
    
    public TileEntityGravestone() {
        this.list = new ItemStack[128];
        this.slots = new ItemStack[40];
        this.tab = 0;
        this.playername = "";
        this.modelType = 0;
        this.ModelRotation = 0.0f;
        this.message1 = "";
        this.message2 = "";
        this.isDecorativeGrave = false;
        this.hasItems = false;
        this.locked = "";
        this.otherPlayerHasTakenItemStack = false;
        this.rand = new Random();
    }
    
    public void setGraveData(final String playername, final int modelid) {
        this.playername = playername;
        this.modelType = modelid;
        if(this.modelType == 5 && playername != "")
        {
        	//this.downloadSkin();
            this.profile = new GameProfile((UUID)null, playername);
            fixProfile();
        }
    }
    
    public int getSizeInventory() {
        return this.slots.length;
    }
    
    public ItemStack getStackInSlot(final int par1) {
        return this.slots[par1];
    }
    
    public void setInventorySlotContents(final int slot, final ItemStack par2ItemStack) {
        final int slotID = this.getListSlotID(slot);
        if (slotID == -1) {
            GraveStones.printDebugMessage("Tab id was not recognized ! This is a bug or inimplemented feature. please report to mod author !");
            GraveStones.printDebugMessage("Tried getting content of tab #" + this.tab + " this should be the " + this.modNameForTab(this.tab) + " inventory");
        }
        this.slots[slot] = par2ItemStack;
        this.list[slotID] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }
    
    public ItemStack decrStackSize(final int slot, final int ammount) {
        final int slotID = this.getListSlotID(slot);
        if (slotID == -1) {
            GraveStones.printDebugMessage("Tab id was not recognized ! This is a bug or inimplemented feature. please report to mod author !");
            GraveStones.printDebugMessage("Tried getting content of tab #" + this.tab + " this should be the " + this.modNameForTab(this.tab) + " inventory");
        }
        GraveStones.printDebugMessage("");
        if (this.slots[slot] == null) {
            return null;
        }
        if (this.slots[slot].stackSize <= ammount) {
            final ItemStack itemstack = this.slots[slot];
            this.slots[slot] = null;
            this.list[slotID] = null;
            return itemstack;
        }
        final ItemStack itemstack = this.slots[slot].splitStack(ammount);
        if (this.slots[slot].stackSize == 0) {
            this.slots[slot] = null;
            this.list[slotID] = null;
        }
        return itemstack;
    }
    
    public ItemStack getStackInSlotOnClosing(final int par1) {
        return null;
    }
    
    public int getInventoryStackLimit() {
        return 64;
    }
    
    public boolean isUseableByPlayer(final EntityPlayer par1EntityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && par1EntityPlayer.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) <= 64.0;
    }
    
    public String getInvName() {
        return "Grave";
    }
    
    public boolean isItemValidForSlot(final int i, final ItemStack itemstack) {
        return true;
    }
    
    public boolean isInvNameLocalized() {
        return true;
    }
    
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.playername = nbt.getString("name");
        this.message1 = nbt.getString("message");
        this.message2 = nbt.getString("message2");
        this.modelType = nbt.getInteger("Meta");
        this.ModelRotation = nbt.getFloat("rotation");
        this.otherPlayerHasTakenItemStack = nbt.getBoolean("isLooted");
        this.isDecorativeGrave = nbt.getBoolean("decoGrave");
        if(this.modelType == 5 && playername != "")
        {
            this.profile = new GameProfile((UUID)null, playername);
            fixProfile();
        }
        /*if(nbt.hasKey("skinLocation")) {
        	this.skinLocation = new ResourceLocation(nbt.getString("skinLocation"));
        }*/
        final NBTTagList tagList = nbt.getTagList("Items", 10);
        for (int i = 0; i < tagList.tagCount(); ++i) {
            final NBTTagCompound tag = tagList.getCompoundTagAt(i);
            final byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < this.slots.length) {
                this.slots[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
        final NBTTagList tagList2 = nbt.getTagList("ListItems", 10);
        for (int j = 0; j < tagList2.tagCount(); ++j) {
            final NBTTagCompound tag2 = tagList2.getCompoundTagAt(j);
            final byte slot2 = tag2.getByte("ListSlot");
            if (slot2 >= 0 && slot2 < this.list.length) {
                this.list[slot2] = ItemStack.loadItemStackFromNBT(tag2);
            }
        }
    }
    
    public void writeToNBT(final NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setString("name", this.playername);
        par1NBTTagCompound.setString("message", this.message1);
        par1NBTTagCompound.setString("message2", this.message2);
        par1NBTTagCompound.setInteger("Meta", this.modelType);
        par1NBTTagCompound.setFloat("rotation", this.ModelRotation);
        par1NBTTagCompound.setBoolean("isLooted", this.otherPlayerHasTakenItemStack);
        par1NBTTagCompound.setBoolean("decoGrave", this.isDecorativeGrave);
        /*if(skinLocation != null) {
        	par1NBTTagCompound.setString("skinLocation", this.skinLocation.toString());
        }*/
        
        final NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.slots.length; ++i) {
            if (this.slots[i] != null) {
                final NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.slots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag((NBTBase)nbttagcompound1);
            }
        }
        par1NBTTagCompound.setTag("Items", (NBTBase)nbttaglist);
        final NBTTagList nbttaglist2 = new NBTTagList();
        for (int j = 0; j < this.list.length; ++j) {
            if (this.list[j] != null) {
                final NBTTagCompound nbttagcompound2 = new NBTTagCompound();
                nbttagcompound2.setByte("ListSlot", (byte)j);
                this.list[j].writeToNBT(nbttagcompound2);
                nbttaglist2.appendTag((NBTBase)nbttagcompound2);
            }
        }
        par1NBTTagCompound.setTag("ListItems", (NBTBase)nbttaglist2);
    }
    
    public void dropContents(final World world, final int x, final int y, final int z) {
        if (this != null) {
            for (int slotIndex = 0; slotIndex < this.list.length; ++slotIndex) {
                final ItemStack items = this.getStackInSlot(slotIndex);
                if (items != null) {
                    final float var10 = this.rand.nextFloat() * 0.8f + 0.1f;
                    final float var11 = this.rand.nextFloat() * 0.8f + 0.1f;
                    final float var12 = this.rand.nextFloat() * 0.8f + 0.1f;
                    while (items.stackSize > 0) {
                        int var13 = this.rand.nextInt(21) + 10;
                        if (var13 > items.stackSize) {
                            var13 = items.stackSize;
                        }
                        final ItemStack itemStack = items;
                        itemStack.stackSize -= var13;
                        final EntityItem entityItem = new EntityItem(world, (double)(x + var10), (double)(y + var11), (double)(z + var12), new ItemStack(items.getItem(), var13, items.getItemDamage()));
                        entityItem.motionX = this.rand.nextGaussian() * 0.05000000074505806;
                        entityItem.motionY = this.rand.nextGaussian() * 0.25;
                        entityItem.motionZ = this.rand.nextGaussian() * 0.05000000074505806;
                        if (items.hasTagCompound()) {
                            entityItem.getEntityItem().setTagCompound((NBTTagCompound)items.getTagCompound().copy());
                        }
                        world.spawnEntityInWorld((Entity)entityItem);
                    }
                }
            }
        }
    }
    
    public String setName(final String name) {
        this.playername = name;
        if(this.modelType == 5 && playername != "")
        {
        	//this.downloadSkin();
        	//this.skinLocation = GraveUtility.instance.processPlayerTexture(this.playername);
            this.profile = new GameProfile((UUID)null, name);
            fixProfile();
        }
        return this.playername;
    }
    
    public Entity setPlayer(final EntityPlayer player) {
        if(this.modelType == 5 && player.getDisplayName() != "")
        {
            this.profile = new GameProfile((UUID)null, player.getDisplayName());
            fixProfile();
        }
        return (Entity)(this.entityPlayerStub = player);
    }
    
    public void setDeathMessage(final String message) {
        this.message1 = message;
    }
    
    public void setDeathMessage2(final String message) {
        this.message2 = message;
    }
    
    public void setMeta(final int i) {
        this.modelType = i;
    }
    
    public void updateEntity() {
        super.updateEntity();
        for (final ItemStack element : this.list) {
            if (element != null) {
                this.hasItems = true;
                break;
            }
            this.hasItems = false;
        }
    }
    
    public double getMaxRenderDistanceSquared() {
        return 65536.0;
    }
    
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }
    
    public String getInventoryName() {
        return StatCollector.translateToLocal("grave.container.name");
    }
    
    public boolean hasCustomInventoryName() {
        return true;
    }
    
    public void openInventory() {
    }
    
    public void closeInventory() {
    }
    
    public boolean canUpdate() {
        return true;
    }
    
    public Packet getDescriptionPacket() {
        final NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return (Packet)new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }
    
    public void onDataPacket(final NetworkManager net, final S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
    }
    
    public void changeSlotLayout(final byte b) {
        for (int i = 0; i < this.slots.length; ++i) {
            this.slots[i] = null;
        }
        switch (b) {
            case 0: {
                for (int i = 0; i < this.slots.length; ++i) {
                    this.slots[i] = this.list[i];
                }
                break;
            }
            case 1: {
                for (int i = 0; i < 7; ++i) {
                    this.slots[i] = this.list[i + 40];
                }
                break;
            }
            case 2: {
                for (int i = 0; i < 27; ++i) {
                    this.slots[i] = this.list[i + 47];
                }
                for (int i = 0; i < 7; ++i) {
                    this.slots[i + 27] = this.list[i + 74];
                }
                break;
            }
            case 3: {
                for (int i = 0; i < 4; ++i) {
                    this.slots[i] = this.list[i + 81];
                }
                break;
            }
            case 4: {
                for (int i = 0; i < 10; ++i) {
                    this.slots[i] = this.list[i + 85];
                }
                break;
            }
            case 5: {
                for (int i = 0; i < 3; ++i) {
                    this.slots[i] = this.list[i + 95];
                }
                break;
            }
        }
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }
    
    private int getListSlotID(final int slot) {
        return (this.tab == 0) ? slot : ((this.tab == 1) ? (slot + 40) : ((this.tab == 2) ? (slot + 47) : ((this.tab == 3) ? (slot + 81) : ((this.tab == 4) ? (slot + 85) : ((this.tab == 5) ? (slot + 95) : -1)))));
    }
    
    private String modNameForTab(final int tabId) {
        String name = null;
        if (tabId == 0) {
            name = "Minecraft";
        }
        if (tabId == 1) {
            name = "Rpg Inventory";
        }
        if (tabId == 2) {
            name = "Tinkers Construct";
        }
        if (tabId == 3) {
            name = "Baubles Inventory";
        }
        if (tabId == 4) {
            name = "Galacticraft";
        }
        if (tabId == 5) {
            name = "Mariculture";
        }
        return name;
    }

    private void fixProfile() {
        if (this.profile != null && !StringUtils.isNullOrEmpty(this.profile.getName())) {
            if (!this.profile.isComplete() || !this.profile.getProperties().containsKey("textures")) {
                GameProfile gameprofile = MinecraftServer.getServer().func_152358_ax().func_152655_a(this.profile.getName());

                if (gameprofile != null) {
                    Property property = (Property)Iterables.getFirst(gameprofile.getProperties().get("textures"), (Object)null);

                    if (property == null) {
                        gameprofile = MinecraftServer.getServer().func_147130_as().fillProfileProperties(gameprofile, true);
                    }

                    this.profile = gameprofile;
                    this.markDirty();
                }
            }
        }
    }
    
    public void downloadSkin()
    {
        HttpsURLConnection httpurlconnection = null;
        ResourceLocation resourcelocation = null;
        GraveStones.printDebugMessage("Downloading "+this.playername+"'s skin");
        String skinPath = "";
        try
        {
            httpurlconnection = (HttpsURLConnection)(new URL("https://mineskin.eu/skin/"+this.playername)).openConnection(Minecraft.getMinecraft().getProxy());
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(false);
            httpurlconnection.connect();
 
            if (httpurlconnection.getResponseCode() / 100 != 2)
            {
            	GraveStones.printDebugMessage("Server response code did not return 200, skin servers might be down.");
            }
 
            BufferedImage bufferedimage;
            bufferedimage = ImageIO.read(httpurlconnection.getInputStream());
            skinPath = "./cachedImages/skins/"+this.playername+".png";
            File outputFile = new File(skinPath);
            ImageIO.write(bufferedimage, "png", outputFile);
        }
        catch (Exception exception)
        {
        	GraveStones.printDebugMessage("Error occurred when downloading skin, however, skin servers seem to be up.");
        }
        finally
        {
            if (httpurlconnection != null)
            {
                httpurlconnection.disconnect();
            }
        }
    }
}
