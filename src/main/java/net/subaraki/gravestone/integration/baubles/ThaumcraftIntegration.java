package net.subaraki.gravestone.integration.baubles;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;

import thaumcraft.common.config.ConfigItems;

public class ThaumcraftIntegration {

    public static ItemStack getModIcon() {
        return GraveStones.hasThaumcraft && ConfigItems.itemBaubleBlanks != null
            ? new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1)
            : new ItemStack(Items.gold_ingot);
    }

}
