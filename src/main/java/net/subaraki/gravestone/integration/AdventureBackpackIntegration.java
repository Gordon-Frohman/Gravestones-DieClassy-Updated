package net.subaraki.gravestone.integration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;

import com.darkona.adventurebackpack.init.ModItems;
import com.darkona.adventurebackpack.playerProperties.BackpackProperty;

public class AdventureBackpackIntegration {

    public static ItemStack getModIcon() {
        if (GraveStones.hasAdventureBackpack) {
            if (ModItems.machete != null) return new ItemStack(ModItems.machete);
        }
        return new ItemStack(Blocks.chest);
    }

    public static ItemStack getBackpack(EntityPlayer player) {
        ItemStack backpack = null;
        BackpackProperty bp = BackpackProperty.get(player);
        if (bp != null) {
            backpack = bp.getWearable();
            if (backpack != null) bp.setWearable(null);
        }
        return backpack;
    }

}
