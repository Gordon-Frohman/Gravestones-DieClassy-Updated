package net.subaraki.gravestone.integration.tconstruct;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class GraveInventoryTiCAccessories extends GraveInventoryTiC {

    public GraveInventoryTiCAccessories() {
        super();
    }

    public GraveInventoryTiCAccessories(int size, TileEntityGravestone grave) {
        super(TinkersConstructIntegration.inventoryAccessories, size, grave);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return TinkersConstructIntegration.getAccessoryInventory(player);
    }

}
