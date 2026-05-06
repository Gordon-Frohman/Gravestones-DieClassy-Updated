package net.subaraki.gravestone.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.common.network.C01PacketOpenGui;
import net.subaraki.gravestone.common.network.C02PacketGraveScroll;
import net.subaraki.gravestone.handler.GuiHandler;
import net.subaraki.gravestone.inventory.ContainerGraveScrollable;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGraveContainerScrollable extends GuiGraveContainerAbstract {

    /** Amount scrolled in Creative mode inventory (0 = top, 1 = bottom) */
    private float currentScroll;
    /** True if the scrollbar is being dragged */
    private boolean isScrolling;
    /** True if the left mouse button was held down last time drawScreen was called. */
    private boolean wasClicking;

    private ContainerGraveScrollable container;

    public GuiGraveContainerScrollable(TileEntityGravestone te) {
        super(te, new ContainerGraveScrollable(te, Minecraft.getMinecraft().thePlayer));
        graveGui = new ResourceLocation("grave:textures/gui/grave_chest_scrollable.png");
        container = (ContainerGraveScrollable) this.inventorySlots;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen() {
        if (this.te.inventories.isEmpty()) {
            GraveStones.instance.network
                .sendToServer(new C01PacketOpenGui(GuiHandler.GRAVE_CONTAINER, te.xCoord, te.yCoord, te.zCoord));
        }
    }

    @Override
    public boolean isMouseOverSlot(Slot slotIn, int mouseX, int mouseY) {
        return (slotIn.slotNumber >= container.enableSlots.length || container.enableSlots[slotIn.slotNumber])
            ? super.isMouseOverSlot(slotIn, mouseX, mouseY)
            : false;
    }

    /**
     * returns (if you are not on the inventoryTab) and (the flag isn't set) and (you have more than 1 page of items)
     */
    private boolean needsScrollBars() {
        return container.getRowsCount() > 5;
    }

    /**
     * Handles mouse input.
     */
    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int scroll = Mouse.getEventDWheel();

        if (scroll != 0 && this.needsScrollBars()) {
            int rows = container.getRowsCount() - 5;

            if (scroll > 0) {
                scroll = 1;
            }

            if (scroll < 0) {
                scroll = -1;
            }

            this.currentScroll = (float) ((double) this.currentScroll - (double) scroll / (double) rows);

            if (this.currentScroll < 0.0F) {
                this.currentScroll = 0.0F;
            }

            if (this.currentScroll > 1.0F) {
                this.currentScroll = 1.0F;
            }

            scrollTo(this.currentScroll);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        boolean flag = Mouse.isButtonDown(0);
        int k = this.guiLeft;
        int l = this.guiTop;
        int i1 = k + 175;
        int j1 = l + 17;
        int k1 = i1 + 14;
        int l1 = j1 + 72;

        if (!this.wasClicking && flag && mouseX >= i1 && mouseY >= j1 && mouseX < k1 && mouseY < l1) {
            this.isScrolling = this.needsScrollBars();
        }

        if (!flag) {
            this.isScrolling = false;
        }

        this.wasClicking = flag;

        if (this.isScrolling) {
            this.currentScroll = ((float) (mouseY - j1) - 7.5F) / ((float) (l1 - j1) - 15.0F);

            if (this.currentScroll < 0.0F) {
                this.currentScroll = 0.0F;
            }

            if (this.currentScroll > 1.0F) {
                this.currentScroll = 1.0F;
            }

            scrollTo(this.currentScroll);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void scrollTo(float scroll) {
        container.scrollTo(scroll);
        GraveStones.instance.network
            .sendToServer(new C02PacketGraveScroll(Minecraft.getMinecraft().thePlayer.openContainer.windowId, scroll));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.tabText = container.tabNames[0];

        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

        this.mc.getTextureManager()
            .bindTexture(this.graveGui);
        int xMin = this.guiLeft + 7;
        int yMin = this.guiTop + 17;
        for (int i = 0; i < 4; i++) {
            boolean skipRow = true;
            for (int j = 0; j < 9; j++) {
                int slotId = i * 9 + j;
                if (container.enableSlots[slotId]) {
                    this.drawTexturedModalRect(xMin + 18 * j, yMin + 18 * i, 7, 104, 18, 18);
                    skipRow = false;
                }
            }
            if (skipRow) {
                this.fontRendererObj.drawString(
                    container.tabNames[i + 1],
                    this.width / 2 - this.xSize / 2 + 8,
                    this.height / 2 - this.ySize / 2 + 4 + (i + 1) * 18,
                    4210752);
            }
        }

        xMin = this.guiLeft + 176;
        yMin = this.guiTop + 18;
        int yMax = yMin + 72;
        this.mc.getTextureManager()
            .bindTexture(GuiContainerCreative.field_147061_u);
        this.drawTexturedModalRect(
            xMin,
            yMin + (int) ((float) (yMax - yMin - 17) * this.currentScroll),
            232 + (this.needsScrollBars() ? 0 : 12),
            0,
            12,
            15);
        this.mc.getTextureManager()
            .bindTexture(this.graveGui);
    }

}
