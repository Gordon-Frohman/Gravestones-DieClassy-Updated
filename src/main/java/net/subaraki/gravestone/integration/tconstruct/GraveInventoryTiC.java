package net.subaraki.gravestone.integration.tconstruct;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public abstract class GraveInventoryTiC extends GraveInventory {

    public GraveInventoryTiC() {
        super();
    }

    public GraveInventoryTiC(String name, int size, TileEntityGravestone grave) {
        super(name, TinkersConstructIntegration.modId, size, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        IInventory inv = getInventory(player);
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if (inv.getStackInSlot(i) == null) {
                inv.setInventorySlotContents(i, this.getStackInSlot(i));
                this.setInventorySlotContents(i, null);
            }
        }
        super.autoEquipItems(player);
    }

    protected abstract IInventory getInventory(EntityPlayer player);

}
