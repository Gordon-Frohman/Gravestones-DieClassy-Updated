
package net.subaraki.gravestone.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.common.network.C01PacketOpenGui;
import net.subaraki.gravestone.handler.GraveTextHandler;
import net.subaraki.gravestone.handler.GuiHandler;
import net.subaraki.gravestone.handler.ModelHandler;
import net.subaraki.gravestone.handler.TextureHandler;
import net.subaraki.gravestone.inventory.ContainerGraveAbstract;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import net.subaraki.gravestone.util.Constants;
import net.subaraki.gravestone.util.GraveUtility;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class GuiGraveContainerAbstract extends GuiContainer {

    static short rotationCounter = 0;
    public String epitaph = "";
    public EntityPlayer deadPlayer;
    public String nameOfDeadPlayer;
    protected TileEntityGravestone te;
    protected String tabText = "";
    protected ResourceLocation graveGui;

    int offsetSize = 33;
    int x = this.width / 2 - this.xSize / 2 + 4;
    int y = this.height / 2 - this.ySize / 2 - 19;

    public GuiGraveContainerAbstract(TileEntityGravestone te, ContainerGraveAbstract grave) {
        super(grave);
        this.deadPlayer = te.getWorldObj()
            .getPlayerEntityByName(te.playername);
        this.nameOfDeadPlayer = te.playername;
        this.te = te;
        this.xSize = 198;
        this.ySize = 186;
        if (te != null) {
            if (te.message1.length() <= 0) {
                te.isDecorativeGrave = false;
                if (this.nameOfDeadPlayer.equals("!Empty!")) {
                    this.epitaph = StatCollector.translateToLocal("grave.empty");
                } else {
                    this.epitaph = GraveTextHandler
                        .getStringFromMeta(this.nameOfDeadPlayer, this.te.modelType, this.te.maleEpitaph);
                }
            } else {
                this.epitaph = te.message1 + te.playername + te.message2;
            }
        }
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj
            .drawString(StatCollector.translateToLocal("grave.container.name"), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int posX = (this.width - this.xSize) / 2;
        int posY = (this.height - this.ySize) / 2;
        this.mc.renderEngine.bindTexture(graveGui);
        this.drawTexturedModalRect(posX, posY, 0, 0, this.xSize, this.ySize);
        this.fontRendererObj.drawSplitString(this.epitaph, this.width / 2 + 109, this.height / 2 - 89, 100, 0);
        this.fontRendererObj.drawSplitString(this.epitaph, this.width / 2 + 110, this.height / 2 - 90, 100, 16777215);
        if (this.te.locked.length() > 0) {
            this.fontRendererObj.drawSplitString(this.te.locked, this.width / 2 + 79, this.height / 2 + 39, 150, 0);
            this.fontRendererObj
                .drawSplitString(this.te.locked, this.width / 2 + 80, this.height / 2 + 40, 150, 16777215);
        }
        this.fontRendererObj.drawString(
            this.tabText,
            this.width / 2 - this.xSize / 2 + 5,
            this.height / 2 - this.ySize / 2 + 5,
            4210752);
        int render = this.te.modelType;
        GL11.glPushMatrix();
        this.mc.renderEngine.bindTexture(TextureHandler.getTextureFromMeta(render));
        float scale = 75.0f;
        int height = 80;
        if (render == 4 || render == 5 || render == 7 || render == 8 || render == 9) {
            height = 40;
        }
        if (render == 9) {
            scale = 60.0f;
        }
        if (render == 8) {
            scale = 50.0f;
        }
        GL11.glTranslatef((float) (this.width / 2 - 150), (float) (this.height / 2 - height), 40.0f);
        GL11.glScaled((double) scale, (double) scale, (double) (-scale));
        float s = -0.65f;
        float s2 = -0.4f;
        if (render == 8) {
            GL11.glScalef(1.0f, -1.0f, 1.0f);
            GL11.glTranslatef(-0.5f, -2.4f, 0.0f);
            GL11.glTranslatef(-s, 0.0f, s);
        }
        if (render == 10) {
            GL11.glScalef(1.0f, -1.0f, 1.0f);
            GL11.glTranslatef(-0.5f, -1.5f, 0.0f);
            GL11.glRotatef(-10.0f, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(-s2, 0.0f, s2);
        }
        GL11.glRotatef(5.0f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef((float) rotationCounter, 0.0f, 1.0f, 0.0f);
        rotationCounter++;
        if (render == 8) {
            GL11.glTranslatef(s, 0.0f, -s);
        }
        if (render == 10) {
            GL11.glTranslatef(s2, 0.0f, -s2);
        }
        ModelHandler.renderModelFromType(render);
        GL11.glPopMatrix();
        if (render == 5) {
            this.renderBust();
        }
    }

    private void renderBust() {
        GL11.glPushMatrix();
        this.mc.renderEngine.bindTexture(GraveUtility.instance.processPlayerTexture(this.nameOfDeadPlayer));
        GL11.glTranslatef((float) (this.width / 2 - 150), (float) (this.height / 2 - 40), 40.0f);
        GL11.glScaled(50.0, 50.0, -50.0);
        GL11.glRotatef(5.0f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef((float) rotationCounter, 0.0f, 1.0f, 0.0f);
        ModelHandler.modelBust.renderBust(0.0625f);
        GL11.glPopMatrix();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        x = this.width / 2 - this.xSize / 2 + 4;
        y = this.height / 2 - this.ySize / 2 - 19;
        if (!this.te.inventories.isEmpty()) {
            this.buttonList.add(
                new GuiTabButton(
                    GuiHandler.GRAVE_CONTAINER,
                    x,
                    y,
                    35,
                    20,
                    "",
                    this instanceof GuiGraveContainer,
                    Constants.ICON_VANILLA,
                    this.fontRendererObj));
            this.buttonList.add(
                new GuiTabButton(
                    GuiHandler.GRAVE_CONTAINER_SCROLLABLE,
                    x + offsetSize,
                    y,
                    35,
                    20,
                    "",
                    this instanceof GuiGraveContainerScrollable,
                    Constants.ICON_MISC,
                    this.fontRendererObj));
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        if ((button.id == GuiHandler.GRAVE_CONTAINER && this instanceof GuiGraveContainerScrollable)
            || (button.id == GuiHandler.GRAVE_CONTAINER_SCROLLABLE && this instanceof GuiGraveContainer)) {
            // Minecraft mc = Minecraft.getMinecraft();
            // mc.thePlayer.openGui(GraveStones.instance, button.id, mc.theWorld, te.xCoord, te.yCoord, te.zCoord);
            GraveStones.instance.network.sendToServer(new C01PacketOpenGui(button.id, te.xCoord, te.yCoord, te.zCoord));
        }
    }

    @Override
    public void func_146977_a(Slot slotIn) {
        if (slotIn.inventory != null) super.func_146977_a(slotIn);
    }
}
