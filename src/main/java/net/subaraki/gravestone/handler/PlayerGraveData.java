
package net.subaraki.gravestone.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.common.network.PacketSyncModelToClient;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PlayerGraveData implements IExtendedEntityProperties {

    EntityPlayer player;
    public static final String PROPS = "graveData";
    private int graveID;

    public PlayerGraveData(final EntityPlayer player) {
        this.graveID = 1;
        this.player = player;
    }

    public static final void register(final EntityPlayer player) {
        player.registerExtendedProperties("graveData", (IExtendedEntityProperties) new PlayerGraveData(player));
    }

    public static final PlayerGraveData get(final EntityPlayer p) {
        return (PlayerGraveData) p.getExtendedProperties("graveData");
    }

    public int getGraveModel() {
        return this.graveID;
    }

    public void setGraveModel(final int i) {
        this.graveID = i;
    }

    public void saveNBTData(final NBTTagCompound compound) {
        compound.setInteger("grave_ID", this.graveID);
        if (this.player != null && this.player.worldObj.isRemote) {
            GraveStones.printDebugMessage("C save " + this.graveID + " ");
        }
        if (this.player != null && !this.player.worldObj.isRemote) {
            GraveStones.printDebugMessage("S save " + this.graveID + " ");
        }
    }

    public void loadNBTData(final NBTTagCompound compound) {
        this.graveID = compound.getInteger("grave_ID");
        if (this.player != null && this.player.worldObj.isRemote) {
            GraveStones.printDebugMessage("C load " + this.graveID + " ");
        }
        if (this.player != null && !this.player.worldObj.isRemote) {
            GraveStones.printDebugMessage("S load " + this.graveID + " ");
        }
    }

    public void init(final Entity entity, final World world) {}

    public void sync() {
        if (this.player != null && this.player instanceof EntityPlayerMP) {
            GraveStones.instance.network
                .sendTo((IMessage) new PacketSyncModelToClient(this.graveID), (EntityPlayerMP) this.player);
        }
    }
}
