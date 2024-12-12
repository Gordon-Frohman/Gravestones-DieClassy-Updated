
package net.subaraki.gravestone.item;

import java.util.*;

import net.minecraft.block.*;
import net.minecraft.creativetab.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
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

    public boolean onItemUse(final ItemStack itemStack, final EntityPlayer player, final World world, int x, int y,
        int z, final int meta, final float par8, final float par9, final float par10) {
        if (meta == 0) {
            return false;
        }
        if (!world.getBlock(x, y, z)
            .getMaterial()
            .isSolid()) {
            return false;
        }
        if (meta == 1) {
            ++y;
        }
        if (meta == 2) {
            --z;
        }
        if (meta == 3) {
            ++z;
        }
        if (meta == 4) {
            --x;
        }
        if (meta == 5) {
            ++x;
        }
        if (!player.canPlayerEdit(x, y, z, meta, itemStack)) {
            return false;
        }
        world.setBlock(x, y, z, GraveStones.graveStone, meta, 2);
        final int i1 = 0;
        final TileEntityGravestone grave = new TileEntityGravestone();
        grave.setDeathMessage(StatCollector.translateToLocal("grave.decorative"));
        grave.setDeathMessage2("");
        grave.isDecorativeGrave = true;
        grave.modelType = itemStack.getItemDamage();

        Vec3 playerLookVec = player.getLookVec();
        double graveRotation = Math.toDegrees(Math.atan2(playerLookVec.zCoord, playerLookVec.xCoord)) - 180;
        if (graveRotation < 0) {
            graveRotation += 360;
        }
        grave.ModelRotation = (float) (Math.round(graveRotation / 15) * 15);

        world.setTileEntity(x, y, z, (TileEntity) grave);
        --itemStack.stackSize;
        return true;
    }
}
