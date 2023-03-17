



package net.subaraki.gravestone.block;

import net.minecraft.block.*;
import java.util.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.entity.player.*;
import net.subaraki.gravestone.tileentity.*;
import net.subaraki.gravestone.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.entity.item.*;
import net.minecraft.nbt.*;
import net.minecraft.world.*;
import cpw.mods.fml.client.registry.*;
import net.minecraft.tileentity.*;

public class BlockGrave extends Block
{
    Random rand;
    
    public BlockGrave(final Material mat) {
        super(mat);
        this.rand = new Random();
        this.setBlockBounds(0.4f, 0.0f, 0.4f, 0.6f, 1.0f, 0.6f);
        this.setBlockUnbreakable();
    }
    
    public void registerBlockIcons(final IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("stonebrick");
    }
    
    public int quantityDropped(final Random par1Random) {
        return -1;
    }
    
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int meta, final float p_149727_7_, final float p_149727_8_, final float p_149727_9_) {
        final TileEntityGravestone te = (TileEntityGravestone)world.getTileEntity(x, y, z);
        if (player.getCurrentEquippedItem() == null || !(player.getCurrentEquippedItem().getItem() instanceof ItemNameTag)) {
            if (!player.isSneaking()) {
                player.openGui((Object)GraveStones.instance, 0, world, x, y, z);
            }
            else {
                final TileEntityGravestone tileEntityGravestone = te;
                tileEntityGravestone.ModelRotation += 15.0f;
            }
            return true;
        }
        if (te.isDecorativeGrave) {
            final String s = player.getCurrentEquippedItem().getDisplayName();
            te.setName(s);
            te.setDeathMessage(StatCollector.translateToLocal("is.Honored.To"));
            te.setDeathMessage2(".");
            return true;
        }
        if (!world.isRemote) {
            player.addChatComponentMessage((IChatComponent)new ChatComponentText("This grave belongs to someone. I should not do that."));
        }
        return true;
    }
    
    public float getExplosionResistance(final Entity par1Entity, final World world, final int x, final int y, final int z, final double explosionX, final double explosionY, final double explosionZ) {
        return this.getExplosionResistance(par1Entity);
    }
    
    public float getExplosionResistance(final Entity par1Entity) {
        return Float.MAX_VALUE;
    }
    
    public void onBlockDestroyedByExplosion(final World world, final int x, final int y, final int z, final Explosion p_149723_5_) {
        world.removeTileEntity(x, y, z);
    }
    
    public void onBlockClicked(final World world, final int x, final int y, final int z, final EntityPlayer player) {
        if (((TileEntityGravestone)world.getTileEntity(x, y, z)).hasItems) {
            this.setBlockUnbreakable();
        }
        else {
            this.setHardness(5.0f);
        }
    }
    
    public void breakBlock(final World world, final int x, final int y, final int z, final Block block, final int a) {
        final TileEntityGravestone te = (TileEntityGravestone)world.getTileEntity(x, y, z);
        if (te != null) {
            for (final ItemStack itemstack : te.list) {
                final ItemStack element = itemstack;
                if (itemstack != null) {
                    final float f = this.rand.nextFloat() * 0.8f + 0.1f;
                    final float f2 = this.rand.nextFloat() * 0.8f + 0.1f;
                    final float f3 = this.rand.nextFloat() * 0.8f + 0.1f;
                    while (itemstack.stackSize > 0) {
                        int k1 = this.rand.nextInt(21) + 10;
                        if (k1 > itemstack.stackSize) {
                            k1 = itemstack.stackSize;
                        }
                        final ItemStack itemStack = itemstack;
                        itemStack.stackSize -= k1;
                        final EntityItem entityitem = new EntityItem(world, (double)(x + f), (double)(y + f2), (double)(z + f3), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));
                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                        final float f4 = 0.05f;
                        entityitem.motionX = (float)this.rand.nextGaussian() * f4;
                        entityitem.motionY = (float)this.rand.nextGaussian() * f4 + 0.2f;
                        entityitem.motionZ = (float)this.rand.nextGaussian() * f4;
                        if (!world.isRemote) {
                            world.spawnEntityInWorld((Entity)entityitem);
                        }
                    }
                }
            }
        }
        world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, block, a);
    }
    
    public void setBlockBoundsBasedOnState(final IBlockAccess par1IBlockAccess, final int x, final int y, final int z) {
        final TileEntityGravestone te = (TileEntityGravestone)par1IBlockAccess.getTileEntity(x, y, z);
        final int meta = te.modelType;
        switch (meta) {
            case 1: {
                this.setBlockBounds(0.4f, 0.0f, 0.4f, 0.6f, 1.0f, 0.6f);
                break;
            }
            case 2: {
                this.setBlockBounds(0.43f, 0.0f, 0.14f, 0.57f, 1.05f, 0.86f);
                break;
            }
            case 3: {
                this.setBlockBounds(0.33f, 0.0f, 0.25f, 0.67f, 0.95f, 0.75f);
                break;
            }
            case 4: {
                this.setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 1.25f, 0.8f);
                break;
            }
            case 5: {
                this.setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 1.25f, 0.8f);
                break;
            }
            case 7: {
                this.setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 1.25f, 0.8f);
                break;
            }
            case 6: {
                this.setBlockBounds(0.4f, 0.0f, 0.4f, 0.6f, 1.0f, 0.6f);
                break;
            }
            case 8: {
                this.setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 1.5f, 0.8f);
                break;
            }
            case 9: {
                this.setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 1.5f, 0.8f);
                break;
            }
            case 10: {
                this.setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 0.85f, 0.8f);
                break;
            }
            default: {
                this.setBlockBounds(0.4f, 0.0f, 0.4f, 0.6f, 1.0f, 0.6f);
                break;
            }
        }
    }
    
    public int getRenderType() {
        return RenderingRegistry.getNextAvailableRenderId();
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public boolean hasTileEntity(final int metadata) {
        return true;
    }
    
    public TileEntity createTileEntity(final World world, final int metadata) {
        return new TileEntityGravestone();
    }
}
