package net.subaraki.gravestone.integration.adventurebackpack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import com.darkona.adventurebackpack.init.ModItems;
import com.darkona.adventurebackpack.playerProperties.BackpackProperty;

public class AdventureBackpackIntegration extends ModIntegration {

    public static final String modName = "Adventure Backpack";
    public static final String modId = "adventurebackpack";

    public AdventureBackpackIntegration() {
        super(modName, modId, modId, GraveInventoryAdventureBackpack.class);
    }

    public ItemStack getModIcon() {
        if (GraveStones.hasAdventureBackpack) {
            if (ModItems.machete != null) return new ItemStack(ModItems.machete);
        }
        return new ItemStack(Blocks.chest);
    }

    @Override
    public boolean storeItems(TileEntityGravestone te, EntityPlayer player) {
        BackpackProperty bp = BackpackProperty.get(player);
        if (bp != null) {
            ItemStack backpack = bp.getWearable();
            if (backpack != null) {
                GraveInventory graveInv = getGraveInventory(1, te);
                bp.setWearable(null);
                graveInv.setInventorySlotContents(0, backpack);
                graveInv.icon = backpack;
                return true;
            }
        } else {
            GraveStones.printDebugMessage(
                "GraveStones Mod couldn't connect to Adventure Backpack. Have these classes been modified? Report to mod Author pleases.");
        }
        return false;
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return null;
    }

    @Override
    protected GraveInventory getGraveInventory(int size, TileEntityGravestone grave) {
        return new GraveInventoryAdventureBackpack(grave);
    }

}
