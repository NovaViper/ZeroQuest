package common.zeroquest.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import common.zeroquest.entity.tileentity.TileEntityNileWorkbench;
import common.zeroquest.inventory.ContainerNileWorkbench;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.ResourceReference;

@SideOnly(Side.CLIENT)
public class GuiNileWorkbench extends GuiContainer {

	public GuiNileWorkbench(InventoryPlayer playerInv, TileEntityNileWorkbench tileTable, World worldIn, BlockPos blockPosition) {
		super(new ContainerNileWorkbench(tileTable, playerInv, worldIn, blockPosition));
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int z) {
		this.fontRendererObj.drawString(StatCollector.translateToLocal("Crafting"), 30, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the
	 * items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(ResourceReference.nileTableGui);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
