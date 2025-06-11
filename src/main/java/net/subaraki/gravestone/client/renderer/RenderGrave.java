
package net.subaraki.gravestone.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.subaraki.gravestone.util.Constants;

import org.lwjgl.opengl.GL11;

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
        GL11.glPushMatrix();
        double scale = 1;
        double offset = 0;
        switch (Constants.GRAVE_PLACEHOLDER.modelType) {
            default:
                break;
            case 4:
            case 5:
            case 7:
                scale = 0.75;
                offset = -0.4;
                break;
            case 8:
            case 9:
                scale = 0.65;
                offset = -0.6;
                break;
        }
        GL11.glScaled(scale, scale, scale);
        GL11.glTranslated(0, offset, 0);
        tesr.renderTileEntityAt((TileEntity) Constants.GRAVE_PLACEHOLDER, 0.0, 0.0, 0.0, 0.0f);
        GL11.glPopMatrix();
        TileEntitySpecialRendererGrave.inventoryRender = false;
    }
}
