
package net.subaraki.gravestone.tileentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.inventory.GraveInventoryArmor;
import net.subaraki.gravestone.util.GraveUtility;

import com.mojang.authlib.GameProfile;

public class TileEntityGravestone extends TileEntity {

    public GraveInventory mainInv = new GraveInventory("Minecraft", "minecraft", 36, this);
    public GraveInventory armor = new GraveInventoryArmor("minecraft", this);
    public List<GraveInventory> inventories = new ArrayList<GraveInventory>();

    public String playername;
    public GameProfile profile;
    public int modelType;
    public boolean maleEpitaph;
    public float ModelRotation;
    public EntityPlayer entityPlayerStub;
    public String message1;
    public String message2;
    public boolean isDecorativeGrave;
    public boolean hasItems;
    public String locked;
    public boolean looted;
    Random rand;

    public TileEntityGravestone() {
        this.playername = "";
        this.modelType = 0;
        this.maleEpitaph = true;
        this.ModelRotation = 0.0f;
        this.message1 = "";
        this.message2 = "";
        this.isDecorativeGrave = false;
        this.hasItems = false;
        this.locked = "";
        this.looted = false;
        this.rand = new Random();
    }

    public void setGraveData(String playername, int modelid, boolean maleEpitaph) {
        this.playername = playername;
        this.modelType = modelid;
        this.maleEpitaph = maleEpitaph;
        if (this.modelType == 5 && playername != "") {
            this.profile = new GameProfile((UUID) null, playername);
            fixProfile();
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.playername = nbt.getString("name");
        this.message1 = nbt.getString("message");
        this.message2 = nbt.getString("message2");
        this.modelType = nbt.getInteger("Meta");
        this.maleEpitaph = nbt.getBoolean("maleEpitaph");
        this.ModelRotation = nbt.getFloat("rotation");
        this.looted = nbt.getBoolean("isLooted");
        this.isDecorativeGrave = nbt.getBoolean("decoGrave");
        if (this.modelType == 5 && playername.length() > 0) {
            this.profile = new GameProfile((UUID) null, playername);
            fixProfile();
        }
        if (nbt.hasKey("ListItems")) {
            NBTTagList listItems = nbt.getTagList("ListItems", 10);
            List<ItemStack> items = new ArrayList<ItemStack>();
            for (int j = 0; j < listItems.tagCount(); ++j) {
                NBTTagCompound tagCompound = listItems.getCompoundTagAt(j);
                items.add(ItemStack.loadItemStackFromNBT(tagCompound));
            }
            if (!items.isEmpty()) {
                int size = items.size();
                GraveInventory inv = new GraveInventory("Outdated", "", size, this);
                for (int i = 0; i < size; i++) {
                    inv.setInventorySlotContents(i, items.get(i));
                }
                this.inventories.add(inv);
            }
        } else {
            this.mainInv = new GraveInventory();
            this.mainInv.grave = this;
            if (nbt.hasKey("mainInv")) this.mainInv.readFromNBT(nbt.getCompoundTag("mainInv"));

            this.armor = new GraveInventoryArmor();
            this.armor.grave = this;
            if (nbt.hasKey("armor")) this.armor.readFromNBT(nbt.getCompoundTag("armor"));

            GraveInventory inv;
            this.inventories.clear();
            NBTTagList inventories = (NBTTagList) nbt.getTag("inventories");
            for (int i = 0; i < inventories.tagCount(); i++) {
                inv = GraveInventory.loadFromNBT(inventories.getCompoundTagAt(i));
                inv.grave = this;
                this.inventories.add(inv);
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setString("name", this.playername);
        nbt.setString("message", this.message1);
        nbt.setString("message2", this.message2);
        nbt.setInteger("Meta", this.modelType);
        nbt.setBoolean("maleEpitaph", this.maleEpitaph);
        nbt.setFloat("rotation", this.ModelRotation);
        nbt.setBoolean("isLooted", this.looted);
        nbt.setBoolean("decoGrave", this.isDecorativeGrave);

        if (this.mainInv != null) nbt.setTag("mainInv", this.mainInv.writeToNBT());
        if (this.armor != null) nbt.setTag("armor", this.armor.writeToNBT());

        NBTTagList inventories = new NBTTagList();
        for (GraveInventory graveInv : this.inventories) {
            if (graveInv != null) inventories.appendTag(graveInv.writeToNBT());
        }
        nbt.setTag("inventories", inventories);
    }

    public List<GraveInventory> getAllInventories() {
        List<GraveInventory> invList = new ArrayList<GraveInventory>();
        invList.add(mainInv);
        invList.add(armor);
        invList.addAll(inventories);
        return invList;
    }

    public void autoEquipItems(EntityPlayer player) {
        for (GraveInventory inv : getAllInventories()) {
            inv.autoEquipItems(player);
            if (inv.isEmpty() && this.inventories.contains(inv)) this.inventories.remove(inv);
        }
        markDirty();
    }

    public void dropContents(World world, int x, int y, int z) {
        for (GraveInventory inv : getAllInventories()) {
            for (int i = 0; i < inv.getSizeInventory(); i++) {
                ItemStack stack = inv.getStackInSlot(i);
                if (stack != null) {
                    double xOffset = this.rand.nextDouble() * 0.8d + 0.1d;
                    double yOffset = this.rand.nextDouble() * 0.8d + 0.1d;
                    double zOffset = this.rand.nextDouble() * 0.8d + 0.1d;
                    while (stack.stackSize > 0) {
                        int dropSize = this.rand.nextInt(21) + 10;
                        if (dropSize > stack.stackSize) {
                            dropSize = stack.stackSize;
                        }
                        ItemStack itemStack = stack;
                        itemStack.stackSize -= dropSize;
                        EntityItem entityItem = new EntityItem(
                            world,
                            x + xOffset,
                            y + yOffset,
                            z + zOffset,
                            new ItemStack(stack.getItem(), dropSize, stack.getItemDamage()));
                        entityItem.motionX = this.rand.nextGaussian() * 0.05000000074505806;
                        entityItem.motionY = this.rand.nextGaussian() * 0.25;
                        entityItem.motionZ = this.rand.nextGaussian() * 0.05000000074505806;
                        if (stack.hasTagCompound()) {
                            entityItem.getEntityItem()
                                .setTagCompound(
                                    (NBTTagCompound) stack.getTagCompound()
                                        .copy());
                        }
                        world.spawnEntityInWorld((Entity) entityItem);
                    }
                }
            }
        }
    }

    public String setName(String name) {
        this.playername = name;
        if (this.modelType == 5 && playername != "") {
            this.profile = new GameProfile((UUID) null, name);
            fixProfile();
        }
        return this.playername;
    }

    public Entity setPlayer(EntityPlayer player) {
        if (this.modelType == 5 && player.getDisplayName() != "") {
            this.profile = new GameProfile((UUID) null, player.getDisplayName());
            fixProfile();
        }
        return this.entityPlayerStub = player;
    }

    public void setDeathMessage(String message) {
        this.message1 = message;
    }

    public void setDeathMessage2(String message) {
        this.message2 = message;
    }

    public void setMeta(int i) {
        this.modelType = i;
    }

    public void updateEntity() {
        super.updateEntity();
        checkForItems();
    }

    public void checkForItems() {
        for (GraveInventory inv : this.getAllInventories()) {
            for (int i = 0; i < inv.getSizeInventory(); i++) {
                ItemStack element = inv.getStackInSlot(i);
                if (element != null) {
                    this.hasItems = true;
                    return;
                }
            }
        }
        this.hasItems = false;
    }

    public double getMaxRenderDistanceSquared() {
        return 65536.0;
    }

    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

    public boolean canUpdate() {
        return true;
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
    }

    private void fixProfile() {
        this.profile = GraveUtility.fixProfile(this.profile);
        this.markDirty();
    }

    public ItemStack getStackInSlot(int slotIndex) {
        // TODO Auto-generated method stub
        return null;
    }
}
