package net.novaviper.zeroquest.common.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.novaviper.zeroquest.ModBlocks;
import net.novaviper.zeroquest.common.container.InventoryPack;

public class EntityFlamingPoisonball extends EntityFireball {

	public EntityFlamingPoisonball(World par1World) {
		super(par1World);
		this.setSize(0.5F, 0.5F);
	}

	public EntityFlamingPoisonball(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
		super(par1World, par2EntityLivingBase, par3, par5, par7);
		this.setSize(0.5F, 0.5F);
	}

	public EntityFlamingPoisonball(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		this.setSize(0.5F, 0.5F);
	}

	/**
	 * Called when this EntityFireball hits a block or entity.
	 */
	@Override
	protected void onImpact(MovingObjectPosition movingObject) {
		if (isServer()) {
			boolean flag;

			if (movingObject.entityHit != null) {
				flag = movingObject.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5.0F);

				if (flag) {
					this.func_174815_a(this.shootingEntity, movingObject.entityHit);

					if (!movingObject.entityHit.isImmuneToFire()) {
						movingObject.entityHit.setFire(5);
					}
				}
			}
			else {
				flag = true;

				if (this.shootingEntity != null && this.shootingEntity instanceof EntityLiving) {
					flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
				}

				if (flag) {
					BlockPos blockpos = movingObject.getBlockPos().offset(movingObject.sideHit);

					if (this.worldObj.isAirBlock(blockpos)) {
						this.worldObj.setBlockState(blockpos, Blocks.fire.getDefaultState());
					}
				}
			}

			this.setDead();
		}
	}

	/**
	 * Returns true if other Entities should be prevented from moving through
	 * this Entity.
	 */
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		return false;
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