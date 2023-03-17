



package net.subaraki.gravestone;

import net.subaraki.gravestone.common.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import net.minecraft.block.*;
import cpw.mods.fml.relauncher.*;
import net.subaraki.gravestone.common.network.*;
import cpw.mods.fml.common.network.*;
import net.minecraft.block.material.*;
import net.subaraki.gravestone.block.*;
import net.minecraft.creativetab.*;
import net.subaraki.gravestone.tileentity.*;
import cpw.mods.fml.common.registry.*;
import net.subaraki.gravestone.item.*;
import net.subaraki.gravestone.handler.*;
import cpw.mods.fml.common.event.*;
import net.subaraki.gravestone.util.*;

@Mod(modid = "gravestonemod", name = "Gravestones", version = "1.7.10")
public class GraveStones
{
    @SidedProxy(serverSide = "net.subaraki.gravestone.common.CommonProxy", clientSide = "net.subaraki.gravestone.client.ClientProxy")
    public static CommonProxy proxy;
    @Mod.Instance("gravestonemod")
    public static GraveStones instance;
    public SimpleNetworkWrapper network;
    public static Block graveStone;
    public static boolean hasTiCo;
    public static boolean hasRpgI;
    public static boolean hasBaub;
    public static boolean hasGal_Craft;
    public static boolean hasMari_Cul;
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        new GraveUtility();
        (this.network = NetworkRegistry.INSTANCE.newSimpleChannel("gravestones")).registerMessage((Class)PacketSyncGraveModel.PacketSyncGraveModelHandler.class, (Class)PacketSyncGraveModel.class, 0, Side.SERVER);
        this.network.registerMessage((Class)PacketSwitchSlotLayout.PacketSwitchSlotLayoutHandler.class, (Class)PacketSwitchSlotLayout.class, 1, Side.SERVER);
        this.network.registerMessage((Class)PacketSyncModelToClient.PacketSyncModelToClientHandler.class, (Class)PacketSyncModelToClient.class, 2, Side.CLIENT);
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)this, (IGuiHandler)new GuiHandler());
        ConfigHandler.instance.loadConfig(event.getSuggestedConfigurationFile());
        GraveStones.graveStone = new BlockGrave(Material.rock).setBlockName("gravestone").setCreativeTab(CreativeTabs.tabDecorations);
        GameRegistry.registerTileEntity((Class)TileEntityGravestone.class, "TileEntityGraveStone");
        GameRegistry.registerBlock(GraveStones.graveStone, (Class)ItemDecoGrave.class, "graveStone");
        RecipeHandler.registerBlockRecipe();
        new GravestoneEventHandler();
        GraveStones.proxy.preInit();
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        GraveStones.hasRpgI = GraveUtility.findClass("rpgInventory.RpgInventoryMod", "Rpg Inventory");
        GraveStones.hasTiCo = GraveUtility.findClass("tconstruct.TConstruct", "Tinkers Construct");
        GraveStones.hasBaub = GraveUtility.findClass("baubles.common.Baubles", "Baubel Inventory");
        GraveStones.hasGal_Craft = GraveUtility.findClass("micdoodle8.mods.galacticraft.core.GalacticraftCore", "GalacticCraft");
        GraveStones.hasMari_Cul = GraveUtility.findClass("mariculture.Mariculture", "Mariculture");
    }
    
    public static void printDebugMessage(final String message) {
        if (ConfigHandler.allowDebug) {
            Constants.LOG.info(message);
        }
    }
    
    static {
        GraveStones.hasTiCo = false;
        GraveStones.hasRpgI = false;
        GraveStones.hasBaub = false;
        GraveStones.hasGal_Craft = false;
        GraveStones.hasMari_Cul = false;
    }
}
