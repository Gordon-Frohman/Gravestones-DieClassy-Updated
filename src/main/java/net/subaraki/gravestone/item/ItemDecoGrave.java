
package net.subaraki.gravestone.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
        switch (meta) {
            case 1:
                ++y;
                break;
            case 2:
                --z;
                break;
            case 3:
                ++z;
                break;
            case 4:
                --x;
                break;
            case 5:
                ++x;
                break;
        }
        if (!player.canPlayerEdit(x, y, z, meta, itemStack)) {
            return false;
        }
        world.setBlock(x, y, z, GraveStones.graveStone, itemStack.getItemDamage(), 2);
        SoundType soundEffect = itemStack.getItemDamage() == 6 || itemStack.getItemDamage() == 10 ? Block.soundTypeWood
            : Block.soundTypeStone;
        world.playSoundEffect(
            x + 0.5,
            y + 0.5,
            z + 0.5,
            soundEffect.getBreakSound(),
            (soundEffect.getVolume() + 1) / 2,
            soundEffect.getPitch() * 0.8F);
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
