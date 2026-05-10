package net.subaraki.gravestone.integration.backhand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import xonin.backhand.api.core.BackhandSlot;
import xonin.backhand.api.core.BackhandUtils;

public class BackhandIntegration extends ModIntegration {

    public static final String modName = "Backhand";
    public static final String modId = "backhand";

    public BackhandIntegration() {
        super(modName, modId, modId, GraveInventoryBackhand.class);
    }

    public ItemStack getModIcon() {
        return new ItemStack(Items.lead);
    }

    @Override
    public boolean storeItems(TileEntityGravestone te, EntityPlayer player) {
        ItemStack offhand = BackhandUtils.getOffhandItem(player);
        if (offhand != null) {
            GraveInventory graveInv = getGraveInventory(1, te);
            BackhandUtils.setPlayerOffhandItem(player, null);
            graveInv.setInventorySlotContents(0, offhand);
            graveInv.icon = offhand;
            te.offhand = graveInv;
            return true;
        }
        return false;
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return null;
    }

    @Override
    protected GraveInventory getGraveInventory(int size, TileEntityGravestone grave) {
        return new GraveInventoryBackhand(grave);
    }

    public static GraveInventory getGraveInventory() {
        return new GraveInventoryBackhand();
    }

    public static Slot getBackhandSlot(InventoryPlayer inventoryPlayer, EntityPlayer player) {
        return new BackhandSlot(inventoryPlayer, BackhandUtils.getOffhandSlot(player), 196, 162);
    }

}
