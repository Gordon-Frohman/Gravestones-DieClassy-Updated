
package net.subaraki.gravestone.client.renderer;

import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.subaraki.gravestone.client.model.ModelBust;
import net.subaraki.gravestone.handler.ModelHandler;
import net.subaraki.gravestone.handler.TextureHandler;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

public class TileEntitySpecialRendererGrave extends TileEntitySpecialRenderer {

    ResourceLocation texture;

    public static boolean inventoryRender = false;

    public TileEntitySpecialRendererGrave() {
        this.texture = null;
    }

    public void renderTileEntityAt(final TileEntity te, final double x, final double y, final double z, final float f) {
        final TileEntityGravestone tile = (TileEntityGravestone) te;
        float rot = tile.ModelRotation;
        final int modeltype = tile.modelType;
        this.renderBeam(tile, x, y, z);
        GL11.glPushMatrix();
        this.bindTexture(TextureHandler.getTextureFromMeta(modeltype));
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        GL11.glScalef(1.0f, -1.0f, -1.0f);
        if (modeltype == 2 || modeltype == 3 || modeltype == 6 || modeltype == 8 || modeltype == 9 || modeltype == 5) {
            rot -= 90.0f;
        }
        if (modeltype == 4) {
            rot = 90.0f;
        }
        final float s = -0.75f;
        final float s2 = -0.4f;
        if (modeltype == 8) {
            GL11.glScalef(0.75f, 0.75f, 0.75f);
            GL11.glTranslatef(-0.75f, 2.0f, 0.75f);
            GL11.glTranslatef(-s, 0.0f, s);
            GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
        }
        if (modeltype == 10) {
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GL11.glTranslatef(-0.4f, 1.5f, 0.4f);
            GL11.glTranslatef(-s2, 0.0f, s2);
            GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
        }
        GL11.glRotatef(rot, 0.0f, 1.0f, 0.0f);
        if (modeltype == 8) {
            GL11.glTranslatef(s, 0.0f, -s);
        }
        if (modeltype == 10) {
            GL11.glTranslatef(s2, 0.0f, -s2);
        }
        ModelHandler.renderModelFromType(modeltype);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        GL11.glScalef(1.0f, -1.0f, -1.0f);
        final float sc = 0.75f;
        GL11.glScalef(sc, sc, sc);
        if (modeltype == 5) {
            /*
             * if(tile.skinLocation == null)
             * tile.skinLocation = GraveUtility.instance.processPlayerTexture(tile.playername);
             * this.bindTexture(tile.skinLocation);
             */

            ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;

            if (tile.profile != null) {
                Minecraft minecraft = Minecraft.getMinecraft();
                Map map = minecraft.func_152342_ad()
                    .func_152788_a(tile.profile);

                if (map.containsKey(Type.SKIN)) {
                    resourcelocation = minecraft.func_152342_ad()
                        .func_152792_a((MinecraftProfileTexture) map.get(Type.SKIN), Type.SKIN);
                }
            }

            this.bindTexture(resourcelocation);

            GL11.glRotatef(rot, 0.0f, 1.0f, 0.0f);
            if (inventoryRender) {
                ModelHandler.modelBust.renderInventoryBust(0.0625f);
            } else {
                ModelHandler.modelBust.renderBust(0.0625f);
            }
            if (tile.getStackInSlot(tile.getSizeInventory() - 1) != null
                && tile.getStackInSlot(tile.getSizeInventory() - 1)
                    .getItem() instanceof ItemArmor) {
                GL11.glPushMatrix();
                final float f2 = 1.2f;
                GL11.glScalef(f2, f2, f2);
                GL11.glTranslatef(0.0f, 0.05f, 0.0f);
                GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
                final ItemStack item = tile.getStackInSlot(tile.getSizeInventory() - 1);
                ItemArmor itemArmor = (ItemArmor) item.getItem();
                // We're creating a zombie because some mods require actual entities to check for armor
                ModelBiped modelArmor = itemArmor
                    .getArmorModel(new EntityZombie(te.getWorldObj()), item, itemArmor.armorType);
                ModelHandler.modelArmorHead = modelArmor == null ? new ModelBust() : modelArmor;
                ModelHandler.helper.setArmorModel(
                    (ModelBiped) ModelHandler.modelArmorHead,
                    item,
                    ((ItemArmor) item.getItem()).armorType,
                    RenderBiped.bipedArmorFilenamePrefix[((ItemArmor) item.getItem()).renderIndex]);
                ModelHandler.modelArmorHead.bipedHead.render(0.0625f);
                ModelHandler.modelArmorHead.bipedHeadwear.render(0.0625f);
                GL11.glPopMatrix();
            }
            if (tile.getStackInSlot(tile.getSizeInventory() - 2) != null
                && tile.getStackInSlot(tile.getSizeInventory() - 2)
                    .getItem() instanceof ItemArmor) {
                GL11.glPushMatrix();
                final float f2 = 1.1f;
                GL11.glScalef(f2, f2, f2);
                GL11.glTranslatef(0.0f, -0.02f, 0.0f);
                GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
                final ItemStack item = tile.getStackInSlot(tile.getSizeInventory() - 2);
                ItemArmor itemArmor = (ItemArmor) item.getItem();
                // We're creating a zombie because some mods require actual entities to check for armor
                ModelBiped modelArmor = itemArmor
                    .getArmorModel(new EntityZombie(te.getWorldObj()), item, itemArmor.armorType);
                if (modelArmor == null) {
                    ModelHandler.modelArmorChest = new ModelBust();
                } else {
                    ModelHandler.modelArmorChest = modelArmor;
                    // This stuff could look very nice, but since I'm unable to copy original armor models, it is
                    // worthless
                    /*
                     * recursivelyCutModel(6, ModelHandler.modelArmorChest.bipedBody);
                     * recursivelyCutModel(6, ModelHandler.modelArmorChest.bipedLeftArm);
                     * recursivelyCutModel(6, ModelHandler.modelArmorChest.bipedRightArm);
                     */
                }
                ModelHandler.helper.setArmorModel(
                    (ModelBiped) ModelHandler.modelArmorChest,
                    item,
                    ((ItemArmor) item.getItem()).armorType,
                    RenderBiped.bipedArmorFilenamePrefix[((ItemArmor) item.getItem()).renderIndex]);
                ModelHandler.modelArmorChest.bipedBody.render(0.0625f);
                ModelHandler.modelArmorChest.bipedLeftArm.render(0.0625f);
                ModelHandler.modelArmorChest.bipedRightArm.render(0.0625f);
                GL11.glPopMatrix();
            }
        }
        GL11.glPopMatrix();
    }

