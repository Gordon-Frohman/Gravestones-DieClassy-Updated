
package net.subaraki.gravestone.network.play.client;

import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.handler.GuiHandler;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class C03PacketAutoEquip implements IMessage {

    public int x, y, z;

    public C03PacketAutoEquip() {}

    public C03PacketAutoEquip(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public static class Handler implements IMessageHandler<C03PacketAutoEquip, IMessage> {

        public IMessage onMessage(C03PacketAutoEquip message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            TileEntityGravestone grave = (TileEntityGravestone) player.worldObj
                .getTileEntity(message.x, message.y, message.z);
            if (grave != null) {
                grave.autoEquipItems(player);
                player.worldObj.markBlockForUpdate(message.x, message.y, message.z);
                player.openGui(
                    GraveStones.instance,
                    GuiHandler.GRAVE_CONTAINER,
                    player.worldObj,
                    message.x,
                    message.y,
                    message.z);
            }
            return null;
        }
    }
}
