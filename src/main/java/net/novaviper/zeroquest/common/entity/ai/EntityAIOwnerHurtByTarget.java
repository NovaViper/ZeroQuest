package net.novaviper.zeroquest.common.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.entity.util.ModeUtil.EnumMode;
import net.novaviper.zeroquest.common.lib.Constants;

/**
 * @author ProPercivalalb
 */
public class EntityAIOwnerHurtByTarget extends EntityAITarget {
	private final EntityZertumEntity dog;
	private EntityLivingBase theOwnerAttacker;
	private int field_142051_e;
	private static final String __OBFID = "CL_00001624";

	public EntityAIOwnerHurtByTarget(EntityZertumEntity dog) {
		super(dog, false);
		this.dog = dog;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		if (!this.dog.isTamed() || !this.dog.mode.isMode(EnumMode.AGGRESIVE) || this.dog.getHealth() <= Constants.lowHP) {
			return false;
		}
		else {
			EntityLivingBase entitylivingbase = this.dog.getOwnerEntity();

			if (entitylivingbase == null) {
				return false;
			}
			else {
				this.theOwnerAttacker = entitylivingbase.getAITarget();
				int i = entitylivingbase.getRevengeTimer();
				return i != this.field_142051_e && this.isSuitableTarget(this.theOwnerAttacker, false) && this.dog.func_142018_a(this.theOwnerAttacker, entitylivingbase);
			}
		}
	}

	@Override
	public boolean continueExecuting() {
		return this.dog.getHealth() > Constants.lowHP && super.continueExecuting();
	}

	@Override
	public void startExecuting() {
		this.taskOwner.setAttackTarget(this.theOwnerAttacker);
		EntityLivingBase entitylivingbase = this.dog.getOwnerEntity();

		if (entitylivingbase != null) {
			this.field_142051_e = entitylivingbase.getRevengeTimer();
		}

		super.startExecuting();
	}
}