package net.subaraki.gravestone.integration;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedList;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.WorldServer;

import fr.eyzox.forgecreeperheal.ForgeCreeperHeal;
import fr.eyzox.forgecreeperheal.worldhealer.BlockData;
import fr.eyzox.forgecreeperheal.worldhealer.HealTask;
import fr.eyzox.forgecreeperheal.worldhealer.WorldHealer;
import fr.eyzox.ticklinkedlist.AbstractTickContainerLinkedList;
import fr.eyzox.ticklinkedlist.TickContainer;

public class ForgeCreeperHealIntegration {

    @SuppressWarnings("unchecked")
    public static boolean isBlockGoingToHeal(WorldServer world, int x, int y, int z) {
        WorldHealer healer = ForgeCreeperHeal.getWorldHealer(world);

        try {
            Field toHeal = WorldHealer.class.getDeclaredField("toHeal");
            toHeal.setAccessible(true);
            HealTask healTask = (HealTask) toHeal.get(healer);
            Field list = AbstractTickContainerLinkedList.class.getDeclaredField("list");
            list.setAccessible(true);
            LinkedList<TickContainer<Collection<BlockData>>> tickDataList = (LinkedList<TickContainer<Collection<BlockData>>>) list
                .get(healTask);
            for (TickContainer<Collection<BlockData>> tickContainer : tickDataList) {
                for (BlockData blockData : tickContainer.getData()) {
                    ChunkPosition pos = blockData.getChunkPosition();
                    if (pos.chunkPosX == x && pos.chunkPosY == y && pos.chunkPosZ == z) {
                        return true;
                    }
                }
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return false;
    }

}
