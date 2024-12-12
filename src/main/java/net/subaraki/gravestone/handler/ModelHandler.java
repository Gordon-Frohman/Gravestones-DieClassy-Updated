
package net.subaraki.gravestone.handler;

import net.minecraft.client.model.ModelBiped;
import net.subaraki.gravestone.client.model.ModelAngel;
import net.subaraki.gravestone.client.model.ModelBust;
import net.subaraki.gravestone.client.model.ModelGraveSkeleton;
import net.subaraki.gravestone.client.model.ModelGraveStone;
import net.subaraki.gravestone.client.model.ModelKnight;
import net.subaraki.gravestone.client.model.ModelPillar;
import net.subaraki.gravestone.client.model.ModelStoneCross;
import net.subaraki.gravestone.client.model.ModelTomb;
import net.subaraki.gravestone.client.model.ModelWoodenGrave;
import net.subaraki.gravestone.util.Constants;

public class ModelHandler {

    public static final ModelAngel angel;
    public static final ModelGraveSkeleton skeleton;
    public static final ModelGraveStone gravestone;
    public static final ModelKnight knight;
    public static final ModelPillar pillar;
    public static final ModelStoneCross cross;
    public static final ModelTomb tomb;
    public static final ModelWoodenGrave wood;
    public static ModelBust modelBust;
    public static ModelBiped modelArmorHead;
    public static ModelBiped modelArmorChest;
    public static final GraveArmorHandler helper;

    public static void renderModelFromType(final int modelType) {
        switch (modelType) {
            case 1: {
                ModelHandler.cross.render(0.0625f);
                break;
            }
            case 2: {
                ModelHandler.gravestone.render(0.0625f);
                break;
            }
            case 3: {
                ModelHandler.tomb.render(0.0625f);
                break;
            }
            case 4: {
                ModelHandler.pillar.render(0.0625f);
                ModelHandler.skeleton.render(0.0625f);
                break;
            }
            case 5: {
                ModelHandler.pillar.render(0.0625f);
                break;
            }
            case 6: {
                ModelHandler.wood.render(0.0625f);
            }
            case 7: {
                ModelHandler.pillar.render(0.0625f);
                break;
            }
            case 8: {
                Constants.angelStatue.render();
                break;
            }
            case 9: {
                ModelHandler.knight.render(0.0625f);
                break;
            }
            case 10: {
                Constants.barrel.render();
                break;
            }
            default: {
                ModelHandler.cross.render(0.0625f);
                break;
            }
        }
    }

    static {
        angel = new ModelAngel();
        skeleton = new ModelGraveSkeleton();
        gravestone = new ModelGraveStone();
        knight = new ModelKnight();
        pillar = new ModelPillar();
        cross = new ModelStoneCross();
        tomb = new ModelTomb();
        wood = new ModelWoodenGrave();
        ModelHandler.modelBust = new ModelBust();
        ModelHandler.modelArmorHead = new ModelBust();
        ModelHandler.modelArmorChest = new ModelBust();
        helper = new GraveArmorHandler();
    }
}
