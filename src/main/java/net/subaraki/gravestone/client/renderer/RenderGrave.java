



package net.subaraki.gravestone.client.renderer;

import net.minecraftforge.client.*;
import net.minecraft.item.*;
import net.subaraki.gravestone.util.*;
import net.minecraft.tileentity.*;
import net.minecraft.client.renderer.tileentity.*;

public class RenderGrave implements IItemRenderer
{
    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return true;
    }
    
    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        Constants.GRAVE_PLACEHOLDER.modelType = item.getItemDamage();
        final TileEntitySpecialRenderer tesr = TileEntityRendererDispatcher.instance.getSpecialRenderer((TileEntity)Constants.GRAVE_PLACEHOLDER);
        tesr.renderTileEntityAt((TileEntity)Constants.GRAVE_PLACEHOLDER, 0.0, 0.0, 0.0, 0.0f);
    }
}
