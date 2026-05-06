package net.subaraki.gravestone.integration.cosmeticarmor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import lain.mods.cos.CosmeticArmorReworked;
import lain.mods.cos.inventory.InventoryCosArmor;

public class GraveInventoryCosmeticArmor extends GraveInventory {

    public GraveInventoryCosmeticArmor() {
        super();
    }

    public GraveInventoryCosmeticArmor(TileEntityGravestone grave) {
        super(CosmeticArmorIntegration.modName, CosmeticArmorIntegration.modId, 4, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        InventoryCosArmor inv = CosmeticArmorReworked.invMan.getCosArmorInventory(player.getUniqueID());
        for (int i = 0; i < 4; i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack == null) {
                inv.setInventorySlotContents(i, this.getStackInSlot(i));
                this.setInventorySlotContents(i, null);
            }
        }
        super.autoEquipItems(player);
    }

}
