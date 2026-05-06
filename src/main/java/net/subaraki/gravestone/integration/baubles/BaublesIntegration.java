package net.subaraki.gravestone.integration.baubles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

public class BaublesIntegration extends ModIntegration {

    public static final String modName = "Baubles";

    public BaublesIntegration() {
        super(modName, modName, GraveInventoryBaubles.class);
    }

    @Override
    public ItemStack getModIcon() {
        if (GraveStones.hasThaumcraft) return ThaumcraftIntegration.getModIcon();
        return new ItemStack(Items.gold_ingot);
    }

    @Override
    public boolean storeItems(TileEntityGravestone te, EntityPlayer player) {
        boolean result = super.storeItems(te, player);
        if (result) {
            InventoryBaubles inv = (InventoryBaubles) getInventory(player);
            for (int i = 0; i < inv.getSizeInventory(); i++) inv.syncSlotToClients(i);
        }
        return result;
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return PlayerHandler.getPlayerBaubles(player);
    }

    @Override
    protected GraveInventory getGraveInventory(int size, TileEntityGravestone grave) {
        return new GraveInventoryBaubles(size, grave);
    }

}
