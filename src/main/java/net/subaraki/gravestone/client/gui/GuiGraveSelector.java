
package net.subaraki.gravestone.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.common.network.C00PacketSyncGraveData;
import net.subaraki.gravestone.handler.ModelHandler;
import net.subaraki.gravestone.handler.PlayerGraveData;
import net.subaraki.gravestone.handler.TextureHandler;
import net.subaraki.gravestone.util.GraveUtility;

import org.lwjgl.opengl.GL11;

public class GuiGraveSelector extends GuiScreen {

    private int xSize;
    private int ySize;
    private short rotationCounter;
    public int graveType;
    public boolean maleEpitaph;
    private GuiButton maleSex;
    private GuiButton femaleSex;

    public GuiGraveSelector() {
        this.xSize = 0;
        this.ySize = 0;
        this.rotationCounter = 0;
        PlayerGraveData pgd = PlayerGraveData.get(Minecraft.getMinecraft().thePlayer);
        this.graveType = pgd.getGraveModel();
        this.maleEpitaph = pgd.shouldUseMaleEpitaph();
    }

    @SuppressWarnings("unchecked")
    public void initGui() {
        this.buttonList.clear();
        int posX = (this.width - this.xSize) / 2;
        int posY = (this.height - this.ySize) / 2;
        this.buttonList.add(new GuiButton(0, posX + 150, posY - 100, 20, 20, "X"));
        this.buttonList
            .add(new GuiButton(1, posX + 90, posY - 70, 80, 20, StatCollector.translateToLocal("pickGrave.cross")));
        this.buttonList
            .add(new GuiButton(2, posX + 90, posY - 40, 80, 20, StatCollector.translateToLocal("pickGrave.classic")));
        this.buttonList
            .add(new GuiButton(3, posX + 90, posY - 10, 80, 20, StatCollector.translateToLocal("pickGrave.tomb")));
        this.buttonList
            .add(new GuiButton(4, posX + 90, posY + 20, 80, 20, StatCollector.translateToLocal("pickGrave.pillar")));
        this.buttonList
            .add(new GuiButton(5, posX + 90, posY + 50, 80, 20, StatCollector.translateToLocal("pickGrave.bust")));
        this.buttonList
            .add(new GuiButton(6, posX, posY - 70, 80, 20, StatCollector.translateToLocal("pickGrave.cheap")));
        this.buttonList
            .add(new GuiButton(7, posX, posY - 40, 80, 20, StatCollector.translateToLocal("pickGrave.stele")));
        this.buttonList
            .add(new GuiButton(8, posX, posY - 10, 80, 20, StatCollector.translateToLocal("pickGrave.angel")));
        this.buttonList
            .add(new GuiButton(9, posX, posY + 20, 80, 20, StatCollector.translateToLocal("pickGrave.knight")));
        this.buttonList
            .add(new GuiButton(10, posX, posY + 50, 80, 20, StatCollector.translateToLocal("pickGrave.barrel")));
        maleSex = new GuiButton(11, posX / 2, posY - 100, 20, 20, "♂");
        maleSex.enabled = !this.maleEpitaph;
        this.buttonList.add(maleSex);
        femaleSex = new GuiButton(12, posX / 2 + 30, posY - 100, 20, 20, "♀");
        femaleSex.enabled = this.maleEpitaph;
        this.buttonList.add(femaleSex);
    }

    public void drawScreen(int par1, int par2, float par3) {
        super.drawScreen(par1, par2, par3);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 5.0f);
        int posX = (this.width - this.xSize) / 2;
        int posY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(posX, posY, 0, 0, this.xSize, this.ySize);
        this.fontRendererObj.drawSplitString(
            StatCollector.translateToLocal("pickGrave.text"),
            this.width / 2 + 1,
            this.height / 2 - 100,
            150,
            16777215);
        this.fontRendererObj
            .drawSplitString("" + this.graveType, this.width / 2 + 140, this.height / 2 - 95, 150, 16777215);
        this.fontRendererObj.drawSplitString(
            StatCollector.translateToLocal("pickGrave.sex"),
            this.width / 2 - 170,
            this.height / 2 - 100,
            60,
            16777215);
        GL11.glPushMatrix();
        this.mc.renderEngine.bindTexture(TextureHandler.getTextureFromMeta(this.graveType));
        float scale = 75.0f;
        int height = 40;
        if (this.graveType == 9) {
            scale = 60.0f;
            height = 20;
        }
        if (this.graveType == 8) {
            scale = 50.0f;
            height = 50;
        }
        GL11.glTranslatef((float) (this.width / 4), (float) (this.height / 2 - height), 40.0f);
        GL11.glScaled((double) scale, (double) scale, (double) (-scale));
        float s = -0.65f;
        float s2 = -0.4f;
        if (this.graveType == 8) {
            GL11.glScalef(1.0f, -1.0f, 1.0f);
            GL11.glRotatef(-10.0f, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(-0.5f, -2.4f, 0.0f);
            GL11.glTranslatef(-s, 0.0f, s);
        }
        if (this.graveType == 10) {
            GL11.glScalef(1.0f, -1.0f, 1.0f);
            GL11.glTranslatef(-0.5f, -1.5f, 0.0f);
            GL11.glRotatef(-10.0f, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(-s2, 0.0f, s2);
        }
        GL11.glRotatef(5.0f, 1.0f, 0.0f, 0.0f);
        short rotationCounter = this.rotationCounter;
        this.rotationCounter = (short) (rotationCounter + 1);
        GL11.glRotatef((float) rotationCounter, 0.0f, 1.0f, 0.0f);
        if (this.graveType == 8) {
            GL11.glTranslatef(s, 0.0f, -s);
        }
        if (this.graveType == 10) {
            GL11.glTranslatef(s2, 0.0f, -s2);
        }
        ModelHandler.renderModelFromType(this.graveType);
        GL11.glPopMatrix();
        if (this.graveType == 5) {
            this.renderBust();
        }
    }

    private void renderBust() {
        GL11.glPushMatrix();
        this.mc.renderEngine.bindTexture(
            GraveUtility.instance.processPlayerTexture(Minecraft.getMinecraft().thePlayer.getDisplayName()));
        GL11.glTranslatef((float) (this.width / 4), (float) (this.height / 2 - 40), 40.0f);
        GL11.glScaled(50.0, 50.0, -50.0);
        GL11.glRotatef(5.0f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef((float) this.rotationCounter, 0.0f, 1.0f, 0.0f);
        ModelHandler.modelBust.renderBust(0.0625f);
        GL11.glPopMatrix();
    }

    protected void actionPerformed(GuiButton b) {
        super.actionPerformed(b);
        if (b.id == 0) {
            Minecraft.getMinecraft().thePlayer.closeScreen();
        } else {
            if (b.id < 11) {
                this.graveType = b.id;
            } else {
                this.maleEpitaph = b.id == 11;
                maleSex.enabled = !this.maleEpitaph;
                femaleSex.enabled = this.maleEpitaph;
            }
            this.sendPacket(this.maleEpitaph, this.graveType);
        }
    }

    private void sendPacket(boolean maleEpitaph, int id) {
        PlayerGraveData pgd = PlayerGraveData.get(Minecraft.getMinecraft().thePlayer);
        pgd.setMaleEpitaph(maleEpitaph);
        pgd.setGraveModel(id);
        GraveStones.instance.network.sendToServer(new C00PacketSyncGraveData(maleEpitaph, id));
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
