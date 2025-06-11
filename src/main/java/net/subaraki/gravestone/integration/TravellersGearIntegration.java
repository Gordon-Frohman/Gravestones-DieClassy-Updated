package net.subaraki.gravestone.integration;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;

import travellersgear.TravellersGear;
import travellersgear.api.TravellersGearAPI;

public class TravellersGearIntegration {

    public static ItemStack getModIcon() {
        if (GraveStones.hasTravellersGear) {
            if (TravellersGear.simpleGear != null) return new ItemStack(TravellersGear.simpleGear, 1, 6);
        }
        return new ItemStack(Items.name_tag);
    }

    public static List<ItemStack> getTravellersGearInventory(EntityPlayer player) {
        List<ItemStack> shortInventory = new ArrayList<ItemStack>();
        for (ItemStack item : TravellersGearAPI.getExtendedInventory(player)) {
            if (item != null) shortInventory.add(item);
        }
        return shortInventory;
    }

    public static void clearTravellersGearInventory(EntityPlayer player) {
        TravellersGearAPI
            .setExtendedInventory(player, new ItemStack[TravellersGearAPI.getExtendedInventory(player).length]);
    }

}
