package net.subaraki.gravestone.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class GraveInventoryArmor extends GraveInventory {

    public GraveInventoryArmor() {
        super();
    }

    public GraveInventoryArmor(String modID, TileEntityGravestone grave) {
        super("Armor", modID, 4, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        for (int i = 0; i < 4; i++) {
            if (player.getCurrentArmor(i) == null && this.getStackInSlot(i) != null) {
                player.setCurrentItemOrArmor(
                    i + 1,
                    this.getStackInSlot(i)
                        .copy());
                this.setInventorySlotContents(i, null);
            }
        }
        super.autoEquipItems(player);
    }

}
