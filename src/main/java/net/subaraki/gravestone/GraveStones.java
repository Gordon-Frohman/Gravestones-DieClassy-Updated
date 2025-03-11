
package net.subaraki.gravestone;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.block.BlockGrave;
import net.subaraki.gravestone.common.CommonProxy;
import net.subaraki.gravestone.common.network.PacketSwitchSlotLayout;
import net.subaraki.gravestone.common.network.PacketSyncGraveModel;
import net.subaraki.gravestone.common.network.PacketSyncModelToClient;
import net.subaraki.gravestone.handler.ConfigHandler;
import net.subaraki.gravestone.handler.GravestoneEventHandler;
import net.subaraki.gravestone.handler.GuiHandler;
import net.subaraki.gravestone.handler.RecipeHandler;
import net.subaraki.gravestone.integration.AetherIntegration;
import net.subaraki.gravestone.integration.BattlegearIntegration;
import net.subaraki.gravestone.integration.GalacticraftIntegration;
import net.subaraki.gravestone.integration.MaricultureIntegration;
import net.subaraki.gravestone.integration.SatchelsIntegration;
import net.subaraki.gravestone.integration.SextiarySectorIntegration;
import net.subaraki.gravestone.integration.ThaumcraftIntegration;
import net.subaraki.gravestone.integration.TinkersConstructIntegration;
import net.subaraki.gravestone.integration.TravellersGearIntegration;
import net.subaraki.gravestone.item.ItemDecoGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import net.subaraki.gravestone.util.Constants;
import net.subaraki.gravestone.util.GraveUtility;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
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
    public static boolean hasTiC;
    public static boolean hasRpgI;
    public static boolean hasBaubles;
    public static boolean hasThaumcraft;
    public static boolean hasGalacticraft;
    public static boolean hasMariculture;
    public static boolean hasCosmeticArmor;
    public static boolean hasSatchels;
    public static boolean hasAether;
    public static boolean hasBattlegear;
    public static boolean hasTravellersGear;
    public static boolean hasSextiarySector;

    public static Map<Integer, String> inventories = new HashMap<Integer, String>();
    public static Map<Integer, Integer> inventorySizes = new HashMap<Integer, Integer>();

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        new GraveUtility();
        (this.network = NetworkRegistry.INSTANCE.newSimpleChannel("gravestones")).registerMessage(
            (Class) PacketSyncGraveModel.PacketSyncGraveModelHandler.class,
            (Class) PacketSyncGraveModel.class,
            0,
            Side.SERVER);
        this.network.registerMessage(
            (Class) PacketSwitchSlotLayout.PacketSwitchSlotLayoutHandler.class,
            (Class) PacketSwitchSlotLayout.class,
            1,
            Side.SERVER);
        this.network.registerMessage(
            (Class) PacketSyncModelToClient.PacketSyncModelToClientHandler.class,
            (Class) PacketSyncModelToClient.class,
            2,
            Side.CLIENT);
        NetworkRegistry.INSTANCE.registerGuiHandler((Object) this, (IGuiHandler) new GuiHandler());
        ConfigHandler.instance.loadConfig(event.getSuggestedConfigurationFile());
        GraveStones.graveStone = new BlockGrave(Material.rock).setBlockName("gravestone")
            .setCreativeTab(CreativeTabs.tabDecorations);
        GameRegistry.registerTileEntity((Class) TileEntityGravestone.class, "TileEntityGraveStone");
        GameRegistry.registerBlock(GraveStones.graveStone, (Class) ItemDecoGrave.class, "graveStone");
        RecipeHandler.registerBlockRecipe();
        new GravestoneEventHandler();
        GraveStones.proxy.preInit();
    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        GraveStones.hasRpgI = GraveUtility.findClass("rpgInventory.RpgInventoryMod", "RPG Inventory");
        GraveStones.hasTiC = GraveUtility.findClass("tconstruct.TConstruct", "Tinkers Construct");
        GraveStones.hasBaubles = GraveUtility.findClass("baubles.common.Baubles", "Baubles");
        GraveStones.hasThaumcraft = GraveUtility.findClass("thaumcraft.common.Thaumcraft", "Thaumcraft");
        GraveStones.hasGalacticraft = GraveUtility
            .findClass("micdoodle8.mods.galacticraft.core.GalacticraftCore", "Galacticraft");
        GraveStones.hasMariculture = GraveUtility.findClass("mariculture.Mariculture", "Mariculture");
        GraveStones.hasCosmeticArmor = GraveUtility
            .findClass("lain.mods.cos.CosmeticArmorReworked", "Cosmetic Armor Reworked");
        GraveStones.hasSatchels = GraveUtility.findClass("makamys.satchels.Satchels", "Satchels");
        GraveStones.hasAether = GraveUtility.findClass("com.gildedgames.the_aether.Aether", "Aether");
        GraveStones.hasBattlegear = GraveUtility.findClass("mods.battlegear2.Battlegear", "Mine & Blade: Battlegear 2");
        GraveStones.hasTravellersGear = GraveUtility.findClass("travellersgear.TravellersGear", "Traveller's Gear");
        GraveStones.hasSextiarySector = GraveUtility
            .findClass("shift.sextiarysector.SextiarySector", "Sextiary Sector 2");
    }

    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        Constants.ICON_TCON = TinkersConstructIntegration.getModIcon();
        Constants.ICON_BAUBLES = ThaumcraftIntegration.getModIcon(); // Yes, that's correct
        Constants.ICON_GALACTICRAFT = GalacticraftIntegration.getModIcon();
        Constants.ICON_MARICULTURE = MaricultureIntegration.getModIcon();
        Constants.ICON_SATCHELS = SatchelsIntegration.getModIcon();
        Constants.ICON_AETHER = AetherIntegration.getModIcon();
        Constants.ICON_BATTLEGEAR = BattlegearIntegration.getModIcon();
        Constants.ICON_TRAVELLERS_GEAR = GraveStones.hasTravellersGear ? TravellersGearIntegration.getModIcon()
            : new ItemStack(Items.name_tag);
        Constants.ICON_SEXTIARY_SECTOR = SextiarySectorIntegration.getModIcon();
    }

    public static void printDebugMessage(final String message) {
        if (ConfigHandler.allowDebug) {
            Constants.LOG.info(message);
        }
    }

    private static void registerInventory(int id, String inventoryName, int size) {
        inventories.put(id, inventoryName);
        inventorySizes.put(id, size);
        TileEntityGravestone.listSize += size;
    }

    public static int getPrevInventoriesSize(int invId) {
        int size = 0;
        for (int i = 0; i < invId; i++) {
            Integer inventorySize = GraveStones.inventorySizes.get(i);
            size += inventorySize == null ? 0 : inventorySize;
        }
        return size;
    }

    static {
        GraveStones.hasTiC = false;
        GraveStones.hasRpgI = false;
        GraveStones.hasBaubles = false;
        GraveStones.hasThaumcraft = false;
        GraveStones.hasGalacticraft = false;
        GraveStones.hasMariculture = false;
        GraveStones.hasCosmeticArmor = false;
        GraveStones.hasSatchels = false;
        GraveStones.hasAether = false;
        GraveStones.hasBattlegear = false;
        GraveStones.hasTravellersGear = false;
        GraveStones.hasSextiarySector = false;
        registerInventory(Constants.VANILLA, "Minecraft", 40);
        registerInventory(Constants.RPGI, "RPG Inventory", 7);
        registerInventory(Constants.TC, "Tinkers Construct", 34);
        registerInventory(Constants.BAUBLES, "Baubles", 4);
        registerInventory(Constants.GALACTICRAFT, "Galacticraft", 10);
        registerInventory(Constants.MARICULTURE, "Mariculture", 3);
        registerInventory(Constants.COSMETIC_ARMOR, "Cosmetic Armor", 4);
        registerInventory(Constants.SATCHELS, "Satchels", 3);
        registerInventory(Constants.AETHER, "Aether", 8);
        registerInventory(Constants.BATTLEGEAR, "Battlegear", 18);
        registerInventory(Constants.TRAVELLERS_GEAR, "Traveller's Gear", 4);
        registerInventory(Constants.SEXTIARY_SECTOR, "Sextiary Sector", 20);
    }
}
