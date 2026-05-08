package net.subaraki.gravestone.integration.travellersgear;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import travellersgear.api.TravellersGearAPI;

public class GraveInventoryTravellersGear extends GraveInventory {

    public GraveInventoryTravellersGear() {
        super();
    }

    public GraveInventoryTravellersGear(int size, TileEntityGravestone grave) {
        super(TravellersGearIntegration.inventoryName, TravellersGearIntegration.modId, size, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        ItemStack[] inv = TravellersGearAPI.getExtendedInventory(player);
        for (int i = 0; i < inv.length; i++) {
            if (inv[i] == null) {
                inv[i] = this.getStackInSlot(i);
                this.setInventorySlotContents(i, null);
            }
        }
        TravellersGearAPI.setExtendedInventory(player, inv);
        super.autoEquipItems(player);
    }

}
