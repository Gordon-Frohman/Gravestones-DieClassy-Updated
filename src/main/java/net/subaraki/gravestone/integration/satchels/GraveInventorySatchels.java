package net.subaraki.gravestone.integration.satchels;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

public abstract class GraveInventorySatchels extends GraveInventory {

    public GraveInventorySatchels() {
        super();
    }

    public GraveInventorySatchels(String name, int size, TileEntityGravestone grave) {
        super(name, SatchelsIntegration.modId, size, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        IInventory inv = getInventory(player);
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if (inv.getStackInSlot(i) == null) {
                inv.setInventorySlotContents(i, this.getStackInSlot(i));
                this.setInventorySlotContents(i, null);
            }
        }
        super.autoEquipItems(player);
    }

    protected abstract IInventory getInventory(EntityPlayer player);

}
