package net.subaraki.gravestone.integration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;

import tconstruct.armor.TinkerArmor;
import tconstruct.armor.player.TPlayerStats;

public class TinkersConstructIntegration {

    public static ItemStack getModIcon() {
        return GraveStones.hasTiC && TinkerArmor.knapsack != null ? new ItemStack(TinkerArmor.knapsack)
            : new ItemStack(Items.diamond_chestplate);
    }

    public static IInventory getKnapsackInventory(EntityPlayer player) {
        TPlayerStats stats = TPlayerStats.get(player);
        return stats.getKnapsackInventory(player);
    }

    public static IInventory getAccessoryInventory(EntityPlayer player) {
        TPlayerStats stats = TPlayerStats.get(player);
        return stats.getAccessoryInventory(player);
    }

}
