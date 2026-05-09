
package net.subaraki.gravestone.inventory;

import net.minecraft.entity.player.EntityPlayer;
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
        this.fillInv(player.inventory);
    }
}
