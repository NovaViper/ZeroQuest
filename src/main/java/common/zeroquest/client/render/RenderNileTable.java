package common.zeroquest.client.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import common.zeroquest.ZeroQuest;
import common.zeroquest.client.model.ModelNileTable;
import common.zeroquest.lib.Constants;
import common.zeroquest.util.ResourceReference;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderNileTable extends TileEntitySpecialRenderer {

	private final ModelNileTable model;

	public RenderNileTable() {
		this.model = new ModelNileTable();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f, int p_180535_9_) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(0, 0F, 0F, 0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		this.bindTexture(ResourceReference.nileTableModel);
		GL11.glPushMatrix();
		model.renderModel(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
