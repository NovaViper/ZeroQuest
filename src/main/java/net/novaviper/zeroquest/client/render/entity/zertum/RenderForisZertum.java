package net.novaviper.zeroquest.client.render.entity.zertum;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.novaviper.zeroquest.client.model.ModelZertumStage1;
import net.novaviper.zeroquest.client.model.ModelZertumStage2;
import net.novaviper.zeroquest.client.model.ModelZertumStage3;
import net.novaviper.zeroquest.client.render.entity.layers.LayersForisZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityForisZertum;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.util.ResourceReference;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderForisZertum extends RenderLiving {

	private final ModelBase model1, model2, model3;

	public RenderForisZertum(RenderManager render, ModelBase base, float par1) {
		super(render, base, par1);
		this.addLayer(new LayersForisZertum(this));
		model1 = base; // assign from the arguments
		model2 = new ModelZertumStage2(); // or create new ones directly
		model3 = new ModelZertumStage3();
	}

	public void func_177135_a(EntityForisZertum entity, double par1, double par2, double par3, float par4, float par5) {
		if (entity.isWolfWet()) {
			float f2 = entity.getBrightness(par5) * entity.getShadingWhileWet(par5);
			GlStateManager.color(f2, f2, f2);
		}

		super.doRender(entity, par1, par2, par3, par4, par5);
	}

	protected ResourceLocation getEntityTexture(EntityForisZertum entity) {
		if (!entity.hasEvolved()) {
			if (entity.isTamed()) {
				return ResourceReference.getZTameSkins("f");
			}
			else if (entity.isAngry()) {
				return ResourceReference.getZAngrySkins("f");
			}
			else if (!entity.isTamed() && !entity.isAngry()) {
				return ResourceReference.getZWildSkins("f");
			}
		}
		else if (entity.hasEvolved()) {
			if (!entity.inFinalStage()) {
				return ResourceReference.getZEvoSkins("f");
			}
			else if (entity.inFinalStage()) {
				return ResourceReference.getZFinalSkins("f");
			}
		}
		return null;
	}

	@Override
	public void doRender(EntityLiving entity, double par1, double par2, double par3, float par4, float par5) {
		EntityForisZertum zertum = (EntityForisZertum) entity;
		if (!zertum.hasEvolved() && !zertum.inFinalStage()) {
			this.mainModel = model1;
		}
		else if (zertum.hasEvolved() && !zertum.inFinalStage()) {
			this.mainModel = model2;
		}
		else if (zertum.hasEvolved() && zertum.inFinalStage()) {
			this.mainModel = model3;
		}

		this.func_177135_a((EntityForisZertum) entity, par1, par2, par3, par4, par5);
	}

	@Override
	public void doRender(EntityLivingBase entity, double par1, double par2, double par3, float par4, float par5) {
		this.func_177135_a((EntityForisZertum) entity, par1, par2, par3, par4, par5);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return this.getEntityTexture((EntityForisZertum) p_110775_1_);
	}

	@Override
	public void passSpecialRender(EntityLivingBase entityLivingBase, double par1, double par2, double par3) {
		EntityForisZertum dog = (EntityForisZertum) entityLivingBase;

		if (!dog.getPetName().isEmpty()) {
			super.passSpecialRender(entityLivingBase, par1, par2, par3);
		}
	}

	@Override
	protected void renderOffsetLivingLabel(Entity entity, double x, double y, double z, String displayName, float scale, double distanceFromPlayer) {
		super.renderOffsetLivingLabel(entity, x, y, z, displayName, scale, distanceFromPlayer);

		EntityForisZertum dog = (EntityForisZertum) entity;

		if (distanceFromPlayer < 100.0D) {

			y += this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * 0.016666668F * 0.7F;

			String tip = dog.mode.getMode().getTip();

			String label = String.format("%s[%d]", tip, dog.getZertumHunger());

			if (dog.isPlayerSleeping()) {
				this.renderLivingLabel(dog, label, x, y - 0.5D, z, 64, 0.7F);
			}
			else {
				this.renderLivingLabel(dog, label, x, y, z, 64, 0.7F);
			}
		}

		if (distanceFromPlayer < 100.0D) {
			y += this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * 0.016666668F * 0.5F;

			if (this.renderManager.livingPlayer.isSneaking()) {
				EntityLivingBase owner = dog.getOwnerEntity();
				if (owner != null) {
					this.renderLivingLabel(dog, owner.getDisplayName().getUnformattedText(), x, y, z, 5, 0.5F);
				}
				else {
					this.renderLivingLabel(dog, dog.getOwnerName(), x, y, z, 5, 0.5F);
				}
			}
		}
	}

	protected void renderLivingLabel(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_, double p_147906_7_, int p_147906_9_, float scale) {
		double d3 = p_147906_1_.getDistanceSqToEntity(this.renderManager.livingPlayer);

		if (d3 <= p_147906_9_ * p_147906_9_) {
			FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
			float f1 = 0.016666668F * scale;
			GlStateManager.pushMatrix();
			GlStateManager.translate((float) p_147906_3_ + 0.0F, (float) p_147906_5_ + p_147906_1_.height + 0.5F, (float) p_147906_7_);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			GlStateManager.scale(-f1, -f1, f1);
			GlStateManager.disableLighting();
			GlStateManager.depthMask(false);
			GlStateManager.disableDepth();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			Tessellator tessellator = Tessellator.getInstance();
			WorldRenderer worldrenderer = tessellator.getWorldRenderer();
			byte b0 = 0;

			if (p_147906_2_.equals("deadmau5")) {
				b0 = -10;
			}

			GlStateManager.disableTexture2D();
			worldrenderer.startDrawingQuads();
			int j = fontrenderer.getStringWidth(p_147906_2_) / 2;
			worldrenderer.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
			worldrenderer.addVertex(-j - 1, -1 + b0, 0.0D);
			worldrenderer.addVertex(-j - 1, 8 + b0, 0.0D);
			worldrenderer.addVertex(j + 1, 8 + b0, 0.0D);
			worldrenderer.addVertex(j + 1, -1 + b0, 0.0D);
			tessellator.draw();
			GlStateManager.enableTexture2D();
			fontrenderer.drawString(p_147906_2_, -fontrenderer.getStringWidth(p_147906_2_) / 2, b0, 553648127);
			GlStateManager.enableDepth();
			GlStateManager.depthMask(true);
			fontrenderer.drawString(p_147906_2_, -fontrenderer.getStringWidth(p_147906_2_) / 2, b0, -1);
			GlStateManager.enableLighting();
			GlStateManager.disableBlend();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.popMatrix();
		}
	}

}