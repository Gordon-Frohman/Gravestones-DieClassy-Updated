package net.subaraki.gravestone.integration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;

import com.darkona.adventurebackpack.init.ModItems;
import com.darkona.adventurebackpack.playerProperties.BackpackProperty;

public class AdventureBackpackIntegration {

    public static ItemStack getModIcon() {
        return GraveStones.hasAdventureBackpack && ModItems.machete != null ? new ItemStack(ModItems.machete)
            : new ItemStack(Blocks.chest);
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
