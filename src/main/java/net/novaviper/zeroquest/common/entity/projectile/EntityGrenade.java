package net.novaviper.zeroquest.common.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityGrenade extends EntityThrowable {
	private static final String __OBFID = "CL_00001722";

	public EntityGrenade(World p_i1773_1_) {
		super(p_i1773_1_);
	}

	public EntityGrenade(World p_i1774_1_, EntityLivingBase p_i1774_2_) {
		super(p_i1774_1_, p_i1774_2_);
	}

	public EntityGrenade(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_) {
		super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
	}

	@Override
	public void onUpdate() {
		this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);

		super.onUpdate();
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@Override
	protected void onImpact(MovingObjectPosition p_70184_1_) {
		if (p_70184_1_.entityHit != null) {
			byte b0 = 0;

			if (p_70184_1_.entityHit instanceof EntityBlaze) {
				b0 = 3;
			}

			p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), b0);
		}

		for (int i = 0; i < 8; ++i) {
			this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}

		if (isServer()) {
			this.setDead();
			if (isServer()) {
				this.worldObj.createExplosion((Entity) null, this.posX, this.posY, this.posZ, 2.5F, true);
			}
		}
	}

	/**
	 * Checks if this entity is running on a client.
	 * 
	 * Required since MCP's isClientWorld returns the exact opposite...
	 * 
	 * @return true if the entity runs on a client or false if it runs on a
	 *         server
	 */
	public boolean isClient() {
		return worldObj.isRemote;
	}

	/**
	 * Checks if this entity is running on a server.
	 * 
	 * @return true if the entity runs on a server or false if it runs on a
	 *         client
	 */
	public boolean isServer() {
		return !worldObj.isRemote;
	}
}