package common.zeroquest.renderer;

import common.zeroquest.renderer.model.ModelNileTable;
import common.zeroquest.tileentity.TileEntityNileTable;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererNileTable implements IItemRenderer{

	private ModelNileTable model;
	
	public ItemRendererNileTable(){
		this.model = new ModelNileTable();
	}
	
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRenderer.instance.renderTileEntityAt(new TileEntityNileTable(), 0.0D, 0.0D, 0.0D, 0.0F);
	}

}
