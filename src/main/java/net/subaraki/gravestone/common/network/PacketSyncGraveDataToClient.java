
package net.subaraki.gravestone.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.handler.PlayerGraveData;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketSyncGraveDataToClient implements IMessage {

    public boolean maleEpitaph;
    public int meta;

    public PacketSyncGraveDataToClient() {}

    public PacketSyncGraveDataToClient(final boolean maleEpitaph, final int meta) {
        this.maleEpitaph = maleEpitaph;
        this.meta = meta;
    }

    public void fromBytes(final ByteBuf buf) {
        this.maleEpitaph = buf.readBoolean();
        this.meta = buf.readInt();
    }

    public void toBytes(final ByteBuf buf) {
        buf.writeBoolean(this.maleEpitaph);
        buf.writeInt(this.meta);
    }

    public static class Handler implements IMessageHandler<PacketSyncGraveDataToClient, IMessage> {

        public IMessage onMessage(final PacketSyncGraveDataToClient message, final MessageContext ctx) {
            final EntityPlayer player = GraveStones.proxy.getClientPlayer();
            final PlayerGraveData pgd = PlayerGraveData.get(player);
            pgd.setMaleEpitaph(message.maleEpitaph);
            pgd.setGraveModel(message.meta);
            return null;
        }
    }
}
