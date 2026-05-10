
package net.subaraki.gravestone.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiTabButton extends GuiButton {

    protected static int ticks = 0;
    protected static RenderItem itemRender = new RenderItem();
    protected static final ResourceLocation button = new ResourceLocation(
        "textures/gui/container/creative_inventory/tabs.png");
    private boolean page;
    private final ItemStack[] icons;

    public GuiTabButton(int id, int xPosition, int yPosition, boolean page, ItemStack... stack) {
        super(id, xPosition, yPosition, 35, 20, "");
        this.page = page;
        this.icons = stack;
    }

    @Override
    public void drawButton(Minecraft minecraft, int x, int y) {
        if (this.visible) {
            FontRenderer fontrenderer = minecraft.fontRenderer;
            minecraft.getTextureManager()
                .bindTexture(GuiTabButton.button);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            this.field_146123_n = (x >= this.xPosition && y >= this.yPosition
                && x < this.xPosition + this.width
                && y < this.yPosition + this.height);
            int k = this.page ? 1 : 2;
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
            this.mouseDragged(minecraft, x, y);
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
            this.zLevel = 100.0f;
            GuiTabButton.itemRender.zLevel = 100.0f;
            final ItemStack itemstack = this.icons[(Math.abs(ticks++) / 100) % this.icons.length];

            GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthFunc(GL11.GL_LEQUAL);

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(775, 769, 1, 0);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.enableGUIStandardItemLighting();

            GuiTabButton.itemRender.renderItemAndEffectIntoGUI(
                fontrenderer,
                Minecraft.getMinecraft()
                    .getTextureManager(),
                itemstack,
                this.xPosition + 9,
                this.yPosition);

            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glDisable(GL11.GL_BLEND);

            GL11.glPopAttrib();
            GuiTabButton.itemRender.zLevel = 0.0f;
        }
    }
}
