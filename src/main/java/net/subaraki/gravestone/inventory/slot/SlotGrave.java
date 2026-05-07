
package net.subaraki.gravestone.inventory.slot;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.inventory.GraveInventoryArmor;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class SlotGrave extends Slot {

    TileEntityGravestone te;

    public SlotGrave(IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition,
        TileEntityGravestone grave) {
        super(inventory, slotIndex, xDisplayPosition, yDisplayPosition);
        this.te = grave;
    }

    public boolean isItemValid(ItemStack itemStack) {
        return te.isDecorativeGrave && this.inventory instanceof GraveInventoryArmor
            && itemStack != null
            && itemStack.getItem() instanceof ItemArmor
            && EntityLiving.getArmorPosition(itemStack) == (this.slotIndex + 1);
    }

    public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
        if (!this.te.playername.equals(par1EntityPlayer.getCommandSenderName())) {
            if (!this.te.looted) {
                this.te.looted = true;
                this.te.locked = "You loot " + this.te.getStackInSlot(this.getSlotIndex())
                    .getDisplayName() + " from " + this.te.playername + "'s grave.";
                return true;
            }
            this.te.locked = "You admire the items from " + this.te.playername + "'s grave...";
        }
        return this.te.playername.equals(par1EntityPlayer.getCommandSenderName());
    }

    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack()) amount = Math.min(amount, this.getStack().stackSize);
        return super.decrStackSize(amount);
    }
}
