
package net.subaraki.gravestone.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import padeg.lib.Padeg;

public class SlotGrave extends Slot {

    TileEntityGravestone te;

    public SlotGrave(IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition,
        TileEntityGravestone grave) {
        super(inventory, slotIndex, xDisplayPosition, yDisplayPosition);
        this.te = grave;
    }

    public boolean isItemValid(ItemStack itemStack) {
        return false;
    }

    public boolean canTakeStack(EntityPlayer player) {
        if (this.te.playername.equals(player.getCommandSenderName())) {
            return true;
        } else {
            if (!this.te.looted) {
                this.te.looted = true;
                String itemName = this.inventory.getStackInSlot(this.getSlotIndex())
                    .getDisplayName();
                if (GraveStones.proxy.usingRussianLanguage()) {
                    String firstLetter = "" + itemName.charAt(0);
                    itemName = Padeg.getOfficePadeg(itemName.replaceFirst(firstLetter, firstLetter.toLowerCase()), 4);
                }
                this.te.locked = StatCollector.translateToLocal("grave.loot.1") + itemName
                    + StatCollector.translateToLocal("grave.loot.2")
                    + this.te.playername
                    + StatCollector.translateToLocal("grave.loot.3");
                return true;
            }
            this.te.locked = StatCollector.translateToLocal("grave.loot.forbidden.1") + this.te.playername
                + StatCollector.translateToLocal("grave.loot.forbidden.2");
        }
        return false;
    }

    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack()) amount = Math.min(amount, this.getStack().stackSize);
        return super.decrStackSize(amount);
    }
}
