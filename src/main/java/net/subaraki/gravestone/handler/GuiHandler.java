
package net.subaraki.gravestone.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.subaraki.gravestone.client.gui.GuiGraveContainer;
import net.subaraki.gravestone.client.gui.GuiPickGrave;
import net.subaraki.gravestone.inventory.ContainerGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public Object getServerGuiElement(final int ID, final EntityPlayer player, final World world, final int x,
        final int y, final int z) {
        final TileEntity te = world.getTileEntity(x, y, z);
        return (ID == 0) ? new ContainerGrave(player.inventory, (TileEntityGravestone) te, player) : null;
    }

    public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x,
        final int y, final int z) {
        final TileEntity te = world.getTileEntity(x, y, z);
        switch (ID) {
            default:
            case 0: {
                return new GuiGraveContainer(player, (TileEntityGravestone) te);
            }
            case 1: {
                return new GuiPickGrave(player);
            }
        }
    }
}
