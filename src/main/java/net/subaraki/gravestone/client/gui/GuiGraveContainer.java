package net.subaraki.gravestone.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.inventory.ContainerGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGraveContainer extends GuiGraveContainerAbstract {

    public GuiGraveContainer(TileEntityGravestone te) {
        super(te, new ContainerGrave(te, Minecraft.getMinecraft().thePlayer));
        this.tabText = "Minecraft";
        this.graveGui = new ResourceLocation("grave:textures/gui/grave_chest.png");
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

        if (GraveStones.hasBackhand || (te.offhand != null && !te.offhand.isEmpty())) {
            int posX = (this.width - this.xSize) / 2;
            int posY = (this.height - this.ySize) / 2;
            this.mc.renderEngine.bindTexture(GuiGraveContainerAbstract.widgets);
            this.drawTexturedModalRect(posX + 191, posY + 64, 0, 0, 29, 32);
        }
    }

}
