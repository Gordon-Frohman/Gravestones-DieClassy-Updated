
package net.subaraki.gravestone.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public class ContainerGrave extends ContainerGraveAbstract {

    public ContainerGrave(TileEntityGravestone te, EntityPlayer player) {
        super(te, player);
        boolean isCreative = player.capabilities.isCreativeMode;
        for (int i = 0; i < 4; ++i) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(
                    getSlot(te.mainInv, this.slotCount, 8 + k * 18, 18 + i * 18, false, isCreative, te));
                ++this.slotCount;
            }
        }
        for (int i = 0; i < 4; ++i) {
            this.addSlotToContainer(getSlot(te.armor, i, 174, 72 - i * 18, true, isCreative, te));
            ++this.slotCount;
        }
        if (GraveStones.hasBackhand || (te.offhand != null && !te.offhand.isEmpty()))
            this.addSlotToContainer(new Slot(te.offhand, 0, 196, 72));
        this.fillInv(player.inventory);
    }
}
