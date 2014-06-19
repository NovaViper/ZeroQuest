package common.zeroquest.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.StepSound;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.minecraft.world.World;
import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.entity.ai.EntityAIHunt;
import common.zeroquest.entity.ai.EntityCustomAIFollowOwner;
import common.zeroquest.entity.ai.EntityCustomAIOwnerHurtByTarget;
import common.zeroquest.entity.ai.EntityCustomAIOwnerHurtTarget;
import common.zeroquest.entity.ai.EntityCustomAISit;
import common.zeroquest.entity.ai.flight.EntityAICatchOwnerAir;
import common.zeroquest.entity.ai.flight.EntityAILand;
import common.zeroquest.entity.ai.flight.EntityAIRideAir;
import common.zeroquest.entity.ai.ground.EntityAICatchOwnerGround;
import common.zeroquest.entity.ai.ground.EntityAIFollowOwner;
import common.zeroquest.entity.ai.ground.EntityAIPanicChild;
import common.zeroquest.entity.ai.ground.EntityAIRideGround;
import common.zeroquest.entity.ai.ground.EntityAIWatchIdle;
import common.zeroquest.entity.ai.ground.EntityAIWatchLiving;
import common.zeroquest.particle.ParticleEffects;
import common.zeroquest.spawn.CustomEntityList;
import common.zeroquest.util.ItemUtils;
import cpw.mods.fml.common.FMLLog;

public class EntityJakanPrime extends EntityFlyingCustomTameable
{	
    private boolean canSeeCreeper;
    public int rare;

    public int field_110278_bp;
    public int field_110279_bq;
    public float field_70886_e;
    public float destPos;
    public float field_70884_g;
    public float field_70888_h;
    public float field_70889_i = 1.0F;
    
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
    
    // data value IDs
    public static final int INDEX_BREED = 20;
    public static final int INDEX_COLLAR_COLOR = 21;
    public static final int INDEX_SADDLE = 22;
    
	
	public EntityJakanPrime(World par1World) {
		super(par1World);
		this.setSize(2.5F, 2.5F);
        // enables walking over blocks
        stepHeight = 1;
        
        // mutex 1: movement
        // mutex 2: looking
        // mutex 4: special state
        tasks.addTask(0, new EntityAICatchOwnerGround(this)); // mutex all
        tasks.addTask(1, new EntityAIRideGround(this, 1)); // mutex all
        tasks.addTask(2, new EntityAISwimming(this)); // mutex 4
        tasks.addTask(3, aiCSit); // mutex 4+1
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        tasks.addTask(5, new EntityAITempt(this, 0.75, ModItems.vitoidFruit.itemID, false)); // mutex 2+1
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
    }
	
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("CollarColor", (byte)this.getCollarColor());
        par1NBTTagCompound.setBoolean("Saddle", this.getSaddled());
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
        	(this.rand.nextInt(3) == 0 ? 
        			(this.getHealth() <=10 ? "zero_quest:jakan.whine"
        					: "zero_quest:jakan.breathe")
        					: "zero_quest:jakan.roar");
        }
    }
	
	protected String getDeathSound()
	{
		return "zero_quest:jakan.death";	
	}
	
    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval() {
        return 240;
    }
	
    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 1.0F;
    }
    
    @Override
    public String getEntityName() {
        // return custom name if set
        if (hasCustomNameTag()) {
            return getCustomNameTag();
        }
        
        // return default breed name otherwise
        String entName = CustomEntityList.getEntityString(this);
        return StatCollector.translateToLocal("entity." + entName + ".name");
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
        super.onLivingUpdate();
        if (isServer() && !this.hasPath() && this.onGround)
        {
            this.worldObj.setEntityState(this, (byte)8);
        }
        
        if(this.entityToAttack != null && this.entityToAttack.isDead) {
        	this.entityToAttack = null;
        }
        
        if (isTamed()) {
            Entity owner = getOwner();
            if (owner != null) {
                setHomeArea((int) owner.posX, (int) owner.posY, (int) owner.posZ, HOME_RADIUS);
            }
        }
        
        if(this.getHealth() <=10 && this.isTamed() && !this.isChild())
        {
       		this.addPotionEffect(new PotionEffect(10, 200)); //TODO
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
    }
    
    @Override
    protected void playTameEffect(boolean par1)
    {
        String s = "heart";

        if (!par1 && isClient())
        {
        	s = "bluedust";
        }

        for (int i = 0; i < 7; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            ParticleEffects.spawnParticle(s, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
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
    protected void fall(float par1) {}

    
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
                
                if (!isOwner(par1EntityPlayer)) {
                    if (isServer()) {
                        par1EntityPlayer.addChatMessage("YOU DO NOT OWN THIS JAKAN!");
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
            else if (this.riddenByEntity == null && !this.isChild() && itemstack.itemID != ModItems.vitoidFruit.itemID)
            {
            	if (itemstack != null && itemstack.itemID == Item.stick.itemID)
                {
            		return true;
                }
                else
                {
                	this.setRidingPlayer(par1EntityPlayer);
                	par1EntityPlayer.triggerAchievement(ModAchievements.MountUp);
                	return true;
                }
            }
        }              
    } else {
        if (isServer()) {
            if (ItemUtils.consumeEquipped(par1EntityPlayer, ModItems.nileBone)) {

                if (rand.nextInt(3) == 0) {
                    this.setTamed(true);
                    this.setSaddled(true);
                    this.setPathToEntity((PathEntity)null);
                    this.setAttackTarget((EntityLivingBase)null);
                    this.aiCSit.setSitting(true);
                    this.setHealth(maxHealthTamedFloat);
                    this.setOwner(par1EntityPlayer.getCommandSenderName());
                    this.playTameEffect(true);
                    this.worldObj.setEntityState(this, (byte)7);
                } else {
                	this.playTameEffect(false);
                	this.worldObj.setEntityState(this, (byte) 6);
                }
            }
        }   
        return true;
    }   
        return super.interact(par1EntityPlayer);
}   
    
    
    /**
     * Returns true if the pig is saddled.
     */
    public boolean getSaddled()
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
    
/*    public boolean canBeSteered() //TODO
    {
        return true;
    }*/
    
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