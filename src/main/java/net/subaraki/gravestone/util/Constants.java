
package net.subaraki.gravestone.util;

import net.minecraft.init.Items;
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

    public static final Logger LOGGER = LogManager.getLogger("Gravestones");

    public static final ModelAngel MODEL_ANGEL = new ModelAngel();
    public static final ModelGraveSkeleton MODEL_SKELETON = new ModelGraveSkeleton();
    public static final ModelGraveStone MODEL_GRAVESTONE = new ModelGraveStone();
    public static final ModelKnight MODEL_KNIGHT = new ModelKnight();
    public static final ModelPillar MODEL_PILLAR = new ModelPillar();
    public static final ModelStoneCross MODEL_CROSS = new ModelStoneCross();
    public static final ModelTomb MODEL_TOMB = new ModelTomb();
    public static final ModelWoodenGrave MODEL_WOOD = new ModelWoodenGrave();

    public static ModelCubeWorld angelStatue;
    public static ModelCubeWorld barrel;

    public static final ItemStack ICON_VANILLA = new ItemStack(Items.iron_sword);
    public static final ItemStack ICON_MISC = new ItemStack(Items.lava_bucket);

    public static final TileEntityGravestone GRAVE_PLACEHOLDER = new TileEntityGravestone();

    static {
        GRAVE_PLACEHOLDER.isDecorative = true;
    }
}
