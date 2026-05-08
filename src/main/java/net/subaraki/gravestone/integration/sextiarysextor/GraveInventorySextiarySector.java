package net.subaraki.gravestone.integration.sextiarysextor;

import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import shift.sextiarysector.container.InventoryPlayerNext;
import shift.sextiarysector.player.EntityPlayerManager;

public class GraveInventorySextiarySector extends GraveInventory {

    public GraveInventorySextiarySector() {
        super();
    }

    public GraveInventorySextiarySector(int size, TileEntityGravestone grave) {
        super(SextiarySectorIntegration.inventoryName, SextiarySectorIntegration.modId, size, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        InventoryPlayerNext inv = EntityPlayerManager.getEquipmentStats(player).inventory;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if (inv.getStackInSlot(i) == null) {
                inv.setInventorySlotContents(i, this.getStackInSlot(i));
                this.setInventorySlotContents(i, null);
            }
        }
        super.autoEquipItems(player);
    }

}
