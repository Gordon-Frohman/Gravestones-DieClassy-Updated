package net.subaraki.gravestone.integration.satchels;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class GraveInventorySatchelsRightPouch extends GraveInventorySatchels {

    public GraveInventorySatchelsRightPouch() {
        super();
    }

    public GraveInventorySatchelsRightPouch(int size, TileEntityGravestone grave) {
        super(SatchelsIntegration.inventoryRightPouch, size, grave);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return SatchelsIntegration.getRightPouchInventory(player);
    }

}
