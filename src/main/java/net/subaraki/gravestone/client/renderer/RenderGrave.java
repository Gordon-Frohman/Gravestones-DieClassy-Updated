
package net.subaraki.gravestone.client.renderer;

import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraftforge.client.*;
import net.subaraki.gravestone.util.*;

public class RenderGrave implements IItemRenderer {

    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item,
        final IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        Constants.GRAVE_PLACEHOLDER.modelType = item.getItemDamage();
        final TileEntitySpecialRenderer tesr = TileEntityRendererDispatcher.instance
            .getSpecialRenderer((TileEntity) Constants.GRAVE_PLACEHOLDER);
        TileEntitySpecialRendererGrave.inventoryRender = true;
        tesr.renderTileEntityAt((TileEntity) Constants.GRAVE_PLACEHOLDER, 0.0, 0.0, 0.0, 0.0f);
        TileEntitySpecialRendererGrave.inventoryRender = false;
    }
}
