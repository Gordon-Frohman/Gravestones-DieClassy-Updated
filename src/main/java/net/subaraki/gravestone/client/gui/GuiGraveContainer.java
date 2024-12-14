
package net.subaraki.gravestone.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.client.model.ModelBust;
import net.subaraki.gravestone.common.network.PacketSwitchSlotLayout;
import net.subaraki.gravestone.handler.GraveTextHandler;
import net.subaraki.gravestone.handler.ModelHandler;
import net.subaraki.gravestone.handler.TextureHandler;
import net.subaraki.gravestone.inventory.ContainerGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import net.subaraki.gravestone.util.Constants;
import net.subaraki.gravestone.util.GraveUtility;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class GuiGraveContainer extends GuiContainer {

    private short rotationCounter;
    public String gravetext;
    public EntityPlayer deathPlayer;
    public EntityPlayer playerOpenGui;
    public String nameOfDeathPlayer;
    private TileEntityGravestone te;
    private String tabText;
    private static final ResourceLocation graveGui;
    private ModelBust modelBust;
    private ResourceLocation texture;

    int offsetX = 0;
    final int offsetSize = 33;
    int x = this.width / 2 - this.xSize / 2 + 4;
    int y = this.height / 2 - this.ySize / 2 - 19;

    public GuiGraveContainer(final EntityPlayer player, final TileEntityGravestone grave) {
        super(new ContainerGrave(player.inventory, grave, player));
        this.rotationCounter = 0;
        this.gravetext = "";
        this.tabText = "Minecraft";
        this.modelBust = new ModelBust();
        this.deathPlayer = player.worldObj.getPlayerEntityByName(grave.playername);
        this.playerOpenGui = player;
        this.nameOfDeathPlayer = grave.playername;
        this.te = grave;
        this.xSize = 198;
        this.ySize = 186;
        if (grave != null) {
            if (grave.message1.length() <= 0) {
                grave.isDecorativeGrave = false;
                if (this.nameOfDeathPlayer.equals("!Empty!")) {
                    this.gravetext = StatCollector.translateToLocal("grave.empty");
                } else {
                    this.gravetext = GraveTextHandler.getStringFromMeta(this.nameOfDeathPlayer, this.te.modelType);
                }
            } else {
                this.gravetext = grave.message1 + grave.playername + grave.message2;
            }
        }
    }

    protected void drawGuiContainerForegroundLayer(final int par1, final int par2) {
        this.fontRendererObj
            .drawString(StatCollector.translateToLocal("grave.container.name"), 8, this.ySize - 96 + 2, 16777215);
    }

    protected void drawGuiContainerBackgroundLayer(final float f, final int i, final int j) {
        final int posX = (this.width - this.xSize) / 2;
        final int posY = (this.height - this.ySize) / 2;
        this.mc.renderEngine.bindTexture(GuiGraveContainer.graveGui);
        this.drawTexturedModalRect(posX, posY, 0, 0, this.xSize, this.ySize);
        this.fontRendererObj.drawSplitString(this.gravetext, this.width / 2 + 109, this.height / 2 - 89, 100, 0);
        this.fontRendererObj.drawSplitString(this.gravetext, this.width / 2 + 110, this.height / 2 - 90, 100, 16777215);
        if (this.te.locked.length() > 0) {
            this.fontRendererObj.drawSplitString(this.te.locked, this.width / 2 + 79, this.height / 2 + 39, 150, 0);
            this.fontRendererObj
                .drawSplitString(this.te.locked, this.width / 2 + 80, this.height / 2 + 40, 150, 16777215);
        }
        this.fontRendererObj.drawString(
            this.tabText,
            this.width / 2 - this.xSize / 2 + 5,
            this.height / 2 - this.ySize / 2 + 5,
            16777215);
        final int render = this.te.modelType;
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
        final float s = -0.65f;
        final float s2 = -0.4f;
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
        final short rotationCounter = this.rotationCounter;
        this.rotationCounter = (short) (rotationCounter + 1);
        GL11.glRotatef((float) rotationCounter, 0.0f, 1.0f, 0.0f);
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
        this.mc.renderEngine.bindTexture(GraveUtility.instance.processPlayerTexture(this.nameOfDeathPlayer));
        GL11.glTranslatef((float) (this.width / 2 - 150), (float) (this.height / 2 - 40), 40.0f);
        GL11.glScaled(50.0, 50.0, -50.0);
        GL11.glRotatef(5.0f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef((float) this.rotationCounter, 0.0f, 1.0f, 0.0f);
        ModelHandler.modelBust.renderBust(0.0625f);
        GL11.glPopMatrix();
    }

    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        offsetX = 0;
        x = this.width / 2 - this.xSize / 2 + 4;
        y = this.height / 2 - this.ySize / 2 - 19;
        // Minecraft
        registerInventory(0, Constants.ICON_VANILLA);
        if (GraveStones.hasRpgI) {
            registerInventory(1, Constants.ICON_RPGI);
        }
        if (GraveStones.hasTiC) {
            registerInventory(2, Constants.ICON_TCON);
        }
        if (GraveStones.hasBaubles) {
            registerInventory(3, Constants.ICON_BAUBLES);
        }
        if (GraveStones.hasGalacticraft) {
            registerInventory(4, Constants.ICON_GALACTICRAFT);
        }
        if (GraveStones.hasMariculture) {
            registerInventory(5, Constants.ICON_MARICULTURE);
        }
        if (GraveStones.hasCosmeticArmor) {
            registerInventory(6, Constants.ICON_COSMETIC_ARMOR);
        }
        if (GraveStones.hasSatchels) {
            registerInventory(7, Constants.ICON_SATCHELS);
        }
    }

    @SuppressWarnings("unchecked")
    private void registerInventory(int id, ItemStack icon) {
        this.buttonList
            .add(new GuiTabButton(id, x + offsetX, y, 35, 20, "", this.te.tab == id, icon, this.fontRendererObj));
        offsetX += offsetSize;
    }

    protected void actionPerformed(final GuiButton button) {
        super.actionPerformed(button);
        this.updateInventory((byte) button.id);
        this.tabText = GraveStones.inventories.containsKey(button.id) ? GraveStones.inventories.get(button.id)
            : tabText;
        this.initGui();
    }

    private void updateInventory(final byte i) {
        GraveStones.instance.network
            .sendToServer((IMessage) new PacketSwitchSlotLayout(this.te.xCoord, this.te.yCoord, this.te.zCoord, i));
        this.te.changeSlotLayout(i);
        Integer inventorySize = GraveStones.inventorySizes.get(Integer.valueOf(i));
        if (inventorySize != null) {
            ContainerGrave grave = (ContainerGrave) inventorySlots;
            if (inventorySize <= 4) {
                grave.interactableIndexMin = 36;
                grave.interactableIndexMax = 39;
            } else {
                grave.interactableIndexMin = 0;
                grave.interactableIndexMax = inventorySize - 1;
            }
        }
        this.te.tab = i;
    }

    static {
        graveGui = new ResourceLocation("grave:textures/entity/tile/grave_chest.png");
    }
}
