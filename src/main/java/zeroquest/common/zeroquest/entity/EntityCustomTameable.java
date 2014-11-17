package common.zeroquest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;
import common.zeroquest.client.particle.ParticleEffects;
import common.zeroquest.entity.ai.EntityAIFetchBone;
import common.zeroquest.inventory.InventoryPack;


public abstract class EntityCustomTameable extends EntityTameable
{
    public EntityAIFetchBone aiFetchBone = new EntityAIFetchBone(this, 1.5D, 0.0F, 30.0F);
    private boolean hasBone;
    protected boolean canSeeCreeper;
    public int rare;
    /**Cooldown for Ranged Attack**/
	protected int cooldown;
    public InventoryPack inventory;

    public EntityCustomTameable(World p_i1604_1_)
    {
        super(p_i1604_1_);
    }
    
    /*=======================================================UNIVERSAL=======================================================*/
    
    /**
     * Play the taming effect, will either be hearts or smoke depending on status
     */
    @Override
    protected void playTameEffect(boolean p_70908_1_)
    {
        String s = "heart";

        if (!p_70908_1_)
        {
            s = "bluedust";
        }

        for (int i = 0; i < 7; ++i)
        {
        	if(isClient()){
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            ParticleEffects.spawnParticle(s, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        	}
        }
    }
    
    /**
     * Returns the entity's health relative to the maximum health.
     * 
     * @return health normalized between 0 and 1
     */
    public double getHealthRelative() {
        return getHealth() / (double) getMaxHealth();
    }
    
    public void tamedFor(EntityPlayer player, boolean successful) { //TODO
        if (successful) {
            setTamed(true);
            setPathToEntity((PathEntity)null);
            setAttackTarget((EntityLivingBase)null);
            aiSit.setSitting(false);
            func_152115_b(player.getUniqueID().toString());
            playTameEffect(true);
            worldObj.setEntityState(this, (byte)7);
        } else {
            playTameEffect(false);
            worldObj.setEntityState(this, (byte) 6);
        }
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
    }
    
    /**
     * Checks if this entity is running on a client.
     * 
     * Required since MCP's isClientWorld returns the exact opposite...
     * 
     * @return true if the entity runs on a client or false if it runs on a server
     */
    public boolean isClient() {
        return worldObj.isRemote;
    }
    
    /**
     * Checks if this entity is running on a server.
     * 
     * @return true if the entity runs on a server or false if it runs on a client
     */
    public boolean isServer() {
        return !worldObj.isRemote;
    }
    
    public double sniffRange(){
        double d = 0.0D;
        for (int i = 0; i < 15 * 6; i++)
        {
            d++;
        }
        return d;
    }  
    
    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getPitch() {
    	if(!this.isChild())
    		return super.getSoundPitch() * -2;
    	else{
    		return super.getSoundPitch() * 2;
    		}
    	}
    
    public boolean canInteract(EntityPlayer player) {
    	if(player.getCommandSenderName().equalsIgnoreCase(this.func_152113_b())) {
    	}
    		return true;
    	}
    
    public void dropChestItems()
    {
        this.dropItemsInChest(this, this.inventory);
    }
    
    private void dropItemsInChest(Entity par1Entity, InventoryPack inventory2)
    {
        if (inventory2 != null && !this.worldObj.isRemote)
        {
            for (int i = 0; i < inventory2.getSizeInventory(); ++i)
            {
                ItemStack itemstack = inventory2.getStackInSlot(i);

                if (itemstack != null)
                {
                    this.entityDropItem(itemstack, 0.0F);
                }
            }
        }
    }

/*=======================================================FOR ZERTUMS ONLY=======================================================*/
    public boolean didWolfFish() {
        return rand.nextInt(15) < 4 * 2;
    }
    
    public boolean didWolfCook() {
        return rand.nextInt(15) < 4 * 2;
    }
    
    public void setHasBone(boolean hasBone) { //TODO bone fetching
    	this.hasBone = hasBone;
    }
    
    public boolean hasBone() {
    	return this.hasBone;
    }
}