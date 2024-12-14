package net.subaraki.gravestone.integration;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;

import mods.battlegear2.utils.BattlegearConfig;

public class BattlegearIntegration {

    public static ItemStack getModIcon() {
        return GraveStones.hasBattlegear && BattlegearConfig.heradricItem != null
            ? new ItemStack(BattlegearConfig.heradricItem)
            : new ItemStack(Items.iron_chestplate);
    }

}
