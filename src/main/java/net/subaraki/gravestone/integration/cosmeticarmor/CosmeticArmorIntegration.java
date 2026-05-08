package net.subaraki.gravestone.integration.cosmeticarmor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import lain.mods.cos.CosmeticArmorReworked;

public class CosmeticArmorIntegration extends ModIntegration {

    public static final String modName = "Cosmetic Armor";
    public static final String modId = "cosmeticarmorreworked";
    public static final String inventoryName = "cosmeticarmor";

    public CosmeticArmorIntegration() {
        super(modName, modId, inventoryName, GraveInventoryCosmeticArmor.class);
    }

    @Override
    public ItemStack getModIcon() {
        return new ItemStack(Items.leather_chestplate);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return CosmeticArmorReworked.invMan.getCosArmorInventory(player.getUniqueID());
    }

    @Override
    protected GraveInventory getGraveInventory(int size, TileEntityGravestone grave) {
        return new GraveInventoryCosmeticArmor(grave);
    }

    public static GraveInventory getGraveInventory(TileEntityGravestone te) {
        for (GraveInventory inv : te.inventories) {
            if (inv instanceof GraveInventoryCosmeticArmor cosInv) return cosInv;
        }
        return null;
    }
}
