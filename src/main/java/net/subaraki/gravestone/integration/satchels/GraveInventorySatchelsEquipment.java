package net.subaraki.gravestone.integration.satchels;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class GraveInventorySatchelsEquipment extends GraveInventorySatchels {

    public GraveInventorySatchelsEquipment() {
        super();
    }

    public GraveInventorySatchelsEquipment(int size, TileEntityGravestone grave) {
        super(SatchelsIntegration.modName, size, grave);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return SatchelsIntegration.getEquipmentInventory(player);
    }

}
