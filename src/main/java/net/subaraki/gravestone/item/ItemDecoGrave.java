
package net.subaraki.gravestone.item;

import java.util.*;

import net.minecraft.block.*;
import net.minecraft.creativetab.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.StatCollector;
import net.minecraft.world.*;
import net.subaraki.gravestone.*;
import net.subaraki.gravestone.tileentity.*;

import cpw.mods.fml.relauncher.*;

public class ItemDecoGrave extends ItemBlock {

    public ItemDecoGrave(final Block p_i45328_1_) {
        super(p_i45328_1_);
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item par1, final CreativeTabs tab, final List par3List) {
        for (int i = 1; i <= 10; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    public int getMetadata(final int par1) {
        return super.getMetadata(par1);
    }

    public boolean onItemUse(final ItemStack par1ItemStack, final EntityPlayer par2EntityPlayer, final World par3World,
        int par4, int par5, int par6, final int par7, final float par8, final float par9, final float par10) {
        if (par7 == 0) {
            return false;
        }
        if (!par3World.getBlock(par4, par5, par6)
            .getMaterial()
            .isSolid()) {
            return false;
        }
        if (par7 == 1) {
            ++par5;
        }
        if (par7 == 2) {
            --par6;
        }
        if (par7 == 3) {
            ++par6;
        }
        if (par7 == 4) {
            --par4;
        }
        if (par7 == 5) {
            ++par4;
        }
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
            return false;
        }
        par3World.setBlock(par4, par5, par6, GraveStones.graveStone, par7, 2);
        final int i1 = 0;
        final TileEntityGravestone grave = new TileEntityGravestone();
        grave.setDeathMessage(StatCollector.translateToLocal("grave.decorative"));
        grave.setDeathMessage2("");
        grave.isDecorativeGrave = true;
        grave.modelType = par1ItemStack.getItemDamage();
        par3World.setTileEntity(par4, par5, par6, (TileEntity) grave);
        --par1ItemStack.stackSize;
        return true;
    }
}
