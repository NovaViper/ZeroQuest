package common.zeroquest.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.base.Predicate;

import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.entity.ai.EntityCustomAIBeg;
import common.zeroquest.inventory.InventoryPack;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Sound;
import common.zeroquest.util.ItemUtils;


public class EntityZertumEntity extends EntityCustomTameable
{   
    public float headRotationCourse;
    public float headRotationCourseOld;
    public boolean isWet;
    public boolean isShaking;
    public float timeWolfIsShaking;
    public float prevTimeWolfIsShaking;
    
    public static final double maxHealth = 25;
    public static final double attackDamage = 6;
    public static final double speed = 0.30000001192092896;
    public static final double maxHealthTamed = 35;
    public static final double attackDamageTamed = 8;
    public static final double maxHealthBaby = 10;
    public static final double attackDamageBaby = 2;
    
    protected EntityAILeapAtTarget aiLeap = new EntityAILeapAtTarget(this, 0.4F);
    protected EntityAIWatchClosest aiStareAtPlayer = new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F);
    protected EntityAIWatchClosest aiGlareAtCreeper = new EntityAIWatchClosest(this, EntityCreeper.class, 20.0F);
    
    // data value IDs TODO
    /**DO NOT CHANGE!**/
    public static final int INDEX_TAME = 16;
    public static final int INDEX_COLLAR = 19;

    public EntityZertumEntity(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.5F);
        ((PathNavigateGround)this.getNavigator()).func_179690_a(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, this.aiLeap);
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityCustomAIBeg(this, 8.0F));
        this.tasks.addTask(9, aiStareAtPlayer);
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate()
        {
            private static final String __OBFID = "CL_00002229";
            public boolean func_180094_a(Entity p_180094_1_)
            {
                return p_180094_1_ instanceof EntitySheep || p_180094_1_ instanceof EntityRabbit;
            }
            public boolean apply(Object p_apply_1_)
            {
                return this.func_180094_a((Entity)p_apply_1_);
            }
        }));
        this.setTamed(false);
        this.inventory = new InventoryPack(this);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);

        if (this.isTamed())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthTamed);
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageTamed);
        }
        else if (this.isChild())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthBaby);
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageBaby);
        }
    }

    /**
     * Sets the active target the Task system uses for tracking
     */
    @Override
    public void setAttackTarget(EntityLivingBase p_70624_1_)
    {
        super.setAttackTarget(p_70624_1_);

        if (p_70624_1_ == null)
        {
            this.setAngry(false);
        }
        else if (!this.isTamed())
        {
            this.setAngry(true);
        }
    }
    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(INDEX_COLLAR, new Byte((byte)EnumDyeColor.RED.getMetadata()));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setBoolean("Angry", this.isAngry());
        tagCompound.setByte("CollarColor", (byte)this.getCollarColor().getDyeDamage());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        this.setAngry(tagCompund.getBoolean("Angry"));

        if (tagCompund.hasKey("CollarColor", 99))
        {
            this.setCollarColor(EnumDyeColor.byDyeDamage(tagCompund.getByte("CollarColor")));
        }
    }
    
    @Override
    protected void playStepSound(BlockPos p_180429_1_, Block p_180429_2_)
    {
        this.playSound("mob.wolf.step", 0.15F, 1.0F);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound()
    {
        return this.canSeeCreeper ? "mob.wolf.growl" : this.isAngry() ? "mob.wolf.growl" : 
        	this.wantToHowl ? Sound.ZertumHowl : (this.rand.nextInt(3) == 0 ? 
        			(this.isTamed() && this.getHealth() <= 10.0F ? "mob.wolf.whine"
        					: "mob.wolf.panting")
        					: "mob.wolf.bark");
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return "mob.wolf.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume()
    {
        return 0.5F;
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
	@Override
    public int getTalkInterval() {
    	if(this.canSeeCreeper){
    		return 40;
    	}else if(this.wantToHowl){
    		return 150;
		}else if(this.getHealth() <=10){
    		return 20;
    	}else{
    		return 200;
    	}
    }
    
    /**
     * Returns the item ID for the item the mob drops on death.
     */
	@Override
    protected void dropFewItems(boolean par1, int par2)
    {
        rare = rand.nextInt(20);
            {
                if (this.isBurning())
                {
                    this.dropItem(ModItems.zertumMeatCooked, 1);
                }
                else if (rare <= 12)
                {
                    this.dropItem(ModItems.zertumMeatRaw, 1);
                }
                if(rare <= 6 && !this.isTamed())
                {
                	this.dropItem(ModItems.nileGrain, 1);
                }
                else
                {
                	
                }
                
            }
        }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
	@Override
    public void onLivingUpdate() //TODO
    {
        super.onLivingUpdate();
        if (isServer() && this.isWet && !this.isShaking && !this.hasPath() && this.onGround)
        {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
            this.worldObj.setEntityState(this, (byte)8);
        }

        if (isServer() && this.getAttackTarget() == null && this.isAngry())
        {
            this.setAngry(false);
        }
        
        if(Constants.DEF_HEALING == true && !this.isChild() && this.getHealth() <=10 && this.isTamed())
        {
       		this.addPotionEffect(new PotionEffect(10, 200));
        }
        
        if (this.getAttackTarget() == null && isTamed() && 15 > 0) {
            List list1 = worldObj.getEntitiesWithinAABB(EntityCreeper.class, AxisAlignedBB.fromBounds(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(sniffRange(), 4D, sniffRange()));

            if (!list1.isEmpty() && !isSitting() && this.getHealth() > 1 && !this.isChild()) {
                canSeeCreeper = true;
                this.tasks.addTask(9, aiGlareAtCreeper);
                this.tasks.removeTask(aiStareAtPlayer);
            }
            else {
                canSeeCreeper = false;
                this.tasks.addTask(9, aiStareAtPlayer);
                this.tasks.removeTask(aiGlareAtCreeper);
            }
        }
        
        if(Constants.DEF_HOWL == true){
        	if (this.worldObj.isDaytime() && isServer() && !this.isChild()) //TODO
        	{
            	wantToHowl = false;
        	}
        	else
        	{
            	wantToHowl = true;
            }
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
	@Override
    public void onUpdate()
    {
        super.onUpdate();
        this.headRotationCourseOld = this.headRotationCourse;

        if (this.func_70922_bv())
        {
            this.headRotationCourse += (1.0F - this.headRotationCourse) * 0.4F;
        }
        else
        {
            this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
        }

        if (this.isWet())
        {
            this.isWet = true;
            this.isShaking = false;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        }
        else if ((this.isWet || this.isShaking) && this.isShaking)
        {
            if (this.timeWolfIsShaking == 0.0F)
            {
                this.playSound("mob.wolf.shake", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
            this.timeWolfIsShaking += 0.05F;

            if (this.prevTimeWolfIsShaking >= 2.0F)
            {
                this.isWet = false;
                this.isShaking = false;
                this.prevTimeWolfIsShaking = 0.0F;
                this.timeWolfIsShaking = 0.0F;
            }

            if (this.timeWolfIsShaking > 0.4F)
            {
                float f = (float)this.getEntityBoundingBox().minY;
                int i = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * (float)Math.PI) * 7.0F);

                for (int j = 0; j < i; ++j)
                {
                    float f1 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    this.worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX + (double)f1, (double)(f + 0.8F), this.posZ + (double)f2, this.motionX, this.motionY, this.motionZ, new int[0]);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean isWolfWet()
    {
        return this.isWet;
    }

    @SideOnly(Side.CLIENT)
    public float getShadingWhileWet(float p_70915_1_)
    {
        return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70915_1_) / 2.0F * 0.25F;
    }

    @SideOnly(Side.CLIENT)
    public float getShakeAngle(float p_70923_1_, float p_70923_2_)
    {
        float f2 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70923_1_ + p_70923_2_) / 1.8F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }
        else if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        return MathHelper.sin(f2 * (float)Math.PI) * MathHelper.sin(f2 * (float)Math.PI * 11.0F) * 0.15F * (float)Math.PI;
    }

    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(float p_70917_1_)
    {
        return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * p_70917_1_) * 0.15F * (float)Math.PI;
    }

    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    public int getVerticalFaceSpeed()
    {
        return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            Entity entity = source.getEntity();
            this.aiSit.setSitting(false);

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
            {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public boolean attackEntityAsMob(Entity par1Entity)
    {
        float damage = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;
        int critChance = 5;
        critChance += 2;
        
        if (rand.nextInt(6) < critChance) { //TODO
        	damage += (damage + 3) / 2;
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) -
            		(double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + 
            		(double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }

        boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);

        if (flag)
        {
            if (i > 0)
            {
                par1Entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }
        }
		return flag;
    }
    
    /**
     * Called when the mob's health reaches 0.
     */
    @Override //TODO
    public void onDeath(DamageSource par1DamageSource)
    {
        super.onDeath(par1DamageSource);

        if (par1DamageSource.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)par1DamageSource.getEntity();
            {
                entityplayer.triggerAchievement(ModAchievements.ZertKill);
                this.dropChestItems();
                
            }
        }
    }
    
    
    /**
     * Gets the pitch of living sounds in living entities.
     */
    @Override
    protected float getPitch() {
    	if(!this.isChild())
    		return super.getSoundPitch();
    	else{
    		return super.getSoundPitch() * 1;
    		}
    	}
    
    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte p_70103_1_)
    {
        if (p_70103_1_ == 8)
        {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        }
        else
        {
            super.handleHealthUpdate(p_70103_1_);
        }
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack itemstack)
    {
    	return itemstack == null ? false : itemstack.getItem() == ModItems.dogTreat;
    }

    public int getMaxSpawnedInChunk()
    {
        return 8;
    }

    public boolean isAngry()
    {
        return (this.dataWatcher.getWatchableObjectByte(INDEX_TAME) & 2) != 0;
    }

    public void setAngry(boolean p_70916_1_)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(INDEX_TAME);

        if (p_70916_1_)
        {
            this.dataWatcher.updateObject(INDEX_TAME, Byte.valueOf((byte)(b0 | 2)));
        }
        else
        {
            this.dataWatcher.updateObject(INDEX_TAME, Byte.valueOf((byte)(b0 & -3)));
        }
    }

    public EnumDyeColor getCollarColor()
    {
        return EnumDyeColor.byDyeDamage(this.dataWatcher.getWatchableObjectByte(INDEX_COLLAR) & 15);
    }

    public void setCollarColor(EnumDyeColor collarcolor)
    {
        this.dataWatcher.updateObject(INDEX_COLLAR, Byte.valueOf((byte)(collarcolor.getDyeDamage() & 15)));
    }

    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
 	 public EntityCustomTameable spawnBabyAnimal(EntityAgeable par1EntityAgeable)
 	 {
 		 double chance = Math.random();

 		 if (chance < 0.5){
 			 EntityZertumEntity var3 = new EntityZertumEntity(this.worldObj);
 			 var3.setOwnerId(this.getOwnerId());
 			 var3.setTamed(true);
 			 return var3;
 		 }else if (chance < 0.7){
 			EntityRedZertum var4 = new EntityRedZertum(this.worldObj);
 			 var4.setOwnerId(this.getOwnerId());
 			 var4.setTamed(true);
 			 return var4;
 		 }else{
 			EntityDestroZertum var2 = new EntityDestroZertum(this.worldObj);
 			 var2.setOwnerId(this.getOwnerId());
 			 var2.setTamed(true);
 			 return var2;
 		 }
 	 }
 	 
		@Override
		public EntityAgeable createChild(EntityAgeable entityageable) {
			return spawnBabyAnimal(entityageable);
		}

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canMateWith(EntityAnimal p_70878_1_)
    {
        if (p_70878_1_ == this)
        {
            return false;
        }
        else if (!this.isTamed())
        {
            return false;
        }
        else if (!(p_70878_1_ instanceof EntityZertumEntity))
        {
            return false;
        }
        else
        {
            EntityZertumEntity entitywolf = (EntityZertumEntity)p_70878_1_;
            return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false : this.isInLove() && entitywolf.isInLove());
        }
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return !this.isTamed() && this.ticksExisted > 2400;
    }
    
    public boolean allowLeashing()
    {
        return !this.isAngry() && super.allowLeashing();
    }
}