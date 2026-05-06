package net.subaraki.gravestone.integration.rpginventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import rpgInventory.RpgInventoryMod;
import rpgInventory.gui.rpginv.PlayerRpgInventory;

public class RPGInventoryIntegration extends ModIntegration {

    public static final String modName = "RPG Inventory";
    public static final String modId = "rpginventorymod";

    public RPGInventoryIntegration() {
        super(modName, modId, GraveInventoryRPGI.class);
    }

    public ItemStack getModIcon() {
        if (GraveStones.hasRpgI) {
            if (RpgInventoryMod.neckdia != null) return new ItemStack(RpgInventoryMod.neckdia);
        }
        return new ItemStack(Items.diamond);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return PlayerRpgInventory.get(player);
    }

    @Override
    protected GraveInventory getGraveInventory(int size, TileEntityGravestone grave) {
        return new GraveInventoryRPGI(size, grave);
    }

}
