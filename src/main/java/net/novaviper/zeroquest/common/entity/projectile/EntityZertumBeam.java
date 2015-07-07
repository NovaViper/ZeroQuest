package net.novaviper.zeroquest.common.entity.projectile;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.entity.util.ModeUtil.EnumMode;

/**
 * @author ProPercivalalb
 */
public class EntityZertumBeam extends EntityThrowable {

	public EntityZertumBeam(World par1World) {
		super(par1World);
	}

	public EntityZertumBeam(World par1World, EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
	}

	public EntityZertumBeam(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingObject) {
		if (movingObject.entityHit != null && movingObject.entityHit instanceof EntityLiving) {
			byte var2 = 0;

			List nearEnts = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(100D, 10D, 100D));
			for (Object o : nearEnts) {
				if (o instanceof EntityZertumEntity) {
					EntityZertumEntity dog = (EntityZertumEntity) o;
					if (!dog.isSitting() && movingObject.entityHit != dog && dog.func_142018_a((EntityLivingBase) movingObject.entityHit, dog.getOwnerEntity()) && this.getThrower() instanceof EntityPlayer && dog.canInteract((EntityPlayer) this.getThrower())) {
						if (dog.getDistanceToEntity(movingObject.entityHit) < this.getTargetDistance(dog) && (dog.mode.isMode(EnumMode.AGGRESIVE) || dog.mode.isMode(EnumMode.TACTICAL))) {
							dog.setAttackTarget((EntityLiving) movingObject.entityHit);
						}
					}
				}
			}
		}

		for (int i = 0; i < 8; ++i) {
			this.worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
		}

		for (int i = 0; i < 8; ++i) {
			this.worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}

		if (isServer()) {
			this.setDead();
		}
	}

	protected double getTargetDistance(EntityZertumEntity dog) {
		IAttributeInstance iattributeinstance = dog.getEntityAttribute(SharedMonsterAttributes.followRange);
		return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
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
