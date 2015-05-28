package common.zeroquest.client.render.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import common.zeroquest.client.render.RenderMetalZertum;
import common.zeroquest.entity.zertum.EntityMetalZertum;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.DataValues;
import common.zeroquest.lib.ResourceReference;

@SideOnly(Side.CLIENT)
public class LayersMetalZertum implements LayerRenderer {

	private final RenderMetalZertum renderer;

	public LayersMetalZertum(RenderMetalZertum p_177145_1_) {
		this.renderer = p_177145_1_;
	}

	public void func_177145_a(EntityMetalZertum entity, float par1, float par2, float par3, float par4, float par5, float par6, float par7) {
		if (!entity.isInvisible()) {
			if (!entity.hasEvolved()) {
				if (entity.isTamed()) {
					this.renderer.bindTexture(ResourceReference.getZLayers("collar"));
					EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(entity.getCollarColor().getMetadata());
					float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
					GlStateManager.color(afloat[0], afloat[1], afloat[2]);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}
				if (entity.isTamed() && entity.getHealth() <= DataValues.lowHP) {
					this.renderer.bindTexture(ResourceReference.getZLayers("dying"));
					GlStateManager.color(1f, 1f, 1f);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}
				if (entity.isTamed() && entity.isSaddled()) {
					this.renderer.bindTexture(ResourceReference.getZLayers("saddle"));
					GlStateManager.color(1f, 1f, 1f);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}
				if (entity.getGender() == true) {
					this.renderer.bindTexture(ResourceReference.getZMaleLayers("m"));
					GlStateManager.color(1f, 1f, 1f);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}
			}
			else if (entity.hasEvolved()) {
				if (entity.isTamed()) {
					this.renderer.bindTexture(ResourceReference.getZELayers("collar"));
					EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(entity.getCollarColor().getMetadata());
					float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
					GlStateManager.color(afloat[0], afloat[1], afloat[2]);
					this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
				}

				if (entity.isTamed() && entity.getHealth() <= DataValues.lowHP) {
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
						this.renderer.bindTexture(ResourceReference.getZEMaleLayers("m"));
						GlStateManager.color(1f, 1f, 1f);
						this.renderer.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
					}
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
		this.func_177145_a((EntityMetalZertum) entity, par1, par2, par3, par4, par5, par6, par7);
	}
}
