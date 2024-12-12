
package net.subaraki.gravestone.inventory;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.subaraki.gravestone.inventory.slot.SlotArmorGrave;
import net.subaraki.gravestone.inventory.slot.SlotGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerGrave extends Container {

    protected TileEntityGravestone te;
    int slotCount;
    private EntityPlayer player;

    public ContainerGrave(final InventoryPlayer inv, final TileEntityGravestone te, final EntityPlayer p) {
        this.slotCount = 0;
        this.te = te;
        this.player = p;
        if (inv.player.capabilities.isCreativeMode) {
            for (int i = 0; i < 4; ++i) {
                for (int k = 0; k < 9; ++k) {
                    this.addSlotToContainer(new Slot((IInventory) te, this.slotCount, 8 + k * 18, 18 + i * 18));
                    ++this.slotCount;
                }
            }
            for (int i = 0; i < 4; ++i) {
                this.addSlotToContainer((Slot) new SlotArmorGrave((IInventory) te, this.slotCount, 174, 72 - i * 18));
                ++this.slotCount;
            }
        } else {
            for (int i = 0; i < 4; ++i) {
                for (int k = 0; k < 9; ++k) {
                    this.addSlotToContainer(
                        (Slot) new SlotGrave((IInventory) te, this.slotCount, 8 + k * 18, 18 + i * 18));
                    ++this.slotCount;
                }
            }
            for (int i = 0; i < 4; ++i) {
                this.addSlotToContainer((Slot) new SlotGrave((IInventory) te, this.slotCount, 174, 72 - i * 18));
                ++this.slotCount;
            }
        }
        this.fillInv(inv);
    }

    private void fillInv(final InventoryPlayer inv) {
        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot((IInventory) inv, k + i * 9 + 9, 8 + k * 18, 104 + i * 18));
            }
        }
        for (int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot((IInventory) inv, j, 8 + j * 18, 162));
        }
        for (int i = 0; i < 4; ++i) {
            final int k = i;
            this.addSlotToContainer((Slot) new Slot(inv, 36 + i, 174, 72 - i * 18 + 86) {

                public int getSlotStackLimit() {
                    return 1;
                }

                public boolean isItemValid(final ItemStack par1ItemStack) {
                    return par1ItemStack != null && par1ItemStack.getItem()
                        .isValidArmor(par1ItemStack, 3 - k, (Entity) ContainerGrave.this.player);
                }

                @SideOnly(Side.CLIENT)
                public IIcon getBackgroundIconIndex() {
                    return ItemArmor.func_94602_b(3 - k);
                }
            });
        }
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }

    public boolean canInteractWith(final EntityPlayer par1EntityPlayer) {
        return this.te.isUseableByPlayer(par1EntityPlayer);
    }

    public ItemStack slotClick(final int par1, final int par2, final int par3, final EntityPlayer par4EntityPlayer) {
        return super.slotClick(par1, par2, par3, par4EntityPlayer);
    }

    public ItemStack transferStackInSlot(final EntityPlayer player, final int slotID) {
        ItemStack stack = null;
        final Slot slot = (Slot) this.inventorySlots.get(slotID);
        if (slot != null && slot.getHasStack()) {
            final ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();
            if (!(slot.inventory instanceof TileEntityGravestone)) {
                return null;
            }
            if (slotID < 36) {
                if (!this.mergeItemStack(stack, 40, 76, true)) {
                    return null;
                }
            } else {
                if (slotID == 36 && !this.mergeItemStack(stack, 76, 77, true)) {
                    return null;
                }
                if (slotID == 37 && !this.mergeItemStack(stack, 77, 78, true)) {
                    return null;
                }
                if (slotID == 38 && !this.mergeItemStack(stack, 78, 79, true)) {
                    return null;
                }
                if (slotID == 39 && !this.mergeItemStack(stack, 79, 80, true)) {
                    return null;
                }
            }
            if (slotStack.stackSize != 1) {
                slot.putStack((ItemStack) null);
                return null;
            }
            slot.putStack((ItemStack) null);
            if (slotStack.stackSize == stack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, slotStack);
        }
        return stack;
    }
}
