package common.zeroquest.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import common.zeroquest.entity.util.ModeUtil.EnumMode;
import common.zeroquest.entity.zertum.EntityZertumEntity;

/**
 * @author ProPercivalalb
 */
public class EntityAIOwnerHurtTarget extends EntityAITarget {
	
    private EntityZertumEntity dog;
    private EntityLivingBase theTarget;
    private int field_142050_e;
    private static final String __OBFID = "CL_00001625";

    public EntityAIOwnerHurtTarget(EntityZertumEntity dog)
    {
        super(dog, false);
        this.dog = dog;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        if (!this.dog.isTamed() || !this.dog.mode.isMode(EnumMode.AGGRESIVE) || this.dog.getHealth() <= 1)
        {
            return false;
        }
        else
        {
            EntityLivingBase entitylivingbase = this.dog.getOwnerEntity();

            if (entitylivingbase == null)
            {
                return false;
            }
            else
            {
                this.theTarget = entitylivingbase.getLastAttacker();
                int i = entitylivingbase.getLastAttackerTime();
                return i != this.field_142050_e && this.isSuitableTarget(this.theTarget, false) && this.dog.func_142018_a(this.theTarget, entitylivingbase);
            }
        }
    }
    
    @Override
    public boolean continueExecuting() {
    	return this.dog.getHealth() > 1 && super.continueExecuting();
    }
    
    @Override
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.theTarget);
        EntityLivingBase entitylivingbase = this.dog.getOwnerEntity();

        if (entitylivingbase != null)
        {
            this.field_142050_e = entitylivingbase.getLastAttackerTime();
        }

        super.startExecuting();
    }
}