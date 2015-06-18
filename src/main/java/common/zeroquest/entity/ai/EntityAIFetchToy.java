package common.zeroquest.entity.ai;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import common.zeroquest.ModItems;
import common.zeroquest.entity.zertum.EntityZertumEntity;
import common.zeroquest.entity.zertum.util.ModeUtil.EnumMode;
import common.zeroquest.lib.Constants;

/**
 * @author ProPercivalalb
 */
public class EntityAIFetchToy extends EntityAIBase {
	private final EntityZertumEntity theDog;
	private EntityLivingBase theOwner;
	private EntityItem theToy;
	private final World theWorld;
	private final double moveSpeed;
	private final PathNavigate petPathfinder;
	private int tenTickTimer;
	private final float maxDist;
	private final float minDist;
	private boolean preShouldAvoidWater;

	public EntityAIFetchToy(EntityZertumEntity par1EntityDog, double moveSpeed, float minDistance, float maxDistance) {
		this.theDog = par1EntityDog;
		this.theWorld = par1EntityDog.worldObj;
		this.moveSpeed = moveSpeed;
		this.petPathfinder = par1EntityDog.getNavigator();
		this.minDist = minDistance;
		this.maxDist = maxDistance;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		this.theToy = this.getClosestsBone();
		EntityLivingBase possibleOwner = this.theDog.getOwnerEntity();

		if (this.theToy == null) {
			return false;
		}
		else if (possibleOwner == null) {
			this.theToy = null;
			return false;
		}
		else if (this.theDog.isSitting()) {
			this.theToy = null;
			return false;
		}
		else if (!this.theDog.mode.isMode(EnumMode.DOCILE)) {
			this.theToy = null;
			return false;
		}
		else if (this.theDog.riddenByEntity instanceof EntityPlayer) {
			this.theToy = null;
			return false;
		}
		else if (!this.theDog.isTamed()) {
			this.theToy = null;
			return false;
		}
		else if (this.theDog.hasToy()) {
			this.theToy = null;
			return false;
		}
		else if (this.theDog.getHealth() <= Constants.lowHP) {
			this.theToy = null;
			return false;
		}
		else if (this.theDog.getDistanceSqToEntity(this.theToy) > this.maxDist * this.maxDist || this.theDog.getDistanceSqToEntity(this.theToy) < this.minDist * this.minDist) {
			this.theToy = null;
			return false;
		}
		else {
			this.theOwner = possibleOwner;
			return true;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting() {
		return !this.petPathfinder.noPath() && this.theOwner != null && !this.theDog.hasToy() && this.theToy != null && !this.theToy.isDead && !(this.theDog.getDistanceSqToEntity(this.theToy) > this.maxDist * this.maxDist || this.theDog.getDistanceSqToEntity(this.theToy) < this.minDist * this.minDist) && !this.theDog.isSitting();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting() {
		this.tenTickTimer = 0;
		this.preShouldAvoidWater = ((PathNavigateGround) this.theDog.getNavigator()).getAvoidsWater();
		((PathNavigateGround) this.theDog.getNavigator()).setAvoidsWater(false);
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask() {
		this.theOwner = null;
		this.petPathfinder.clearPathEntity();
		((PathNavigateGround) this.theDog.getNavigator()).setAvoidsWater(this.preShouldAvoidWater);
	}

	public EntityItem getClosestsBone() {
		EntityItem entityItem = null;

		if (this.theDog.hasToy()) {
			return null;
		}

		List list = this.theWorld.getEntitiesWithinAABBExcludingEntity(this.theDog, this.theDog.getEntityBoundingBox().expand(maxDist, maxDist, maxDist));
		for (int i = 0; i < list.size(); i++) {
			Entity listEntity = (Entity) list.get(i);

			if ((listEntity instanceof EntityItem) && ((EntityItem) listEntity).getEntityItem().getItem() == ModItems.toy && ((EntityItem) listEntity).getEntityItem().getItemDamage() == 0) {
				entityItem = (EntityItem) listEntity;
			}
		}
		return entityItem;
	}

	@Override
	public void updateTask() {
		this.theDog.getLookHelper().setLookPositionWithEntity(this.theToy, 10.0F, this.theDog.getVerticalFaceSpeed());

		if (!this.theDog.isSitting()) {
			if (--this.tenTickTimer <= 0) {
				this.tenTickTimer = 10;

				this.petPathfinder.tryMoveToEntityLiving(this.theToy, this.moveSpeed);
			}
		}

		if (this.theDog.getDistanceSqToEntity(this.theToy) < 1.5F * 1.5F && !this.theDog.hasToy()) {
			if (this.theToy.isEntityAlive()) {
				this.theToy.attackEntityFrom(DamageSource.generic, 12F);
				this.theDog.setHasToy(true);
				this.theToy = null;
				this.theDog.getNavigator().clearPathEntity();
				this.theDog.setAttackTarget((EntityLivingBase) null);
			}
		}
	}

	public EntityItem getCurrentTarget() {
		return this.theToy;
	}
}
