
package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.*;

public class ModelHead extends ModelBiped {

    public ModelHead() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        (this.bipedBody = new ModelRenderer((ModelBase) this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
        this.bipedBody.setRotationPoint(0.0f, 0.0f, 0.0f);
        (this.bipedRightArm = new ModelRenderer((ModelBase) this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 6, 4);
        this.bipedRightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.bipedLeftArm = new ModelRenderer((ModelBase) this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 6, 4);
        this.bipedLeftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.bipedLeftLeg.showModel = false;
        this.bipedRightLeg.showModel = false;
    }

    public void renderHead(final float f5) {
        this.bipedHead.render(f5);
        this.bipedHeadwear.render(f5);
        renderHeadwear(0, -0.25, 0, 0.56d);
        this.bipedBody.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightArm.render(f5);
    }

    private void renderHeadwear(final double x, final double y, final double z, final double scale) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        // Right plane
        tessellator.addVertexWithUV(x - 0.5 * scale, y - 0.5 * scale, z - 0.5 * scale, 0.125 * 5, 0.25 * 1);
        tessellator.addVertexWithUV(x - 0.5 * scale, y + 0.5 * scale, z - 0.5 * scale, 0.125 * 5, 0.25 * 2);
        tessellator.addVertexWithUV(x - 0.5 * scale, y + 0.5 * scale, z + 0.5 * scale, 0.125 * 4, 0.25 * 2);
        tessellator.addVertexWithUV(x - 0.5 * scale, y - 0.5 * scale, z + 0.5 * scale, 0.125 * 4, 0.25 * 1);
        // Back plane
        tessellator.addVertexWithUV(x - 0.5 * scale, y - 0.5 * scale, z + 0.5 * scale, 0.125 * 8, 0.25 * 1);
        tessellator.addVertexWithUV(x - 0.5 * scale, y + 0.5 * scale, z + 0.5 * scale, 0.125 * 8, 0.25 * 2);
        tessellator.addVertexWithUV(x + 0.5 * scale, y + 0.5 * scale, z + 0.5 * scale, 0.125 * 7, 0.25 * 2);
        tessellator.addVertexWithUV(x + 0.5 * scale, y - 0.5 * scale, z + 0.5 * scale, 0.125 * 7, 0.25 * 1);
        // Left plane
        tessellator.addVertexWithUV(x + 0.5 * scale, y - 0.5 * scale, z + 0.5 * scale, 0.125 * 7, 0.25 * 1);
        tessellator.addVertexWithUV(x + 0.5 * scale, y + 0.5 * scale, z + 0.5 * scale, 0.125 * 7, 0.25 * 2);
        tessellator.addVertexWithUV(x + 0.5 * scale, y + 0.5 * scale, z - 0.5 * scale, 0.125 * 6, 0.25 * 2);
        tessellator.addVertexWithUV(x + 0.5 * scale, y - 0.5 * scale, z - 0.5 * scale, 0.125 * 6, 0.25 * 1);
        // Front plane
        tessellator.addVertexWithUV(x + 0.5 * scale, y - 0.5 * scale, z - 0.5 * scale, 0.125 * 6, 0.25 * 1);
        tessellator.addVertexWithUV(x + 0.5 * scale, y + 0.5 * scale, z - 0.5 * scale, 0.125 * 6, 0.25 * 2);
        tessellator.addVertexWithUV(x - 0.5 * scale, y + 0.5 * scale, z - 0.5 * scale, 0.125 * 5, 0.25 * 2);
        tessellator.addVertexWithUV(x - 0.5 * scale, y - 0.5 * scale, z - 0.5 * scale, 0.125 * 5, 0.25 * 1);
        // Bottom plane
        tessellator.addVertexWithUV(x + 0.5 * scale, y + 0.5 * scale, z + 0.5 * scale, 0.125 * 7, 0.25 * 0);
        tessellator.addVertexWithUV(x - 0.5 * scale, y + 0.5 * scale, z + 0.5 * scale, 0.125 * 6, 0.25 * 0);
        tessellator.addVertexWithUV(x - 0.5 * scale, y + 0.5 * scale, z - 0.5 * scale, 0.125 * 6, 0.25 * 1);
        tessellator.addVertexWithUV(x + 0.5 * scale, y + 0.5 * scale, z - 0.5 * scale, 0.125 * 7, 0.25 * 1);
        // Top plane
        tessellator.addVertexWithUV(x - 0.5 * scale, y - 0.5 * scale, z + 0.5 * scale, 0.125 * 6, 0.25 * 0);
        tessellator.addVertexWithUV(x + 0.5 * scale, y - 0.5 * scale, z + 0.5 * scale, 0.125 * 5, 0.25 * 0);
        tessellator.addVertexWithUV(x + 0.5 * scale, y - 0.5 * scale, z - 0.5 * scale, 0.125 * 5, 0.25 * 1);
        tessellator.addVertexWithUV(x - 0.5 * scale, y - 0.5 * scale, z - 0.5 * scale, 0.125 * 6, 0.25 * 1);

        tessellator.draw();
    }

    public void showHead(final boolean b) {
        final ModelRenderer bipedHead = this.bipedHead;
        final ModelRenderer bipedHeadwear = this.bipedHeadwear;
        final ModelRenderer bipedBody = this.bipedBody;
        final ModelRenderer bipedLeftArm = this.bipedLeftArm;
        this.bipedRightArm.showModel = b;
        bipedLeftArm.showModel = b;
        bipedBody.showModel = b;
        bipedHeadwear.showModel = b;
        bipedHead.showModel = b;
    }

    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3,
        final float f4, final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.renderHead(f5);
    }
}
