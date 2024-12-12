package net.subaraki.gravestone.integration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

import tconstruct.armor.player.TPlayerStats;

public class TinkersConstructIntegration {

    public static IInventory getKnapsackInventory(EntityPlayer player) {
        TPlayerStats stats = TPlayerStats.get(player);
        return stats.getKnapsackInventory(player);
    }

    public static IInventory getAccessoryInventory(EntityPlayer player) {
        TPlayerStats stats = TPlayerStats.get(player);
        return stats.getAccessoryInventory(player);
    }

}
