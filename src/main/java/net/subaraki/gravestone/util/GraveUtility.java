



package net.subaraki.gravestone.util;

import net.minecraft.util.*;
import net.minecraft.world.World;
import net.subaraki.gravestone.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.*;
import net.minecraft.client.renderer.texture.DynamicTexture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import cpw.mods.fml.relauncher.*;

public class GraveUtility
{
    public static GraveUtility instance;
    public ResourceLocation SKIN_ABSTRACT_PLAYER;
    public ResourceLocation SKIN_STEVE;
    
    public GraveUtility() {
        this.SKIN_ABSTRACT_PLAYER = null;
        this.SKIN_STEVE = new ResourceLocation("textures/entity/steve.png");
        GraveUtility.instance = this;
    }
    
    public static boolean findClass(final String classPath, final String modName) {
        try {
            final Class classToFind = Class.forName(classPath);
            if (classToFind != null) {
                GraveStones.printDebugMessage("GraveStones Detected " + modName + ". Inventory Content will be dumped into grave");
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public ResourceLocation processPlayerTexture(final EntityPlayer player) {
        if (this.SKIN_ABSTRACT_PLAYER == null) {
            this.SKIN_ABSTRACT_PLAYER = AbstractClientPlayer.locationStevePng;
        }
        try {
            if (player != null && player.getCommandSenderName().length() > 0) {
            	GameProfile gameprofile = player.getGameProfile();
                Minecraft minecraft = Minecraft.getMinecraft();
                Map map = minecraft.func_152342_ad().func_152788_a(gameprofile);
                if (map.containsKey(Type.SKIN)) {
                	this.SKIN_ABSTRACT_PLAYER = minecraft.func_152342_ad().func_152792_a((MinecraftProfileTexture)map.get(Type.SKIN), Type.SKIN);
                }
            }
            else {
                this.SKIN_ABSTRACT_PLAYER = this.SKIN_STEVE;
            }
        }
        catch (Exception ex) {}
        return this.SKIN_ABSTRACT_PLAYER;
    }
    
    @SideOnly(Side.CLIENT)
    public ResourceLocation processPlayerTexture(final String playername) {
        if (this.SKIN_ABSTRACT_PLAYER == null) {
            this.SKIN_ABSTRACT_PLAYER = this.SKIN_STEVE;
        }
        try {
            if (playername != null && playername.length() > 1) {
            	EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(playername);
            	if(player != null) {
                	GameProfile gameprofile = player.getGameProfile();
                    Minecraft minecraft = Minecraft.getMinecraft();
                    Map<Type, MinecraftProfileTexture> map = minecraft.func_152342_ad().func_152788_a(gameprofile);
                    if (map.containsKey(Type.SKIN)) {
                    	this.SKIN_ABSTRACT_PLAYER = minecraft.func_152342_ad().func_152792_a((MinecraftProfileTexture)map.get(Type.SKIN), Type.SKIN);
                    }
            	}
            	else {
                    this.SKIN_ABSTRACT_PLAYER = this.SKIN_STEVE;
            	}
            }
            else {
                this.SKIN_ABSTRACT_PLAYER = this.SKIN_STEVE;
            }
        }
        catch (Exception e) {
            this.SKIN_ABSTRACT_PLAYER = this.SKIN_STEVE;
        }
        if(this.SKIN_ABSTRACT_PLAYER == this.SKIN_STEVE)
        {
        	if(playername != null && playername.length() > 1)
        	{
        		try {
        			BufferedImage bufferedImage = ImageIO.read(new File("./cachedImages/skins/"+playername+".png"));
        			if(bufferedImage.getWidth() == bufferedImage.getHeight())
        			{
        				bufferedImage = bufferedImage.getSubimage(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight()/2);
        			}
        			this.SKIN_ABSTRACT_PLAYER = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(" ", new DynamicTexture(bufferedImage));
        		} catch (IOException e) {
        			e.printStackTrace();
                    this.SKIN_ABSTRACT_PLAYER = this.SKIN_STEVE;
        		}
        	}
            else {
                this.SKIN_ABSTRACT_PLAYER = this.SKIN_STEVE;
            }
        }
        return this.SKIN_ABSTRACT_PLAYER;
    }
}
