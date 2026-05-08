package net.subaraki.gravestone.integration.galacticraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.items.GCItems;

public class GalacticraftIntegration extends ModIntegration {

    public static final String modName = "Galacticraft";
    public static final String modId = "GalacticraftCore";
    public static final String inventoryName = "galacticraft";

    public GalacticraftIntegration() {
        super(modName, modId, inventoryName, GraveInventoryGalacticraft.class);
    }

    public ItemStack getModIcon() {
        return GraveStones.hasGalacticraft && GCItems.oxMask != null ? new ItemStack(GCItems.oxMask)
            : new ItemStack(Blocks.glass);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        if (player instanceof EntityPlayerMP playerMP) {
            return GCPlayerStats.get(playerMP).extendedInventory;
        }
        return null;
    }

    @Override
    protected GraveInventory getGraveInventory(int size, TileEntityGravestone grave) {
        return new GraveInventoryGalacticraft(size, grave);
    }

}
