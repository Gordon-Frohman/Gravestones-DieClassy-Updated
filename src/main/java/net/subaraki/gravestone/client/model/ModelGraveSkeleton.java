
package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

public class ModelGraveSkeleton extends ModelBase {

    ModelRenderer skeleton;
    ModelRenderer head;
    ModelRenderer ribs;
    ModelRenderer arm1;
    ModelRenderer arm2;
    ModelRenderer forearm1;
    ModelRenderer forearm2;

    public ModelGraveSkeleton() {
        this.textureHeight = 64;
        this.textureWidth = 64;

        skeleton = new ModelRenderer(this, 0, 0);
        skeleton.setRotationPoint(0, 0, 0);

        this.head = new ModelRenderer(this, 0, 0).addBox(-2.5f, -4.0f, -2.0f, 5, 5, 5);
        this.head.setRotationPoint(0.0f, -4.0f, 2.0f);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, -0.2268928f, 0.0f, 0.0f);
        skeleton.addChild(head);

        this.ribs = new ModelRenderer(this, 0, 10).addBox(-2.5f, -3.0f, -1.0f, 5, 7, 2);
        this.ribs.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ribs.setTextureSize(64, 64);
        this.ribs.mirror = true;
        this.setRotation(this.ribs, 0.0f, 0.0f, 0.0f);
        skeleton.addChild(ribs);

        this.arm1 = new ModelRenderer(this, 20, 0).addBox(-1.5f, 0.0f, 0.0f, 1, 5, 1);
        this.arm1.setRotationPoint(-2.0f, -2.0f, -1.0f);
        this.arm1.setTextureSize(64, 64);
        this.arm1.mirror = true;
        this.setRotation(this.arm1, 0.5759587f, 0.0f, 0.0f);
        skeleton.addChild(arm1);

        this.arm2 = new ModelRenderer(this, 20, 0).addBox(0.5f, 0.0f, 0.0f, 1, 5, 1);
        this.arm2.setRotationPoint(2.0f, -2.0f, -1.0f);
        this.arm2.setTextureSize(64, 64);
        this.arm2.mirror = true;
        this.setRotation(this.arm2, 0.5759587f, 0.0f, 0.0f);
        skeleton.addChild(arm2);

        this.forearm1 = new ModelRenderer(this, 24, 0).addBox(-3.0f, -0.3f, 0.0f, 4, 1, 1);
        this.forearm1.setRotationPoint(2.5f, 2.0f, 2.0f);
        this.forearm1.setTextureSize(64, 64);
        this.forearm1.mirror = true;
        this.setRotation(this.forearm1, 0.0f, 0.0f, 0.4363323f);
        skeleton.addChild(forearm1);

        this.forearm2 = new ModelRenderer(this, 24, 0).addBox(-1.0f, -0.3f, 0.0f, 4, 1, 1);
        this.forearm2.setRotationPoint(-2.5f, 2.0f, 2.0f);
        this.forearm2.setTextureSize(64, 64);
        this.forearm2.mirror = true;
        this.setRotation(this.forearm2, 0.0f, 0.0f, -0.4363323f);
        skeleton.addChild(forearm2);

        skeleton.rotateAngleY = (float) Math.PI;
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void render(final float f5) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);

        this.skeleton.render(f5);

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }
}
