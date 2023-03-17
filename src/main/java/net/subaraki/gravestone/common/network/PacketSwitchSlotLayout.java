



package net.subaraki.gravestone.common.network;

import io.netty.buffer.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import net.subaraki.gravestone.tileentity.*;
import net.subaraki.gravestone.*;
import net.minecraft.world.*;
import net.minecraft.tileentity.*;

public class PacketSwitchSlotLayout implements IMessage
{
    public int x;
    public int y;
    public int z;
    public byte graveSlotType;
    
    public PacketSwitchSlotLayout() {
    }
    
    public PacketSwitchSlotLayout(final int x, final int y, final int z, final byte slotType) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.graveSlotType = slotType;
    }
    
    public void fromBytes(final ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.graveSlotType = buf.readByte();
    }
    
    public void toBytes(final ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeByte((int)this.graveSlotType);
    }
    
    public static class PacketSwitchSlotLayoutHandler implements IMessageHandler<PacketSwitchSlotLayout, IMessage>
    {
        public IMessage onMessage(final PacketSwitchSlotLayout message, final MessageContext ctx) {
            final World world = ctx.getServerHandler().playerEntity.worldObj;
            final TileEntity tile = world.getTileEntity(message.x, message.y, message.z);
            if (tile != null && tile instanceof TileEntityGravestone) {
                final TileEntityGravestone grave = (TileEntityGravestone)tile;
                grave.changeSlotLayout(message.graveSlotType);
                grave.tab = message.graveSlotType;
            }
            else {
                GraveStones.printDebugMessage("Tile Entity did not exist ! Could not acces inventory");
            }
            return null;
        }
    }
}
