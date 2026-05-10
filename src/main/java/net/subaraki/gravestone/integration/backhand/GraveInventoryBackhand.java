package net.subaraki.gravestone.integration.backhand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import xonin.backhand.api.core.BackhandUtils;

public class GraveInventoryBackhand extends GraveInventory {

    public GraveInventoryBackhand() {
        super();
    }

    public GraveInventoryBackhand(TileEntityGravestone grave) {
        super(BackhandIntegration.modId, BackhandIntegration.modId, 1, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        ItemStack offhand = BackhandUtils.getOffhandItem(player);
        if (offhand == null) {
            BackhandUtils.setPlayerOffhandItem(player, this.getStackInSlot(0));
            this.setInventorySlotContents(0, null);
        }
        super.autoEquipItems(player);
    }

}
