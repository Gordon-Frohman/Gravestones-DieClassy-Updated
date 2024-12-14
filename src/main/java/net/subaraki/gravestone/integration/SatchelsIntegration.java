package net.subaraki.gravestone.integration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;

import makamys.satchels.EntityPropertiesSatchels;
import makamys.satchels.SatchelsItems;

public class SatchelsIntegration {

    public static ItemStack getModIcon() {
        return GraveStones.hasSatchels && SatchelsItems.pouch != null ? new ItemStack(SatchelsItems.pouch)
            : new ItemStack(Items.leather);
    }

    public static IInventory getSatchelsInventory(EntityPlayer player) {
        EntityPropertiesSatchels props = EntityPropertiesSatchels.fromPlayer(player);
        return props.equipment;
    }

}
