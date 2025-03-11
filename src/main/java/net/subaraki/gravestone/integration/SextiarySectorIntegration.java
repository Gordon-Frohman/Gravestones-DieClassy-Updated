package net.subaraki.gravestone.integration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;

import shift.sextiarysector.SSItems;
import shift.sextiarysector.container.InventoryPlayerNext;
import shift.sextiarysector.player.EntityPlayerManager;

public class SextiarySectorIntegration {

    public static ItemStack getModIcon() {
        return GraveStones.hasSextiarySector && SSItems.rucksack != null ? new ItemStack(SSItems.rucksack)
            : new ItemStack(Items.golden_hoe);
    }

    public static ItemStack removeStackFromSlot(EntityPlayer player, int slotID) {
        if (slotID < 20) {
            InventoryPlayerNext equipment = EntityPlayerManager.getEquipmentStats(player).inventory;
            ItemStack stack = equipment.getStackInSlot(slotID);
            if (stack != null) {
                equipment.setInventorySlotContents(slotID, null);
                equipment.markDirty();
            }
            return stack;
        } else return null;
    }

}
