package common.zeroquest.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityHeartFX extends EntityFX {
	float particleScaleOverTime;
	private static final String __OBFID = "CL_00000909";

	public EntityHeartFX(World worldIn, double p_i1211_2_, double p_i1211_4_, double p_i1211_6_, double p_i1211_8_, double p_i1211_10_, double p_i1211_12_) {
		this(worldIn, p_i1211_2_, p_i1211_4_, p_i1211_6_, p_i1211_8_, p_i1211_10_, p_i1211_12_, 2.0F);
	}

	protected EntityHeartFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i46354_8_, double p_i46354_10_, double p_i46354_12_, float scale) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.009999999776482582D;
		this.motionY *= 0.009999999776482582D;
		this.motionZ *= 0.009999999776482582D;
		this.motionY += 0.1D;
		this.particleScale *= 0.75F;
		this.particleScale *= scale;
		this.particleScaleOverTime = this.particleScale;
		this.particleMaxAge = 16;
		this.noClip = false;
		this.setParticleTextureIndex(80);
	}

	/**
	 * Renders the particle
	 * 
	 * @param worldRendererIn
	 *        The WorldRenderer instance
	 */
	@Override
	public void renderParticle(WorldRenderer worldRendererIn, Entity entityIn, float partialTicks, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
		float f6 = (this.particleAge + partialTicks) / this.particleMaxAge * 32.0F;
		f6 = MathHelper.clamp_float(f6, 0.0F, 1.0F);
		this.particleScale = this.particleScaleOverTime * f6;
		super.renderParticle(worldRendererIn, entityIn, partialTicks, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}

		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		if (this.posY == this.prevPosY) {
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.8600000143051147D;
		this.motionY *= 0.8600000143051147D;
		this.motionZ *= 0.8600000143051147D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}

	@SideOnly(Side.CLIENT)
	public static class AngryVillagerFactory implements IParticleFactory {
		private static final String __OBFID = "CL_00002600";

		@Override
		public EntityFX getEntityFX(int p_178902_1_, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			EntityHeartFX entityheartfx = new EntityHeartFX(worldIn, xCoordIn, yCoordIn + 0.5D, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
			entityheartfx.setParticleTextureIndex(81);
			entityheartfx.setRBGColorF(1.0F, 1.0F, 1.0F);
			return entityheartfx;
		}
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		private static final String __OBFID = "CL_00002599";

		@Override
		public EntityFX getEntityFX(int p_178902_1_, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new EntityHeartFX(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		}
	}
}