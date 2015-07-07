package net.novaviper.zeroquest.client.render.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.novaviper.zeroquest.client.render.entity.layers.LayerJakan;
import net.novaviper.zeroquest.common.entity.creature.EntityJakan;
import net.novaviper.zeroquest.common.entity.creature.EntityRowarn;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.util.ResourceReference;

@SideOnly(Side.CLIENT)
public class RenderJakan extends RenderLiving {

	public RenderJakan(RenderManager p_i46128_1_, ModelBase p_i46128_2_, float p_i46128_3_) {

		super(p_i46128_1_, p_i46128_2_, p_i46128_3_);
		this.addLayer(new LayerJakan(this));
	}

	protected ResourceLocation getEntityTexture(EntityJakan p_110775_1_) {
		return p_110775_1_.isTamed() ? ResourceReference.getJakanSkins("_tame")
				: ResourceReference.getJakanSkins("");
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return this.getEntityTexture((EntityJakan) p_110775_1_);
	}
}
