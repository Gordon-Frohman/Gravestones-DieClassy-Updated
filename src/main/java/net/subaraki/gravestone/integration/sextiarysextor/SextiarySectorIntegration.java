package net.subaraki.gravestone.integration.sextiarysextor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import shift.sextiarysector.SSItems;
import shift.sextiarysector.player.EntityPlayerManager;

public class SextiarySectorIntegration extends ModIntegration {

    public static final String modName = "Sextiary Sector";
    public static final String modId = "SextiarySector";
    public static final String inventoryName = "sextiarysector";

    public SextiarySectorIntegration() {
        super(modName, modId, inventoryName, GraveInventorySextiarySector.class);
    }

    public ItemStack getModIcon() {
        return GraveStones.hasSextiarySector && SSItems.rucksack != null ? new ItemStack(SSItems.rucksack)
            : new ItemStack(Items.golden_hoe);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return EntityPlayerManager.getEquipmentStats(player).inventory;
    }

    @Override
    protected GraveInventory getGraveInventory(int size, TileEntityGravestone grave) {
        return new GraveInventorySextiarySector(size, grave);
    }

}
