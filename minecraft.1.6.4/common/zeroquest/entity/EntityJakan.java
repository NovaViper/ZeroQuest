package common.zeroquest.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.ai.tameable.EntityCustomAIFollowOwner;
import common.zeroquest.entity.ai.tameable.EntityCustomAIOwnerHurtByTarget;
import common.zeroquest.entity.ai.tameable.EntityCustomAIOwnerHurtTarget;
import common.zeroquest.entity.ai.tameable.EntityCustomAISit;
import common.zeroquest.entity.ai.tameable.EntityCustomAITargetNonTamed;
import common.zeroquest.entity.helper.CustomTameableHacks;
import common.zeroquest.entity.helper.CustomTameableHelper;
import common.zeroquest.entity.projectile.EntityFlamingPoisonball;
import common.zeroquest.lib.Constants;
import common.zeroquest.particle.ParticleEffects;
import common.zeroquest.proxy.CommonProxy;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.StepSound;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityJakan extends EntityCustomTameable
{

    private boolean canSeeCreeper;
    public int rare;

    public int field_110278_bp;
    public int field_110279_bq;
    
    public static final double maxHealth = 30;
    public static final double attackDamage = 6;
    public static final double speed = 0.30000001192092896;
    public static final double maxHealthTamed = 40;
    public static final float maxHealthTamedFloat = 40;
    public static final double attackDamageTamed = 8;
    public static final double maxHealthBaby = 10;
    public static final double attackDamageBaby = 2;
    
    // server/client delegates
    private Map<Class, CustomTameableHelper> helpers;
    
	
	public EntityJakan(World par1World) {
		super(par1World);
		this.setSize(2.5F, 2.5F);
		this.isImmuneToFire = true;
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiCSit);
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(4, new EntityCustomAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(5, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityCustomAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityCustomAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.setTamed(false);
	}
	
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.experienceValue = 5;
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(speed);

        if (this.isTamed())
        {
            this.experienceValue = 0;
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(maxHealthTamed);
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(attackDamageTamed);
    		FMLLog.getLogger().info("maxhealth");
        }
        else if (this.isChild())
        {
            this.experienceValue = 0;
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(maxHealthBaby);
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(attackDamageBaby);
			FMLLog.getLogger().info("minhealth");
		}

    }
	
    public boolean isAIEnabled()
    {
        return true;
    }
    
    public EntityCustomAISit getSitAI() {
    	return this.aiCSit;
    } 
    
    /**
     * Sets the active target the Task system uses for tracking
     */
    public void setAttackTarget(EntityLivingBase par1EntityLivingBase)
    {
        super.setAttackTarget(par1EntityLivingBase);

        if (par1EntityLivingBase == null)
        {

        }
        else if (!this.isTamed())
        {

        }
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(21, Byte.valueOf((byte)0));
        this.dataWatcher.addObject(19, new Byte((byte)0));
        this.dataWatcher.addObject(20, new Byte((byte)BlockColored.getBlockFromDye(1)));
        addHelper(new CustomTameableHacks(this));
    }
	
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("CollarColor", (byte)this.getCollarColor());
        par1NBTTagCompound.setBoolean("Saddle", this.getSaddled());
        
        for (CustomTameableHelper helper : helpers.values()) {
            helper.writeToNBT(par1NBTTagCompound);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setSaddled(par1NBTTagCompound.getBoolean("Saddle"));
        if (par1NBTTagCompound.hasKey("CollarColor"))
        {
            this.setCollarColor(par1NBTTagCompound.getByte("CollarColor"));
        }
        
        for (CustomTameableHelper helper : helpers.values()) {
            helper.readFromNBT(par1NBTTagCompound);
        }
    }
    
    protected void playStepSound(int x, int y, int z, int blockId) {
        if (inWater) {
            // no sounds for underwater action
        } else if (this.isChild()) {
            // play default step sound for babies
            super.playStepSound(x, y, z, blockId);
        } else {
            // play stomping for bigger dragons
            worldObj.playSoundAtEntity(this, "zero_quest:jakan.step", 0.15F, 1.0F);
        }
    }
	
	protected String getHurtSound()
	{
		return "mob.enderdragon.hit";	
	}
	
    protected String getLivingSound()
    {
        return this.canSeeCreeper ? "zero_quest:jakan.growl" : 
        	 this.getHealth() <=10 ? "zero_quest:jakan.whine" :
        	(this.rand.nextInt(3) == 0 ? 
        			( "zero_quest:jakan.breathe")
        					: "zero_quest:jakan.roar");
        }
    
    @Override
    public void playLivingSound() {
        String sound = getLivingSound();
        if (sound == null) {
            return;
        }
        
        if (!this.isChild()) {
        	float volume = getSoundVolume() * 1.0f;
        	float pitch =  getSoundPitch();
            this.playSound(sound, volume, pitch);	
        }else{
        	
            float volume = getSoundVolume() * 1.0f;
            float pitch =  getSoundPitch() * 2;
            this.playSound(sound, volume, pitch);	
        }
    }
	
	protected String getDeathSound()
	{
		return "zero_quest:jakan.death";	
	}
	
    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
	@Override
    public int getTalkInterval() {
    	if(this.canSeeCreeper){
    		return 40;
    	}else if(this.getHealth() <=10){
    		return 20;
    	}else{
    		return 200;
    	}
    }
	
    protected void dropFewItems(boolean par1, int par2)
    {
        rare = rand.nextInt(20);
            {
                if (this.isBurning())
                {
                    this.dropItem(ModItems.jakanMeatCooked.itemID, 1);
                }
                else if (rare <= 12)
                {
                    this.dropItem(ModItems.jakanMeatRaw.itemID, 1);
                }
                if(rare <= 6 && !this.isTamed())
                {
                	this.dropItem(ModItems.nileGrain.itemID, 1);
                }
                /*if(rare >= 17)
                {
                	this.dropItem(ModItems.nileDust.itemID, 1);
                }*/
                else
                {
                	
                }            
            }
        }

    public void onLivingUpdate()
    {
        for (CustomTameableHelper helper : helpers.values()) {
            helper.onLivingUpdate();
        }
    	
        super.onLivingUpdate();
        
        if (isServer() && !this.hasPath() && this.onGround)
        {
            this.worldObj.setEntityState(this, (byte)8);
        }
        
        if(this.entityToAttack != null && this.entityToAttack.isDead) {
        	this.entityToAttack = null;
        }
        
        if(Constants.DEF_HEALING == true && !this.isChild() && this.getHealth() <=10 && this.isTamed())
        {
       		this.addPotionEffect(new PotionEffect(10, 200));//TODO
        }
        //Dying
        if(this.getHealth() <=10 && this.isTamed()){
        	double d0 = this.rand.nextGaussian() * 0.04D;
        	double d1 = this.rand.nextGaussian() * 0.04D;
        	double d2 = this.rand.nextGaussian() * 0.04D;
        	worldObj.spawnParticle("witchMagic", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
        if (this.getAttackTarget() == null && isTamed() && 15 > 0) {
            List list1 = worldObj.getEntitiesWithinAABB(EntityCreeper.class, AxisAlignedBB.getBoundingBox(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(sniffRange(), 4D, sniffRange()));


            if (!list1.isEmpty() && !isSitting() && this.getHealth() > 1 && !this.isChild()) {
                canSeeCreeper = true;
            }
            else {
                canSeeCreeper = false;
            }
        }
    }
    
    public void onUpdate()
    {
        super.onUpdate();
        
        if(this.isSitting()){ //TODO
        	double d0 = this.rand.nextGaussian() * 0.04D;
        	double d1 = this.rand.nextGaussian() * 0.04D;
        	double d2 = this.rand.nextGaussian() * 0.04D;
        	worldObj.spawnParticle("smoke", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
        
        if (riddenByEntity != null) //check if there is a rider
        {
          //currentTarget = this;
          this.randomYawVelocity = 0; //try not to let the horse control where to look.
          this.rotationYaw = riddenByEntity.rotationYaw;
        }
    }
    
    @Override
    protected void playTameEffect(boolean par1)
    {
        String s = "heart";

        if (!par1)
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
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity entity = par1DamageSource.getEntity();
            this.aiCSit.setSitting(false);

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
            {
                par2 = (par2 + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }
    
    public boolean attackEntityAsMob(Entity victim) {
        float attackDamage = (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int knockback = 0;

        if (victim instanceof EntityLivingBase) {
            attackDamage += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) victim);
            knockback += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) victim);
        }

        boolean attacked = victim.attackEntityFrom(DamageSource.causeMobDamage(this), attackDamage);

        if (attacked) {
            if (knockback > 0) {
                double vx = -Math.sin(Math.toRadians(rotationYaw)) * knockback * 0.5;
                double vy = 0.1;
                double vz = Math.cos(Math.toRadians(rotationYaw)) * knockback * 0.5;
                victim.addVelocity(vx, vy, vz);
                
                motionX *= 0.6;
                motionZ *= 0.6;
            }

            int fireAspect = EnchantmentHelper.getFireAspectModifier(this);

            if (fireAspect > 0) {
                victim.setFire(fireAspect * 4);
            }

            if (victim instanceof EntityLivingBase) {
                EnchantmentThorns.func_92096_a(this, (EntityLivingBase) victim, rand);
            }
            
            setLastAttacker(victim);
            
        if(!this.isChild())
            {
            	float volume = getSoundVolume() * 1.0f;
            	float pitch =  getSoundPitch() * -2;
            	worldObj.playSoundAtEntity(this, "random.eat", volume, pitch);
        	}
            else
        	{
                float volume = getSoundVolume() * 1.0f;
                float pitch =  getSoundPitch() * 2;
                worldObj.playSoundAtEntity(this, "random.eat", volume, pitch);
        	}
        }

        return attacked;
    }
    
    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float par1)
    {

        int i = MathHelper.ceiling_float_int(par1 * 0.5F - 10.0F);

        if (i > 0)
        {
            this.attackEntityFrom(DamageSource.fall, (float)i);

            if (this.riddenByEntity != null)
            {
                this.riddenByEntity.attackEntityFrom(DamageSource.fall, (float)i);
            }

            int j = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 0.2D - (double)this.prevRotationYaw), MathHelper.floor_double(this.posZ));

            if (j > 0)
            {
                StepSound stepsound = Block.blocksList[j].stepSound;
                this.worldObj.playSoundAtEntity(this, stepsound.getStepSound(), stepsound.getVolume() * 0.5F, stepsound.getPitch() * 0.75F);
            }
        }
    }

    
    /**
     * Called when the mob's health reaches 0.
     */
    /*public void onDeath(DamageSource par1DamageSource)
    {
        super.onDeath(par1DamageSource);

        if (par1DamageSource.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)par1DamageSource.getEntity();
            {
                entityplayer.triggerAchievement(ModAchievements.ZertKill);
            }
        }
    }*/

    public void setTamed(boolean par1)
    {
        super.setTamed(par1);

        if (par1)
        {
            this.experienceValue = 0;
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(maxHealthTamed);
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(attackDamageTamed);
        }
    }
    
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

        if (this.isTamed())
        {
            if (itemstack != null)
            {
                if (Item.itemsList[itemstack.itemID] instanceof ItemFood)
                {
                    ItemFood itemfood = (ItemFood)Item.itemsList[itemstack.itemID];

                    if (itemfood.isWolfsFavoriteMeat() && this.getHealth() < maxHealthTamed)
                    {
                        if (!par1EntityPlayer.capabilities.isCreativeMode)
                        {
                            --itemstack.stackSize;
                        }

                        if(!this.isChild())
                        {
                        	float volume = getSoundVolume() * 1.0f;
                        	float pitch =  getSoundPitch() * -2;
                        	worldObj.playSoundAtEntity(this, "random.eat", volume, pitch);
                            this.heal((float)itemfood.getHealAmount());
                    	}
                        else
                    	{
                            float volume = getSoundVolume() * 1.0f;
                            float pitch =  getSoundPitch() * 2;
                            worldObj.playSoundAtEntity(this, "random.eat", volume, pitch);
                            this.heal((float)itemfood.getHealAmount());
                    	}

                        if (itemstack.stackSize <= 0)
                        {
                            par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                        }

                        return true;
                    }
                }
                
                if (this.riddenByEntity == null && !this.isChild() && itemstack.itemID != ModItems.vitoidFruit.itemID)
                {
                    if (itemstack != null && itemstack.itemID == Item.stick.itemID)
                    {
                        return false;
                    }
                    else
                    {
                        par1EntityPlayer.mountEntity(this);
                    	par1EntityPlayer.triggerAchievement(ModAchievements.MountUp);//TODO
                        return true;
                    }
                }

                else if (itemstack.itemID == Item.dyePowder.itemID)
                {
                    int i = BlockColored.getBlockFromDye(itemstack.getItemDamage());

                    if (i != this.getCollarColor())
                    {
                        this.setCollarColor(i);

                        if (!par1EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
                        {
                            par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                        }

                        return true;
                    }
                }
            }

            if (canInteract(par1EntityPlayer) && isServer() && !this.isBreedingItem(itemstack))
            {
                this.isJumping = false;
                this.aiCSit.setSitting(!this.isSitting());
                this.setPathToEntity((PathEntity)null);
                this.setTarget((Entity)null);
                this.setAttackTarget((EntityLivingBase)null);
            }
        }                
        else if (itemstack != null && itemstack.itemID == ModItems.nileBone.itemID)
        {
            if (!par1EntityPlayer.capabilities.isCreativeMode)
            {
                --itemstack.stackSize;
            }

            if (itemstack.stackSize <= 0)
            {
                par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
            }

            if (!this.worldObj.isRemote)
            {
                if (this.rand.nextInt(3) == 0)
                {
                    this.setTamed(true);
                    this.setSaddled(true);
                    this.setPathToEntity((PathEntity)null);
                    this.setAttackTarget((EntityLivingBase)null);
                    this.aiCSit.setSitting(true);
                    this.setHealth(maxHealthTamedFloat);
                    this.setOwner(par1EntityPlayer.getCommandSenderName());
                    this.playTameEffect(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.interact(par1EntityPlayer);
    }
    
    private void addHelper(CustomTameableHelper jakanHacks) {
        if (helpers == null) {
            helpers = new HashMap<Class, CustomTameableHelper>();
        }
        helpers.put(jakanHacks.getClass(), jakanHacks);
    }
    
    public <T extends CustomTameableHelper> T getHelper(Class<T> clazz) {
        return (T) helpers.get(clazz);
    }
    
    /**
     * Returns true if the pig is saddled.
     */
    public boolean getSaddled()
    {
        return (this.dataWatcher.getWatchableObjectByte(21) & 1) != 0;
    }

    /**
     * Set or remove the saddle of the pig.
     */
    public void setSaddled(boolean par1)
    {
        if (par1)
        {
            this.dataWatcher.updateObject(21, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(21, Byte.valueOf((byte)0));
        }
    }
    
    public boolean canInteract(EntityPlayer player) {
    	if(player.getCommandSenderName().equalsIgnoreCase(this.getOwnerName())) {
    	}
		return true;
    }
    
    public boolean canBeSteered()
    {
             return true;
    }
    
    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed() //TODO
    {
        return true;
    }

    
    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    public void moveEntityWithHeading(float par1, float par2)
    {
        if (this.riddenByEntity != null && this.getSaddled())
        {
            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
            par1 = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
            par2 = ((EntityLivingBase)this.riddenByEntity).moveForward;

            if (par2 <= 0.0F)
            {
                par2 *= 0.25F;
            }

            this.stepHeight = 2.0F;
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

            if (!this.worldObj.isRemote)
            {
                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                super.moveEntityWithHeading(par1, par2);
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d0 = this.posX - this.prevPosX;
            double d1 = this.posZ - this.prevPosZ;
            float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

            if (f4 > 1.0F)
            {
                f4 = 1.0F;
            }

            this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        }
        else
        {
            this.stepHeight = 2.0F;
            this.jumpMovementFactor = 0.02F;
            super.moveEntityWithHeading(par1, par2);
        }
    }
    
    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack par1ItemStack) //TODO
    {
        return par1ItemStack != null && par1ItemStack.itemID == ModItems.vitoidFruit.itemID;
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 8;
    }
    
    /**
     * Return this wolf's collar color.
     */
    public int getCollarColor()
    {
        return this.dataWatcher.getWatchableObjectByte(20) & 15;
    }

    /**
     * Set this wolf's collar color.
     */
    public void setCollarColor(int par1)
    {
        this.dataWatcher.updateObject(20, Byte.valueOf((byte)(par1 & 15)));
    }
    
    public double sniffRange(){//TODO
        double d = 0.0D;
        for (int i = 0; i < 15 * 6; i++)
        {
            d++;
        }
        return d;
    }
    
	 public void func_70918_i(boolean par1) //TODO Breeding stuff
	 {
		 byte var2 = this.dataWatcher.getWatchableObjectByte(19);


		 if (par1)
		 {
			 this.dataWatcher.updateObject(19, Byte.valueOf((byte)1));
		 }
		 else
		 {
			 this.dataWatcher.updateObject(19, Byte.valueOf((byte)0));
		 }
	 }
	
    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
	 public EntityCustomTameable spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	 {
		 double chance = Math.random();


		 if (chance < 0.5){
			 EntityJakan var3 = new EntityJakan(this.worldObj);
			 var3.setOwner(this.getOwnerName());
			 var3.setTamed(true);
			 var3.setSaddled(true);
			 return var3;
		 }else{
			 EntityJakanPrime var4 = new EntityJakanPrime(this.worldObj);
			 var4.setOwner(this.getOwnerName());
			 var4.setTamed(true);
			 this.setSaddled(true);
			 return var4;
		 }
	 }


	 /**
	  * Returns true if the mob is currently able to mate with the specified mob.
	  */
	 @Override
	 public boolean canMateWith(EntityAnimal par1EntityAnimal)
	 {
		 if (par1EntityAnimal == this)
		 {
			 return false;
		 }
		 else if (!this.isTamed())
		 {
			 return false;
		 }
		 else if (!(par1EntityAnimal instanceof EntityJakan))
		 {
			 return false;
		 }
		 else
		 {
			 EntityJakan var2 = (EntityJakan)par1EntityAnimal;
			 return !var2.isTamed() ? false : (var2.isSitting() ? false : this.isInLove() && var2.isInLove());
		 }
		 
	 }
	 
		@Override
		public EntityAgeable createChild(EntityAgeable entityageable) {
			return spawnBabyAnimal(entityageable);
		}
}