
package net.subaraki.gravestone.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.subaraki.gravestone.client.gui.GuiGraveContainer;
import net.subaraki.gravestone.client.gui.GuiGraveContainerScrollable;
import net.subaraki.gravestone.client.gui.GuiGraveSelector;
import net.subaraki.gravestone.inventory.ContainerGrave;
import net.subaraki.gravestone.inventory.ContainerGraveScrollable;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final int GRAVE_CONTAINER = 0;
    public static final int GRAVE_CONTAINER_SCROLLABLE = 1;
    public static final int GRAVE_SELECTOR = 2;

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntityGravestone te = (TileEntityGravestone) world.getTileEntity(x, y, z);
        switch (ID) {
            default:
                return null;
            case GRAVE_CONTAINER:
                return new ContainerGrave(te, player);
            case GRAVE_CONTAINER_SCROLLABLE:
                return new ContainerGraveScrollable(te, player);
        }
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntityGravestone te = (TileEntityGravestone) world.getTileEntity(x, y, z);
        switch (ID) {
            default:
                return null;
            case GRAVE_CONTAINER:
                return new GuiGraveContainer(te);
            case GRAVE_CONTAINER_SCROLLABLE:
                return new GuiGraveContainerScrollable(te);
            case GRAVE_SELECTOR:
                return new GuiGraveSelector();
        }
    }
}
