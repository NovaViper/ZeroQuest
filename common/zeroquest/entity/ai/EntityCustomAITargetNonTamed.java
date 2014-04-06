package common.zeroquest.entity.ai;

import common.zeroquest.entity.EntityCustomTameable;

import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityTameable;

public class EntityCustomAITargetNonTamed extends EntityAINearestAttackableTarget
{
    private EntityCustomTameable theTameable;

    public EntityCustomAITargetNonTamed(EntityCustomTameable par1EntityTameable, Class par2Class, int par3, boolean par4)
    {
        super(par1EntityTameable, par2Class, par3, par4);
        this.theTameable = par1EntityTameable;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return !this.theTameable.isTamed() && super.shouldExecute();
    }
}
