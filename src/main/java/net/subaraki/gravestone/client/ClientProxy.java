
package net.subaraki.gravestone.client;

import net.minecraft.client.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.client.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.*;
import net.subaraki.gravestone.*;
import net.subaraki.gravestone.client.model.*;
import net.subaraki.gravestone.client.renderer.*;
import net.subaraki.gravestone.common.*;
import net.subaraki.gravestone.handler.*;
import net.subaraki.gravestone.tileentity.*;
import net.subaraki.gravestone.util.*;

import cpw.mods.fml.client.registry.*;

public class ClientProxy extends CommonProxy {

    public static KeyBinding keyGui;

    @Override
    public void preInit() {
        if (ConfigHandler.enableGravesTroughKey) {
            ClientRegistry.registerKeyBinding(ClientProxy.keyGui);
        }
        ClientRegistry.bindTileEntitySpecialRenderer(
            (Class) TileEntityGravestone.class,
            (TileEntitySpecialRenderer) new TileEntitySpecialRendererGrave());
        Constants.angelStatue = new ModelCubeWorld(
            ModelCubeWorld.class.getResourceAsStream("/assets/grave/models/angelStatue.cub"));
        Constants.barrel = new ModelCubeWorld(
            ModelCubeWorld.class.getResourceAsStream("/assets/grave/models/barrel.cub"));
        MinecraftForgeClient
            .registerItemRenderer(Item.getItemFromBlock(GraveStones.graveStone), (IItemRenderer) new RenderGrave());
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return (EntityPlayer) Minecraft.getMinecraft().thePlayer;
    }

    static {
        ClientProxy.keyGui = new KeyBinding(
            StatCollector.translateToLocal("pickGrave.settings.0"),
            50,
            StatCollector.translateToLocal("pickGrave.settings.category"));
    }
}
