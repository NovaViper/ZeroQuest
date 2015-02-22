package common.zeroquest.entity;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import common.zeroquest.client.particle.EntityDustFX;
import common.zeroquest.client.particle.EntityForisDustFX;
import common.zeroquest.client.particle.EntityHeart2FX;
import common.zeroquest.inventory.InventoryPack;


public abstract class EntityCustomTameable extends EntityTameable
{
    protected boolean canSeeCreeper;
    protected boolean wantToHowl;
    public int rare;
    /**Cooldown for Ranged Attack**/
	protected int cooldown;
    public InventoryPack inventory;
    /**For Ridable Entities***/
    protected float jumpPower;
    /**For Ridable Entities***/
    protected int gallopTime;
    
    public static final int INDEX_BREED = 18;

    public EntityCustomTameable(World p_i1604_1_)
    {
        super(p_i1604_1_);
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(INDEX_BREED, new Byte((byte)0));
    }
    
    /*=======================================================UNIVERSAL=======================================================*/
    
    /**
     * Play the taming effect, will either be hearts or smoke depending on status
     */
    @Override
    @SideOnly(Side.CLIENT)
    protected void playTameEffect(boolean p_70908_1_)
    {
        double d0 = this.rand.nextGaussian() * 0.02D;
        double d1 = this.rand.nextGaussian() * 0.02D;
        double d2 = this.rand.nextGaussian() * 0.02D;
    	if(isClient()){
        EntityFX entityFX = new EntityHeart2FX(this.worldObj, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);

        if (!p_70908_1_)
        {
        	entityFX = new EntityDustFX(this.worldObj, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }

        for (int i = 0; i < 7; ++i)
        {
        		FMLClientHandler.instance().getClient().effectRenderer.addEffect(entityFX);
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
            this.setTamed(true);
            this.navigator.clearPathEntity();
            this.setAttackTarget((EntityLivingBase)null);
            this.aiSit.setSitting(false);
            this.setOwnerId(player.getUniqueID().toString());
            this.playTameEffect(true);
            this.worldObj.setEntityState(this, (byte)7);
        }
        else
        {
            this.playTameEffect(false);
            this.worldObj.setEntityState(this, (byte)6);
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
    	if(player.getName().equalsIgnoreCase(this.getOwnerId())) {
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
    
    public boolean hasItemsinChest() //TODO
    {
        if (this.inventory != null && !this.worldObj.isRemote)
        {
            for (int i = 0; i < inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = inventory.getStackInSlot(i);

                if (itemstack != null)
                {
            		return true;
                }
            }
        }
		return false;
    }
    
    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource par1DamageSource) //TODO
    {
        super.onDeath(par1DamageSource);

        if (par1DamageSource.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)par1DamageSource.getEntity();
            {
                if(isServer()){
                this.dropChestItems();
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
    
    public void func_70918_i(boolean p_70918_1_)
    {
        if (p_70918_1_)
        {
            this.dataWatcher.updateObject(INDEX_BREED, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(INDEX_BREED, Byte.valueOf((byte)0));
        }
    }
    
    public boolean func_70922_bv()
    {
        return this.dataWatcher.getWatchableObjectByte(INDEX_BREED) == 1;
    }
}