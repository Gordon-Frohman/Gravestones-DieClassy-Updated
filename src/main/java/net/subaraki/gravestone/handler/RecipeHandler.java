



package net.subaraki.gravestone.handler;

import net.subaraki.gravestone.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import cpw.mods.fml.common.registry.*;

public class RecipeHandler
{
    public static void registerItemRecipe() {
    }
    
    public static void registerBlockRecipe() {
        GameRegistry.addRecipe(new ItemStack(GraveStones.graveStone, 1, 1), new Object[] { "www", " w ", " w ", 'w', Blocks.cobblestone });
        GameRegistry.addShapelessRecipe(new ItemStack(GraveStones.graveStone, 1, 2), new Object[] { new ItemStack(GraveStones.graveStone, 1, 1) });
        GameRegistry.addShapelessRecipe(new ItemStack(GraveStones.graveStone, 1, 3), new Object[] { new ItemStack(GraveStones.graveStone, 1, 2) });
        GameRegistry.addShapelessRecipe(new ItemStack(GraveStones.graveStone, 1, 4), new Object[] { new ItemStack(GraveStones.graveStone, 1, 3) });
        GameRegistry.addShapelessRecipe(new ItemStack(GraveStones.graveStone, 1, 5), new Object[] { new ItemStack(GraveStones.graveStone, 1, 4) });
        GameRegistry.addShapelessRecipe(new ItemStack(GraveStones.graveStone, 1, 6), new Object[] { new ItemStack(GraveStones.graveStone, 1, 5) });
        GameRegistry.addShapelessRecipe(new ItemStack(GraveStones.graveStone, 1, 7), new Object[] { new ItemStack(GraveStones.graveStone, 1, 6) });
        GameRegistry.addShapelessRecipe(new ItemStack(GraveStones.graveStone, 1, 8), new Object[] { new ItemStack(GraveStones.graveStone, 1, 7) });
        GameRegistry.addShapelessRecipe(new ItemStack(GraveStones.graveStone, 1, 9), new Object[] { new ItemStack(GraveStones.graveStone, 1, 8) });
        GameRegistry.addShapelessRecipe(new ItemStack(GraveStones.graveStone, 1, 10), new Object[] { new ItemStack(GraveStones.graveStone, 1, 9) });
        GameRegistry.addShapelessRecipe(new ItemStack(GraveStones.graveStone, 1, 1), new Object[] { new ItemStack(GraveStones.graveStone, 1, 10) });
    }
}
