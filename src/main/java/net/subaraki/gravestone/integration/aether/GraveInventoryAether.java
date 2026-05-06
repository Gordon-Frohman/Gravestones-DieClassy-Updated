package net.subaraki.gravestone.integration.aether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;

public class GraveInventoryAether extends GraveInventory {

    public GraveInventoryAether() {
        super();
    }

    public GraveInventoryAether(int size, TileEntityGravestone grave) {
        super(AetherIntegration.modName, AetherIntegration.modId, size, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        if (player instanceof EntityPlayerMP playerMP) {
            IAccessoryInventory inv = AetherAPI.get(player)
                .getAccessoryInventory();
            for (int i = 0; i < inv.getSizeInventory(); i++) {
                if (inv.getStackInSlot(i) == null) {
                    inv.setInventorySlotContents(i, this.getStackInSlot(i));
                    this.setInventorySlotContents(i, null);
                }
            }
        }
        super.autoEquipItems(player);
    }

}
