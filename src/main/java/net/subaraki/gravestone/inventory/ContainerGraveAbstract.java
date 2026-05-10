
package net.subaraki.gravestone.inventory;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.backhand.BackhandIntegration;
import net.subaraki.gravestone.inventory.slot.SlotArmorGrave;
import net.subaraki.gravestone.inventory.slot.SlotGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ContainerGraveAbstract extends Container {

    public TileEntityGravestone te;
    int slotCount;
    protected EntityPlayer player;

    public ContainerGraveAbstract(TileEntityGravestone te, EntityPlayer player) {
        this.slotCount = 0;
        this.te = te;
        this.player = player;
        Iterator<GraveInventory> i$ = te.inventories.iterator();
        while (i$.hasNext()) {
            GraveInventory inv = i$.next();
            if (inv.isEmpty()) i$.remove();
        }
    }

    protected Slot getSlot(IInventory inv, int slotIndex, int xDisplayPosition, int yDisplayPosition, boolean isArmor,
        boolean isCreative, TileEntityGravestone grave) {
        if (isCreative || (grave.isDecorative && isArmor)) {
            if (isArmor) {
                return new SlotArmorGrave(inv, slotIndex, xDisplayPosition, yDisplayPosition, grave);
            } else {
                return new Slot(inv, slotIndex, xDisplayPosition, yDisplayPosition);
            }
        } else {
            return new SlotGrave(inv, slotIndex, xDisplayPosition, yDisplayPosition, grave);
        }
    }

    protected void fillInv(InventoryPlayer inv) {

        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(inv, k + i * 9 + 9, 8 + k * 18, 104 + i * 18));
            }
        }
        for (int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(inv, j, 8 + j * 18, 162));
        }
        for (int i = 0; i < 4; ++i) {
            int k = i;
            this.addSlotToContainer(new Slot(inv, 36 + (GraveStones.hasBackhand ? 1 : 0) + i, 174, 162 - i * 19) {

                public int getSlotStackLimit() {
                    return 1;
                }

                public boolean isItemValid(ItemStack par1ItemStack) {
                    return par1ItemStack != null && par1ItemStack.getItem()
                        .isValidArmor(par1ItemStack, 3 - k, ContainerGraveAbstract.this.player);
                }

                @SideOnly(Side.CLIENT)
                public IIcon getBackgroundIconIndex() {
                    return ItemArmor.func_94602_b(3 - k);
                }
            });
        }

        if (GraveStones.hasBackhand) {
            this.addSlotToContainer(BackhandIntegration.getBackhandSlot(inv, player));
        }
    }

    public boolean canInteractWith(EntityPlayer player) {
        return te.getWorldObj()
            .getTileEntity(te.xCoord, te.yCoord, te.zCoord) == te
            && player.getDistanceSq(te.xCoord + 0.5, te.yCoord + 0.5, te.zCoord + 0.5) <= 64.0;
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
        ItemStack stack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotID);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();
            IInventory slotInv = slot.inventory;
            if (slotInv != te.armor || (slotInv == te.armor
                && !this.mergeItemStack(stack, slotCount + slotID, slotCount + slotID + 1, true))) {
                if (!this.mergeItemStack(stack, slotCount, slotCount + 36, true)) return null;
            }
            if (slotStack.stackSize != 1) {
                slot.putStack(null);
                return null;
            }
            slot.putStack(null);
            if (slotStack.stackSize == stack.stackSize) return null;
            slot.onPickupFromSlot(player, slotStack);
        }
        return stack;
    }
}
