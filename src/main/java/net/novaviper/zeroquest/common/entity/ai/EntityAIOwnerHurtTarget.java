package net.novaviper.zeroquest.common.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.entity.util.ModeUtil.EnumMode;
import net.novaviper.zeroquest.common.lib.Constants;

/**
 * @author ProPercivalalb
 */
public class EntityAIOwnerHurtTarget extends EntityAITarget {

	private final EntityZertumEntity dog;
	private EntityLivingBase theTarget;
	private int field_142050_e;
	private static final String __OBFID = "CL_00001625";

	public EntityAIOwnerHurtTarget(EntityZertumEntity dog) {
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
				this.theTarget = entitylivingbase.getLastAttacker();
				int i = entitylivingbase.getLastAttackerTime();
				return i != this.field_142050_e && this.isSuitableTarget(this.theTarget, false) && this.dog.func_142018_a(this.theTarget, entitylivingbase);
			}
		}
	}

	@Override
	public boolean continueExecuting() {
		return this.dog.getHealth() > Constants.lowHP && super.continueExecuting();
	}

	@Override
	public void startExecuting() {
		this.taskOwner.setAttackTarget(this.theTarget);
		EntityLivingBase entitylivingbase = this.dog.getOwnerEntity();

		if (entitylivingbase != null) {
			this.field_142050_e = entitylivingbase.getLastAttackerTime();
		}

		super.startExecuting();
	}
}