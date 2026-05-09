package net.subaraki.gravestone.integration.galacticraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.inventory.InventoryExtended;

public class GraveInventoryGalacticraft extends GraveInventory {

    public GraveInventoryGalacticraft() {
        super();
    }

    public GraveInventoryGalacticraft(int size, TileEntityGravestone grave) {
        super(GalacticraftIntegration.inventoryName, GalacticraftIntegration.modId, size, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        if (player instanceof EntityPlayerMP playerMP) {
            InventoryExtended inv = GCPlayerStats.get(playerMP).extendedInventory;
            for (int i = 0; i < inv.getSizeInventory(); i++) {
                if (inv.getStackInSlot(i) == null) {
                    inv.setInventorySlotContents(i, this.getStackInSlot(i));
                    this.setInventorySlotContents(i, null);
                }
            }
        }
        super.autoEquipItems(player);
    }

}
