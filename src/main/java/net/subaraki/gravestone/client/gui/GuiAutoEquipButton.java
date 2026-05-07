
package net.subaraki.gravestone.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiAutoEquipButton extends GuiButton {

    protected static final ResourceLocation button = new ResourceLocation("grave:textures/gui/grave_widgets.png");
    public static final int BUTTON_ID = 3;

    public GuiAutoEquipButton(int id, int xPosition, int yPosition) {
        super(id, xPosition, yPosition, 12, 13, "");
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            mc.getTextureManager()
                .bindTexture(button);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition
                && mouseX < this.xPosition + this.width
                && mouseY < this.yPosition + this.height;
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(
                this.xPosition,
                this.yPosition,
                58,
                0 + (this.field_146123_n ? this.height : 0),
                this.width,
                this.height);
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }
}
