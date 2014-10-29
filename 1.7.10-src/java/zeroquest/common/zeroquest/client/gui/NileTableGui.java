package common.zeroquest.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;



import org.lwjgl.opengl.GL11;

import common.zeroquest.ZeroQuest;
import common.zeroquest.inventory.ContainerNileTable;
import common.zeroquest.tileentity.TileEntityNileTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class NileTableGui extends GuiContainer{



	private static final ResourceLocation field_110422_t = new ResourceLocation(ZeroQuest.modid + ":" + "textures/gui/niletable.png");


	public NileTableGui(InventoryPlayer playerInv, TileEntityNileTable tileFabTable, World world, int x, int y, int z)
	{
	         super(new ContainerNileTable(tileFabTable, playerInv, world, x, y, z));
	}


	/**
	         * Draw the foreground layer for the GuiContainer (everything in front of the items)
	         */
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int z)	
	{
	         this.fontRendererObj.drawString(StatCollector.translateToLocal("Crafting"), 30, 6, 4210752);
	         this.fontRendererObj.drawString(StatCollector.translateToLocal("Inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	/**
	         * Draw the background layer for the GuiContainer (everything behind the items)
	         */
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
	         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	         this.mc.getTextureManager().bindTexture(field_110422_t);
	         int k = (this.width - this.xSize) / 2;
	         int l = (this.height - this.ySize) / 2;
	         this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}


	 @Override
	    public void onGuiClosed() {
	        super.onGuiClosed();
	    }


}
