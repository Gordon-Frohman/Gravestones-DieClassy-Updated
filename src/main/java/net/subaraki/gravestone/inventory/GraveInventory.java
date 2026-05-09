package net.subaraki.gravestone.inventory;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import cpw.mods.fml.common.Loader;

public class GraveInventory implements IInventory {

    public static Map<String, Class<? extends GraveInventory>> modSpecificInventories = new HashMap<String, Class<? extends GraveInventory>>();

    protected String name;
    protected String modID;
    protected int size;
    public ItemStack icon;
    protected Map<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
    public TileEntityGravestone grave;

    public GraveInventory() {}

    public GraveInventory(String name, String modID, int size, TileEntityGravestone grave) {
        this.name = name;
        this.modID = modID;
        this.size = size;
        this.grave = grave;
    }

    public void autoEquipItems(EntityPlayer player) {
        for (int i = 0; i < size; i++) {
            ItemStack item = items.get(i);
            if (item != null) {
                boolean removeItem = false;
                if (player.inventory.getStackInSlot(i) == null) {
                    player.inventory.setInventorySlotContents(i, item);
                    removeItem = true;
                } else {
                    removeItem = player.inventory.addItemStackToInventory(item);
                }
                if (!removeItem) break;
                else items.put(i, null);
            }
        }
    }

    public boolean isEmpty() {
        for (ItemStack item : items.values()) if (item != null) return false;
        return true;
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setString("name", name);
        nbt.setString("modID", modID);
        nbt.setInteger("size", size);
        if (this.icon != null) nbt.setTag("icon", this.icon.writeToNBT(new NBTTagCompound()));
        NBTTagList items = new NBTTagList();
        for (int i = 0; i < size; i++) {
            ItemStack item = this.items.get(i);
            if (item != null) {
                NBTTagCompound itemNBT = item.writeToNBT(new NBTTagCompound());
                itemNBT.setInteger("slotId", i);
                items.appendTag(itemNBT);
            }
        }
        nbt.setTag("items", items);

        return nbt;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.name = nbt.getString("name");
        this.modID = nbt.getString("modID");
        this.size = nbt.getInteger("size");
        if (nbt.hasKey("icon")) this.icon = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("icon"));
        NBTTagList items = (NBTTagList) nbt.getTag("items");
        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound itemNBT = items.getCompoundTagAt(i);
            if (itemNBT != null) {
                ItemStack item = ItemStack.loadItemStackFromNBT(itemNBT);
                if (item != null) {
                    int slotId = itemNBT.hasKey("slotId") ? itemNBT.getInteger("slotId") : i;
                    this.items.put(slotId, item);
                }
            }
        }
    }

    public static GraveInventory loadFromNBT(NBTTagCompound nbt) {
        GraveInventory result = null;

        String modId = nbt.getString("modID");
        boolean useDefaultInv = true;
        if (Loader.isModLoaded(modId)) {
            Class<? extends GraveInventory> invClass = modSpecificInventories.get(modId + ": " + nbt.getString("name"));
            try {
                result = invClass.newInstance();
                useDefaultInv = false;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (useDefaultInv) result = new GraveInventory();
        result.readFromNBT(nbt);

        return (result.isEmpty() && result.modID != "minecraft") ? null : result;
    }

    @Override
    public int getSizeInventory() {
        return this.size;
    }

    public int getRowsCount() {
        int rows = Math.floorDiv(this.size, 9);
        return this.size % 9 == 0 ? rows : rows + 1;
    }

    @Override
    public ItemStack getStackInSlot(int slotIn) {
        return this.items.get(slotIn);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = this.items.get(index);
        if (stack != null) {
            ItemStack copy = stack.copy();
            if (copy.stackSize > count) {
                copy.stackSize = count;
                stack.stackSize -= count;
            } else {
                this.items.remove(index);
            }
            return copy;
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return this.items.get(index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index >= 0 && index < size) items.put(index, stack);
    }

    @Override
    public String getInventoryName() {
        return StatCollector.translateToLocal("grave.inventory." + this.name);
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
        if (this.grave != null) this.grave.markDirty();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

}
