package net.subaraki.gravestone.integration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public abstract class ModIntegration {

    protected final String modName;
    public final String modID;

    public ModIntegration(String modName, String modID, String invName, Class<? extends GraveInventory> invClass) {
        this(modName, modID, new String[] { invName }, new Class<?>[] { invClass });
    }

    @SuppressWarnings("unchecked")
    public ModIntegration(String modName, String modID, String[] invNames, Class<?>[] classes) {
        this.modName = modName;
        this.modID = modID;
        for (int i = 0; i < invNames.length; i++) GraveInventory.modSpecificInventories
            .put(modID + ": " + invNames[i], (Class<? extends GraveInventory>) classes[i]);
    }

    public abstract ItemStack getModIcon();

    public boolean storeItems(TileEntityGravestone te, EntityPlayer player) {
        IInventory inv = getInventory(player);
        if (inv != null) {
            int size = inv.getSizeInventory();
            GraveInventory graveInv = getGraveInventory(size, te);
            for (int i = 0; i < size; ++i) {
                ItemStack stack = inv.getStackInSlot(i);
                if (stack != null) {
                    graveInv.setInventorySlotContents(i, stack);
                    inv.setInventorySlotContents(i, null);
                }
            }
            if (!graveInv.isEmpty()) {
                te.inventories.add(graveInv);
                return true;
            }
        } else {
            GraveStones.printDebugMessage(
                "GraveStones Mod couldn't connect to " + modName
                    + ". Have these classes been modified? Report to mod Author pleases.");
        }
        return false;
    }

    protected abstract IInventory getInventory(EntityPlayer player);

    protected abstract GraveInventory getGraveInventory(int size, TileEntityGravestone grave);

}
