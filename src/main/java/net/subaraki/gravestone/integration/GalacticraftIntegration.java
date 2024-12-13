package net.subaraki.gravestone.integration;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;

import micdoodle8.mods.galacticraft.core.items.GCItems;

public class GalacticraftIntegration {

    public static ItemStack getModIcon() {
        return GraveStones.hasGalacticraft && GCItems.oxMask != null ? new ItemStack(GCItems.oxMask)
            : new ItemStack(Blocks.glass);
    }

}
