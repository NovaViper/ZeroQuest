package common.zeroquest.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityDarkPortalFX extends EntityFX {
	private final float portalParticleScale;
	private final double portalPosX;
	private final double portalPosY;
	private final double portalPosZ;
	private static final String __OBFID = "CL_00000921";

	public EntityDarkPortalFX(World worldIn, double p_i46351_2_, double p_i46351_4_, double p_i46351_6_, double p_i46351_8_, double p_i46351_10_, double p_i46351_12_) {
		super(worldIn, p_i46351_2_, p_i46351_4_, p_i46351_6_, p_i46351_8_, p_i46351_10_, p_i46351_12_);
		this.motionX = p_i46351_8_;
		this.motionY = p_i46351_10_;
		this.motionZ = p_i46351_12_;
		this.portalPosX = this.posX = p_i46351_2_;
		this.portalPosY = this.posY = p_i46351_4_;
		this.portalPosZ = this.posZ = p_i46351_6_;
		this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
		float f = this.rand.nextFloat() * 0.1F + 0.2F;
		this.particleRed = f;
		this.particleGreen = f;
		this.particleBlue = f;
		this.particleMaxAge = (int) (Math.random() * 10.0D) + 40;
		this.noClip = true;
		this.setParticleTextureIndex((int) (Math.random() * 8.0D));
	}

	@Override
	public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
		float f6 = (this.particleAge + p_180434_3_) / this.particleMaxAge;
		f6 = 1.0F - f6;
		f6 *= f6;
		f6 = 1.0F - f6;
		this.particleScale = this.portalParticleScale * f6;
		super.func_180434_a(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
	}

	@Override
	public int getBrightnessForRender(float p_70070_1_) {
		int i = super.getBrightnessForRender(p_70070_1_);
		float f1 = (float) this.particleAge / (float) this.particleMaxAge;
		f1 *= f1;
		f1 *= f1;
		int j = i & 255;
		int k = i >> 16 & 255;
		k += (int) (f1 * 15.0F * 16.0F);

		if (k > 240) {
			k = 240;
		}

		return j | k << 16;
	}

	/**
	 * Gets how bright this entity is.
	 */
	@Override
	public float getBrightness(float p_70013_1_) {
		float f1 = super.getBrightness(p_70013_1_);
		float f2 = (float) this.particleAge / (float) this.particleMaxAge;
		f2 = f2 * f2 * f2 * f2;
		return f1 * (1.0F - f2) + f2;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		float f = (float) this.particleAge / (float) this.particleMaxAge;
		float f1 = f;
		f = -f + f * f * 2.0F;
		f = 1.0F - f;
		this.posX = this.portalPosX + this.motionX * f;
		this.posY = this.portalPosY + this.motionY * f + (1.0F - f1);
		this.posZ = this.portalPosZ + this.motionZ * f;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		private static final String __OBFID = "CL_00002590";

		@Override
		public EntityFX getEntityFX(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
			return new EntityDarkPortalFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
		}
	}
}