



package net.subaraki.gravestone.inventory.slot;

import net.subaraki.gravestone.tileentity.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;

public class SlotGrave extends Slot
{
    private int field_75228_b;
    TileEntityGravestone te;
    
    public SlotGrave(final IInventory par1iInventory, final int par2, final int par3, final int par4) {
        super(par1iInventory, par2, par3, par4);
        this.te = (TileEntityGravestone)par1iInventory;
    }
    
    public boolean isItemValid(final ItemStack par1ItemStack) {
        return false;
    }
    
    public boolean canTakeStack(final EntityPlayer par1EntityPlayer) {
        if (!this.te.playername.equals(par1EntityPlayer.getCommandSenderName())) {
            if (!this.te.otherPlayerHasTakenItemStack) {
                this.te.otherPlayerHasTakenItemStack = true;
                this.te.locked = "You loot " + this.te.getStackInSlot(this.getSlotIndex()).getDisplayName() + " from " + this.te.playername + "'s grave.";
                return true;
            }
            this.te.locked = "You admire the items from " + this.te.playername + "'s grave...";
        }
        return this.te.playername.equals(par1EntityPlayer.getCommandSenderName());
    }
    
    public ItemStack decrStackSize(final int par1) {
        if (this.getHasStack()) {
            this.field_75228_b += Math.min(par1, this.getStack().stackSize);
        }
        return super.decrStackSize(par1);
    }
    
    public void onPickupFromSlot(final EntityPlayer par1EntityPlayer, final ItemStack par2ItemStack) {
        this.onCrafting(par2ItemStack);
        super.onPickupFromSlot(par1EntityPlayer, par2ItemStack);
    }
}
