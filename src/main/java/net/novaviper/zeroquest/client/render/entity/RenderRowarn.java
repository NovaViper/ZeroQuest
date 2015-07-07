package net.novaviper.zeroquest.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.novaviper.zeroquest.common.entity.creature.EntityRowarn;
import net.novaviper.zeroquest.common.util.ResourceReference;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderRowarn extends RenderLiving {
	float modelHeight = 2.9f;

	public RenderRowarn(RenderManager p_i46128_1_, ModelBase p_i46128_2_, float p_i46128_3_) {

		super(p_i46128_1_, p_i46128_2_, p_i46128_3_);
	}

	protected ResourceLocation getEntityTexture(EntityRowarn p_110775_1_) {
		return ResourceReference.rowaren;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return this.getEntityTexture((EntityRowarn) p_110775_1_);
	}
}