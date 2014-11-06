package common.zeroquest.entity.ai;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import common.zeroquest.ModItems;
import common.zeroquest.entity.EntityCustomTameable;

/**
 * @author ProPercivalalb
 */
public class EntityAIFetchBone extends EntityAIBase
{
    private EntityCustomTameable thePet;
    private EntityLivingBase theOwner;
    private EntityItem theBone;
    private World theWorld;
    private double moveSpeed;
    private PathNavigate petPathfinder;
    private int tenTickTimer;
    private float maxDist;
    private float minDist;
    private boolean preShouldAvoidWater;

    public EntityAIFetchBone(EntityCustomTameable par1EntityCustomTameable, double moveSpeed, float minDistance, float maxDistance)
    {
        this.thePet = par1EntityCustomTameable;
        this.theWorld = par1EntityCustomTameable.worldObj;
        this.moveSpeed = moveSpeed;
        this.petPathfinder = par1EntityCustomTameable.getNavigator();
        this.minDist = minDistance;
        this.maxDist = maxDistance;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
    	this.theBone = getClosestsBone();
    	EntityLivingBase possibleOwner = this.thePet.getOwner();
    	
        if(this.theBone == null)
        {
            return false;
        }
        else if(possibleOwner == null)
        {
        	this.theBone = null;
            return false;
        }
        else if(this.thePet.isSitting())
        {
        	this.theBone = null;
            return false;
        }
        else if(this.thePet.riddenByEntity instanceof EntityPlayer)
        {
        	this.theBone = null;
            return false;
        }
        else if(!this.thePet.isTamed())
        {
        	this.theBone = null;
            return false;
        }
        else if(this.thePet.hasBone())
        {
        	this.theBone = null;
            return false;
        }
        else if(this.thePet.getDistanceSqToEntity(this.theBone) > (double)(this.maxDist * this.maxDist) || this.thePet.getDistanceSqToEntity(this.theBone) < (double)(this.minDist * this.minDist))
        {
        	this.theBone = null;
            return false;
        }
        else
        {
        	this.theOwner = possibleOwner;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return !this.petPathfinder.noPath() && this.theOwner != null && !this.thePet.hasBone() && this.theBone != null && !this.theBone.isDead && !(this.thePet.getDistanceSqToEntity(this.theBone) > (double)(this.maxDist * this.maxDist) || this.thePet.getDistanceSqToEntity(this.theBone) < (double)(this.minDist * this.minDist)) && !this.thePet.isSitting();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
        this.tenTickTimer = 0;
        this.preShouldAvoidWater = this.thePet.getNavigator().getAvoidsWater();
        this.thePet.getNavigator().setAvoidsWater(false);
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        this.theOwner = null;
        this.petPathfinder.clearPathEntity();
        this.thePet.getNavigator().setAvoidsWater(this.preShouldAvoidWater);
    }
    
    public EntityItem getClosestsBone() {
        EntityItem entityItem = null;
        
        if(this.thePet.hasBone())
        	return null;
        
        List list = this.theWorld.getEntitiesWithinAABBExcludingEntity(this.thePet, this.thePet.boundingBox.expand((double)maxDist, (double)maxDist, (double)maxDist));
        for (int i = 0; i < list.size(); i++) {
            Entity listEntity = (Entity)list.get(i);

            if ((listEntity instanceof EntityItem) && ((EntityItem)listEntity).getEntityItem().getItem() == ModItems.toy && ((EntityItem)listEntity).getEntityItem().getItemDamage() == 0) {
                entityItem = (EntityItem)listEntity;
            }
        }
        return entityItem;
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask()
    {
        this.thePet.getLookHelper().setLookPositionWithEntity(this.theBone, 10.0F, (float)this.thePet.getVerticalFaceSpeed());

        if (!this.thePet.isSitting())
        {
            if (--this.tenTickTimer <= 0)
            {
                this.tenTickTimer = 10;

                if (!this.petPathfinder.tryMoveToEntityLiving(this.theBone, this.moveSpeed))
                {
                    if (!this.thePet.getLeashed())
                    {
                        //When following a player, here it will check how far to the player and if greater
                    	//that 12 blocks the dog would teleport, for now do nothing
                    }
                }
            }
        }
        
        if(this.thePet.getDistanceSqToEntity(this.theBone) < (double)(3F * 3F) && !this.thePet.hasBone()) {
        	if(!this.theBone.isDead) {
        		this.theBone.attackEntityFrom(DamageSource.generic, 12F);
        		this.thePet.setHasBone(true);
        	}
        }
    }
    
    public EntityItem getCurrentTarget() {
    	return this.theBone;
    }
}