



package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.*;

public class ModelTomb extends ModelBase
{
    public ModelRenderer Shape5;
    public ModelRenderer Shape6;
    public ModelRenderer Shape7;
    public ModelRenderer Shape8;
    public ModelRenderer Shape9;
    public ModelRenderer Shape10;
    public ModelRenderer Shape11;
    public ModelRenderer Shape12;
    public ModelRenderer Shape13;
    
    public ModelTomb() {
        this.textureHeight = 64;
        this.textureWidth = 64;
        (this.Shape10 = new ModelRenderer((ModelBase)this, 24, 0)).addBox(-1.0f, -5.0f, -1.0f, 2, 3, 2);
        this.Shape10.setRotationPoint(7.0f, 26.0f, 7.0f);
        this.Shape10.setTextureSize(64, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0f, 0.0f, 0.0f);
        (this.Shape11 = new ModelRenderer((ModelBase)this, 0, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 3, 2);
        this.Shape11.setRotationPoint(-7.0f, 23.0f, 7.0f);
        this.Shape11.setTextureSize(64, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, 0.0f, 0.0f);
        (this.Shape12 = new ModelRenderer((ModelBase)this, 16, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 3, 2);
        this.Shape12.setRotationPoint(7.0f, 23.0f, -7.0f);
        this.Shape12.setTextureSize(64, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, 0.0f, 0.0f);
        (this.Shape13 = new ModelRenderer((ModelBase)this, 8, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 3, 2);
        this.Shape13.setRotationPoint(-7.0f, 23.0f, -7.0f);
        this.Shape13.setTextureSize(64, 32);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0f, 0.0f, 0.0f);
        (this.Shape5 = new ModelRenderer((ModelBase)this, 0, 17)).addBox(-7.0f, 0.0f, -7.0f, 14, 1, 14);
        this.Shape5.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        (this.Shape6 = new ModelRenderer((ModelBase)this, 32, 0)).addBox(-4.0f, -2.0f, -4.0f, 8, 4, 8);
        this.Shape6.setRotationPoint(0.0f, 20.0f, 0.0f);
        this.Shape6.setTextureSize(64, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        (this.Shape7 = new ModelRenderer((ModelBase)this, 7, 21)).addBox(-5.0f, 0.0f, -5.0f, 10, 1, 10);
        this.Shape7.setRotationPoint(0.0f, 17.0f, 0.0f);
        this.Shape7.setTextureSize(64, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        (this.Shape8 = new ModelRenderer((ModelBase)this, 0, 5)).addBox(-4.0f, -5.0f, -2.0f, 8, 8, 4);
        this.Shape8.setRotationPoint(0.0f, 14.0f, 0.0f);
        this.Shape8.setTextureSize(64, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        (this.Shape9 = new ModelRenderer((ModelBase)this, 4, 19)).addBox(-6.0f, 0.0f, -6.0f, 12, 1, 12);
        this.Shape9.setRotationPoint(0.0f, 22.0f, 0.0f);
        this.Shape9.setTextureSize(64, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void render(final float f5) {
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
        this.Shape10.render(f5);
        this.Shape11.render(f5);
        this.Shape12.render(f5);
        this.Shape13.render(f5);
    }
}
