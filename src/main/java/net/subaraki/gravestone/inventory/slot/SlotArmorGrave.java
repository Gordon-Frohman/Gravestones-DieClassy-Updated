
package net.subaraki.gravestone.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class SlotArmorGrave extends Slot {

    TileEntityGravestone te;

    public SlotArmorGrave(IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition,
        TileEntityGravestone grave) {
        super(inventory, slotIndex, xDisplayPosition, yDisplayPosition);
        this.te = grave;
    }

    public boolean isItemValid(ItemStack item) {
        return true;
    }

    public boolean canTakeStack(EntityPlayer player) {
        return true;
    }

    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack()) {
            Math.min(amount, this.getStack().stackSize);
        }
        return super.decrStackSize(amount);
    }
}
