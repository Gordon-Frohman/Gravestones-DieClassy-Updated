
package net.subaraki.gravestone.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.inventory.ContainerGraveScrollable;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class C02PacketGraveScroll implements IMessage {

    public int windowId;
    public float scrollValue;

    public C02PacketGraveScroll() {}

    public C02PacketGraveScroll(int windowId, float scrollValue) {
        this.windowId = windowId;
        this.scrollValue = scrollValue;
    }

    public void fromBytes(ByteBuf buf) {
        this.windowId = buf.readInt();
        this.scrollValue = buf.readFloat();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.windowId);
        buf.writeFloat(this.scrollValue);
    }

    public static class Handler implements IMessageHandler<C02PacketGraveScroll, IMessage> {

        public IMessage onMessage(C02PacketGraveScroll message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            if (player.openContainer.windowId == message.windowId) {
                ((ContainerGraveScrollable) player.openContainer).scrollTo(message.scrollValue);
            }
            return null;
        }
    }
}
