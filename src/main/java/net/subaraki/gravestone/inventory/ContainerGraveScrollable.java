
package net.subaraki.gravestone.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class ContainerGraveScrollable extends ContainerGraveAbstract {

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
    }

    public static IInventory placeholderInv = new IInventory() {

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
