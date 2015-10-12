package net.subaraki.gravestone.client.gui;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.subaraki.gravestone.GraveStones;
import net.subaraki.gravestone.client.model.ModelAngel;
import net.subaraki.gravestone.client.model.ModelGraveSkeleton;
import net.subaraki.gravestone.client.model.ModelGraveStone;
import net.subaraki.gravestone.client.model.ModelHead;
import net.subaraki.gravestone.client.model.ModelKnight;
import net.subaraki.gravestone.client.model.ModelPillar;
import net.subaraki.gravestone.client.model.ModelStoneCross;
import net.subaraki.gravestone.client.model.ModelTomb;
import net.subaraki.gravestone.client.model.ModelWoodenGrave;
import net.subaraki.gravestone.handler.ModelHandler;
import net.subaraki.gravestone.handler.GraveTextHandler;
import net.subaraki.gravestone.handler.TextureHandler;
import net.subaraki.gravestone.inventory.ContainerGrave;
import net.subaraki.gravestone.packets.ServerPacket;
import net.subaraki.gravestone.tileentity.TileEntityGravestone;
import net.subaraki.gravestone.tileentity.TileEntityGravestone.EnumGrave;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class GuiGraveContainer extends GuiContainer{

	private short rotationCounter = 0;

	public String gravetext = "" ;

	public EntityPlayer deathPlayer;
	public EntityPlayer playerOpenGui;

	public String nameOfDeathPlayer;
	public String nameOfPlayerOpeningGui;

	private TileEntityGravestone te;

	private String tabText = "MineCraft";

	public static final ModelAngel angel = new ModelAngel();
	public static final ModelGraveSkeleton skeleton = new ModelGraveSkeleton();
	public static final ModelGraveStone gravestone = new ModelGraveStone();
	public static final ModelKnight knight = new ModelKnight();
	public static final ModelPillar pillar = new ModelPillar();
	public static final ModelStoneCross cross = new ModelStoneCross();
	public static final ModelTomb tomb = new ModelTomb();
	public static final ModelWoodenGrave wood = new ModelWoodenGrave();

	private static final ResourceLocation graveGui = new ResourceLocation("subaraki:grave/grave_chest.png");

	private ModelHead modelhead = new ModelHead();

	private static final ItemStack vanilla = new ItemStack(Items.iron_sword);
	private static final ItemStack tcon = new ItemStack(Items.diamond_chestplate);
	private static final ItemStack rpgi = new ItemStack(Items.golden_chestplate);
	private static final ItemStack baub = new ItemStack(Items.gold_ingot);
	private static final ItemStack galacti = new ItemStack(Blocks.glass);
	private static final ItemStack mariculture = new ItemStack(Items.fish);

	public GuiGraveContainer(EntityPlayer player, TileEntityGravestone grave ) {
		super(new ContainerGrave(player.inventory, grave, player));

		deathPlayer = player.worldObj.getPlayerEntityByName(grave.playername);
		playerOpenGui = player;

		nameOfDeathPlayer = grave.playername;
		nameOfPlayerOpeningGui = player.getCommandSenderName();

		te = grave;

		this.xSize = 198;
		this.ySize = 186;

		if(grave != null) {
			if(grave.message1.length() <= 0)
			{
				GraveStones.proxy.setCustomNameBoolean(grave,false);

				if(nameOfDeathPlayer.equals("!Empty!")){
					gravetext = "The Grave is empty !";
				}

				else{
					gravetext = GraveTextHandler.getStringFromMeta(nameOfDeathPlayer, te.modelType);
				}


			}else{
				gravetext = grave.message1+grave.playername + grave.message2;
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
		fontRendererObj.drawString(StatCollector.translateToLocal("grave.container.name"), 8, (ySize - 96) + 2, 0xffffff);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;

		mc.renderEngine.bindTexture(graveGui);
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);

		fontRendererObj.drawSplitString(gravetext, (this.width / 2)+109, (this.height / 2)-89, 100 ,0x000000);
		fontRendererObj.drawSplitString(gravetext, (this.width / 2)+110, (this.height / 2)-90, 100 ,0xffffff);

		if(te.locked.length() > 0){
			fontRendererObj.drawSplitString(te.locked, (this.width / 2)+79, (this.height / 2)+39, 150 ,0x000000);
			fontRendererObj.drawSplitString(te.locked, (this.width / 2)+80, (this.height / 2)+40, 150 ,0xffffff);
		}

		fontRendererObj.drawString(tabText, ((this.width/2)-(xSize/2)) + 5, ((this.height/2)-(ySize/2)) + 5, 0xffffff);

		int render = te.modelType;

		GL11.glPushMatrix();
		mc.renderEngine.bindTexture(TextureHandler.getTextureFromMeta(render));

		float scale = 75f;
		int height = 80;

		if((render == 4) || (render == 5)|| (render == 7) || (render == 8)|| (render == 9)){
			height = 40;
		}

		if((render == 9)){
			scale = 60f;
		}

		if(render == 8) {
			scale = 50f;
		}

		GL11.glTranslatef((this.width / 2) - 150, (this.height / 2) - height, 40);
		GL11.glScaled(scale, scale, -scale);

		float s = -0.65f;
		float s2 = -0.4f;
		if(render == 8 ){
			GL11.glScalef(1, -1, 1);
			GL11.glTranslatef(-0.5f, -2.4f, 0f);
			GL11.glTranslatef(-s, 0f, s);
		}

		if(render == 10){
			GL11.glScalef(1, -1, 1);
			GL11.glTranslatef(-0.5f, -1.5f, 0f);
			GL11.glRotatef(-10, 1f, 0f, 0f);
			GL11.glTranslatef(-s2, 0f, s2);
		}

		GL11.glRotatef(5, 1f, 0f, 0f);
		GL11.glRotatef(rotationCounter++, 0, 1, 0);

		if(render == 8) {
			GL11.glTranslatef(s, 0, -s);
		}

		if(render == 10){
			GL11.glTranslatef(s2, 0, -s2);
		}

		ModelHandler.renderModelFromType(render);
		GL11.glPopMatrix();

		if(render == 5){
			renderBust();
		}
	}

	ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;
	ResourceLocation steve = new ResourceLocation("textures/entity/steve.png");

	private void renderBust()
	{
		GL11.glPushMatrix();
		if(playerOpenGui != null)
		{
			try{
				if ((nameOfDeathPlayer != null) && (nameOfDeathPlayer.length() > 0))
				{
					resourcelocation = AbstractClientPlayer.getLocationSkin(nameOfDeathPlayer);
					AbstractClientPlayer.getDownloadImageSkin(resourcelocation, nameOfDeathPlayer);

				}else{
					resourcelocation = steve;
				}
				Minecraft.getMinecraft().renderEngine.bindTexture(resourcelocation);
			}catch(Throwable e){}

			GL11.glTranslatef((this.width / 2)-150, (this.height / 2)-40, 40);
			GL11.glScaled(50, 50, -50);
			GL11.glRotatef(5, 1f, 0f, 0f);
			GL11.glRotatef(rotationCounter, 0, 1, 0);

			ModelHandler.modelhead.renderHead(0.0625f);
		}
		GL11.glPopMatrix();

	}


	@Override
	public void initGui() {
		super.initGui();

		this.buttonList.clear();
		int i = 0;
		int i2 = 33;
		int x = ((this.width/2) - (xSize/2)) + 4;
		int y = ((this.height/2) - (ySize/2)) - 19;

		buttonList.add(new GuiTabButton(0, x     , y, 35 , 20, "", te.tab == 0, this.vanilla, fontRendererObj));
		i += i2;
		if(GraveStones.hasRpgI){
			buttonList.add(new GuiTabButton(1, x + i , y, 35 , 20, "", te.tab == 1, this.rpgi, fontRendererObj));
			i+= i2;
		}
		if(GraveStones.hasTC){
			buttonList.add(new GuiTabButton(2, x + i , y, 35 , 20, "", te.tab == 2, this.tcon,fontRendererObj));
			i+= i2;
		}

		if(GraveStones.hasBaubel){
			buttonList.add(new GuiTabButton(3, x + i , y, 35 , 20, "", te.tab == 3, this.baub,fontRendererObj));
			i+= i2;
		}
		if(GraveStones.hasGalacti){
			buttonList.add(new GuiTabButton(4, x + i , y, 35 , 20, "", te.tab == 4, this.galacti,fontRendererObj));
			i+=i2;
		}
		if(GraveStones.hasMariCulture){
			buttonList.add(new GuiTabButton(5, x + i , y, 35 , 20, "", te.tab == 5, this.mariculture,fontRendererObj));
			i+=i;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		super.actionPerformed(button);

		updateInventory(button.id);

		if(button.id == 0)
			tabText = "MineCraft";

		if(button.id == 1)
			tabText = "Rpg Inventory";

		if(button.id == 2)
			tabText = "Tinkers Construct";

		if(button.id == 3)
			tabText = "Baubel Inventory";

		if(button.id == 4)
			tabText = "Galacticraft";

		if(button.id == 5)
			tabText = "Mariculture";

		initGui();
	}

	private void updateInventory(int i){

		ByteBuf buf = Unpooled.buffer();
		ByteBufOutputStream out = new ByteBufOutputStream(buf);

		try {

			out.writeInt(ServerPacket.CHANGE_GRAVE);
			out.writeInt(te.xCoord);
			out.writeInt(te.yCoord);
			out.writeInt(te.zCoord);
			out.writeInt(i);

			GraveStones.channel.sendToServer(new FMLProxyPacket(buf,"gravestone"));

			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		switch(i){
		case 0:
			te.changeGrave(EnumGrave.VANILLA);
			break;
		case 1:
			te.changeGrave(EnumGrave.RPGI);
			break;
		case 2:
			te.changeGrave(EnumGrave.TC);
			break;
		case 3:
			te.changeGrave(EnumGrave.BAUBEL);
			break;
		case 4:
			te.changeGrave(EnumGrave.GALACTICRAFT);
			break;
		case 5:
			te.changeGrave(EnumGrave.MARICULTURE);
			break;
		}

		te.tab = i;
	}
}