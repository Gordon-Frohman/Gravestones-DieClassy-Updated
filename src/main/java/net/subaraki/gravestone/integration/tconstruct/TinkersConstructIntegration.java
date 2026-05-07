package net.subaraki.gravestone.integration.tconstruct;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import tconstruct.armor.TinkerArmor;
import tconstruct.armor.player.TPlayerStats;

public class TinkersConstructIntegration extends ModIntegration {

    public static final String modName = "Tinkers Construct";
    public static final String modId = "TConstruct";
    public static final String inventoryAccessories = "Tinkers Construct - Accessories";
    public static final String inventoryKnapsack = "Tinkers Construct - Knapsack";

    public TinkersConstructIntegration() {
        super(
            modName,
            modId,
            new String[] { inventoryAccessories, inventoryKnapsack },
            new Class<?>[] { GraveInventoryTiCAccessories.class, GraveInventoryTiCKnapsack.class });
    }

    public ItemStack getModIcon() {
        return GraveStones.hasTiC && TinkerArmor.knapsack != null ? new ItemStack(TinkerArmor.knapsack)
            : new ItemStack(Items.diamond_chestplate);
    }

    public static IInventory getKnapsackInventory(EntityPlayer player) {
        TPlayerStats stats = TPlayerStats.get(player);
        return stats.getKnapsackInventory(player);
    }

    public static IInventory getAccessoryInventory(EntityPlayer player) {
        TPlayerStats stats = TPlayerStats.get(player);
        return stats.getAccessoryInventory(player);
    }

    @Override
    public boolean storeItems(TileEntityGravestone te, EntityPlayer player) {
        boolean result = false;
        IInventory accessories = getAccessoryInventory(player);
        if (accessories != null) {
            int size = accessories.getSizeInventory();
            GraveInventory accInv = new GraveInventoryTiCAccessories(size, te);
            for (int i = 0; i < size; ++i) {
                ItemStack stack = accessories.getStackInSlot(i);
                accInv.setInventorySlotContents(i, stack);
                accessories.setInventorySlotContents(i, null);
            }
            if (!accInv.isEmpty()) {
                te.inventories.add(accInv);
                result = true;
            }
        } else {
            GraveStones.printDebugMessage(
                "GraveStones Mod couldn't connect to Tinkers Construct's Accessories. Have these classes been modified? Report to mod Author pleases.");
        }
        IInventory sack = getKnapsackInventory(player);
        if (sack != null) {
            int size = sack.getSizeInventory();
            GraveInventory sackInv = new GraveInventoryTiCKnapsack(size, te);
            for (int i = 0; i < size; ++i) {
                ItemStack stack = sack.getStackInSlot(i);
                sackInv.setInventorySlotContents(i, stack);
                sack.setInventorySlotContents(i, null);
            }
            if (!sackInv.isEmpty()) {
                te.inventories.add(sackInv);
                result = true;
            }
        } else {
            GraveStones.printDebugMessage(
                "GraveStones Mod couldn't connect to Tinkers Construct's Knapsack. Have these classes been modified? Report to mod Author pleases.");
        }
        return result;
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return null;
    }

    @Override
    protected GraveInventory getGraveInventory(int size, TileEntityGravestone grave) {
        return null;
    }

}
