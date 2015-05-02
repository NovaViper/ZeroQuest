package common.zeroquest.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import common.zeroquest.ModItems;
import common.zeroquest.entity.zertum.EntityDarkZertum;
import common.zeroquest.entity.zertum.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class EntityCustomAIBeg extends EntityAIBase {

	private final EntityZertumEntity theWolf;
	private EntityPlayer thePlayer;
	private final World worldObject;
	private final float minPlayerDistance;
	private int field_75384_e;
	private static final String __OBFID = "CL_00001576";

	public EntityCustomAIBeg(EntityZertumEntity theDog, float minPlayerDistance) {
		this.theWolf = theDog;
		this.worldObject = theDog.worldObj;
		this.minPlayerDistance = minPlayerDistance;
		this.setMutexBits(2);
	}

	@Override
	public boolean shouldExecute() {
		this.thePlayer = this.worldObject.getClosestPlayerToEntity(this.theWolf, this.minPlayerDistance);
		return this.thePlayer == null ? false : this.hasPlayerGotBoneInHand(this.thePlayer);
	}

	@Override
	public boolean continueExecuting() {
		return !this.thePlayer.isEntityAlive()
				? false : (this.theWolf.getDistanceSqToEntity(this.thePlayer) > this.minPlayerDistance * this.minPlayerDistance ? false : this.field_75384_e > 0 && this.hasPlayerGotBoneInHand(this.thePlayer));
	}

	@Override
	public void startExecuting() {
		this.theWolf.setBegging(true);
		this.field_75384_e = 40 + this.theWolf.getRNG().nextInt(40);
	}

	@Override
	public void resetTask() {
		this.theWolf.setBegging(false);
		this.thePlayer = null;
	}

	@Override
	public void updateTask() {
		this.theWolf.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F, this.theWolf.getVerticalFaceSpeed());
		--this.field_75384_e;
	}

	private boolean hasPlayerGotBoneInHand(EntityPlayer player) {
		ItemStack stack = player.inventory.getCurrentItem();
		return stack == null ? false : (!this.theWolf.isTamed() && !(this.theWolf instanceof EntityDarkZertum) && stack.getItem() == ModItems.nileBone ? true: !this.theWolf.isTamed() && this.theWolf instanceof EntityDarkZertum && stack.getItem() == ModItems.darkBone ? true : this.theWolf.isBreedingItem(stack) || theWolf.foodValue(stack) > 0);
	}
}
