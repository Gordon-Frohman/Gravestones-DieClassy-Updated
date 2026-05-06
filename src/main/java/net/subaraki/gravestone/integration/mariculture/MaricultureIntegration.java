package net.subaraki.gravestone.integration.mariculture;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import mariculture.magic.Magic;
import mariculture.magic.MirrorHelper;

public class MaricultureIntegration extends ModIntegration {

    public static final String modName = "Mariculture";

    public MaricultureIntegration() {
        super(modName, modName, GraveInventoryMariculture.class);
    }

    public ItemStack getModIcon() {
        return GraveStones.hasMariculture && Magic.celestialMirror != null ? new ItemStack(Magic.celestialMirror)
            : new ItemStack(Items.fish);
    }

    @Override
    public boolean storeItems(TileEntityGravestone te, EntityPlayer player) {
        ItemStack[] inv = MirrorHelper.getInventory(player);
        if (inv != null) {
            int size = inv.length;
            GraveInventory graveInv = getGraveInventory(size, te);
            for (int i = 0; i < size; ++i) {
                ItemStack stack = inv[i];
                if (stack != null) graveInv.setInventorySlotContents(i, stack);
            }
            if (!graveInv.isEmpty()) {
                MirrorHelper.save(player, new ItemStack[size]);
                te.inventories.add(graveInv);
                return true;
            }
        }
        return false;
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return null;
    }

    @Override
    protected GraveInventory getGraveInventory(int size, TileEntityGravestone grave) {
        return new GraveInventoryMariculture(size, grave);
    }

}
