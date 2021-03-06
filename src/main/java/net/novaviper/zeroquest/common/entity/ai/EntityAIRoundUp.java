package net.novaviper.zeroquest.common.entity.ai;

import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.entity.util.ModeUtil.EnumMode;

import com.google.common.base.Predicate;

/**
 * @author ProPercivalalb
 */
public class EntityAIRoundUp extends EntityAINearestAttackableTarget {

	public EntityZertumEntity dog;
	
	public EntityAIRoundUp(EntityZertumEntity dog, Class target, int targetChance, boolean shouldCheckSight) {
		super(dog, target, targetChance, shouldCheckSight, true, (Predicate)null);
		this.dog = dog;
	}

	@Override
	public boolean shouldExecute() {
		int order = dog.masterOrder();
        return order == 3 && this.dog.mode.isMode(EnumMode.DOCILE) && this.dog.isTamed() && this.dog.riddenByEntity == null && this.dog.talents.getLevel("roundup") > 0 && super.shouldExecute();
    }
	
	@Override
	protected double getTargetDistance() {
	    return 24.0D;
	}
	
	@Override
	public boolean continueExecuting() {
		int order = dog.masterOrder();
        return order == 3 && super.continueExecuting();
	}
}
