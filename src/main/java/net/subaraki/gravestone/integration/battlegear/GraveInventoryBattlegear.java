package net.subaraki.gravestone.integration.battlegear;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class GraveInventoryBattlegear extends GraveInventory {

    public GraveInventoryBattlegear() {
        super();
    }

    public GraveInventoryBattlegear(int size, TileEntityGravestone grave) {
        super(BattlegearIntegration.modName, BattlegearIntegration.modId, size, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        if (player instanceof EntityPlayerMP playerMP) {
            InventoryPlayer inv = player.inventory;
            for (int i = 0; i < this.size; i++) {
                int slotId = i + 150;
                if (inv.getStackInSlot(slotId) == null) {
                    inv.setInventorySlotContents(slotId, this.getStackInSlot(i));
                    this.setInventorySlotContents(i, null);
                }
            }
        }
        super.autoEquipItems(player);
    }

}
