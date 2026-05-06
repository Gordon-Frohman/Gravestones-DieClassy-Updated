package net.subaraki.gravestone.integration.tconstruct;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class GraveInventoryTiCKnapsack extends GraveInventoryTiC {

    public GraveInventoryTiCKnapsack() {
        super();
    }

    public GraveInventoryTiCKnapsack(int size, TileEntityGravestone grave) {
        super(TinkersConstructIntegration.inventoryKnapsack, size, grave);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return TinkersConstructIntegration.getKnapsackInventory(player);
    }

}
