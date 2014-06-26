package common.zeroquest.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.StepSound;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import common.zeroquest.*;
import common.zeroquest.entity.ai.*;
import common.zeroquest.entity.ai.flight.*;
import common.zeroquest.entity.ai.ground.*;
import common.zeroquest.entity.ai.tameable.EntityCustomAIOwnerHurtByTarget;
import common.zeroquest.entity.ai.tameable.EntityCustomAIOwnerHurtTarget;
import common.zeroquest.entity.ai.tameable.EntityCustomAISit;
import common.zeroquest.entity.anim.JakanAnimator;
import common.zeroquest.entity.helper.CustomJakanHelper;
import common.zeroquest.entity.helper.JakanHacks;
import common.zeroquest.entity.projectile.EntityFlamingPoisonball;
import common.zeroquest.lib.Constants;
import common.zeroquest.particle.*;
import common.zeroquest.spawn.*;
import common.zeroquest.util.*;
import cpw.mods.fml.common.FMLLog;

public class EntityJakanPrime extends EntityFlyingCustomTameable
{	
    private boolean canSeeCreeper;
    public int rare;
    private static final Logger L = ZeroQuest.L;
    
    public static final double maxHealth = 30;
    public static final double attackDamage = 6;
    public static final double speed = 0.30000001192092896;
    public static final double speedAir = 1.5;
    public static final double maxHealthTamed = 40;
    public static final float maxHealthTamedFloat = 40;
    public static final double attackDamageTamed = 8;
    public static final double maxHealthBaby = 10;
    public static final double attackDamageBaby = 2;
    public static final int HOME_RADIUS = 256;
    public static final Item FAVORITE_FOOD = Item.fishRaw;
    
    // data value IDs
    public static final int INDEX_BREED = 20;
    public static final int INDEX_COLLAR_COLOR = 21;
    public static final int INDEX_SADDLE = 22;
    
    // server/client delegates
    private Map<Class, CustomJakanHelper> helpers;
    
    // client-only delegates
    private JakanAnimator animator;
    
	
	public EntityJakanPrime(World par1World) {
		super(par1World);
		this.setSize(2.5F, 2.5F);
        // enables walking over blocks
        stepHeight = 1;
        isImmuneToFire = true;
        
        // mutex 1: movement
        // mutex 2: looking
        // mutex 4: special state
        tasks.addTask(0, new EntityAICatchOwnerGround(this)); // mutex all
        tasks.addTask(1, new EntityAIRideGround(this, 1)); // mutex all
        tasks.addTask(2, new EntityAISwimming(this)); // mutex 4
        tasks.addTask(3, aiCSit); // mutex 4+1
        tasks.addTask(4, new EntityAIMate(this, 0.6)); // mutex 2+1
        tasks.addTask(5, new EntityAITempt(this, 0.75, FAVORITE_FOOD.itemID, false)); // mutex 2+1
        tasks.addTask(6, new EntityAIAttackOnCollide(this, 1, true)); // mutex 2+1
        tasks.addTask(7, new EntityAIFollowParent(this, 0.8)); // mutex 2+1
        tasks.addTask(8, new EntityAIFollowOwner(this, 1, 12, 128)); // mutex 2+1
        tasks.addTask(8, new EntityAIPanicChild(this, 1)); // mutex 1
        tasks.addTask(9, new EntityAIWander(this, 1)); // mutex 1
        tasks.addTask(10, new EntityAIWatchIdle(this)); // mutex 2
        tasks.addTask(10, new EntityAIWatchLiving(this, 16, 0.05f)); // mutex 2
        
        // mutex 1: waypointing
        // mutex 2: continuous waypointing
        airTasks.addTask(0, new EntityAIRideAir(this)); // mutex all
        airTasks.addTask(0, new EntityAILand(this)); // mutex 0
        airTasks.addTask(0, new EntityAICatchOwnerAir(this)); // mutex all

        // mutex 1: generic targeting
        targetTasks.addTask(1, new EntityCustomAIOwnerHurtByTarget(this)); // mutex 1
        targetTasks.addTask(2, new EntityCustomAIOwnerHurtTarget(this)); // mutex 1
        targetTasks.addTask(3, new EntityAIHurtByTarget(this, false)); // mutex 1
        targetTasks.addTask(4, new EntityAIHunt(this, EntitySheep.class, 200, false)); // mutex 1
        targetTasks.addTask(4, new EntityAIHunt(this, EntityPig.class, 200, false)); // mutex 1
        targetTasks.addTask(4, new EntityAIHunt(this, EntityChicken.class, 200, false)); // mutex 1
    }
	
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.experienceValue = 5;
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(speed);
        this.getEntityAttribute(MOVE_SPEED_AIR).setAttribute(speedAir);

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
    
