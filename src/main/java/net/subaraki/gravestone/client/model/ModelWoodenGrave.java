
package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelWoodenGrave extends ModelBase {

    ModelRenderer Cross1;
    ModelRenderer Cross2;
    ModelRenderer Rope1;
    ModelRenderer Rope2;
    ModelRenderer Rope3;
    ModelRenderer Rope4;
    ModelRenderer ground1;
    ModelRenderer Ground2;
    ModelRenderer ground3;
    ModelRenderer ground4;
    ModelRenderer ground5;
    ModelRenderer ground6;
    ModelRenderer ground7;
    ModelRenderer ground8;

    public ModelWoodenGrave() {
        this.textureHeight = 64;
        this.textureWidth = 64;
        (this.Cross1 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(-1.0f, -4.0f, -1.0f, 2, 17, 2);
        this.Cross1.setRotationPoint(0.0f, 11.0f, 0.0f);
        this.Cross1.setTextureSize(64, 64);
        this.Cross1.mirror = true;
        this.setRotation(this.Cross1, 0.0f, 0.0f, 0.0523599f);
        (this.Cross2 = new ModelRenderer((ModelBase) this, 8, 0)).addBox(-6.0f, 0.0f, -0.5f, 12, 1, 1);
        this.Cross2.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.Cross2.setTextureSize(64, 64);
        this.Cross2.mirror = true;
        this.setRotation(this.Cross2, 0.0f, 0.0f, -0.1047198f);
        (this.Rope1 = new ModelRenderer((ModelBase) this, 8, 2)).addBox(-1.3f, 1.0f, 0.5f, 3, 1, 1);
        this.Rope1.setRotationPoint(-1.0f, 11.0f, 0.0f);
        this.Rope1.setTextureSize(64, 64);
        this.Rope1.mirror = true;
        this.setRotation(this.Rope1, 0.0f, 0.0f, -0.5235988f);
        (this.Rope2 = new ModelRenderer((ModelBase) this, 8, 2)).addBox(0.0f, 0.0f, 0.3f, 3, 1, 1);
        this.Rope2.setRotationPoint(-1.0f, 11.0f, 0.0f);
        this.Rope2.setTextureSize(64, 64);
        this.Rope2.mirror = true;
        this.setRotation(this.Rope2, 0.0f, 0.0f, 0.5759587f);
        (this.Rope3 = new ModelRenderer((ModelBase) this, 8, 2)).addBox(0.0f, 0.0f, -1.4f, 3, 1, 1);
        this.Rope3.setRotationPoint(-1.0f, 11.0f, 0.0f);
        this.Rope3.setTextureSize(64, 64);
        this.Rope3.mirror = true;
        this.setRotation(this.Rope3, 0.0f, 0.0f, 0.5759587f);
        (this.Rope4 = new ModelRenderer((ModelBase) this, 8, 2)).addBox(-1.3f, 1.0f, -1.2f, 3, 1, 1);
        this.Rope4.setRotationPoint(-1.0f, 11.0f, 0.0f);
        this.Rope4.setTextureSize(64, 64);
        this.Rope4.mirror = true;
        this.setRotation(this.Rope4, 0.0f, 0.0f, -0.5235988f);
        (this.ground1 = new ModelRenderer((ModelBase) this, 46, 8)).addBox(-3.0f, -1.0f, -1.0f, 5, 2, 4);
        this.ground1.setRotationPoint(0.0f, 23.0f, -1.0f);
        this.ground1.setTextureSize(64, 64);
        this.ground1.mirror = true;
        this.setRotation(this.ground1, 0.0f, 0.0f, 0.0f);
        (this.Ground2 = new ModelRenderer((ModelBase) this, 34, 0)).addBox(-5.0f, 0.0f, -4.5f, 8, 1, 7);
        this.Ground2.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Ground2.setTextureSize(64, 64);
        this.Ground2.mirror = true;
        this.setRotation(this.Ground2, 0.0f, 0.0f, 0.0f);
        (this.ground3 = new ModelRenderer((ModelBase) this, 41, 0)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.ground3.setRotationPoint(4.0f, 23.0f, 0.0f);
        this.ground3.setTextureSize(64, 64);
        this.ground3.mirror = true;
        this.setRotation(this.ground3, 0.0f, 0.0f, 0.0f);
        (this.ground4 = new ModelRenderer((ModelBase) this, 41, 0)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.ground4.setRotationPoint(0.0f, 23.0f, -6.0f);
        this.ground4.setTextureSize(64, 64);
        this.ground4.mirror = true;
        this.setRotation(this.ground4, 0.0f, 0.0f, 0.0f);
        (this.ground5 = new ModelRenderer((ModelBase) this, 41, 0)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.ground5.setRotationPoint(-7.0f, 23.0f, 5.0f);
        this.ground5.setTextureSize(64, 64);
        this.ground5.mirror = true;
        this.setRotation(this.ground5, 0.0f, 0.0f, 0.0f);
        (this.ground6 = new ModelRenderer((ModelBase) this, 41, 0)).addBox(-6.0f, 0.0f, -5.0f, 1, 1, 1);
        this.ground6.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.ground6.setTextureSize(64, 64);
        this.ground6.mirror = true;
        this.setRotation(this.ground6, 0.0f, 0.0f, 0.0f);
        (this.ground7 = new ModelRenderer((ModelBase) this, 41, 0)).addBox(3.0f, 0.0f, 4.0f, 1, 1, 1);
        this.ground7.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.ground7.setTextureSize(64, 64);
        this.ground7.mirror = true;
        this.setRotation(this.ground7, 0.0f, 0.0f, 0.0f);
        (this.ground8 = new ModelRenderer((ModelBase) this, 41, 0)).addBox(-2.0f, 0.5f, 2.0f, 3, 1, 1);
        this.ground8.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.ground8.setTextureSize(64, 64);
        this.ground8.mirror = true;
        this.setRotation(this.ground8, 0.0f, 0.0f, 0.0f);
    }

    public void render(final float f5) {
        this.Cross1.render(f5);
        this.Cross2.render(f5);
        this.Rope1.render(f5);
        this.Rope2.render(f5);
        this.Rope3.render(f5);
        this.Rope4.render(f5);
        this.ground1.render(f5);
        this.Ground2.render(f5);
        this.ground3.render(f5);
        this.ground4.render(f5);
        this.ground5.render(f5);
        this.ground6.render(f5);
        this.ground7.render(f5);
        this.ground8.render(f5);
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
