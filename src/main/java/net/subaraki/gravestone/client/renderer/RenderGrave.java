
package net.subaraki.gravestone.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.subaraki.gravestone.util.Constants;

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
