package net.subaraki.gravestone.integration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;

import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.items.GCItems;

public class GalacticraftIntegration {

    public static ItemStack getModIcon() {
        return GraveStones.hasGalacticraft && GCItems.oxMask != null ? new ItemStack(GCItems.oxMask)
            : new ItemStack(Blocks.glass);
    }

    public static ItemStack[] getGalacticraftInventory(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
            return stats.extendedInventory.inventoryStacks;
        }
        return new ItemStack[0];
    }

}
