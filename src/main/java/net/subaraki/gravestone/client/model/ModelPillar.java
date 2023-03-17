



package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.*;

public class ModelPillar extends ModelBase
{
    ModelRenderer Base4;
    ModelRenderer Base5;
    ModelRenderer Base6;
    ModelRenderer Base7;
    ModelRenderer Base8;
    ModelRenderer Pillar1;
    ModelRenderer Base9;
    ModelRenderer Base10;
    ModelRenderer Pillar2;
    ModelRenderer Pillar3;
    ModelRenderer Pillar4;
    ModelRenderer Pillar5;
    ModelRenderer Pillar6;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    
    public ModelPillar() {
        this.textureHeight = 64;
        this.textureWidth = 64;
        (this.Base4 = new ModelRenderer((ModelBase)this, 0, 51)).addBox(0.0f, 0.0f, 0.0f, 12, 1, 12);
        this.Base4.setRotationPoint(-6.0f, 23.0f, -6.0f);
        this.Base4.setTextureSize(64, 64);
        this.Base4.mirror = true;
        this.setRotation(this.Base4, 0.0f, 0.0f, 0.0f);
        (this.Base5 = new ModelRenderer((ModelBase)this, 0, 52)).addBox(0.0f, 0.0f, 0.0f, 11, 1, 11);
        this.Base5.setRotationPoint(-5.5f, 22.5f, -5.5f);
        this.Base5.setTextureSize(64, 64);
        this.Base5.mirror = true;
        this.setRotation(this.Base5, 0.0f, 0.0f, 0.0f);
        (this.Base6 = new ModelRenderer((ModelBase)this, 0, 53)).addBox(-5.0f, 0.0f, -5.0f, 10, 1, 10);
        this.Base6.setRotationPoint(0.0f, 22.0f, 0.0f);
        this.Base6.setTextureSize(64, 64);
        this.Base6.mirror = true;
        this.setRotation(this.Base6, 0.0f, 0.0f, 0.0f);
        (this.Base7 = new ModelRenderer((ModelBase)this, 0, 54)).addBox(-4.5f, 0.0f, -4.5f, 9, 1, 9);
        this.Base7.setRotationPoint(0.0f, 21.5f, 0.0f);
        this.Base7.setTextureSize(64, 64);
        this.Base7.mirror = true;
        this.setRotation(this.Base7, 0.0f, 0.0f, 0.0f);
        (this.Base8 = new ModelRenderer((ModelBase)this, 0, 55)).addBox(0.0f, 0.0f, 0.0f, 8, 1, 8);
        this.Base8.setRotationPoint(-4.0f, 21.0f, -4.0f);
        this.Base8.setTextureSize(64, 64);
        this.Base8.mirror = true;
        this.setRotation(this.Base8, 0.0f, 0.0f, 0.0f);
        (this.Pillar1 = new ModelRenderer((ModelBase)this, 0, 20)).addBox(0.0f, 0.0f, 0.0f, 7, 16, 2);
        this.Pillar1.setRotationPoint(-3.5f, 5.0f, -1.0f);
        this.Pillar1.setTextureSize(64, 64);
        this.Pillar1.mirror = true;
        this.setRotation(this.Pillar1, 0.0f, 0.0f, 0.0f);
        (this.Base9 = new ModelRenderer((ModelBase)this, 0, 55)).addBox(0.0f, 0.0f, 0.0f, 8, 1, 8);
        this.Base9.setRotationPoint(-4.0f, 4.5f, -4.0f);
        this.Base9.setTextureSize(64, 64);
        this.Base9.mirror = true;
        this.setRotation(this.Base9, 0.0f, 0.0f, 0.0f);
        (this.Base10 = new ModelRenderer((ModelBase)this, 0, 54)).addBox(0.0f, 0.0f, 0.0f, 9, 1, 9);
        this.Base10.setRotationPoint(-4.5f, 4.0f, -4.5f);
        this.Base10.setTextureSize(64, 64);
        this.Base10.mirror = true;
        this.setRotation(this.Base10, 0.0f, 0.0f, 0.0f);
        (this.Pillar2 = new ModelRenderer((ModelBase)this, 18, 20)).addBox(-1.0f, 5.0f, -3.5f, 2, 16, 7);
        this.Pillar2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Pillar2.setTextureSize(64, 64);
        this.Pillar2.mirror = true;
        this.setRotation(this.Pillar2, 0.0f, 0.0f, 0.0f);
        (this.Pillar3 = new ModelRenderer((ModelBase)this, 47, 20)).addBox(-3.0f, 5.0f, -2.0f, 1, 16, 4);
        this.Pillar3.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Pillar3.setTextureSize(64, 64);
        this.Pillar3.mirror = true;
        this.setRotation(this.Pillar3, 0.0f, 0.0f, 0.0f);
        (this.Pillar4 = new ModelRenderer((ModelBase)this, 47, 20)).addBox(2.0f, 5.0f, -2.0f, 1, 16, 4);
        this.Pillar4.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Pillar4.setTextureSize(64, 64);
        this.Pillar4.mirror = true;
        this.setRotation(this.Pillar4, 0.0f, 0.0f, 0.0f);
        (this.Pillar5 = new ModelRenderer((ModelBase)this, 37, 20)).addBox(-2.0f, 5.0f, 2.0f, 4, 16, 1);
        this.Pillar5.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Pillar5.setTextureSize(64, 64);
        this.Pillar5.mirror = true;
        this.setRotation(this.Pillar5, 0.0f, 0.0f, 0.0f);
        (this.Pillar6 = new ModelRenderer((ModelBase)this, 37, 20)).addBox(-2.0f, 5.0f, -3.0f, 4, 16, 1);
        this.Pillar6.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Pillar6.setTextureSize(64, 64);
        this.Pillar6.mirror = true;
        this.setRotation(this.Pillar6, 0.0f, 0.0f, 0.0f);
        (this.Shape18 = new ModelRenderer((ModelBase)this, 0, 0)).addBox(-2.5f, -4.0f, -2.0f, 5, 5, 5);
        this.Shape18.setRotationPoint(0.0f, -4.0f, 2.0f);
        this.Shape18.setTextureSize(64, 64);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, -0.2268928f, 0.0f, 0.0f);
        (this.Shape19 = new ModelRenderer((ModelBase)this, 0, 10)).addBox(-2.5f, -3.0f, -1.0f, 5, 7, 2);
        this.Shape19.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Shape19.setTextureSize(64, 64);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0f, 0.0f, 0.0f);
        (this.Shape20 = new ModelRenderer((ModelBase)this, 20, 0)).addBox(-1.5f, 0.0f, 0.0f, 1, 5, 1);
        this.Shape20.setRotationPoint(-2.0f, -2.0f, -1.0f);
        this.Shape20.setTextureSize(64, 64);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.5759587f, 0.0f, 0.0f);
        (this.Shape21 = new ModelRenderer((ModelBase)this, 20, 0)).addBox(0.5f, 0.0f, 0.0f, 1, 5, 1);
        this.Shape21.setRotationPoint(2.0f, -2.0f, -1.0f);
        this.Shape21.setTextureSize(64, 64);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.5759587f, 0.0f, 0.0f);
        (this.Shape22 = new ModelRenderer((ModelBase)this, 24, 0)).addBox(-3.0f, -0.3f, 0.0f, 4, 1, 1);
        this.Shape22.setRotationPoint(2.5f, 2.0f, 2.0f);
        this.Shape22.setTextureSize(64, 64);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.0f, 0.0f, 0.4363323f);
        (this.Shape23 = new ModelRenderer((ModelBase)this, 24, 0)).addBox(-1.0f, -0.3f, 0.0f, 4, 1, 1);
        this.Shape23.setRotationPoint(-2.5f, 2.0f, 2.0f);
        this.Shape23.setTextureSize(64, 64);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.0f, 0.0f, -0.4363323f);
    }
    
    public void render(final float f5) {
        this.Base4.render(f5);
        this.Base5.render(f5);
        this.Base6.render(f5);
        this.Base7.render(f5);
        this.Base8.render(f5);
        this.Base9.render(f5);
        this.Base10.render(f5);
        this.Pillar1.render(f5);
        this.Pillar2.render(f5);
        this.Pillar3.render(f5);
        this.Pillar4.render(f5);
        this.Pillar5.render(f5);
        this.Pillar6.render(f5);
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
