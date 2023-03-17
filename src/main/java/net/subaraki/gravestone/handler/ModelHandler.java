



package net.subaraki.gravestone.handler;

import net.subaraki.gravestone.client.model.*;
import net.subaraki.gravestone.util.*;

public class ModelHandler
{
    public static final ModelAngel angel;
    public static final ModelGraveSkeleton skeleton;
    public static final ModelGraveStone gravestone;
    public static final ModelKnight knight;
    public static final ModelPillar pillar;
    public static final ModelStoneCross cross;
    public static final ModelTomb tomb;
    public static final ModelWoodenGrave wood;
    public static ModelHead modelhead;
    public static ModelHead modelarmorhead;
    public static ModelHead modelarmorchest;
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
        ModelHandler.modelhead = new ModelHead();
        ModelHandler.modelarmorhead = new ModelHead();
        ModelHandler.modelarmorchest = new ModelHead();
        helper = new GraveArmorHandler();
    }
}
