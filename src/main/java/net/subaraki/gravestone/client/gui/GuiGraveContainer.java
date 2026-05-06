package net.subaraki.gravestone.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.subaraki.gravestone.inventory.ContainerGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGraveContainer extends GuiGraveContainerAbstract {

    public GuiGraveContainer(EntityPlayer player, TileEntityGravestone te) {
        super(player, te, new ContainerGrave(te, player));
        this.tabText = "Minecraft";
        this.graveGui = new ResourceLocation("grave:textures/gui/grave_chest.png");
    }

}
