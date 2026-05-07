
package net.subaraki.gravestone.inventory;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class ContainerGraveScrollable extends ContainerGraveAbstract {

    public Boolean[] enableSlots = new Boolean[36];
    public String[] tabNames = new String[5];

    public ContainerGraveScrollable(TileEntityGravestone te, EntityPlayer player) {
        super(te, player);
        boolean isCreative = player.capabilities.isCreativeMode;
        for (int i = 0; i < 4; ++i) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(
                    getSlot(placeholderInv, this.slotCount, 8 + k * 18, 18 + i * 18, false, isCreative, te));
                ++this.slotCount;
            }
        }
        this.fillInv(player.inventory);
        scrollTo(0);
    }

    private void resetSlots() {
        for (int i = 0; i < enableSlots.length; i++) {
            enableSlots[i] = false;
            Slot slot = getSlot(i);
            slot.inventory = placeholderInv;
            slot.slotIndex = i;
        }
        for (int i = 0; i < 5; i++) this.tabNames[i] = "";
    }

    public void scrollTo(float scroll) {
        this.resetSlots();
        if (this.te.inventories.isEmpty()) return;

        int rows = this.getRowsCount() - 5;
        int offset = (int) ((double) (scroll * (float) rows) + 0.5D) + 1;

        if (offset < 0) offset = 0;

        boolean skipRow = true; // Skipping one row when we need to draw new inventory
        Iterator<GraveInventory> i$ = this.te.inventories.iterator();
        GraveInventory currentInv = i$.next();
        int currentInvSlot = 0;
        int totalInvSlot = 0;
        int currentRow = 0;
        while (currentRow < offset + 4) {
            if (skipRow) {
                if (currentRow == offset + 3) this.tabNames[4] = currentInv.name;
                currentRow++;
                totalInvSlot = currentRow * 9;
                skipRow = false;
                continue;
            } else {
                if (currentRow >= offset) {
                    if (currentRow == offset) {
                        this.tabNames[0] = currentInv.name;
                    } else {
                        if (currentInvSlot == 0) this.tabNames[currentRow - offset] = currentInv.name;
                    }
                    int index = totalInvSlot - offset * 9;
                    this.enableSlots[index] = true;
                    Slot slot = getSlot(index);
                    slot.inventory = currentInv;
                    slot.slotIndex = currentInvSlot;
                }
                if (currentInvSlot + 1 < currentInv.getSizeInventory()) {
                    if (currentInvSlot % 9 == 8) currentRow++;
                    currentInvSlot++;
                    totalInvSlot++;
                } else {
                    if (i$.hasNext()) {
                        currentInv = i$.next();
                        currentInvSlot = 0;
                        currentRow++;
                        totalInvSlot = currentRow * 9;
                        skipRow = true;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public int getRowsCount() {
        int rowsCount = 0;
        for (GraveInventory inv : te.inventories) {
            rowsCount += inv.getRowsCount() + 1;
        }
        return rowsCount;
    }

    private static IInventory placeholderInv = new IInventory() {

        @Override
        public int getSizeInventory() {
            return 0;
        }

        @Override
        public ItemStack getStackInSlot(int slotIn) {
            return null;
        }

        @Override
        public ItemStack decrStackSize(int index, int count) {
            return null;
        }

        @Override
        public ItemStack getStackInSlotOnClosing(int index) {
            return null;
        }

        @Override
        public void setInventorySlotContents(int index, ItemStack stack) {}

        @Override
        public String getInventoryName() {
            return null;
        }

        @Override
        public boolean hasCustomInventoryName() {
            return false;
        }

        @Override
        public int getInventoryStackLimit() {
            return 0;
        }

        @Override
        public void markDirty() {}

        @Override
        public boolean isUseableByPlayer(EntityPlayer player) {
            return false;
        }

        @Override
        public void openInventory() {}

        @Override
        public void closeInventory() {}

        @Override
        public boolean isItemValidForSlot(int index, ItemStack stack) {
            return false;
        }
    };
}
