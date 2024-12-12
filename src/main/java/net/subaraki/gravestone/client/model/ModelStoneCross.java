
package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.*;

public class ModelStoneCross extends ModelBase {

    public ModelRenderer Shape1;
    public ModelRenderer Shape2;
    public ModelRenderer Shape3;
    public ModelRenderer Shape4;

    public ModelStoneCross() {
        this.textureHeight = 64;
        this.textureWidth = 64;
        (this.Shape1 = new ModelRenderer((ModelBase) this, 0, 32)).addBox(-2.0f, 0.0f, -2.0f, 4, 4, 4);
        this.Shape1.setRotationPoint(0.0f, 19.0f, 0.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        (this.Shape2 = new ModelRenderer((ModelBase) this, 0, 40)).addBox(-3.0f, 0.0f, -3.0f, 6, 1, 6);
        this.Shape2.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        (this.Shape3 = new ModelRenderer((ModelBase) this, 25, 32)).addBox(-1.0f, 0.0f, -1.0f, 2, 14, 2);
        this.Shape3.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        (this.Shape4 = new ModelRenderer((ModelBase) this, 33, 32)).addBox(-0.5f, -5.0f, -2.0f, 1, 10, 2);
        this.Shape4.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, -1.570796f, 0.0f, 0.0f);
    }

    public void render(final float f5) {
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
