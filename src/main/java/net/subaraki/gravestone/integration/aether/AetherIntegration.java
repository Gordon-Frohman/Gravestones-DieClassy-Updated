package net.subaraki.gravestone.integration.aether;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.items.ItemsAether;

public class AetherIntegration extends ModIntegration {

    public static final String modName = "Aether";
    public static final String modId = "aether_legacy";

    public AetherIntegration() {
        super(modName, modId, GraveInventoryAether.class);
    }

    public ItemStack getModIcon() {
        return GraveStones.hasAether && ItemsAether.golden_feather != null ? new ItemStack(ItemsAether.golden_feather)
            : new ItemStack(Items.feather);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return AetherAPI.get(player)
            .getAccessoryInventory();
    }

    @Override
    protected GraveInventory getGraveInventory(int size, TileEntityGravestone grave) {
        return new GraveInventoryAether(size, grave);
    }

}
