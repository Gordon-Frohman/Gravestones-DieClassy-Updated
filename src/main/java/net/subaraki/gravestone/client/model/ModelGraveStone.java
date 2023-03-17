



package net.subaraki.gravestone.client.model;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;

public class ModelGraveStone extends ModelBase
{
    public ModelRenderer Shape14;
    public ModelRenderer Shape15;
    public ModelRenderer Shape16;
    public ModelRenderer Shape17;
    public ModelRenderer Base1;
    public ModelRenderer Base2;
    public ModelRenderer Base3;
    
    public ModelGraveStone() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.Base1 = new ModelRenderer((ModelBase)this, 0, 55)).addBox(-8.0f, 0.0f, -4.0f, 16, 1, 8);
        this.Base1.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Base1.setTextureSize(64, 64);
        this.Base1.mirror = true;
        this.setRotation(this.Base1, 0.0f, 0.0f, 0.0f);
        (this.Base2 = new ModelRenderer((ModelBase)this, 0, 47)).addBox(-7.5f, 0.0f, -3.5f, 15, 1, 7);
        this.Base2.setRotationPoint(0.0f, 22.0f, 0.0f);
        this.Base2.setTextureSize(64, 64);
        this.Base2.mirror = true;
        this.setRotation(this.Base2, 0.0f, 0.0f, 0.0f);
        (this.Base3 = new ModelRenderer((ModelBase)this, 0, 40)).addBox(-7.0f, 0.0f, -3.0f, 14, 1, 6);
        this.Base3.setRotationPoint(0.0f, 21.0f, 0.0f);
        this.Base3.setTextureSize(64, 64);
        this.Base3.mirror = true;
        this.setRotation(this.Base3, 0.0f, 0.0f, 0.0f);
        (this.Shape14 = new ModelRenderer((ModelBase)this, 0, 24)).addBox(-6.0f, 0.0f, -1.0f, 12, 14, 2);
        this.Shape14.setRotationPoint(0.0f, 7.0f, 0.0f);
        this.Shape14.setTextureSize(64, 64);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0f, 0.0f, 0.0f);
        (this.Shape15 = new ModelRenderer((ModelBase)this, 0, 21)).addBox(-5.5f, 0.0f, 0.0f, 1, 1, 2);
        this.Shape15.setRotationPoint(0.0f, 6.5f, -1.0f);
        this.Shape15.setTextureSize(64, 64);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0f, 0.0f, 0.0f);
        (this.Shape16 = new ModelRenderer((ModelBase)this, 0, 18)).addBox(-5.0f, -2.0f, 0.0f, 10, 1, 2);
        this.Shape16.setRotationPoint(0.0f, 8.0f, -1.0f);
        this.Shape16.setTextureSize(64, 64);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0f, 0.0f, 0.0f);
        (this.Shape17 = new ModelRenderer((ModelBase)this, 0, 21)).addBox(4.5f, 0.0f, 0.0f, 1, 1, 2);
        this.Shape17.setRotationPoint(0.0f, 6.5f, -1.0f);
        this.Shape17.setTextureSize(64, 64);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0f, 0.0f, 0.0f);
    }
    
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.render(f5);
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void showZerk(final boolean b) {
        final ModelRenderer shape14 = this.Shape14;
        final ModelRenderer shape15 = this.Shape15;
        final ModelRenderer shape16 = this.Shape16;
        final ModelRenderer shape17 = this.Shape17;
        final ModelRenderer base1 = this.Base1;
        final ModelRenderer base2 = this.Base2;
        this.Base3.showModel = b;
        base2.showModel = b;
        base1.showModel = b;
        shape17.showModel = b;
        shape16.showModel = b;
        shape15.showModel = b;
        shape14.showModel = b;
    }
    
    public void render(final float f5) {
        this.Shape14.render(f5);
        this.Shape15.render(f5);
        this.Shape16.render(f5);
        this.Shape17.render(f5);
        this.Base1.render(f5);
        this.Base2.render(f5);
        this.Base3.render(f5);
    }
}
