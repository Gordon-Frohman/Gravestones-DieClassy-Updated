package net.subaraki.gravestone.integration.rpginventory;

import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import rpgInventory.gui.rpginv.PlayerRpgInventory;

public class GraveInventoryRPGI extends GraveInventory {

    public GraveInventoryRPGI() {
        super();
    }

    public GraveInventoryRPGI(int size, TileEntityGravestone grave) {
        super(RPGInventoryIntegration.inventoryName, RPGInventoryIntegration.modId, size, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        PlayerRpgInventory inv = PlayerRpgInventory.get(player);
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if (inv.getStackInSlot(i) == null) {
                inv.setInventorySlotContents(i, this.getStackInSlot(i));
                this.setInventorySlotContents(i, null);
            }
        }
        super.autoEquipItems(player);
    }

}
