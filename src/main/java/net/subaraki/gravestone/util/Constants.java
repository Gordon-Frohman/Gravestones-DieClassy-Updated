
package net.subaraki.gravestone.util;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.subaraki.gravestone.client.model.ModelAngel;
import net.subaraki.gravestone.client.model.ModelCubeWorld;
import net.subaraki.gravestone.client.model.ModelGraveSkeleton;
import net.subaraki.gravestone.client.model.ModelGraveStone;
import net.subaraki.gravestone.client.model.ModelKnight;
import net.subaraki.gravestone.client.model.ModelPillar;
import net.subaraki.gravestone.client.model.ModelStoneCross;
import net.subaraki.gravestone.client.model.ModelTomb;
import net.subaraki.gravestone.client.model.ModelWoodenGrave;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {

    public static final String MODID = "gravestonemod";
    public static final String MOD_NAME = "Gravestones";
    public static final String VERSION = "1.7.10";
    public static final String SERVER = "net.subaraki.gravestone.common.CommonProxy";
    public static final String CLIENT = "net.subaraki.gravestone.client.ClientProxy";
    public static final String FACTORY = "net.subaraki.gravestone.client.gui.GraveGuiFactory";
    public static final String DEPENDENCY = "";
    public static final Logger LOG;
    public static final ModelAngel MODEL_ANGEL;
    public static final ModelGraveSkeleton MODEL_SKELETON;
    public static final ModelGraveStone MODEL_GRAVESTONE;
    public static final ModelKnight MODEL_KNIGHT;
    public static final ModelPillar MODEL_PILLAR;
    public static final ModelStoneCross MODEL_CROSS;
    public static final ModelTomb MODEL_TOMB;
    public static final ModelWoodenGrave MODEL_WOOD;
    public static ModelCubeWorld angelStatue;
    public static ModelCubeWorld barrel;
    public static final ItemStack ICON_VANILLA;
    public static ItemStack ICON_TCON;
    public static ItemStack ICON_RPGI;
    public static ItemStack ICON_BAUBLES;
    public static ItemStack ICON_GALACTICRAFT;
    public static ItemStack ICON_MARICULTURE;
    public static ItemStack ICON_COSMETIC_ARMOR;
    public static ItemStack ICON_SATCHELS;
    public static ItemStack ICON_AETHER;
    public static ItemStack ICON_BATTLEGEAR;
    public static ItemStack ICON_TRAVELLERS_GEAR;
    public static ItemStack ICON_SEXTIARY_SECTOR;
    public static final byte VANILLA = 0;
    public static final byte RPGI = 1;
    public static final byte TC = 2;
    public static final byte BAUBLES = 3;
    public static final byte GALACTICRAFT = 4;
    public static final byte MARICULTURE = 5;
    public static final byte COSMETIC_ARMOR = 6;
    public static final byte SATCHELS = 7;
    public static final byte AETHER = 8;
    public static final byte BATTLEGEAR = 9;
    public static final byte TRAVELLERS_GEAR = 10;
    public static final byte SEXTIARY_SECTOR = 11;
    public static final TileEntityGravestone GRAVE_PLACEHOLDER;

    static {
        LOG = LogManager.getLogger("Gravestones");
        MODEL_ANGEL = new ModelAngel();
        MODEL_SKELETON = new ModelGraveSkeleton();
        MODEL_GRAVESTONE = new ModelGraveStone();
        MODEL_KNIGHT = new ModelKnight();
        MODEL_PILLAR = new ModelPillar();
        MODEL_CROSS = new ModelStoneCross();
        MODEL_TOMB = new ModelTomb();
        MODEL_WOOD = new ModelWoodenGrave();
        ICON_VANILLA = new ItemStack(Items.iron_sword);
        ICON_TCON = new ItemStack((Item) Items.diamond_chestplate);
        ICON_RPGI = new ItemStack((Item) Items.golden_chestplate);
        ICON_BAUBLES = new ItemStack(Items.gold_ingot);
        ICON_GALACTICRAFT = new ItemStack(Blocks.glass);
        ICON_MARICULTURE = new ItemStack(Items.fish);
        ICON_COSMETIC_ARMOR = new ItemStack(Items.leather_chestplate);
        ICON_SATCHELS = new ItemStack(Items.leather);
        ICON_AETHER = new ItemStack(Items.feather);
        ICON_BATTLEGEAR = new ItemStack(Items.iron_chestplate);
        ICON_TRAVELLERS_GEAR = new ItemStack(Items.name_tag);
        ICON_SEXTIARY_SECTOR = new ItemStack(Items.golden_hoe);
        GRAVE_PLACEHOLDER = new TileEntityGravestone();
        GRAVE_PLACEHOLDER.isDecorativeGrave = true;
    }
}
