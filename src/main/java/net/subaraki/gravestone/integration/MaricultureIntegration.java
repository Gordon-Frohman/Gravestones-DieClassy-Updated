package net.subaraki.gravestone.integration;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;

import mariculture.magic.Magic;

public class MaricultureIntegration {

    public static ItemStack getModIcon() {
        return GraveStones.hasMariculture && Magic.celestialMirror != null ? new ItemStack(Magic.celestialMirror)
            : new ItemStack(Items.fish);
    }

}
