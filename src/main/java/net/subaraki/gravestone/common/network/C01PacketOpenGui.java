
package net.subaraki.gravestone.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.GraveStones;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class C01PacketOpenGui implements IMessage {

    public int modGuiId, x, y, z;

    public C01PacketOpenGui() {}

    public C01PacketOpenGui(int modGuiId, int x, int y, int z) {
        this.modGuiId = modGuiId;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void fromBytes(ByteBuf buf) {
        this.modGuiId = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.modGuiId);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    public static class Handler implements IMessageHandler<C01PacketOpenGui, IMessage> {

        public IMessage onMessage(C01PacketOpenGui message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            player.openGui(GraveStones.instance, message.modGuiId, player.worldObj, message.x, message.y, message.z);
            return null;
        }
    }
}
