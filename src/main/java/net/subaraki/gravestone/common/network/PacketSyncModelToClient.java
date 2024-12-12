
package net.subaraki.gravestone.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.handler.PlayerGraveData;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketSyncModelToClient implements IMessage {

    public int meta;

    public PacketSyncModelToClient() {}

    public PacketSyncModelToClient(final int meta) {
        this.meta = meta;
    }

    public void fromBytes(final ByteBuf buf) {
        this.meta = buf.readInt();
    }

    public void toBytes(final ByteBuf buf) {
        buf.writeInt(this.meta);
    }

    public static class PacketSyncModelToClientHandler implements IMessageHandler<PacketSyncModelToClient, IMessage> {

        public IMessage onMessage(final PacketSyncModelToClient message, final MessageContext ctx) {
            final EntityPlayer player = GraveStones.proxy.getClientPlayer();
            final PlayerGraveData pgd = PlayerGraveData.get(player);
            pgd.setGraveModel(message.meta);
            return null;
        }
    }
}
