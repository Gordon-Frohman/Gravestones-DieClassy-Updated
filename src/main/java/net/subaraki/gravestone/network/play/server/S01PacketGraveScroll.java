
package net.subaraki.gravestone.network.play.server;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.inventory.ContainerGraveScrollable;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;

public class S01PacketGraveScroll implements IMessage {

    public int windowId;
    public float scrollValue;

    public S01PacketGraveScroll() {}

    public S01PacketGraveScroll(int windowId, float scrollValue) {
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

    public static class Handler implements IMessageHandler<S01PacketGraveScroll, IMessage> {

        @SideOnly(Side.CLIENT)
        public IMessage onMessage(S01PacketGraveScroll message, MessageContext ctx) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            if (player.openContainer.windowId == message.windowId) {
                ((ContainerGraveScrollable) player.openContainer).scrollTo(message.scrollValue);
            }
            return null;
        }
    }
}
