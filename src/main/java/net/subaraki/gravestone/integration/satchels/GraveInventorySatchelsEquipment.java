package net.subaraki.gravestone.integration.satchels;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import makamys.satchels.EntityPropertiesSatchels;
import makamys.satchels.Packets.MessageSyncEquipment;
import makamys.satchels.Satchels;
import makamys.satchels.inventory.ContainerSatchels;

public class GraveInventorySatchelsEquipment extends GraveInventorySatchels {

    public GraveInventorySatchelsEquipment() {
        super();
    }

    public GraveInventorySatchelsEquipment(int size, TileEntityGravestone grave) {
        super(SatchelsIntegration.modId, size, grave);
    }

    @Override
    public void autoEquipItems(EntityPlayer player) {
        super.autoEquipItems(player);

        EntityPropertiesSatchels satchelsProps = EntityPropertiesSatchels.fromPlayer(player);
        NBTTagCompound tag = new NBTTagCompound();
        satchelsProps.saveNBTData(tag);
        ((ContainerSatchels) player.inventoryContainer).redoSlots(!player.capabilities.isCreativeMode);

        Satchels.networkWrapper.sendTo(new MessageSyncEquipment(tag), (EntityPlayerMP) player);
    }

    @Override
    protected IInventory getInventory(EntityPlayer player) {
        return SatchelsIntegration.getEquipmentInventory(player);
    }

}
