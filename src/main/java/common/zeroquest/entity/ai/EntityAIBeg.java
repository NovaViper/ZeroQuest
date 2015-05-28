package common.zeroquest.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import common.zeroquest.ModItems;
import common.zeroquest.entity.zertum.EntityDarkZertum;
import common.zeroquest.entity.zertum.EntityZertumEntity;

public class EntityAIBeg extends EntityAIBase {
	private final EntityZertumEntity theWolf;
	private EntityPlayer thePlayer;
	private final World worldObject;
	private final float minPlayerDistance;
	private int timeoutCounter;
	private static final String __OBFID = "CL_00001576";

	public EntityAIBeg(EntityZertumEntity entityZertumEntity, float minDistance) {
		this.theWolf = entityZertumEntity;
		this.worldObject = entityZertumEntity.worldObj;
		this.minPlayerDistance = minDistance;
		this.setMutexBits(2);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute() {
		this.thePlayer = this.worldObject.getClosestPlayerToEntity(this.theWolf, this.minPlayerDistance);
		return this.thePlayer == null ? false : this.hasPlayerGotBoneInHand(this.thePlayer);
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting() {
		return !this.thePlayer.isEntityAlive()
				? false
				: (this.theWolf.getDistanceSqToEntity(this.thePlayer) > this.minPlayerDistance * this.minPlayerDistance
						? false
						: this.timeoutCounter > 0 && this.hasPlayerGotBoneInHand(this.thePlayer));
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting() {
		this.theWolf.setBegging(true);
		this.timeoutCounter = 40 + this.theWolf.getRNG().nextInt(40);
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask() {
		this.theWolf.setBegging(false);
		this.thePlayer = null;
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask() {
		this.theWolf.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F, this.theWolf.getVerticalFaceSpeed());
		--this.timeoutCounter;
	}

	private boolean hasPlayerGotBoneInHand(EntityPlayer player) {
		ItemStack stack = player.inventory.getCurrentItem();
		return stack == null
				? false
				: (!this.theWolf.isTamed() && !(this.theWolf instanceof EntityDarkZertum) && stack.getItem() == ModItems.nileBone
						? true
						: !this.theWolf.isTamed() && this.theWolf instanceof EntityDarkZertum && stack.getItem() == ModItems.darkBone
								? true
								: this.theWolf.isBreedingItem(stack) || theWolf.foodValue(stack) > 0);
	}
}
