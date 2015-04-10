package common.zeroquest.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import common.zeroquest.core.proxy.ClientProxy;
import common.zeroquest.entity.zertum.EntityIceZertum;

public class EntityIceball extends EntityThrowable {
	private static final String __OBFID = "CL_00001722";
	EntityLivingBase entity;

	public EntityIceball(World p_i1773_1_) {
		super(p_i1773_1_);
	}

	public EntityIceball(World p_i1774_1_, EntityLivingBase p_i1774_2_) {
		super(p_i1774_1_, p_i1774_2_);
		this.entity = p_i1774_2_;
	}

	public EntityIceball(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_) {
		super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
	}

	@Override
	public void onUpdate() {
		if (worldObj.isRemote) {
			ClientProxy.spawnIceParticle(this);
		}
		super.onUpdate();
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@Override
	protected void onImpact(MovingObjectPosition movingObject) {
		if (movingObject.entityHit != null) {
			if (entity instanceof EntityIceZertum) {
				EntityIceZertum zertum = (EntityIceZertum) entity;
				byte b0 = (byte) zertum.talents.getLevel("frigidfrost");

				movingObject.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.getThrower()), b0);
			}
		}
		else {

			BlockPos blockpos = movingObject.getBlockPos().offset(movingObject.sideHit);

			if (this.worldObj.isAirBlock(blockpos)) {
				this.worldObj.setBlockState(blockpos, Blocks.ice.getDefaultState());
			}
		}

		for (int i = 0; i < 8; ++i) {
			this.worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
		}

		for (int i = 0; i < 8; ++i) {
			this.worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}

		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}
}