
package net.subaraki.gravestone.common.network;

import net.minecraft.entity.player.*;
import net.subaraki.gravestone.handler.*;

import cpw.mods.fml.common.network.simpleimpl.*;
import io.netty.buffer.*;

public class PacketSyncGraveModel implements IMessage {

    public int meta;

    public PacketSyncGraveModel() {}

    public PacketSyncGraveModel(final int meta) {
        this.meta = meta;
    }

    public void fromBytes(final ByteBuf buf) {
        this.meta = buf.readInt();
    }

    public void toBytes(final ByteBuf buf) {
        buf.writeInt(this.meta);
    }

    public static class PacketSyncGraveModelHandler implements IMessageHandler<PacketSyncGraveModel, IMessage> {

        public IMessage onMessage(final PacketSyncGraveModel message, final MessageContext ctx) {
            final EntityPlayer player = (EntityPlayer) ctx.getServerHandler().playerEntity;
            final PlayerGraveData pgd = PlayerGraveData.get(player);
            pgd.setGraveModel(message.meta);
            return null;
        }
    }
}