    private void recursivelyCutModel(int treshold, ModelRenderer model) {
        Object[] boxes = model.cubeList.toArray();
        for (Object boxObject : boxes) {
            ModelBox box = (ModelBox) boxObject;
            if (box.posY1 + model.rotationPointY > treshold) {
                model.cubeList.remove(box);
            } else {
                if (box.posY2 + model.rotationPointY > treshold) {
                    ModelBox newBox = new ModelBox(
                        model,
                        model.textureOffsetX,
                        model.textureOffsetY,
                        box.posX1,
                        box.posY1,
                        box.posZ1,
                        (int) (box.posX2 - box.posX1),
                        (int) (6 - box.posY1),
                        (int) (box.posZ2 - box.posZ1),
                        0.0F);
                    model.cubeList.add(newBox);
                    model.cubeList.remove(box);
                }
            }
        }
        if (model.childModels != null) {
            Object[] childModels = model.childModels.toArray();
            for (Object childModelObject : childModels) {
                ModelRenderer childModel = (ModelRenderer) childModelObject;
                recursivelyCutModel(treshold, childModel);
            }
        }
    }

    private void renderBeam(final TileEntityGravestone tileentity, final double d, final double d1, final double d2) {
        if (tileentity.hasItems) {
            final Tessellator tesselator = Tessellator.instance;
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDisable(2912);
            GL11.glDepthMask(false);
            GL11.glDisable(2884);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 1);
            final char var5 = '\u00f0';
            final int var6 = var5 % 65536;
            final int var7 = var5 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6 / 1.0f, var7 / 1.0f);
            final int height = 256;
            final double topWidthFactor = 0.5;
            final double bottomWidthFactor = 0.5;
            final boolean other = !tileentity.playername
                .equals(Minecraft.getMinecraft().thePlayer.getCommandSenderName());
            final float otherFloat = 0.0f;
            final float color = (other && !Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) ? 0.0f
                : 0.7f;
            for (int width = 0; width < (other ? 2 : 5); ++width) {
                tesselator.startDrawing(5);
                tesselator.setColorRGBA_F(color - otherFloat, color - otherFloat, color, 0.11f);
                double var8 = 0.1 + width * 0.2;
                var8 *= topWidthFactor;
                double var9 = 0.1 + width * 0.2;
                var9 *= bottomWidthFactor;
                for (int side = 0; side < 5; ++side) {
                    double vertX2 = d + 0.5 - var8;
                    double vertZ2 = d2 + 0.5 - var8;
                    if (side == 1 || side == 2) {
                        vertX2 += var8 * 2.0;
                    }
                    if (side == 2 || side == 3) {
                        vertZ2 += var8 * 2.0;
                    }
                    double vertX3 = d + 0.5 - var9;
                    double vertZ3 = d2 + 0.5 - var9;
                    if (side == 1 || side == 2) {
                        vertX3 += var9 * 2.0;
                    }
                    if (side == 2 || side == 3) {
                        vertZ3 += var9 * 2.0;
                    }
                    tesselator.addVertex(vertX3, d1 + 0.0, vertZ3);
                    tesselator.addVertex(vertX2, d1 + height, vertZ2);
                }
                tesselator.draw();
            }
            GL11.glDisable(3042);
            GL11.glEnable(2912);
            GL11.glEnable(2896);
            GL11.glEnable(3553);
            GL11.glDepthMask(true);
        }
    }
}
