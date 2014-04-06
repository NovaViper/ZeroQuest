package common.zeroquest.entity.ai;

import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;

public class EntityMobAITarget extends EntityAINearestAttackableTarget
{
    private EntityMob theMob;

    public EntityMobAITarget(EntityMob par1EntityMob, Class par2Class, int par3, boolean par4)
    {
        super(par1EntityMob, par2Class, par3, par4);
        this.theMob = par1EntityMob;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return super.shouldExecute();
    }
}