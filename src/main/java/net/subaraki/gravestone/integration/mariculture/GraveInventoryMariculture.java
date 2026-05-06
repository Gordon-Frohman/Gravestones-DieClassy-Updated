package net.subaraki.gravestone.integration.mariculture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import mariculture.magic.MirrorHelper;

public class GraveInventoryMariculture extends GraveInventory {

    public GraveInventoryMariculture() {
        super();
    }

    public GraveInventoryMariculture(int size, TileEntityGravestone grave) {
        super(MaricultureIntegration.modName, MaricultureIntegration.modName, size, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        ItemStack[] inv = MirrorHelper.getInventory(player);
        for (int i = 0; i < inv.length; i++) {
            if (inv[i] == null) {
                inv[i] = this.getStackInSlot(i);
                this.setInventorySlotContents(i, null);
            }
        }
        MirrorHelper.save(player, inv);
        super.autoEquipItems(player);
    }

}
