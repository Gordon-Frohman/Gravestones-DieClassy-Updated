package net.subaraki.gravestone.integration;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.player.IPlayerAether;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.gildedgames.the_aether.items.ItemsAether;

public class AetherIntegration {

    private static AccessoryType[] slotTypes = new AccessoryType[] { AccessoryType.PENDANT, AccessoryType.CAPE,
        AccessoryType.SHIELD, AccessoryType.MISC, AccessoryType.RING, AccessoryType.EXTRA_RING, AccessoryType.GLOVES,
        AccessoryType.EXTRA_MISC };

    public static ItemStack getModIcon() {
        return GraveStones.hasAether && ItemsAether.golden_feather != null ? new ItemStack(ItemsAether.golden_feather)
            : new ItemStack(Items.feather);
    }

    public static ItemStack removeStackFromSlot(EntityPlayer player, int slotID) {
        if (slotID < slotTypes.length) {
            IPlayerAether playerAether = AetherAPI.get(player);
            IAccessoryInventory accessoryInventory = playerAether.getAccessoryInventory();
            AccessoryType slotType = slotTypes[slotID];
            ItemStack stack = accessoryInventory.getStackInSlot(slotType);
            if (stack != null) {
                accessoryInventory.setInventorySlotContents(slotID, null);
                accessoryInventory.markDirty();
            }
            return stack;
        } else return null;
    }

}
