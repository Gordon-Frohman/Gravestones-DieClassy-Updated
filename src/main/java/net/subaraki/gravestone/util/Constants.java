
package net.subaraki.gravestone.util;

import net.minecraft.init.*;
import net.minecraft.item.*;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.client.model.*;
import net.subaraki.gravestone.tileentity.*;

import org.apache.logging.log4j.*;

import tconstruct.armor.TinkerArmor;

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
    public static final ItemStack ICON_TCON;
    public static final ItemStack ICON_RPGI;
    public static final ItemStack ICON_BAUBLES;
    public static final ItemStack ICON_GALACTICRAFT;
    public static final ItemStack ICON_MARICULTURE;
    public static final byte VANILLA = 0;
    public static final byte RPGI = 1;
    public static final byte TC = 2;
    public static final byte BAUBEL = 3;
    public static final byte GALACTICRAFT = 4;
    public static final byte MARICULTURE = 5;
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
        ICON_TCON = GraveStones.hasTiCo ? new ItemStack((Item) TinkerArmor.knapsack)
            : new ItemStack((Item) Items.diamond_chestplate);
        ICON_RPGI = new ItemStack((Item) Items.golden_chestplate);
        ICON_BAUBLES = new ItemStack(Items.gold_ingot);
        ICON_GALACTICRAFT = new ItemStack(Blocks.glass);
        ICON_MARICULTURE = new ItemStack(Items.fish);
        GRAVE_PLACEHOLDER = new TileEntityGravestone();
    }
}
