



package net.subaraki.gravestone.handler;

import cpw.mods.fml.common.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.subaraki.gravestone.tileentity.*;
import net.subaraki.gravestone.inventory.*;
import net.minecraft.tileentity.*;
import net.subaraki.gravestone.client.gui.*;

public class GuiHandler implements IGuiHandler
{
    public Object getServerGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        final TileEntity te = world.getTileEntity(x, y, z);
        return (ID == 0) ? new ContainerGrave(player.inventory, (TileEntityGravestone)te, player) : null;
    }
    
    public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        final TileEntity te = world.getTileEntity(x, y, z);
        switch (ID) {
            case 0: {
                return new GuiGraveContainer(player, (TileEntityGravestone)te);
            }
            case 1: {
                return new GuiPickGrave(player);
            }
            default: {
                return new GuiGraveContainer(player, (TileEntityGravestone)te);
            }
        }
    }
}
