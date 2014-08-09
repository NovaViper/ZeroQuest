package common.zeroquest.entity.ai;

import common.zeroquest.entity.EntityCustomTameable;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityZombie;

public class EntityAITamed extends EntityAINearestAttackableTarget
{
    private EntityCustomTameable theTameable;
    private Class creep;
    private Class targetClass;
    public EntityAITamed(EntityCustomTameable par1EntityTameable, Class par2Class, int par4, boolean par5)
    {
        super(par1EntityTameable, par2Class, par4, par5);
        this.theTameable = par1EntityTameable;
        this.setTargetClass(EntityZombie.class);


    }
    public Class getTargetClass() {
		return targetClass;
	}


	public void setTargetClass(Class targetClass) {
		this.targetClass = targetClass;
	}
    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	if (this.theTameable.isTamed() == true)
    	{
    		super.shouldExecute();
    	}
        return true;
    }
}
