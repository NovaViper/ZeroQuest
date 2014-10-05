package common.zeroquest.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import common.zeroquest.client.renderer.model.ModelNileTable;

public class ItemRendererNileTable implements IItemRenderer{
	TileEntitySpecialRenderer render;
	private TileEntity entity;
	private ModelNileTable model;
	
	public ItemRendererNileTable(TileEntitySpecialRenderer render, TileEntity entity){
		this.entity = entity;
	    this.render = render;
		this.model = new ModelNileTable();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
			this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F);
		}
	}
