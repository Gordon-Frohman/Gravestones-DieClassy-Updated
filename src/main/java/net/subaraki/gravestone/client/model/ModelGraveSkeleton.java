
package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.*;

public class ModelGraveSkeleton extends ModelBase {

    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;

    public ModelGraveSkeleton() {
        this.textureHeight = 64;
        this.textureWidth = 64;
        (this.Shape18 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(-2.5f, -4.0f, -2.0f, 5, 5, 5);
        this.Shape18.setRotationPoint(0.0f, -4.0f, 2.0f);
        this.Shape18.setTextureSize(64, 64);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, -0.2268928f, 0.0f, 0.0f);
        (this.Shape19 = new ModelRenderer((ModelBase) this, 0, 10)).addBox(-2.5f, -3.0f, -1.0f, 5, 7, 2);
        this.Shape19.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Shape19.setTextureSize(64, 64);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0f, 0.0f, 0.0f);
        (this.Shape20 = new ModelRenderer((ModelBase) this, 20, 0)).addBox(-1.5f, 0.0f, 0.0f, 1, 5, 1);
        this.Shape20.setRotationPoint(-2.0f, -2.0f, -1.0f);
        this.Shape20.setTextureSize(64, 64);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.5759587f, 0.0f, 0.0f);
        (this.Shape21 = new ModelRenderer((ModelBase) this, 20, 0)).addBox(0.5f, 0.0f, 0.0f, 1, 5, 1);
        this.Shape21.setRotationPoint(2.0f, -2.0f, -1.0f);
        this.Shape21.setTextureSize(64, 64);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.5759587f, 0.0f, 0.0f);
        (this.Shape22 = new ModelRenderer((ModelBase) this, 24, 0)).addBox(-3.0f, -0.3f, 0.0f, 4, 1, 1);
        this.Shape22.setRotationPoint(2.5f, 2.0f, 2.0f);
        this.Shape22.setTextureSize(64, 64);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.0f, 0.0f, 0.4363323f);
        (this.Shape23 = new ModelRenderer((ModelBase) this, 24, 0)).addBox(-1.0f, -0.3f, 0.0f, 4, 1, 1);
        this.Shape23.setRotationPoint(-2.5f, 2.0f, 2.0f);
        this.Shape23.setTextureSize(64, 64);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.0f, 0.0f, -0.4363323f);
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void render(final float f5) {
        this.Shape18.render(f5);
        this.Shape19.render(f5);
        this.Shape20.render(f5);
        this.Shape21.render(f5);
        this.Shape22.render(f5);
        this.Shape23.render(f5);
    }
}
