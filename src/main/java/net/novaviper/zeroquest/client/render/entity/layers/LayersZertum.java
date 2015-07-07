package net.novaviper.zeroquest.client.render.entity.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.novaviper.zeroquest.client.render.entity.zertum.RenderZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityZertum;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.lib.DataValues;
import net.novaviper.zeroquest.common.util.ResourceReference;

@SideOnly(Side.CLIENT)
public class LayersZertum implements LayerRenderer {

	private final RenderZertum renderer;

	public LayersZertum(RenderZertum p_177145_1_) {
		this.renderer = p_177145_1_;
	}

	public void func_177145_a(EntityZertum entity, float par1, float par2, float par3, float par4, float par5, float par6, float par7) {
		if (!entity.isInvisible()) {
			if (!entity.hasEvolved()) {
				if (entity.isTamed()) {
					this.renderer.bindTexture(ResourceReference.getZLayers("collar"));
					EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(entity.getCollarColor().getMetadata());
					float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
					GlStateManager.color(afloat[0], afloat[1], afloat[2]);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}
				if (entity.isTamed() && entity.getHealth() <= Constants.lowHP) {
					this.renderer.bindTexture(ResourceReference.getZLayers("dying"));
					GlStateManager.color(1f, 1f, 1f);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}
				if (entity.isTamed() && entity.isSaddled()) {
					this.renderer.bindTexture(ResourceReference.getZLayers("saddle"));
					GlStateManager.color(1f, 1f, 1f);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}
				if (entity.isTamed() && entity.getGender() == true) {
					this.renderer.bindTexture(ResourceReference.getZMaleTameLayers(""));
					GlStateManager.color(1f, 1f, 1f);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}
				if (!entity.isTamed() && entity.getGender() == true) {
					this.renderer.bindTexture(ResourceReference.getZMaleLayers(""));
					GlStateManager.color(1f, 1f, 1f);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}
			}
			else if (entity.hasEvolved() && !entity.inFinalStage()) {
				if (entity.isTamed()) {
					this.renderer.bindTexture(ResourceReference.getZELayers("collar"));
					EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(entity.getCollarColor().getMetadata());
					float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
					GlStateManager.color(afloat[0], afloat[1], afloat[2]);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}

				if (entity.isTamed() && entity.getHealth() <= Constants.lowHP) {
					this.renderer.bindTexture(ResourceReference.getZELayers("dying"));
					GlStateManager.color(1f, 1f, 1f);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}

				if (entity.isTamed() && entity.isSaddled()) {
					this.renderer.bindTexture(ResourceReference.getZELayers("saddle"));
					GlStateManager.color(1f, 1f, 1f);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}
				if (entity.getGender() == true) {
					if (entity.isTamed()) {
						this.renderer.bindTexture(ResourceReference.getZEMaleLayers(""));
						GlStateManager.color(1f, 1f, 1f);
						this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
					}
				}
			}
			else if (entity.hasEvolved() && entity.inFinalStage()) {
				if (entity.isTamed()) {
					this.renderer.bindTexture(ResourceReference.getZFinalLayers("collar"));
					EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(entity.getCollarColor().getMetadata());
					float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
					GlStateManager.color(afloat[0], afloat[1], afloat[2]);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}

				if (entity.isTamed() && entity.getHealth() <= Constants.lowHP) {
					this.renderer.bindTexture(ResourceReference.getZFinalLayers("dying"));
					GlStateManager.color(1f, 1f, 1f);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}

				if (entity.isTamed() && entity.isSaddled()) {
					this.renderer.bindTexture(ResourceReference.getZFinalLayers("saddle"));
					GlStateManager.color(1f, 1f, 1f);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}
				if (entity.getGender() == true) {
					this.renderer.bindTexture(ResourceReference.getZFinalMaleLayers(""));
					GlStateManager.color(1f, 1f, 1f);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}
			}
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return true;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entity, float par1, float par2, float par3, float par4, float par5, float par6, float par7) {
		this.func_177145_a((EntityZertum) entity, par1, par2, par3, par4, par5, par6, par7);
	}
}
