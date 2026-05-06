package net.subaraki.gravestone.client.gui;

import java.util.Iterator;

import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.handler.GuiHandler;
import net.subaraki.gravestone.inventory.ContainerGraveScrollable;
import net.subaraki.gravestone.inventory.GraveInventory;
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

    private Boolean[] enableSlots = new Boolean[36];

    public GuiGraveContainerScrollable(EntityPlayer player, TileEntityGravestone te) {
        super(player, te, new ContainerGraveScrollable(te, player));
        graveGui = new ResourceLocation("grave:textures/gui/grave_chest_scrollable.png");
        resetSlots();
        scrollTo(0);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen() {
        if (this.te.inventories.isEmpty()) {
            playerOpenGui.openGui(
                GraveStones.instance,
                GuiHandler.GRAVE_CONTAINER,
                playerOpenGui.worldObj,
                te.xCoord,
                te.yCoord,
                te.zCoord);
        }
    }

    @Override
    public boolean isMouseOverSlot(Slot slotIn, int mouseX, int mouseY) {
        return (slotIn.slotNumber >= enableSlots.length || enableSlots[slotIn.slotNumber])
            ? super.isMouseOverSlot(slotIn, mouseX, mouseY)
            : false;
    }

    private void resetSlots() {
        for (int i = 0; i < enableSlots.length; i++) {
            enableSlots[i] = false;
            Slot slot = ((ContainerGraveScrollable) this.inventorySlots).getSlot(i);
            slot.inventory = ContainerGraveScrollable.placeholderInv;
            slot.slotIndex = i;
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        // TODO Do we need this?
        super.drawGuiContainerForegroundLayer(par1, par2);
    }

    private int getRowsCount() {
        int rowsCount = 0;
        for (GraveInventory inv : te.inventories) {
            rowsCount += inv.getRowsCount() + 1;
        }
        return rowsCount;
    }

    /**
     * returns (if you are not on the inventoryTab) and (the flag isn't set) and (you have more than 1 page of items)
     */
    private boolean needsScrollBars() {
        return getRowsCount() > 5;
    }

    /**
     * Handles mouse input.
     */
    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int scroll = Mouse.getEventDWheel();

        if (scroll != 0 && this.needsScrollBars()) {
            int rows = getRowsCount() - 5;

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
        this.resetSlots();

        int rows = this.getRowsCount() - 5;
        int offset = (int) ((double) (scroll * (float) rows) + 0.5D) + 1;

        if (offset < 0) offset = 0;

        ContainerGraveScrollable container = (ContainerGraveScrollable) this.inventorySlots;

        boolean skipRow = true; // Skipping one row when we need to draw new inventory
        Iterator<GraveInventory> i$ = this.te.inventories.iterator();
        GraveInventory currentInv = i$.next();
        int currentInvSlot = 0;
        int totalInvSlot = 0;
        int currentRow = 0;
        while (currentRow < offset + 4) {
            if (skipRow) {
                currentRow++;
                totalInvSlot = currentRow * 9;
                skipRow = false;
                continue;
            } else {
                if (currentRow >= offset) {
                    int index = totalInvSlot - offset * 9;
                    this.enableSlots[index] = true;
                    Slot slot = container.getSlot(index);
                    slot.inventory = currentInv;
                    slot.slotIndex = currentInvSlot;
                }
                if (currentInvSlot + 1 < currentInv.getSizeInventory()) {
                    if (currentInvSlot % 9 == 8) currentRow++;
                    currentInvSlot++;
                    totalInvSlot++;
                } else {
                    if (i$.hasNext()) {
                        currentInv = i$.next();
                        currentInvSlot = 0;
                        currentRow++;
                        totalInvSlot = currentRow * 9;
                        skipRow = true;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        this.mc.getTextureManager()
            .bindTexture(this.graveGui);
        int xMin = this.guiLeft + 7;
        int yMin = this.guiTop + 17;
        boolean[] disableRows = new boolean[4];
        for (int i = 0; i < 4; i++) {
            disableRows[i] = true;
            for (int j = 0; j < 9; j++) {
                if (enableSlots[i * 9 + j]) {
                    disableRows[i] = false;
                    break;
                }
            }
            if (disableRows[i]) {
                if (i != 0) this.drawTexturedModalRect(xMin, yMin + 18 * i - 1, 0, 186, 162, 1);
                this.drawTexturedModalRect(xMin, yMin + 18 * i, 0, 187, 162, 18);
                if (i != 3) this.drawTexturedModalRect(xMin, yMin + 18 * (i + 1), 0, 205, 162, 1);
            }
        }
        for (int i = 0; i < 4; i++) {
            if (disableRows[i]) {
                continue;
            } else {
                for (int j = 0; j < 9; j++) {
                    int slotId = i * 9 + j;
                    if (enableSlots[slotId]) {
                        this.drawTexturedModalRect(xMin + 18 * j, yMin + 18 * i, 7, 104, 18, 18);
                        if (j < 8 && !enableSlots[slotId + 1]) {
                            this.drawTexturedModalRect(xMin + 18 * (j + 1), yMin + 18 * i, 25, 104, 1, 18);
                        }
                        if (i < 3 && !enableSlots[slotId + 9] && !disableRows[i + 1]) {
                            this.drawTexturedModalRect(
                                xMin + 18 * j,
                                yMin + 18 * (i + 1),
                                j == 8 ? 151 : 150,
                                17,
                                18,
                                1);
                        }
                    }
                }
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
