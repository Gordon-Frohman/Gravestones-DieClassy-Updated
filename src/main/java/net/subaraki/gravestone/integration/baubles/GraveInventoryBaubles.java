package net.subaraki.gravestone.integration.baubles;

import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

public class GraveInventoryBaubles extends GraveInventory {

    public GraveInventoryBaubles() {
        super();
    }

    public GraveInventoryBaubles(int size, TileEntityGravestone grave) {
        super(BaublesIntegration.modName, BaublesIntegration.modName, size, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        InventoryBaubles inv = PlayerHandler.getPlayerBaubles(player);
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if (inv.getStackInSlot(i) == null) {
                inv.setInventorySlotContents(i, this.getStackInSlot(i));
                this.setInventorySlotContents(i, null);
            }
        }
        super.autoEquipItems(player);
    }

}
