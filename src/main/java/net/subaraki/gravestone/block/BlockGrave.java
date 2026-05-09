
package net.subaraki.gravestone.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemNameTag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.handler.GuiHandler;
import net.subaraki.gravestone.inventory.GraveInventory;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGrave extends Block {

    IIcon iconCobblestone;
    IIcon iconOakPlanks;
    IIcon iconAcaciaPlanks;
    IIcon iconStone;
    IIcon iconQuartz;

    Random rand;

    public BlockGrave(Material mat) {
        super(mat);
        this.rand = new Random();
        this.setBlockBounds(0.4f, 0.0f, 0.4f, 0.6f, 1.0f, 0.6f);
        this.setBlockUnbreakable();
        this.setResistance(6000000.0F);
    }

    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("stonebrick");
        this.iconCobblestone = par1IconRegister.registerIcon("cobblestone");
        this.iconOakPlanks = par1IconRegister.registerIcon("planks_oak");
        this.iconAcaciaPlanks = par1IconRegister.registerIcon("planks_acacia");
        this.iconStone = par1IconRegister.registerIcon("stone");
        this.iconQuartz = par1IconRegister.registerIcon("quartz_block_bottom");
    }

    public int quantityDropped(Random par1Random) {
        return -1;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float p_149727_7_,
        float p_149727_8_, float p_149727_9_) {
        TileEntityGravestone te = (TileEntityGravestone) world.getTileEntity(x, y, z);
        if (player.getCurrentEquippedItem() == null || !(player.getCurrentEquippedItem()
            .getItem() instanceof ItemNameTag)) {
            if (!player.isSneaking()) {
                player.openGui(GraveStones.instance, GuiHandler.GRAVE_CONTAINER, world, x, y, z);
            } else {
                TileEntityGravestone tileEntityGravestone = te;
                tileEntityGravestone.rotation += 15.0f;
            }
            return true;
        }
        if (te.isDecorative) {
            String s = player.getCurrentEquippedItem()
                .getDisplayName();
            te.setName(s);
            te.setDeathMessage(StatCollector.translateToLocal("is.Honored.To"));
            te.setDeathMessage2(".");
            return true;
        }
        if (!world.isRemote) {
            player.addChatComponentMessage(
                (IChatComponent) new ChatComponentText(StatCollector.translateToLocal("chat.dontTouch")));
        }
        return true;
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX,
        double explosionY, double explosionZ) {
        return this.getExplosionResistance(par1Entity);
    }

    public float getExplosionResistance(Entity par1Entity) {
        return Float.MAX_VALUE;
    }

    @Override
    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
        TileEntityGravestone te = (TileEntityGravestone) world.getTileEntity(x, y, z);
        if (te.isDecorative || !te.hasItems) super.onBlockExploded(world, x, y, z, explosion);
    }

    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion p_149723_5_) {
        world.removeTileEntity(x, y, z);
    }

    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        TileEntityGravestone te = (TileEntityGravestone) world.getTileEntity(x, y, z);
        if (te.hasItems && !te.isDecorative) {
            this.setBlockUnbreakable();
        } else {
            this.setHardness(5.0f);
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int a) {
        TileEntityGravestone te = (TileEntityGravestone) world.getTileEntity(x, y, z);
        if (te != null) {
            for (GraveInventory inventory : te.getAllInventories()) {
                for (int i = 0; i < inventory.getSizeInventory(); i++) {
                    ItemStack stack = inventory.getStackInSlot(i);
                    if (stack != null) {
                        float f = this.rand.nextFloat() * 0.8f + 0.1f;
                        float f2 = this.rand.nextFloat() * 0.8f + 0.1f;
                        float f3 = this.rand.nextFloat() * 0.8f + 0.1f;
                        while (stack.stackSize > 0) {
                            int k1 = this.rand.nextInt(21) + 10;
                            if (k1 > stack.stackSize) {
                                k1 = stack.stackSize;
                            }
                            ItemStack itemStack = stack;
                            itemStack.stackSize -= k1;
                            EntityItem entityitem = new EntityItem(
                                world,
                                (double) (x + f),
                                (double) (y + f2),
                                (double) (z + f3),
                                new ItemStack(stack.getItem(), k1, stack.getItemDamage()));
                            if (stack.hasTagCompound()) {
                                entityitem.getEntityItem()
                                    .setTagCompound(
                                        (NBTTagCompound) stack.getTagCompound()
                                            .copy());
                            }
                            float f4 = 0.05f;
                            entityitem.motionX = (float) this.rand.nextGaussian() * f4;
                            entityitem.motionY = (float) this.rand.nextGaussian() * f4 + 0.2f;
                            entityitem.motionZ = (float) this.rand.nextGaussian() * f4;
                            if (!world.isRemote) {
                                world.spawnEntityInWorld((Entity) entityitem);
                            }
                        }
                    }
                }
            }
        }
        world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, block, a);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        TileEntityGravestone te = (TileEntityGravestone) par1IBlockAccess.getTileEntity(x, y, z);
        int meta = te.modelType;
        float minX, minZ, maxX, maxZ;
        float minY = 0.0f;
        float maxY = 1.0f;
        switch (meta) {
            default:
            case 1:
                minX = minZ = 0.4f;
                maxX = maxZ = 0.6f;
                break;
            case 2:
            case 3: {
                // The grave's width and length are not equal
                // We must address that
                float minThin, minThick, maxThin, maxThick;
                switch (meta) {
                    default:
                    case 2:
                        maxY = 1.05f;
                        minThin = 0.43f;
                        minThick = 0.14f;
                        maxThin = 0.57f;
                        maxThick = 0.86f;
                        break;
                    case 3:
                        maxY = 0.95f;
                        minThin = 0.33f;
                        minThick = 0.25f;
                        maxThin = 0.67f;
                        maxThick = 0.75f;
                        break;
                }

                minX = minZ = minThick;
                maxX = maxZ = maxThick;

                if (te.rotation % 180f == 0) {
                    minX = minThin;
                    maxX = maxThin;
                } else {
                    if (te.rotation % 90f == 0) {
                        minZ = minThin;
                        maxZ = maxThin;
                    }
                }
                break;
            }
            case 4:
            case 5:
            case 7:
                minX = minZ = 0.2f;
                maxX = maxZ = 0.8f;
                maxY = 1.25f;
                break;
            case 6:
                minX = minZ = 0.4f;
                maxX = maxZ = 0.6f;
                break;
            case 8:
            case 9:
                minX = minZ = 0.2f;
                maxX = maxZ = 0.8f;
                maxY = 1.5f;
                break;
            case 10:
                minX = minZ = 0.2f;
                maxX = maxZ = 0.8f;
                maxY = 0.85f;
                break;
        }
        this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
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

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityGravestone();
    }

    public int getDamageValue(World worldIn, int x, int y, int z) {
        TileEntityGravestone te = (TileEntityGravestone) worldIn.getTileEntity(x, y, z);
        return te.modelType;
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        switch (meta) {
            default:
                return this.blockIcon;
            case 1:
            case 9:
                return this.iconCobblestone;
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
                return this.iconStone;
            case 6:
                return this.iconOakPlanks;
            case 8:
                return this.iconQuartz;
            case 10:
                return this.iconAcaciaPlanks;
        }
    }
}
