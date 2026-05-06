package net.subaraki.gravestone.integration.adventurebackpack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import com.darkona.adventurebackpack.playerProperties.BackpackProperty;

public class GraveInventoryAdventureBackpack extends GraveInventory {

    public GraveInventoryAdventureBackpack() {
        super();
    }

    public GraveInventoryAdventureBackpack(TileEntityGravestone grave) {
        super(AdventureBackpackIntegration.modName, AdventureBackpackIntegration.modId, 1, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        BackpackProperty bp = BackpackProperty.get(player);
        if (bp != null) {
            ItemStack backpack = bp.getWearable();
            if (backpack == null) {
                bp.setWearable(this.getStackInSlot(0));
                this.setInventorySlotContents(0, null);
            }
        }
        super.autoEquipItems(player);
    }

}
