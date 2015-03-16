package common.zeroquest.entity;


import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.entity.ai.EntityAIFetchBone;
import common.zeroquest.entity.ai.EntityAIFollowOwner;
import common.zeroquest.entity.ai.EntityAIModeAttackTarget;
import common.zeroquest.entity.ai.EntityAIOwnerHurtByTarget;
import common.zeroquest.entity.ai.EntityAIOwnerHurtTarget;
import common.zeroquest.entity.ai.EntityAIRoundUp;
import common.zeroquest.entity.ai.EntityCustomAIBeg;
import common.zeroquest.entity.util.CoordUtil;
import common.zeroquest.entity.util.LevelUtil;
import common.zeroquest.entity.util.ModeUtil;
import common.zeroquest.entity.util.TalentHelper;
import common.zeroquest.entity.util.TalentUtil;
import common.zeroquest.inventory.InventoryPack;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Sound;


public abstract class EntityZertumEntity extends EntityCustomTameable
{   
    public float headRotationCourse;
    public float headRotationCourseOld;
    public boolean isWet;
    public boolean isShaking;
    public float timeWolfIsShaking;
    public float prevTimeWolfIsShaking;
    private int hungerTick;
   	private int prevHungerTick;
    private int healingTick;
    private int prevHealingTick;
    private int regenerationTick;
    private int prevRegenerationTick;
    public TalentUtil talents;
    public LevelUtil levels;
    public ModeUtil mode;
    public CoordUtil coords;
    public Map<String, Object> objects;
    private boolean hasBone;
    
    public static final double maxHealth = 25;
    public static final double attackDamage = 6;
    public static final double speed = 0.30000001192092896;
    public static final double maxHealthTamed = 35;
    public static final double attackDamageTamed = 8;
    public static final double maxHealthBaby = 10;
    public static final double attackDamageBaby = 2;
    
