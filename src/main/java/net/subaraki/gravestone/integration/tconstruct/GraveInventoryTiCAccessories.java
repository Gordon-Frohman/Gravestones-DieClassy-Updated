package net.subaraki.gravestone.integration.tconstruct;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import tconstruct.TConstruct;
import tconstruct.armor.player.TPlayerStats;
import tconstruct.util.network.ArmourGuiSyncPacket;

public class GraveInventoryTiCAccessories extends GraveInventoryTiC {

    public GraveInventoryTiCAccessories() {
        super();
    }

    public GraveInventoryTiCAccessories(int size, TileEntityGravestone grave) {
        super(TinkersConstructIntegration.inventoryAccessories, size, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        super.autoEquipItems(player);
        TPlayerStats stats = TPlayerStats.get(player);
        NBTTagCompound tag = new NBTTagCompound();
        stats.saveNBTData(tag);
        ArmourGuiSyncPacket syncPacket = new ArmourGuiSyncPacket(tag);
        TConstruct.packetPipeline.sendTo(syncPacket, (EntityPlayerMP) player);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return TinkersConstructIntegration.getAccessoryInventory(player);
    }

}
