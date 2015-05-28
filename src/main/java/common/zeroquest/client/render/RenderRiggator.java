package common.zeroquest.client.render;

import org.lwjgl.opengl.GL11;

import common.zeroquest.entity.EntityRiggator;
import common.zeroquest.entity.EntityRowarn;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.ResourceReference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRiggator extends RenderLiving {
	public RenderRiggator(RenderManager p_i46128_1_, ModelBase p_i46128_2_, float p_i46128_3_) {

		super(p_i46128_1_, p_i46128_2_, p_i46128_3_);
	}

	protected ResourceLocation getEntityTexture(EntityRiggator p_110775_1_) {
		return ResourceReference.riggator;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return this.getEntityTexture((EntityRiggator) p_110775_1_);
	}
}
