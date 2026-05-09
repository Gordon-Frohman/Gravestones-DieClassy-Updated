package net.subaraki.gravestone.integration.satchels;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class GraveInventorySatchel extends GraveInventorySatchels {

    public GraveInventorySatchel() {
        super();
    }

    public GraveInventorySatchel(int size, TileEntityGravestone grave) {
        super(SatchelsIntegration.inventorySatchel, size, grave);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return SatchelsIntegration.getSatchelInventory(player);
    }

}
