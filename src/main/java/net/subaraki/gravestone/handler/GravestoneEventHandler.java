
package net.subaraki.gravestone.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.subaraki.gravestone.ClientProxy;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.integration.ModIntegration;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GravestoneEventHandler {

    public GravestoneEventHandler() {
        MinecraftForge.EVENT_BUS.register((Object) this);
        FMLCommonHandler.instance()
            .bus()
            .register((Object) this);
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote
            && PlayerGraveData.get((EntityPlayer) event.entity) != null) {
            PlayerGraveData.get((EntityPlayer) event.entity)
                .sync();
        }
    }

    @SubscribeEvent
    public void onEntityConstruction(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer && PlayerGraveData.get((EntityPlayer) event.entity) == null) {
            PlayerGraveData.register((EntityPlayer) event.entity);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void keyHandling(InputEvent.KeyInputEvent evt) {
        if (ConfigHandler.enableGravesTroughKey && ClientProxy.keyGui.isPressed()
            && Minecraft.getMinecraft().currentScreen == null) {
            EntityPlayer p = Minecraft.getMinecraft().thePlayer;
            p.openGui(
                GraveStones.instance,
                GuiHandler.GRAVE_SELECTOR,
                Minecraft.getMinecraft().theWorld,
                (int) p.posX,
                (int) p.posY,
                (int) p.posZ);
        }
    }

    @SubscribeEvent
    public void onCloneEvent(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
        PlayerGraveData dead = PlayerGraveData.get(event.original);
        PlayerGraveData clone = PlayerGraveData.get(event.entityPlayer);
        clone.setGraveModel(dead.getGraveModel());
        clone.setMaleEpitaph(dead.shouldUseMaleEpitaph());
    }

    private Map<UUID, TileEntityGravestone> playerGraves = new HashMap<UUID, TileEntityGravestone>();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onDeathEvent(LivingDeathEvent evt) {
        if (evt.entityLiving instanceof EntityPlayer player) {
            if (player.worldObj.getGameRules()
                .getGameRuleBooleanValue("keepInventory")) return;
            createGraveTE(player);
            World world = player.worldObj;
            int x = MathHelper.floor_double(player.posX);
            int y = MathHelper.floor_double(player.posY);
            int z = MathHelper.floor_double(player.posZ);
            this.scheduleEvent(() -> {
                int localY = y;
                if (world.isAirBlock(x, localY, z)) {
                    if (localY < 0) {
                        // When you die from falling into void, your gravestone should spawn above the bedrock
                        if (world.isAirBlock(x, 0, z)) world.setBlock(x, 0, z, Blocks.bedrock);
                        localY = 1;
                        while (!world.isAirBlock(x, localY, z) && localY < 255) localY++;
                    }
                    while (world.isAirBlock(x, localY, z)) --localY;
                }
                int X = 100;
                int Z = 100;
                int x2 = 0;
                int z2 = 0;
                int dx = 0;
                int dz = -1;
                int t = Math.max(X, Z);
                int maxI = t * t;
                boolean flag = false;
                boolean liquid = false;
                for (int i = 0; i < maxI; ++i) {
                    if (-X / 2 <= x2 && x2 <= X / 2 && -Z / 2 <= z2 && z2 <= Z / 2) {
                        int y2;
                        for (y2 = 0; !world.getBlock(x + x2, localY + 1 + y2, z + z2)
                            .getMaterial()
                            .equals(Material.air); ++y2) {}
                        if (world.getBlock(x + x2, localY + y2, z + z2)
                            .getMaterial()
                            .isLiquid()) {
                            GraveStones.printDebugMessage("You were standing in liquid !");
                            --y2;
                            liquid = true;
                        }
                        if (world.getBlock(x + x2, localY + y2, z + z2)
                            .getMaterial()
                            .isSolid()
                            && (world.getBlock(x + x2, localY + 1 + y2, z + z2)
                                .getMaterial()
                                .equals(Material.air) || liquid)) {
                            GraveStones.printDebugMessage(
                                "Potential grave at " + (x + x2) + " " + (localY + y2) + " " + (z + z2));
                            this.placeGrave(player, x + x2, localY + y2, z + z2);
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
                    for (y3 = 0; !world.getBlock(x, localY + 1 + y3, z)
                        .getMaterial()
                        .equals(Material.air); ++y3) {}
                    if (world.getBlock(x, localY + y3, z)
                        .getMaterial()
                        .isLiquid()) {
                        if (world.getBlock(x, localY + 1 + y3, z)
                            .getMaterial()
                            .equals(Material.air)) {
                            world.setBlock(x, localY + y3, z, Blocks.cobblestone);
                        }
                        this.placeGrave(player, x, localY + y3, z);
                    }
                } else if (!flag) {
                    this.placeGrave(player, x, localY, z);
                }
                playerGraves.remove(player.getUniqueID());
            }, 10);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void playerDeathDrop(PlayerDropsEvent event) {
        if (event.entityLiving instanceof EntityPlayer player) {}
    }

    private void placeGrave(EntityPlayer player, int x, int y, int z) {
        World world = player.worldObj;
        world.setBlock(x, y + 1, z, GraveStones.graveStone);
        TileEntityGravestone te = playerGraves.get(player.getUniqueID());
        if (te != null) {
            world.setTileEntity(x, y + 1, z, te);
            world.markBlockForUpdate(x, y + 1, z);
            te.markDirty();
        }
    }

    private void createGraveTE(EntityPlayer player) {
        TileEntityGravestone te = new TileEntityGravestone();
        InventoryPlayer inv = player.inventory;
        PlayerGraveData pgd = PlayerGraveData.get(player);
        int graveID = pgd.getGraveModel();
        int max = 9;
        if (!ConfigHandler.enableGravesTroughKey) {
            graveID = ConfigHandler.graveOrder[Math.min(player.experienceLevel / ConfigHandler.graveLevel, max)];
        }
        te.setGraveData(player.getCommandSenderName(), graveID, pgd.shouldUseMaleEpitaph());

        for (int i = 0; i < 36; i++) {
            te.mainInv.setInventorySlotContents(i, inv.mainInventory[i]);
            inv.setInventorySlotContents(i, null);
        }

        for (int i = 0; i < 4; i++) {
            te.armor.setInventorySlotContents(i, inv.armorItemInSlot(i));
            inv.setInventorySlotContents(inv.mainInventory.length + i, null);
        }

        this.addOtherInventories(te, player);
        te.checkForItems();

        Vec3 playerLookVec = player.getLookVec();
        double graveRotation = Math.toDegrees(Math.atan2(playerLookVec.zCoord, playerLookVec.xCoord));
        if (graveRotation < 0) graveRotation += 360;
        te.rotation = (float) (Math.round(graveRotation / 15) * 15);
        playerGraves.put(player.getUniqueID(), te);
    }

    private void addOtherInventories(TileEntityGravestone te, EntityPlayer player) {
        for (ModIntegration integration : GraveStones.integrations) integration.storeItems(te, player);
    }

    private List<ScheduledEvent> scheduledEvents = new ArrayList<ScheduledEvent>();

    private void scheduleEvent(Runnable event, int delay) {
        scheduledEvents.add(new ScheduledEvent(delay, event));
    }

    @SubscribeEvent
    public void onWorldTick(WorldTickEvent event) {
        for (ScheduledEvent scheduledEvent : scheduledEvents) scheduledEvent.tick();
    }

    private class ScheduledEvent {

        private int delay;
        private Runnable runnable;

        public ScheduledEvent(int delay, Runnable runnable) {
            this.delay = delay;
            this.runnable = runnable;
        }

        public void tick() {
            if (delay-- == 0) runnable.run();
        }
    }
}
