package net.subaraki.gravestone.integration.battlegear;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import mods.battlegear2.utils.BattlegearConfig;

public class BattlegearIntegration extends ModIntegration {

    public static final String modName = "Battlegear";
    public static final String modId = "battlegear2";

    public BattlegearIntegration() {
        super(modName, modId, GraveInventoryBattlegear.class);
    }

    public ItemStack getModIcon() {
        return GraveStones.hasBattlegear && BattlegearConfig.heradricItem != null
            ? new ItemStack(BattlegearConfig.heradricItem)
            : new ItemStack(Items.iron_chestplate);
    }

    @Override
    public boolean storeItems(TileEntityGravestone te, EntityPlayer player) {
        IInventory inv = getInventory(player);
        if (inv != null) {
            int size = 18;
            GraveInventory graveInv = getGraveInventory(size, te);
            for (int i = 0; i < size; ++i) {
                ItemStack stack = inv.getStackInSlot(i + 150);
                if (stack != null) {
                    graveInv.setInventorySlotContents(i, stack);
                    inv.setInventorySlotContents(i + 150, null);
                }
            }
            if (!graveInv.isEmpty()) {
                inv.markDirty();
                te.inventories.add(graveInv);
                return true;
            }
        } else {
            GraveStones.printDebugMessage(
                "GraveStones Mod couldn't connect to Battlegear. Have these classes been modified? Report to mod Author pleases.");
        }
        return false;
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return player.inventory;
    }

    @Override
    protected GraveInventory getGraveInventory(int size, TileEntityGravestone grave) {
        return new GraveInventoryBattlegear(size, grave);
    }

}
