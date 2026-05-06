package net.subaraki.gravestone.integration.travellersgear;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import travellersgear.TravellersGear;
import travellersgear.api.TravellersGearAPI;

public class TravellersGearIntegration extends ModIntegration {

    public static final String modName = "Traveller's Gear";
    public static final String modId = "TravellersGear";

    public TravellersGearIntegration() {
        super(modName, modId, GraveInventoryTravellersGear.class);
    }

    public ItemStack getModIcon() {
        if (GraveStones.hasTravellersGear) {
            if (TravellersGear.simpleGear != null) return new ItemStack(TravellersGear.simpleGear, 1, 6);
        }
        return new ItemStack(Items.name_tag);
    }

    public boolean storeItems(TileEntityGravestone te, EntityPlayer player) {
        ItemStack[] inv = TravellersGearAPI.getExtendedInventory(player);
        if (inv != null) {
            int size = inv.length;
            GraveInventory graveInv = getGraveInventory(size, te);
            for (int i = 0; i < size; ++i) {
                ItemStack stack = inv[i];
                if (stack != null) {
                    graveInv.setInventorySlotContents(i, stack);
                    inv[i] = null;
                }
            }
            if (!graveInv.isEmpty()) {
                TravellersGearAPI.setExtendedInventory(player, inv);
                te.inventories.add(graveInv);
                return true;
            }
        } else {
            GraveStones.printDebugMessage(
                "GraveStones Mod couldn't connect to Traveller's Gear. Have these classes been modified? Report to mod Author pleases.");
        }
        return false;
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return null;
    }

    @Override
    protected GraveInventory getGraveInventory(int size, TileEntityGravestone grave) {
        return new GraveInventoryTravellersGear(size, grave);
    }

}
