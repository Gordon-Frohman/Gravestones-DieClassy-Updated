
package net.subaraki.gravestone;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.subaraki.gravestone.client.model.ModelCubeWorld;
import net.subaraki.gravestone.client.renderer.RenderGrave;
import net.subaraki.gravestone.client.renderer.TileEntitySpecialRendererGrave;
import net.subaraki.gravestone.handler.ConfigHandler;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import net.subaraki.gravestone.util.Constants;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    public static KeyBinding keyGui;

    @Override
    public void preInit() {
        if (ConfigHandler.enableGravesTroughKey) {
            ClientRegistry.registerKeyBinding(ClientProxy.keyGui);
        }
        ClientRegistry.bindTileEntitySpecialRenderer(
            TileEntityGravestone.class,
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
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public boolean usingRussianLanguage() {
        return Minecraft.getMinecraft()
            .getLanguageManager()
            .getCurrentLanguage()
            .getLanguageCode()
            .equals("ru_RU");
    }

    static {
        ClientProxy.keyGui = new KeyBinding(
            StatCollector.translateToLocal("pickGrave.settings.0"),
            50,
            StatCollector.translateToLocal("pickGrave.settings.category"));
    }
}
