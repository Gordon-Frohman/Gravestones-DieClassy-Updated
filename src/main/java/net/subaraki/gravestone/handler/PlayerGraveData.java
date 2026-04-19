
package net.subaraki.gravestone.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.common.network.PacketSyncGraveDataToClient;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PlayerGraveData implements IExtendedEntityProperties {

    EntityPlayer player;
    public static final String PROPS = "graveData";
    private boolean maleEpitaph;
    private int graveID;

    public PlayerGraveData(final EntityPlayer player) {
        this.graveID = 1;
        this.maleEpitaph = true;
        this.player = player;
    }

    public static final void register(final EntityPlayer player) {
        player.registerExtendedProperties("graveData", (IExtendedEntityProperties) new PlayerGraveData(player));
    }

    public static final PlayerGraveData get(final EntityPlayer p) {
        return (PlayerGraveData) p.getExtendedProperties("graveData");
    }

    public boolean shouldUseMaleEpitaph() {
        return this.maleEpitaph;
    }

    public void setMaleEpitaph(boolean maleEpitaph) {
        this.maleEpitaph = maleEpitaph;
    }

    public int getGraveModel() {
        return this.graveID;
    }

    public void setGraveModel(final int i) {
        this.graveID = i;
    }

    public void saveNBTData(final NBTTagCompound compound) {
        compound.setInteger("grave_ID", this.graveID);
        compound.setBoolean("male_epitaph", this.maleEpitaph);
        if (this.player != null && this.player.worldObj.isRemote) {
            GraveStones.printDebugMessage("C save " + this.graveID + " ");
        }
        if (this.player != null && !this.player.worldObj.isRemote) {
            GraveStones.printDebugMessage("S save " + this.graveID + " ");
        }
    }

    public void loadNBTData(final NBTTagCompound compound) {
        this.graveID = compound.getInteger("grave_ID");
        this.maleEpitaph = compound.getBoolean("male_epitaph");
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
            GraveStones.instance.network.sendTo(
                (IMessage) new PacketSyncGraveDataToClient(this.maleEpitaph, this.graveID),
                (EntityPlayerMP) this.player);
        }
    }
}
