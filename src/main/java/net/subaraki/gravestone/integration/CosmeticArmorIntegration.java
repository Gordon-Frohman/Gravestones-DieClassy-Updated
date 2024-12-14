package net.subaraki.gravestone.integration;

import java.util.UUID;

import net.minecraft.inventory.IInventory;

import lain.mods.cos.CosmeticArmorReworked;

public class CosmeticArmorIntegration {

    public static IInventory getCosArmorInventory(UUID uuid) {
        return CosmeticArmorReworked.invMan.getCosArmorInventory(uuid);
    }
}
