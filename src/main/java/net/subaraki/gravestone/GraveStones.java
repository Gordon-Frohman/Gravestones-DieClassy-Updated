
package net.subaraki.gravestone;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.subaraki.gravestone.block.BlockGrave;
import net.subaraki.gravestone.common.CommonProxy;
import net.subaraki.gravestone.common.network.C00PacketSyncGraveData;
import net.subaraki.gravestone.common.network.S00PacketSyncGraveData;
import net.subaraki.gravestone.handler.ConfigHandler;
import net.subaraki.gravestone.handler.GravestoneEventHandler;
import net.subaraki.gravestone.handler.GuiHandler;
import net.subaraki.gravestone.handler.RecipeHandler;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.integration.adventurebackpack.AdventureBackpackIntegration;
import net.subaraki.gravestone.integration.aether.AetherIntegration;
import net.subaraki.gravestone.integration.battlegear.BattlegearIntegration;
import net.subaraki.gravestone.integration.baubles.BaublesIntegration;
import net.subaraki.gravestone.integration.cosmeticarmor.CosmeticArmorIntegration;
import net.subaraki.gravestone.integration.galacticraft.GalacticraftIntegration;
import net.subaraki.gravestone.integration.mariculture.MaricultureIntegration;
import net.subaraki.gravestone.integration.rpginventory.RPGInventoryIntegration;
import net.subaraki.gravestone.integration.satchels.SatchelsIntegration;
import net.subaraki.gravestone.integration.sextiarysextor.SextiarySectorIntegration;
import net.subaraki.gravestone.integration.tconstruct.TinkersConstructIntegration;
import net.subaraki.gravestone.integration.travellersgear.TravellersGearIntegration;
import net.subaraki.gravestone.item.ItemDecoGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import net.subaraki.gravestone.util.Constants;
import net.subaraki.gravestone.util.GraveUtility;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "gravestonemod")
public class GraveStones {

    @SidedProxy(
        serverSide = "net.subaraki.gravestone.common.CommonProxy",
        clientSide = "net.subaraki.gravestone.client.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance("gravestonemod")
    public static GraveStones instance;

    public SimpleNetworkWrapper network;

    public static Block graveStone;

    public static boolean hasTiC = false;
    public static boolean hasRpgI = false;
    public static boolean hasBaubles = false;
    public static boolean hasThaumcraft = false;
    public static boolean hasGalacticraft = false;
    public static boolean hasMariculture = false;
    public static boolean hasCosmeticArmor = false;
    public static boolean hasSatchels = false;
    public static boolean hasAether = false;
    public static boolean hasBattlegear = false;
    public static boolean hasTravellersGear = false;
    public static boolean hasSextiarySector = false;
    public static boolean hasAdventureBackpack = false;

    public static List<ModIntegration> integrations = new ArrayList<ModIntegration>();

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        new GraveUtility();
        this.network = NetworkRegistry.INSTANCE.newSimpleChannel("gravestones");
        this.network
            .registerMessage(C00PacketSyncGraveData.Handler.class, C00PacketSyncGraveData.class, 0, Side.SERVER);
        this.network
            .registerMessage(S00PacketSyncGraveData.Handler.class, S00PacketSyncGraveData.class, 1, Side.CLIENT);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        ConfigHandler.instance.loadConfig(event.getSuggestedConfigurationFile());

        GameRegistry.registerTileEntity(TileEntityGravestone.class, "TileEntityGraveStone");

        GraveStones.graveStone = new BlockGrave(Material.rock).setBlockName("gravestone")
            .setCreativeTab(CreativeTabs.tabDecorations);
        GameRegistry.registerBlock(GraveStones.graveStone, ItemDecoGrave.class, "graveStone");

        RecipeHandler.registerBlockRecipe();

        GraveStones.proxy.preInit();
    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        if (Loader.isModLoaded("rpginventorymod")) {
            GraveStones.hasRpgI = true;
            integrations.add(new RPGInventoryIntegration());
        }
        if (Loader.isModLoaded("TConstruct")) {
            GraveStones.hasTiC = true;
            integrations.add(new TinkersConstructIntegration());
        }
        if (Loader.isModLoaded("Baubles")) {
            GraveStones.hasBaubles = true;
            integrations.add(new BaublesIntegration());
        }
        GraveStones.hasThaumcraft = Loader.isModLoaded("Thaumcraft");
        if (Loader.isModLoaded("GalacticraftCore")) {
            GraveStones.hasGalacticraft = true;
            integrations.add(new GalacticraftIntegration());
        }
        if (Loader.isModLoaded("Mariculture")) {
            GraveStones.hasMariculture = true;
            integrations.add(new MaricultureIntegration());
        }
        if (Loader.isModLoaded("cosmeticarmorreworked")) {
            GraveStones.hasCosmeticArmor = true;
            integrations.add(new CosmeticArmorIntegration());
        }
        if (Loader.isModLoaded("satchels")) {
            GraveStones.hasSatchels = true;
            integrations.add(new SatchelsIntegration());
        }
        if (Loader.isModLoaded("aether_legacy")) {
            GraveStones.hasAether = true;
            integrations.add(new AetherIntegration());
        }
        if (Loader.isModLoaded("battlegear2")) {
            GraveStones.hasBattlegear = true;
            integrations.add(new BattlegearIntegration());
        }
        if (Loader.isModLoaded("TravellersGear")) {
            GraveStones.hasTravellersGear = true;
            integrations.add(new TravellersGearIntegration());
        }
        if (Loader.isModLoaded("SextiarySector")) {
            GraveStones.hasSextiarySector = true;
            integrations.add(new SextiarySectorIntegration());
        }
        if (Loader.isModLoaded("adventurebackpack")) {
            GraveStones.hasAdventureBackpack = true;
            integrations.add(new AdventureBackpackIntegration());
        }
    }

    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        new GravestoneEventHandler();
    }

    public static void printDebugMessage(final String message) {
        if (ConfigHandler.allowDebug) {
            Constants.LOGGER.info(message);
        }
    }
}
