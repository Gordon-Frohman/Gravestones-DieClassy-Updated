



package net.subaraki.gravestone.client.gui;

import net.minecraft.entity.player.*;
import net.minecraft.util.StatCollector;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import net.subaraki.gravestone.handler.*;
import net.subaraki.gravestone.util.*;
import net.subaraki.gravestone.*;
import net.subaraki.gravestone.common.network.*;
import cpw.mods.fml.common.network.simpleimpl.*;

public class GuiPickGrave extends GuiScreen
{
    private int xSize;
    private int ySize;
    private short rotationCounter;
    private EntityPlayer player;
    public int render;
    
    public GuiPickGrave(final EntityPlayer player) {
        this.xSize = 0;
        this.ySize = 0;
        this.rotationCounter = 0;
        this.player = player;
        this.render = PlayerGraveData.get(player).getGraveModel();
    }
    
    public void initGui() {
        this.buttonList.clear();
        final int posX = (this.width - this.xSize) / 2;
        final int posY = (this.height - this.ySize) / 2;
        this.buttonList.add(new GuiButton(0, posX + 150, posY - 100, 20, 20, "X"));
        this.buttonList.add(new GuiButton(1, posX + 90, posY - 70, 80, 20, StatCollector.translateToLocal("pickGrave.cross")));
        this.buttonList.add(new GuiButton(2, posX + 90, posY - 40, 80, 20, StatCollector.translateToLocal("pickGrave.classic")));
        this.buttonList.add(new GuiButton(3, posX + 90, posY - 10, 80, 20, StatCollector.translateToLocal("pickGrave.tomb")));
        this.buttonList.add(new GuiButton(4, posX + 90, posY + 20, 80, 20, StatCollector.translateToLocal("pickGrave.pillar")));
        this.buttonList.add(new GuiButton(5, posX + 90, posY + 50, 80, 20, StatCollector.translateToLocal("pickGrave.bust")));
        this.buttonList.add(new GuiButton(6, posX, posY - 70, 80, 20, StatCollector.translateToLocal("pickGrave.cheap")));
        this.buttonList.add(new GuiButton(7, posX, posY - 40, 80, 20, StatCollector.translateToLocal("pickGrave.stele")));
        this.buttonList.add(new GuiButton(8, posX, posY - 10, 80, 20, StatCollector.translateToLocal("pickGrave.angel")));
        this.buttonList.add(new GuiButton(9, posX, posY + 20, 80, 20, StatCollector.translateToLocal("pickGrave.knight")));
        this.buttonList.add(new GuiButton(10, posX, posY + 50, 80, 20, StatCollector.translateToLocal("pickGrave.barrel")));
    }
    
    public void drawScreen(final int par1, final int par2, final float par3) {
        super.drawScreen(par1, par2, par3);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 5.0f);
        final int posX = (this.width - this.xSize) / 2;
        final int posY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(posX, posY, 0, 0, this.xSize, this.ySize);
        this.fontRendererObj.drawSplitString(StatCollector.translateToLocal("pickGrave.text"), this.width / 2 - 84, this.height / 2 - 100, 150, 16777215);
        this.fontRendererObj.drawSplitString("" + this.render, this.width / 2 + 140, this.height / 2 - 95, 150, 16777215);
        GL11.glPushMatrix();
        this.mc.renderEngine.bindTexture(TextureHandler.getTextureFromMeta(this.render));
        float scale = 75.0f;
        int height = 80;
        if (this.render == 4 || this.render == 5 || this.render == 7 || this.render == 8 || this.render == 9) {
            height = 40;
        }
        if (this.render == 9) {
            scale = 60.0f;
        }
        if (this.render == 8) {
            scale = 50.0f;
        }
        GL11.glTranslatef((float)(this.width / 2 - 150), (float)(this.height / 2 - height), 40.0f);
        GL11.glScaled((double)scale, (double)scale, (double)(-scale));
        final float s = -0.65f;
        final float s2 = -0.4f;
        if (this.render == 8) {
            GL11.glScalef(1.0f, -1.0f, 1.0f);
            GL11.glRotatef(-10.0f, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(-0.5f, -2.4f, 0.0f);
            GL11.glTranslatef(-s, 0.0f, s);
        }
        if (this.render == 10) {
            GL11.glScalef(1.0f, -1.0f, 1.0f);
            GL11.glTranslatef(-0.5f, -1.5f, 0.0f);
            GL11.glRotatef(-10.0f, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(-s2, 0.0f, s2);
        }
        GL11.glRotatef(5.0f, 1.0f, 0.0f, 0.0f);
        final short rotationCounter = this.rotationCounter;
        this.rotationCounter = (short)(rotationCounter + 1);
        GL11.glRotatef((float)rotationCounter, 0.0f, 1.0f, 0.0f);
        if (this.render == 8) {
            GL11.glTranslatef(s, 0.0f, -s);
        }
        if (this.render == 10) {
            GL11.glTranslatef(s2, 0.0f, -s2);
        }
        ModelHandler.renderModelFromType(this.render);
        GL11.glPopMatrix();
        if (this.render == 5) {
            this.renderBust();
        }
    }
    
    private void renderBust() {
        GL11.glPushMatrix();
        if (this.player != null) {
            this.mc.renderEngine.bindTexture(GraveUtility.instance.processPlayerTexture(this.player.getDisplayName()));
            GL11.glTranslatef((float)(this.width / 2 - 150), (float)(this.height / 2 - 40), 40.0f);
            GL11.glScaled(50.0, 50.0, -50.0);
            GL11.glRotatef(5.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef((float)this.rotationCounter, 0.0f, 1.0f, 0.0f);
            ModelHandler.modelhead.renderHead(0.0625f);
        }
        GL11.glPopMatrix();
    }
    
    protected void actionPerformed(final GuiButton b) {
        super.actionPerformed(b);
        if (b.id == 0) {
            this.player.closeScreen();
        }
        else {
            this.sendPacket(b.id);
        }
    }
    
    private void sendPacket(final int id) {
        this.render = id;
        PlayerGraveData.get(this.player).setGraveModel(id);
        GraveStones.instance.network.sendToServer((IMessage)new PacketSyncGraveModel(id));
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
}