    protected boolean isGroundAIEnabled() {
        return super.isGroundAIEnabled();
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
        this.dataWatcher.addObject(INDEX_BREED, new Byte((byte)0));
        this.dataWatcher.addObject(INDEX_COLLAR_COLOR, new Byte((byte)BlockColored.getBlockFromDye(1)));
        this.dataWatcher.addObject(INDEX_SADDLE, Byte.valueOf((byte)0));
        addHelper(new JakanHacks(this));
        
        // don't use this on server side or you're asking for troubles!
        /*if (isClient()) {
            animator = new JakanAnimator(this);
        }*/
    }
	
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("CollarColor", (byte)this.getCollarColor());
        par1NBTTagCompound.setBoolean("Saddle", this.isSaddled());
        
        for (CustomJakanHelper helper : helpers.values()) {
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
        
        for (CustomJakanHelper helper : helpers.values()) {
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
        if (isFlying()) {
            return null;
        }else{
        return this.canSeeCreeper ? "zero_quest:jakan.growl" : 
        	 this.getHealth() <=10 ? "zero_quest:jakan.whine" :
        	(this.rand.nextInt(3) == 0 ? 
        			( "zero_quest:jakan.breathe")
        					: "zero_quest:jakan.roar");
        }
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
        for (CustomJakanHelper helper : helpers.values()) {
            helper.onLivingUpdate();
        }
        
        /*if (isClient()) {
            if (!isChild() || isChild()) {
                animator.setOnGround(!isFlying());
                animator.update();
            }
        } else {*/
            // set home position near owner when tamed
            if (isTamed()) {
                Entity owner = getOwner();
                if (owner != null) {
                    setHomeArea((int) owner.posX, (int) owner.posY, (int) owner.posZ, HOME_RADIUS);
                }
            }
        super.onLivingUpdate();
        //Attack
        if (isServer() && !this.hasPath() && this.onGround)
        {
            this.worldObj.setEntityState(this, (byte)8);
        }
        
        if(this.entityToAttack != null && this.entityToAttack.isDead) {
        	this.entityToAttack = null;
        }
        //Heal
        if(Constants.DEF_HEALING == true && !this.isChild() && this.getHealth() <=10 && this.isTamed())
        {
       		this.addPotionEffect(new PotionEffect(10, 200)); //TODO
        }
        //Dying
        if(this.getHealth() <=10 && this.isTamed()){
        	double d0 = this.rand.nextGaussian() * 0.04D;
        	double d1 = this.rand.nextGaussian() * 0.04D;
        	double d2 = this.rand.nextGaussian() * 0.04D;
        	worldObj.spawnParticle("witchMagic", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
        //See Creeper
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
//}
    
    public void onUpdate()
    {
        super.onUpdate();
        
        if(this.isSitting()){ //TODO
        	double d0 = this.rand.nextGaussian() * 0.04D;
        	double d1 = this.rand.nextGaussian() * 0.04D;
        	double d2 = this.rand.nextGaussian() * 0.04D;
        	worldObj.spawnParticle("smoke", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
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
    
    
/*    @Override //TODO
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.func_82209_a(par1EntityLivingBase, 0);
    }
    
    private void func_82209_a(Entity par1Entity, float par2)
    {
        this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
        double d0 = par1Entity.posX - this.posX;
        double d1 = par1Entity.boundingBox.minY + (double)(par1Entity.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
        double d2 = par1Entity.posZ - this.posZ;
        float f1 = MathHelper.sqrt_float(par2) * 0.5F;
        
        EntityFlamingPoisonball entityflamingpoisonBall = new EntityFlamingPoisonball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);

        
        entityflamingpoisonBall.posY = this.posY + (double)(this.height / 2.0F) + 1.0D;
        entityflamingpoisonBall.posZ = this.posZ + 2.0D;
        //entityflamingpoisonBall.posZ = this.posZ + 2.0D;
        this.worldObj.spawnEntityInWorld(entityflamingpoisonBall);
    }*/

	/**
     * Called when the entity is attacked.
     */
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
    
    public boolean attackEntityFrom(DamageSource src, float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }

        aiCSit.setSitting(false);
        
        return super.attackEntityFrom(src, par2);
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
                        return true;
                    }
                    else
                    {
                        par1EntityPlayer.mountEntity(this);
                    	par1EntityPlayer.triggerAchievement(ModAchievements.MountUp);
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
            if (isServer())
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
    
    public boolean canInteract(EntityPlayer player) {
    	if(player.getCommandSenderName().equalsIgnoreCase(this.getOwnerName())) {
    	}
		return true;
    }
    
    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed() //TODO
    {
        return true;
    }
    
    public boolean isOnLadder() {
        // this better doesn't happen...
        return false;
    }
    
    private void addHelper(CustomJakanHelper jakanHacks) {
        if (helpers == null) {
            helpers = new HashMap<Class, CustomJakanHelper>();
        }
        helpers.put(jakanHacks.getClass(), jakanHacks);
    }
    
    public <T extends CustomJakanHelper> T getHelper(Class<T> clazz) {
        return (T) helpers.get(clazz);
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
     * Returns true if the pig is saddled.
     */
    public boolean isSaddled()
    {
        return (this.dataWatcher.getWatchableObjectByte(INDEX_SADDLE) & 1) != 0;
    }

    /**
     * Set or remove the saddle of the pig.
     */
    public void setSaddled(boolean par1)
    {
        if (par1)
        {
            this.dataWatcher.updateObject(INDEX_SADDLE, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(INDEX_SADDLE, Byte.valueOf((byte)0));
        }
    }
    
    /**
     * Return this wolf's collar color.
     */
    public int getCollarColor()
    {
        return this.dataWatcher.getWatchableObjectByte(INDEX_COLLAR_COLOR) & 15;
    }

    /**
     * Set this wolf's collar color.
     */
    public void setCollarColor(int par1)
    {
        this.dataWatcher.updateObject(INDEX_COLLAR_COLOR, Byte.valueOf((byte)(par1 & 15)));
    }
    
    public double sniffRange(){//TODO
        double d = 0.0D;
        for (int i = 0; i < 15 * 6; i++)
        {
            d++;
        }
        return d;
    }
    
    /**
     * Returns the entity's health relative to the maximum health.
     * 
     * @return health normalized between 0 and 1
     */
    public double getHealthRelative() {
        return getHealth() / (double) getMaxHealth();
    }
    
    @Override
    protected void updateAITasks() {
        // disable AI on eggs
            super.updateAITasks();
        }
    
    public boolean isOwner(EntityPlayer player) {
        return player.getCommandSenderName().equalsIgnoreCase(getOwnerName());
    }
    
    public boolean isRiddenByOwner() {
        return riddenByEntity == getOwner();
    }

    public EntityPlayer getRidingPlayer() {
        if (riddenByEntity instanceof EntityPlayer) {
            return (EntityPlayer) riddenByEntity;
        } else {
            return null;
        }
    }
    
    public JakanAnimator getAnimator() {
        return animator;
    }
    
    public void setRidingPlayer(EntityPlayer player) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
        player.mountEntity(this);
    }
    
    public void onWingsDown(float speed) {
        if (!inWater) {
            // play wing sounds
            float pitch = getSoundPitch() + (1 - speed);
            float volume = getSoundVolume() * 0.3f + (1 - speed) * 0.2f;
            worldObj.playSound(posX, posY, posZ, "mob.enderdragon.wings", volume, pitch, false);
        }
    }
    
	 public void func_70918_i(boolean par1) //TODO Breeding stuff
	 {
		 byte var2 = this.dataWatcher.getWatchableObjectByte(INDEX_BREED);


		 if (par1)
		 {
			 this.dataWatcher.updateObject(INDEX_BREED, Byte.valueOf((byte)1));
		 }
		 else
		 {
			 this.dataWatcher.updateObject(INDEX_BREED, Byte.valueOf((byte)0));
		 }
	 }
	
    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
	 public EntityCustomTameable spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	 {
		 double chance = Math.random();


		 if (chance < 0.5){
			 EntityJakanPrime var3 = new EntityJakanPrime(this.worldObj);
			 var3.setOwner(this.getOwnerName());
			 var3.setTamed(true);
			 var3.setSaddled(true);
			 return var3;
		 }else{
			 EntityJakan var4 = new EntityJakan(this.worldObj);
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
		 else if (!(par1EntityAnimal instanceof EntityJakanPrime))
		 {
			 return false;
		 }
		 else
		 {
			 EntityJakanPrime var2 = (EntityJakanPrime)par1EntityAnimal;
			 return !var2.isTamed() ? false : (var2.isSitting() ? false : this.isInLove() && var2.isInLove());
		 }
		 
	 }
	 
		@Override
		public EntityAgeable createChild(EntityAgeable entityageable) {
			return spawnBabyAnimal(entityageable);
		}
}