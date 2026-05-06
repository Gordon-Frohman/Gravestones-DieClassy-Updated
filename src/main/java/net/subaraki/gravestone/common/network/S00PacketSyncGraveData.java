
package net.subaraki.gravestone.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.handler.PlayerGraveData;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class S00PacketSyncGraveData implements IMessage {

    public boolean maleEpitaph;
    public int meta;

    public S00PacketSyncGraveData() {}

    public S00PacketSyncGraveData(boolean maleEpitaph, int meta) {
        this.maleEpitaph = maleEpitaph;
        this.meta = meta;
    }

    public void fromBytes(ByteBuf buf) {
        this.maleEpitaph = buf.readBoolean();
        this.meta = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.maleEpitaph);
        buf.writeInt(this.meta);
    }

    public static class Handler implements IMessageHandler<S00PacketSyncGraveData, IMessage> {

        public IMessage onMessage(S00PacketSyncGraveData message, MessageContext ctx) {
            EntityPlayer player = GraveStones.proxy.getClientPlayer();
            PlayerGraveData pgd = PlayerGraveData.get(player);
            pgd.setMaleEpitaph(message.maleEpitaph);
            pgd.setGraveModel(message.meta);
            return null;
        }
    }
}
