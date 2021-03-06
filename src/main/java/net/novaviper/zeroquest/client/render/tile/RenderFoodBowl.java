package net.novaviper.zeroquest.client.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.novaviper.zeroquest.client.model.ModelFoodBowl;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.util.ResourceReference;

import org.lwjgl.opengl.GL11;

public class RenderFoodBowl extends TileEntitySpecialRenderer {

	private final ModelFoodBowl model;

	public RenderFoodBowl() {
		this.model = new ModelFoodBowl();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f, int p_180535_9_) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(0, 0F, 0F, 0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		this.bindTexture(ResourceReference.foodBowlModel);
		GL11.glPushMatrix();
		model.renderModel(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
