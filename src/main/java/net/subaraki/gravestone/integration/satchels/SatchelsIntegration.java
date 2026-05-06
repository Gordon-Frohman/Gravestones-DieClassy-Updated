package net.subaraki.gravestone.integration.satchels;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import makamys.satchels.EntityPropertiesSatchels;
import makamys.satchels.SatchelsItems;

public class SatchelsIntegration extends ModIntegration {

    public static final String modName = "Satchels";
    public static final String modId = "satchels";
    public static final String inventoryLeftPouch = "Left Pouch";
    public static final String inventoryRightPouch = "Right Pouch";
    public static final String inventorySatchel = "Satchel";

    public SatchelsIntegration() {
        super(
            modName,
            modId,
            new String[] { modName, inventoryLeftPouch, inventoryRightPouch, inventorySatchel },
            new Class<?>[] { GraveInventorySatchelsEquipment.class, GraveInventorySatchelsLeftPouch.class,
                GraveInventorySatchelsRightPouch.class, GraveInventorySatchel.class });
    }

    public ItemStack getModIcon() {
        return GraveStones.hasSatchels && SatchelsItems.pouch != null ? new ItemStack(SatchelsItems.pouch)
            : new ItemStack(Items.leather);
    }

    public static IInventory getEquipmentInventory(EntityPlayer player) {
        return EntityPropertiesSatchels.fromPlayer(player).equipment;
    }

    public static IInventory getLeftPouchInventory(EntityPlayer player) {
        return EntityPropertiesSatchels.fromPlayer(player).leftPouch;
    }

    public static IInventory getRightPouchInventory(EntityPlayer player) {
        return EntityPropertiesSatchels.fromPlayer(player).rightPouch;
    }

    public static IInventory getSatchelInventory(EntityPlayer player) {
        return EntityPropertiesSatchels.fromPlayer(player).satchel;
    }

    @Override
    public boolean storeItems(TileEntityGravestone te, EntityPlayer player) {
        boolean result = false;
        IInventory equipment = getEquipmentInventory(player);
        if (equipment != null) {
            int size = equipment.getSizeInventory();
            GraveInventory graveInv = new GraveInventorySatchelsEquipment(size, te);
            for (int i = 0; i < size; ++i) {
                ItemStack stack = equipment.getStackInSlot(i);
                graveInv.setInventorySlotContents(i, stack);
                equipment.setInventorySlotContents(i, null);
            }
            if (!graveInv.isEmpty()) {
                te.inventories.add(graveInv);
                result = true;
            }
        } else {
            GraveStones.printDebugMessage(
                "GraveStones Mod couldn't connect to Satchels' Equipment. Have these classes been modified? Report to mod Author pleases.");
        }
        IInventory leftPouch = getLeftPouchInventory(player);
        if (leftPouch != null) {
            int size = leftPouch.getSizeInventory();
            GraveInventory graveInv = new GraveInventorySatchelsLeftPouch(size, te);
            for (int i = 0; i < size; ++i) {
                ItemStack stack = leftPouch.getStackInSlot(i);
                graveInv.setInventorySlotContents(i, stack);
                leftPouch.setInventorySlotContents(i, null);
            }
            if (!graveInv.isEmpty()) {
                te.inventories.add(graveInv);
                result = true;
            }
        } else {
            GraveStones.printDebugMessage(
                "GraveStones Mod couldn't connect to Satchels' Left Pouch. Have these classes been modified? Report to mod Author pleases.");
        }
        IInventory rightPouch = getRightPouchInventory(player);
        if (rightPouch != null) {
            int size = rightPouch.getSizeInventory();
            GraveInventory graveInv = new GraveInventorySatchelsRightPouch(size, te);
            for (int i = 0; i < size; ++i) {
                ItemStack stack = rightPouch.getStackInSlot(i);
                graveInv.setInventorySlotContents(i, stack);
                rightPouch.setInventorySlotContents(i, null);
            }
            if (!graveInv.isEmpty()) {
                te.inventories.add(graveInv);
                result = true;
            }
        } else {
            GraveStones.printDebugMessage(
                "GraveStones Mod couldn't connect to Satchels' Right Pouch. Have these classes been modified? Report to mod Author pleases.");
        }
        IInventory satchel = getSatchelInventory(player);
        if (satchel != null) {
            int size = satchel.getSizeInventory();
            GraveInventory graveInv = new GraveInventorySatchel(size, te);
            for (int i = 0; i < size; ++i) {
                ItemStack stack = satchel.getStackInSlot(i);
                graveInv.setInventorySlotContents(i, stack);
                satchel.setInventorySlotContents(i, null);
            }
            if (!graveInv.isEmpty()) {
                te.inventories.add(graveInv);
                result = true;
            }
        } else {
            GraveStones.printDebugMessage(
                "GraveStones Mod couldn't connect to Satchel. Have these classes been modified? Report to mod Author pleases.");
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