    protected EntityAILeapAtTarget aiLeap = new EntityAILeapAtTarget(this, 0.4F);
    public EntityAIWatchClosest aiStareAtPlayer = new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F);
	int level2 = this.talents.getLevel("creeperspotter");
    public EntityAIWatchClosest aiGlareAtCreeper = new EntityAIWatchClosest(this, EntityCreeper.class, level2 * 6);    
    public EntityAIFetchBone aiFetchBone;
    
    // data value IDs TODO
    /**DO NOT CHANGE!**/
    public static final int INDEX_TAME = 16;
    public static final int INDEX_COLLAR = 19;

    public EntityZertumEntity(World worldIn)
    {
        super(worldIn);
        this.objects = new HashMap<String, Object>();
        this.setSize(0.6F, 1.5F);
        ((PathNavigateGround)this.getNavigator()).func_179690_a(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, this.aiLeap);
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, this.aiFetchBone = new EntityAIFetchBone(this, 1.0D, 0.5F, 20.0F));
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(9, new EntityCustomAIBeg(this, 8.0F));
        this.tasks.addTask(10, aiStareAtPlayer);
        this.tasks.addTask(10, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIModeAttackTarget(this));
        this.targetTasks.addTask(4, new EntityAIHurtByTarget(this, true));
        this.setTamed(false);
        this.inventory = new InventoryPack(this);
        this.targetTasks.addTask(6, new EntityAIRoundUp(this, EntityAnimal.class, 0, false));
        TalentHelper.onClassCreation(this);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);
        this.updateEntityAttributes();
    }
    public void updateEntityAttributes() {
        if (this.isTamed())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthTamed + this.effectiveLevel());
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
    public String getName() {
    	String name = this.getDogName();
    	if(name != "")
    		return name;
    	return super.getName();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean getAlwaysRenderNameTagForRender() {
        return true;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(INDEX_COLLAR, new Byte((byte)EnumDyeColor.RED.getMetadata()));
        
        this.talents = new TalentUtil(this);
        this.levels = new LevelUtil(this);
        this.mode = new ModeUtil(this);
        this.coords = new CoordUtil(this);
        
        this.dataWatcher.addObject(21, new String("")); //Dog Name
        this.dataWatcher.addObject(22, new String("")); //Talent Data
        this.dataWatcher.addObject(23, new Integer(60)); //Dog Hunger
        this.dataWatcher.addObject(24, new String("0:0")); //Level Data
        this.dataWatcher.addObject(26, new Integer(0)); //Obey Others
        this.dataWatcher.addObject(27, new Integer(0)); //Dog Mode
        this.dataWatcher.addObject(28, "-1:-1:-1:-1:-1:-1"); //Dog Mode
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setBoolean("Angry", this.isAngry());
        tagCompound.setByte("CollarColor", (byte)this.getCollarColor().getDyeDamage());
        
        tagCompound.setString("version", Constants.version);
        tagCompound.setString("dogName", this.getDogName());
        tagCompound.setInteger("dogHunger", this.getDogHunger());
        tagCompound.setBoolean("willObey", this.willObeyOthers());
        
        this.talents.writeTalentsToNBT(tagCompound);
        this.levels.writeTalentsToNBT(tagCompound);
        this.mode.writeToNBT(tagCompound);
        this.coords.writeToNBT(tagCompound);
        TalentHelper.writeToNBT(this, tagCompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        super.readEntityFromNBT(tagCompound);
        this.setAngry(tagCompound.getBoolean("Angry"));

        if (tagCompound.hasKey("CollarColor", 99))
        {
            this.setCollarColor(EnumDyeColor.byDyeDamage(tagCompound.getByte("CollarColor")));
        }
        
        String lastVersion = tagCompound.getString("version");
        this.setDogName(tagCompound.getString("dogName"));
        this.setDogHunger(tagCompound.getInteger("dogHunger"));
        this.setWillObeyOthers(tagCompound.getBoolean("willObey"));
        
        this.talents.readTalentsFromNBT(tagCompound);
        this.levels.readTalentsFromNBT(tagCompound);
        this.mode.readFromNBT(tagCompound);
        this.coords.readFromNBT(tagCompound);
        TalentHelper.readFromNBT(this, tagCompound);
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
    	String sound = TalentHelper.getLivingSound(this);
    	if(!"".equals(sound))
    		return sound;
    	
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

    public EntityAISit getSitAI() {
    	return this.aiSit;
    }
    
    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
	public float getSoundVolume()
    {
        return 0.5F;
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
	@Override
    public int getTalkInterval() {
    	if((Boolean)this.objects.get("canseecreeper") == true){
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
        
        if(Constants.IS_HUNGER_ON) {
        	this.prevHungerTick = this.hungerTick;
        	
	        if (this.riddenByEntity == null && !this.isSitting() /** && !this.mode.isMode(EnumMode.WANDERING) && !this.level.isDireDog() || worldObj.getWorldInfo().getWorldTime() % 2L == 0L **/)
	        	this.hungerTick += 1;
	        
	        this.hungerTick += TalentHelper.onHungerTick(this, this.hungerTick - this.prevHungerTick);
	        
	        if (this.hungerTick > 400) {
	            this.setDogHunger(this.getDogHunger() - 1);
	            this.hungerTick -= 400;
	        }
        }
        
        if(Constants.DEF_HEALING) {
        	this.prevRegenerationTick = this.regenerationTick;
        	
	        if(this.getHealth() <= 1) {
	        	this.regenerationTick += 1;
	        	
	        	this.regenerationTick += TalentHelper.onRegenerationTick(this, this.regenerationTick - this.prevRegenerationTick);
	        }
	        
	        if(this.regenerationTick >= 12000) {
	            this.setHealth(this.getHealth() + 1);
	            this.worldObj.setEntityState(this, (byte)7);
	            this.regenerationTick = 0;
	        }
    	}
        
        if(this.getHealth() != 1) {
	        this.prevHealingTick = this.healingTick;
	        this.healingTick += this.nourishment();
	        
	        if (this.healingTick >= 6000) {
	            if (this.getHealth() < this.getMaxHealth())
	            	this.setHealth(this.getHealth() + 1);
	            
	            this.healingTick = 0;
	        }
        }
        
        if(this.getDogHunger() == 0 && this.worldObj.getWorldInfo().getWorldTime() % 100L == 0L && this.getHealth() > 1) {
            this.attackEntityFrom(DamageSource.generic, 1);
            //this.fleeingTick = 0;
        }

        if (isServer() && this.getAttackTarget() == null && this.isAngry())
        {
            this.setAngry(false);
        }
        
        if(Constants.DEF_HOWL == true){
        	if (this.worldObj.isDaytime() && isServer()) //TODO
        	{
            	wantToHowl = false;
        	}
        	else if(isServer() && (this.isChild() || this.isChild()))
        	{
            	wantToHowl = true;
            }
        }
        
        TalentHelper.onLivingUpdate(this);
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
            	if(this.rand.nextInt(15) < this.talents.getLevel("fishing") * 2) {
                    if(this.rand.nextInt(15) < this.talents.getLevel("fire") * 2 && this instanceof EntityRedZertum) {
                    	if(!this.worldObj.isRemote) {
                    		dropItem(Items.cooked_fish, 1);
                    	}
                    }
                    else {
                    	if(!this.worldObj.isRemote) {
                    		dropItem(Items.fish, 1);
                    	}
                    }
            	}
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
        if(this.isTamed()) {
    		EntityPlayer player = (EntityPlayer)this.getOwner();
    		
    		if(player != null) {
    			float distanceToOwner = player.getDistanceToEntity(this);

                if (distanceToOwner <= 2F && this.hasBone()) {
                	if(!this.worldObj.isRemote) {
                		this.entityDropItem(new ItemStack(ModItems.toy, 1, 1), 0.0F);
                	}
                	
                    this.setHasBone(false);
                }
    		}
    	}
        
        TalentHelper.onUpdate(this);
    }
	
    @Override
    public void moveEntityWithHeading(float strafe, float forward) {
        if (this.riddenByEntity instanceof EntityPlayer) {
            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
            strafe = ((EntityPlayer)this.riddenByEntity).moveStrafing * 0.5F;
            forward = ((EntityPlayer)this.riddenByEntity).moveForward;

            if (forward <= 0.0F)
                forward *= 0.25F;

            if (this.onGround) {
                if (forward > 0.0F) {
                    float f2 = MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F);
                    float f3 = MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F);
                    this.motionX += (double)(-0.4F * f2 * 0.15F); // May change
                    this.motionZ += (double)(0.4F * f3 * 0.15F);
                }
            }

            this.stepHeight = 1.0F;
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.2F;

            if (!this.worldObj.isRemote)  {
                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() / 4);
                super.moveEntityWithHeading(strafe, forward);
            }

            if (this.onGround) {
                //this.jumpPower = 0.0F;
               // this.setHorseJumping(false);
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d0 = this.posX - this.prevPosX;
            double d1 = this.posZ - this.prevPosZ;
            float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

            if (f4 > 1.0F)
                f4 = 1.0F;

            this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        }
        else {
            this.stepHeight = 0.5F;
            this.jumpMovementFactor = 0.02F;
            super.moveEntityWithHeading(strafe, forward);
        }
    }
    
    @Override
    public float getAIMoveSpeed() {
    	double speed = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
    	
    	speed += TalentHelper.addToMoveSpeed(this);

    	if(this.riddenByEntity instanceof EntityPlayer)
    		speed /= 4;
    	
        return (float)speed;
    }
    
    @Override
    public void fall(float distance, float damageMultiplier) {
        float[] ret = net.minecraftforge.common.ForgeHooks.onLivingFall(this, distance, damageMultiplier);
        if (ret == null) return;
        distance = ret[0]; damageMultiplier = ret[1];
        super.fall(distance, damageMultiplier);
        PotionEffect potioneffect = this.getActivePotionEffect(Potion.jump);
        float f2 = potioneffect != null ? (float)(potioneffect.getAmplifier() + 1) : 0.0F;
        int i = MathHelper.ceiling_float_int(((distance - 3.0F - f2) - TalentHelper.fallProtection(this)) * damageMultiplier);

        if (i > 0 && !TalentHelper.isImmuneToFalls(this)) {
            this.playSound(this.getFallSoundString(i), 1.0F, 1.0F);
            this.attackEntityFrom(DamageSource.fall, (float)i);
            int j = MathHelper.floor_double(this.posX);
            int k = MathHelper.floor_double(this.posY - 0.20000000298023224D);
            int l = MathHelper.floor_double(this.posZ);
            Block block = this.worldObj.getBlockState(new BlockPos(j, k, l)).getBlock();

            if (block.getMaterial() != Material.air) {
                Block.SoundType soundtype = block.stepSound;
                this.playSound(soundtype.getStepSound(), soundtype.getVolume() * 0.5F, soundtype.getFrequency() * 0.75F);
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
    
    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        if (this.isEntityInvulnerable(damageSource))
            return false;
        else {
        	if(!TalentHelper.attackEntityFrom(this, damageSource, damage))
        		return false;
        	
            Entity entity = damageSource.getEntity();
            this.aiSit.setSitting(false);

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
                damage = (damage + 1.0F) / 2.0F;

            return super.attackEntityFrom(damageSource, damage);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
    	if(!TalentHelper.shouldDamageMob(this, entity))
    		return false;
    	
    	int damage = (int)(4 + (this.effectiveLevel() + this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getBaseValue()) / 2);
        damage = TalentHelper.attackEntityAsMob(this, entity, damage);
        
        if (entity instanceof EntityZombie)
            ((EntityZombie)entity).setAttackTarget(this);
        
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)damage);
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
    
    protected boolean isMovementBlocked() {
    	return this.isPlayerSleeping() || this.ridingEntity != null || this.riddenByEntity instanceof EntityPlayer || super.isMovementBlocked();
    	}

    @Override
    public double getYOffset() {
        return this.ridingEntity instanceof EntityPlayer ? 0.5D : 0.0D;
    }
    
    @Override
    public boolean isPotionApplicable(PotionEffect potionEffect) {
        if (this.getHealth() <= 1)
            return false;

        if(!TalentHelper.isPostionApplicable(this, potionEffect))
        	return false;

        return true;
    }
    
    @Override
    public void setFire(int amount) {
    	if(TalentHelper.setFire(this, amount))
    		super.setFire(amount);
    }
    
    public int foodValue(ItemStack stack) {
    	if(stack == null || stack.getItem() == null)
    		return 0;
    	
    	int foodValue = 0;
    	
    	Item item = stack.getItem();
    	
        if(stack.getItem() != Items.rotten_flesh && item instanceof ItemFood) {
            ItemFood itemfood = (ItemFood)item;

            if (itemfood.isWolfsFavoriteMeat())
            	foodValue = 40;
        }
        
        foodValue = TalentHelper.changeFoodValue(this, stack, foodValue);

        return foodValue;
    }
    
    public int masterOrder() {
    	int order = 0;
        EntityPlayer player = (EntityPlayer)this.getOwner();

        if (player != null) {
        	
            float distanceAway = player.getDistanceToEntity(this);
            ItemStack itemstack = player.inventory.getCurrentItem();

            if (itemstack != null && (itemstack.getItem() instanceof ItemTool) && distanceAway <= 20F)
                order = 1;

            if (itemstack != null && ((itemstack.getItem() instanceof ItemSword) || (itemstack.getItem() instanceof ItemBow)))
                order = 2;

            if (itemstack != null && itemstack.getItem() == Items.wheat)
                order = 3;
        }

        return order;
    }
    
    @Override
    public boolean isPlayerSleeping() {
        return false;
    }
    
    @Override
    public boolean canBreatheUnderwater() {
        return TalentHelper.canBreatheUnderwater(this);
    }
    
    public boolean canInteract(EntityPlayer player) {
    	return this.isOwner(player) || this.willObeyOthers();
    }
    
    public int nourishment() {
        int amount = 0;

        if (this.getDogHunger() > 0) {
            amount = 40 + 4 * (this.effectiveLevel() + 1);

            if (isSitting() && this.talents.getLevel("rapidregen") == 5) {
                amount += 20 + 2 * (this.effectiveLevel() + 1);
            }

            if (!this.isSitting()) {
                amount *= 5 + this.talents.getLevel("rapidregen");
                amount /= 10;
            }
        }

        return amount;
    }
    
    public int effectiveLevel() {
        return (this.levels.getLevel()) / 2;
    }
    
    public String getDogName() {
        return this.dataWatcher.getWatchableObjectString(21);
    }
    
    public void setDogName(String var1) {
       this.dataWatcher.updateObject(21, var1);
    }
    
    public void setWillObeyOthers(boolean flag) {
    	this.dataWatcher.updateObject(26, flag ? 1 : 0);
    }
    
    public boolean willObeyOthers() {
    	return this.dataWatcher.getWatchableObjectInt(26) != 0;
    }
    
    public int points() {
        return this.levels.getLevel() + (this.getGrowingAge() < 0 ? 0 : 20);
    }

    public int spendablePoints() {
        return this.points() - this.usedPoints();
    }
    
    public int usedPoints() {
		return TalentHelper.getUsedPoints(this);
    }
    
    public int deductive(int par1) {
        byte byte0 = 0;
        switch(par1) {
        case 1: return 1;
		case 2: return 2;
        case 3: return 4;
        case 4: return 6;
        case 5: return 8;
        default: return 0;
        }
    }
    
    public int getDogHunger() {
		return this.dataWatcher.getWatchableObjectInt(23);
	}
    
    public void setDogHunger(int par1) {
    	this.dataWatcher.updateObject(23, MathHelper.clamp_int(par1, 0, 100));
    }
    
    @Override
    public boolean func_142018_a(EntityLivingBase entityToAttack, EntityLivingBase owner) {
    	if(TalentHelper.canAttackEntity(this, entityToAttack))
    		return true;
    	
        if (!(entityToAttack instanceof EntityCreeper) && !(entityToAttack instanceof EntityGhast)) {
            if (entityToAttack instanceof EntityZertumEntity) {
            	EntityZertumEntity entityDog = (EntityZertumEntity)entityToAttack;

                if (entityDog.isTamed() && entityDog.getOwner() == owner)
                    return false;
            }

            return entityToAttack instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer)owner).canAttackPlayer((EntityPlayer)entityToAttack) ? false : !(entityToAttack instanceof EntityHorse) || !((EntityHorse)entityToAttack).isTame();
        }
        else {
            return false;
        }
    }
    
    @Override
    public boolean canAttackClass(Class p_70686_1_) {
    	if(TalentHelper.canAttackClass(this, p_70686_1_))
    		return true;
    	
        return super.canAttackClass(p_70686_1_);
    }
    
    public void setHasBone(boolean hasBone) {
    	this.hasBone = hasBone;
    }
    
    public boolean hasBone() {
    	return this.hasBone;
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