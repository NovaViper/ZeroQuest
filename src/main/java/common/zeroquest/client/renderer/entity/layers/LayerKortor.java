package common.zeroquest.client.renderer.entity.layers;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import common.zeroquest.client.renderer.entity.RenderKortor;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.lib.Constants;

@SideOnly(Side.CLIENT)
public class LayerKortor implements LayerRenderer {
	private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.modid + ":" + "textures/entity/kortor/kortor_saddle.png");
	private static final ResourceLocation Male = new ResourceLocation(Constants.modid + ":" + "textures/entity/kortor/kortor_male.png");
	private final RenderKortor field_177146_b;
	private static final String __OBFID = "CL_00002414";

	public LayerKortor(RenderKortor p_i46113_1_) {
		this.field_177146_b = p_i46113_1_;
	}

	public void func_177145_a(EntityKortor entity, float par1, float par2, float par3, float par4, float par5, float par6, float par7) {
		if (entity.getSaddled() && !entity.isInvisible()) {
			this.field_177146_b.bindTexture(TEXTURE);
			this.field_177146_b.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
		}
		if (entity.getGender() == true && !entity.isInvisible()) {
			this.field_177146_b.bindTexture(Male);
			this.field_177146_b.getMainModel().render(entity, par1, par2, par4, par5, par6, par7);
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return true;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entity, float par1, float par2, float par3, float par4, float par5, float par6, float par7) {
		this.func_177145_a((EntityKortor) entity, par1, par2, par3, par4, par5, par6, par7);
	}
}