



package net.subaraki.gravestone.handler;

import net.minecraft.client.model.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.item.*;

public class GraveArmorHandler
{
    public void setArmorModel(final ModelBiped model, final ItemStack itemstack, final int par2, final String armor) {
        if (itemstack != null) {
            final Item item = itemstack.getItem();
            if (item instanceof ItemArmor) {
                final ItemArmor itemarmor = (ItemArmor)item;
                Minecraft.getMinecraft().renderEngine.bindTexture(RenderBiped.getArmorResource((Entity)null, itemstack, par2, (String)null));
                final boolean for0 = par2 == 0;
                final boolean for2 = par2 == 1;
                final boolean for3 = par2 == 2;
                final boolean for4 = par2 == 3;
                model.bipedHead.showModel = for0;
                model.bipedHeadwear.showModel = for0;
                model.bipedBody.showModel = (for2 || for3);
                model.bipedRightArm.showModel = for2;
                model.bipedLeftArm.showModel = for2;
                model.bipedRightLeg.showModel = (for3 || for4);
                model.bipedLeftLeg.showModel = (for3 || for4);
                final float f1 = 1.0f;
                GL11.glColor3f(f1, f1, f1);
                if (itemarmor.getArmorMaterial() == ItemArmor.ArmorMaterial.CLOTH) {
                    final int j = itemarmor.getColor(itemstack);
                    final float f2 = (j >> 16 & 0xFF) / 255.0f;
                    final float f3 = (j >> 8 & 0xFF) / 255.0f;
                    final float f4 = (j & 0xFF) / 255.0f;
                    GL11.glColor3f(f1 * f2, f1 * f3, f1 * f4);
                }
            }
        }
    }
}
