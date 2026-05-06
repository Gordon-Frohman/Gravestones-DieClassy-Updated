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
        IInventory sack = getInventory(player);
        for (int i = 0; i < sack.getSizeInventory(); i++) {
            if (sack.getStackInSlot(i) == null) {
                sack.setInventorySlotContents(i, this.getStackInSlot(i));
                this.setInventorySlotContents(i, null);
            }
        }
        super.autoEquipItems(player);
    }

    protected abstract IInventory getInventory(EntityPlayer player);

}
