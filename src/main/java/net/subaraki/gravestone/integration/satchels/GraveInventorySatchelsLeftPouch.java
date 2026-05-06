package net.subaraki.gravestone.integration.satchels;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class GraveInventorySatchelsLeftPouch extends GraveInventorySatchels {

    public GraveInventorySatchelsLeftPouch() {
        super();
    }

    public GraveInventorySatchelsLeftPouch(int size, TileEntityGravestone grave) {
        super(SatchelsIntegration.inventoryLeftPouch, size, grave);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return SatchelsIntegration.getLeftPouchInventory(player);
    }

}
