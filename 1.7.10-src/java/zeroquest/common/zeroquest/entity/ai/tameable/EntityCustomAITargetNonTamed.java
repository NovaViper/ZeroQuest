package common.zeroquest.entity.ai.tameable;

import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

import common.zeroquest.entity.EntityCustomTameable;

public class EntityCustomAITargetNonTamed extends EntityAINearestAttackableTarget
{
    private EntityCustomTameable theTameable;
    private static final String __OBFID = "CL_00001623";

    public EntityCustomAITargetNonTamed(EntityCustomTameable p_i1666_1_, Class p_i1666_2_, int p_i1666_3_, boolean p_i1666_4_)
    {
        super(p_i1666_1_, p_i1666_2_, p_i1666_3_, p_i1666_4_);
        this.theTameable = p_i1666_1_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return !this.theTameable.isTamed() && super.shouldExecute();
    }
}