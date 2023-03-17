



package net.subaraki.gravestone.client;

import net.subaraki.gravestone.common.*;
import net.minecraft.client.settings.*;
import net.subaraki.gravestone.handler.*;
import cpw.mods.fml.client.registry.*;
import net.subaraki.gravestone.tileentity.*;
import net.minecraft.client.renderer.tileentity.*;
import net.subaraki.gravestone.client.model.*;
import net.subaraki.gravestone.util.*;
import net.subaraki.gravestone.*;
import net.minecraft.item.*;
import net.subaraki.gravestone.client.renderer.*;
import net.minecraftforge.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.*;

public class ClientProxy extends CommonProxy
{
    public static KeyBinding keyGui;
    
    @Override
    public void preInit() {
        if (ConfigHandler.enableGravesTroughKey) {
            ClientRegistry.registerKeyBinding(ClientProxy.keyGui);
        }
        ClientRegistry.bindTileEntitySpecialRenderer((Class)TileEntityGravestone.class, (TileEntitySpecialRenderer)new TileEntitySpecialRendererGrave());
        Constants.angelStatue = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/grave/models/angelStatue.cub"));
        Constants.barrel = new ModelCubeWorld(ModelCubeWorld.class.getResourceAsStream("/assets/grave/models/barrel.cub"));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GraveStones.graveStone), (IItemRenderer)new RenderGrave());
    }
    
    @Override
    public EntityPlayer getClientPlayer() {
        return (EntityPlayer)Minecraft.getMinecraft().thePlayer;
    }
    
    static {
        ClientProxy.keyGui = new KeyBinding("Pick Grave Gui", 50, "gravestonemod");
    }
}
