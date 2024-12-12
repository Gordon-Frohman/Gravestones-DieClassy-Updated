
package net.subaraki.gravestone.handler;

import net.minecraft.util.ResourceLocation;

public class TextureHandler {

    private static final String path = "grave:textures/entity/tile/";
    private static final ResourceLocation graveTexture;
    private static final ResourceLocation tombTexture;
    private static final ResourceLocation pillarTexture;
    private static final ResourceLocation woodTexture;
    private static final ResourceLocation knightTexture;

    public static ResourceLocation getTextureFromMeta(final int meta) {
        ResourceLocation resourcelocation = null;
        switch (meta) {
            case 1: {
                resourcelocation = TextureHandler.graveTexture;
                break;
            }
            case 2: {
                resourcelocation = TextureHandler.tombTexture;
                break;
            }
            case 3: {
                resourcelocation = TextureHandler.graveTexture;
                break;
            }
            case 4: {
                resourcelocation = TextureHandler.pillarTexture;
                break;
            }
            case 5: {
                resourcelocation = TextureHandler.pillarTexture;
                break;
            }
            case 6: {
                resourcelocation = TextureHandler.woodTexture;
                break;
            }
            case 7: {
                resourcelocation = TextureHandler.pillarTexture;
                break;
            }
            case 9: {
                resourcelocation = TextureHandler.knightTexture;
                break;
            }
            default: {
                resourcelocation = TextureHandler.graveTexture;
                break;
            }
        }
        return resourcelocation;
    }

    static {
        graveTexture = new ResourceLocation("grave:textures/entity/tile/grave_stone.png");
        tombTexture = new ResourceLocation("grave:textures/entity/tile/grave_zerk.png");
        pillarTexture = new ResourceLocation("grave:textures/entity/tile/grave_pillar.png");
        woodTexture = new ResourceLocation("grave:textures/entity/tile/grave_wood.png");
        knightTexture = new ResourceLocation("grave:textures/entity/tile/grave_knight.png");
    }
}
