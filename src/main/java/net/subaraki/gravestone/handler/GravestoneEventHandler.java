
package net.subaraki.gravestone.handler;

import java.lang.reflect.*;

import net.minecraft.block.material.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.entity.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.*;
import net.subaraki.gravestone.*;
import net.subaraki.gravestone.client.*;
import net.subaraki.gravestone.integration.TinkersConstructIntegration;
import net.subaraki.gravestone.tileentity.*;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.eventhandler.*;
import cpw.mods.fml.common.gameevent.*;
import cpw.mods.fml.relauncher.*;

public class GravestoneEventHandler {

    public GravestoneEventHandler() {
        MinecraftForge.EVENT_BUS.register((Object) this);
        FMLCommonHandler.instance()
            .bus()
            .register((Object) this);
    }

    @SubscribeEvent
    public void onEntityJoinWorld(final EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote
            && PlayerGraveData.get((EntityPlayer) event.entity) != null) {
            PlayerGraveData.get((EntityPlayer) event.entity)
                .sync();
        }
    }

    @SubscribeEvent
    public void onEntityConstruction(final EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer && PlayerGraveData.get((EntityPlayer) event.entity) == null) {
            PlayerGraveData.register((EntityPlayer) event.entity);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void keyHandling(final InputEvent.KeyInputEvent evt) {
        if (ConfigHandler.enableGravesTroughKey && ClientProxy.keyGui.isPressed()
            && Minecraft.getMinecraft().currentScreen == null) {
            final EntityPlayer p = (EntityPlayer) Minecraft.getMinecraft().thePlayer;
            p.openGui(
                (Object) GraveStones.instance,
                1,
                (World) Minecraft.getMinecraft().theWorld,
                (int) p.posX,
                (int) p.posY,
                (int) p.posZ);
        }
    }

    @SubscribeEvent
    public void onCloneEvent(final net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
        final PlayerGraveData dead = PlayerGraveData.get(event.original);
        final PlayerGraveData clone = PlayerGraveData.get(event.entityPlayer);
        clone.setGraveModel(dead.getGraveModel());
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onDeathEvent(final LivingDeathEvent evt) {
        if (evt.entityLiving instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer) evt.entityLiving;
            if (player.worldObj.getGameRules()
                .getGameRuleBooleanValue("keepInventory")) {
                return;
            }
            final int x = MathHelper.floor_double(player.posX);
            int y = MathHelper.floor_double(player.posY);
            final int z = MathHelper.floor_double(player.posZ);
            if (player.worldObj.isAirBlock(x, y, z)) {
                if (y < 0) {
                    // When you die from falling into void, your gravestone should spawn above the bedrock
                    if (player.worldObj.isAirBlock(x, 0, z)) player.worldObj.setBlock(x, 0, z, Blocks.bedrock);
                    y = 1;
                    while (!player.worldObj.isAirBlock(x, y, z) && y < 255) y++;
                }
                while (player.worldObj.isAirBlock(x, y, z)) {
                    --y;
                }
            }
            final int X = 100;
            final int Z = 100;
            int x2 = 0;
            int z2 = 0;
            int dx = 0;
            int dz = -1;
            int t = Math.max(X, Z);
            final int maxI = t * t;
            boolean flag = false;
            boolean liquid = false;
            for (int i = 0; i < maxI; ++i) {
                if (-X / 2 <= x2 && x2 <= X / 2 && -Z / 2 <= z2 && z2 <= Z / 2) {
                    int y2;
                    for (y2 = 0; !player.worldObj.getBlock(x + x2, y + 1 + y2, z + z2)
                        .getMaterial()
                        .equals(Material.air); ++y2) {}
                    if (player.worldObj.getBlock(x + x2, y + y2, z + z2)
                        .getMaterial()
                        .isLiquid()) {
                        GraveStones.printDebugMessage("You were standing in liquid !");
                        --y2;
                        liquid = true;
                    }
                    if (player.worldObj.getBlock(x + x2, y + y2, z + z2)
                        .getMaterial()
                        .isSolid()
                        && (player.worldObj.getBlock(x + x2, y + 1 + y2, z + z2)
                            .getMaterial()
                            .equals(Material.air) || liquid)) {
                        GraveStones
                            .printDebugMessage("Potential grave at " + (x + x2) + " " + (y + y2) + " " + (z + z2));
                        this.placeGrave(player, x + x2, y + y2, z + z2);
                        flag = true;
                        break;
                    }
                }
                if (x2 == z2 || (x2 < 0 && x2 == -z2) || (x2 > 0 && x2 == 1 - z2)) {
                    t = dx;
                    dx = -dz;
                    dz = t;
                }
                x2 += dx;
                z2 += dz;
            }
            if (!flag && liquid) {
                int y3;
                for (y3 = 0; !player.worldObj.getBlock(x, y + 1 + y3, z)
                    .getMaterial()
                    .equals(Material.air); ++y3) {}
                if (player.worldObj.getBlock(x, y + y3, z)
                    .getMaterial()
                    .isLiquid()) {
                    if (player.worldObj.getBlock(x, y + 1 + y3, z)
                        .getMaterial()
                        .equals(Material.air)) {
                        player.worldObj.setBlock(x, y + y3, z, Blocks.cobblestone);
                    }
                    this.placeGrave(player, x, y + y3, z);
                }
            } else if (!flag) {
                this.placeGrave(player, x, y, z);
            }
        }
    }

    private void placeGrave(final EntityPlayer player, final int x, final int y, final int z) {
        player.worldObj.setBlock(x, y + 1, z, GraveStones.graveStone);
        final TileEntityGravestone te = new TileEntityGravestone();
        final InventoryPlayer inv = player.inventory;
        int graveID = PlayerGraveData.get(player)
            .getGraveModel();
        final int max = 9;
        if (!ConfigHandler.enableGravesTroughKey) {
            graveID = ConfigHandler.graveOrder[Math.min(player.experienceLevel / ConfigHandler.graveLevel, max)];
        }
        te.setGraveData(player.getCommandSenderName(), graveID);
        for (int slot = 0; slot < inv.getSizeInventory(); ++slot) {
            final ItemStack is = inv.getStackInSlot(slot);
            if (is != null && slot < te.getSizeInventory()) {
                te.tab = 0;
                te.setInventorySlotContents(slot, is);
                inv.setInventorySlotContents(slot, (ItemStack) null);
            }
        }
        this.addOtherInventory(te, player);
        player.worldObj.setTileEntity(x, y + 1, z, (TileEntity) te);
        player.worldObj.markBlockForUpdate(x, y + 1, z);
        te.markDirty();
    }

    private void addOtherInventory(final TileEntityGravestone te, final EntityPlayer player) {
        if (GraveStones.hasRpgI) {
            final IInventory inv = this
                .accesInventoryContents(player, "get", "rpgInventory.gui.rpginv.PlayerRpgInventory", "Rpg Inventory");
            if (inv != null) {
                for (int i = 0; i < 7; ++i) {
                    final ItemStack is = inv.getStackInSlot(i);
                    te.list[i + 40] = is;
                    inv.setInventorySlotContents(i, (ItemStack) null);
                }
            } else {
                GraveStones.printDebugMessage(
                    "GraveStones Mod couldn't connect to Rpg Inventory. Have these classes been modified ? Report to mod Author pleases.");
            }
        }
        if (GraveStones.hasTiCo) {
            final IInventory sack = TinkersConstructIntegration.getKnapsackInventory(player);
            final IInventory inv2 = TinkersConstructIntegration.getAccessoryInventory(player);
            if (sack != null) {
                for (int j = 0; j < 27; ++j) {
                    final ItemStack is2 = sack.getStackInSlot(j);
                    te.list[j + 47] = is2;
                    sack.setInventorySlotContents(j, (ItemStack) null);
                }
            } else {
                GraveStones.printDebugMessage(
                    "GraveStones Mod couldn't connect to Tinkers Construct's Knapsack. Have these classes been modified ? Report to mod Author pleases.");
            }
            if (inv2 != null) {
                for (int j = 0; j < 7; ++j) {
                    final ItemStack is2 = inv2.getStackInSlot(j);
                    te.list[j + 74] = is2;
                    inv2.setInventorySlotContents(j, (ItemStack) null);
                }
            } else {
                GraveStones.printDebugMessage(
                    "GraveStones Mod couldn't connect to Tinkers Construct's Armor. Have these classes been modified ? Report to mod Author pleases.");
            }
        }
        if (GraveStones.hasBaub) {
            final IInventory inv = this
                .accesInventoryContents(player, "getPlayerBaubles", "baubles.common.lib.PlayerHandler", "Baubles");
            if (inv != null) {
                for (int i = 0; i < 4; ++i) {
                    final ItemStack is = inv.getStackInSlot(i);
                    te.list[i + 81] = is;
                    inv.setInventorySlotContents(i, (ItemStack) null);
                }
            } else {
                GraveStones.printDebugMessage(
                    "GraveStones Mod couldn't connect to Baubles. Have these classes been modified ? Report to mod Author pleases.");
            }
            try {
                final Class<?> clazz = Class.forName("baubles.common.container.InventoryBaubles");
                final Method m = clazz.getDeclaredMethod("syncSlotToClients", Integer.TYPE);
                for (int k = 0; k < 7; ++k) {
                    m.invoke(inv, k);
                }
                GraveStones.printDebugMessage(
                    "Gravestones was able to access Baubles' save mechanicism ! This print is proof that it got saved correctly.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (SecurityException e3) {
                e3.printStackTrace();
            } catch (IllegalAccessException e4) {
                e4.printStackTrace();
            } catch (IllegalArgumentException e5) {
                e5.printStackTrace();
            } catch (InvocationTargetException e6) {
                e6.printStackTrace();
            }
        }
        if (GraveStones.hasGal_Craft) {
            final ItemStack[] inv3 = this.accesInventoryContentsStacks(
                player,
                "getExtendedInventory",
                "micdoodle8.mods.galacticraft.core.inventory.InventoryExtended",
                "GalactiCraft");
            for (int i = 0; i < 10; ++i) {
                final ItemStack is = inv3[i];
                te.list[i + 85] = is;
                inv3[i] = null;
            }
        }
        if (GraveStones.hasMari_Cul) {
            final ItemStack[] inv3 = this
                .accesInventoryContentsStacks(player, "getInventory", "mariculture.magic.MirrorHelper", "Mariculture");
            for (int i = 0; i < 3; ++i) {
                final ItemStack is = inv3[i];
                te.list[i + 95] = is;
            }
            try {
                final ItemStack[] newstack = new ItemStack[4];
                final Class<?> clazz2 = Class.forName("mariculture.magic.MirrorHelper");
                final Method m2 = clazz2.getDeclaredMethod("save", EntityPlayer.class, ItemStack[].class);
                final Object saveEmptyArray = m2.invoke(null, player, newstack);
            } catch (Exception e7) {
                e7.printStackTrace();
            }
        }
    }

    private IInventory accesInventoryContents(final EntityPlayer player, final String methodName, final String path,
        final String modName) {
        try {
            final Class<?> clazz = Class.forName(path);
            final Method m = clazz.getDeclaredMethod(methodName, EntityPlayer.class);
            final Object result = m.invoke(null, player);
            GraveStones.printDebugMessage("Dumping all " + modName + " content into grave");
            return (IInventory) result;
        } catch (Exception e) {
            GraveStones.printDebugMessage(
                "Error Encountered trying to acces " + modName + "  Inventory Content. Please report to mod author");
            return null;
        }
    }

    private ItemStack[] accesInventoryContentsStacks(final EntityPlayer player, final String methodName,
        final String path, final String modName) {
        try {
            final Class<?> clazz = Class.forName(path);
            final Method m = clazz.getDeclaredMethod(methodName, EntityPlayer.class);
            final Object result = m.invoke(null, player);
            GraveStones.printDebugMessage("Dumping all " + modName + " content into grave");
            return (ItemStack[]) result;
        } catch (Exception e) {
            GraveStones.printDebugMessage(
                "Error Encountered trying to acces " + modName + "  Inventory Content. Please report to mod author");
            return null;
        }
    }

    private IInventory accesInventoryContents(final EntityPlayer player, final String methodName, final String path,
        final String declaredField, final String modName) {
        try {
            final Class<?> clazz = Class.forName(path);
            final Method m = clazz.getDeclaredMethod(methodName, EntityPlayer.class);
            final Object result = m.invoke(null, player);
            final Field f = clazz.getDeclaredField(declaredField);
            final IInventory inv = (IInventory) f.get(result);
            GraveStones.printDebugMessage("Dumping all " + modName + " content into grave");
            return inv;
        } catch (Exception e) {
            GraveStones.printDebugMessage(
                "Error Encountered trying to acces " + modName + "  Inventory Content. Please report to mod author");
            return null;
        }
    }
}
