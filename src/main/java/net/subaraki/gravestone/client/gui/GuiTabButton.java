
package net.subaraki.gravestone.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiTabButton extends GuiButton {

    protected static RenderItem itemRender;
    protected static final ResourceLocation button;
    private boolean page;
    private final ItemStack stack;
    private final FontRenderer fontRendererObj;

    public GuiTabButton(final int par1, final int par2, final int par3, final int par4, final int par5,
        final String par6Str, final boolean page, final ItemStack stack, final FontRenderer font) {
        super(par1, par2, par3, par4, par5, par6Str);
        this.page = page;
        this.stack = stack;
        this.fontRendererObj = font;
    }

    public void drawButton(final Minecraft p_146112_1_, final int p_146112_2_, final int p_146112_3_) {
        if (this.visible) {
            final FontRenderer fontrenderer = p_146112_1_.fontRenderer;
            p_146112_1_.getTextureManager()
                .bindTexture(GuiTabButton.button);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            this.field_146123_n = (p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition
                && p_146112_2_ < this.xPosition + this.width
                && p_146112_3_ < this.yPosition + this.height);
            final int k = this.page ? 1 : 2;
            GL11.glEnable(3042);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(770, 771);
            this.drawTexturedModalRect(
                this.xPosition,
                this.yPosition - 9,
                28,
                32 - k * 32 + 32,
                28,
                this.page ? 32 : 28);
            this.drawTexturedModalRect(
                this.xPosition + 9,
                this.yPosition - 9,
                32,
                32 - k * 32 + 32,
                24,
                this.page ? 32 : 28);
            this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
            int l = 14737632;
            if (this.packedFGColour != 0) {
                l = this.packedFGColour;
            } else if (!this.enabled) {
                l = 10526880;
            } else if (this.field_146123_n) {
                l = 16777120;
            }
            this.drawCenteredString(
                fontrenderer,
                this.displayString,
                this.xPosition + this.width / 2,
                this.yPosition + (this.height - 8) / 2,
                l);
            GL11.glDisable(2896);
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            this.zLevel = 100.0f;
            GuiTabButton.itemRender.zLevel = 100.0f;
            GL11.glEnable(2896);
            GL11.glEnable(32826);
            final ItemStack itemstack = this.stack;
            GuiTabButton.itemRender.renderItemAndEffectIntoGUI(
                this.fontRendererObj,
                Minecraft.getMinecraft()
                    .getTextureManager(),
                itemstack,
                this.xPosition + 9,
                this.yPosition);
            GuiTabButton.itemRender.renderItemOverlayIntoGUI(
                this.fontRendererObj,
                Minecraft.getMinecraft()
                    .getTextureManager(),
                itemstack,
                this.xPosition,
                this.yPosition);
            GL11.glDisable(2896);
            GuiTabButton.itemRender.zLevel = 0.0f;
        }
    }

    static {
        GuiTabButton.itemRender = new RenderItem();
        button = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
    }
}
