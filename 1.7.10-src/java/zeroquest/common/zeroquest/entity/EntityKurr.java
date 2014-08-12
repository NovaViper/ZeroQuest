package common.zeroquest.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.entity.ai.tameable.EntityCustomAIFollowOwner;
import common.zeroquest.entity.ai.tameable.EntityCustomAIOwnerHurtByTarget;
import common.zeroquest.entity.ai.tameable.EntityCustomAIOwnerHurtTarget;
import common.zeroquest.entity.ai.tameable.EntityCustomAITargetNonTamed;
import common.zeroquest.entity.projectile.EntityFlamingPoisonball;
import common.zeroquest.helper.ChatHelper;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Sound;
import common.zeroquest.particle.ParticleEffects;
import common.zeroquest.util.ItemUtils;
import cpw.mods.fml.common.FMLLog;

public class EntityKurr extends EntityMob /*implements IRangedAttackMob*/
{
    public int rare;
    
    private static final UUID field_110189_bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private static final AttributeModifier field_110190_br = (new AttributeModifier(field_110189_bq, "Attacking speed boost", 0.45D, 0)).setSaved(false);

    public static final double maxHealth = 60;
    public static final double attackDamage = 8;
    public static final double speed = 0.30000001192092896;
    public static final double maxHealthBaby = 10;
    public static final double attackDamageBaby = 2;
    
    /** A random delay until this Kurr next makes a sound. */
    private Entity field_110191_bu;
    
    // data value IDs
    public static final int INDEX_ANGRY = 16;
	
    public EntityKurr(World p_i1696_1_)
    {
        super(p_i1696_1_);
        this.setSize(2.6F, 2.6F);
        this.isImmuneToFire = true;
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        //this.inventory = new InventoryJakanPack(this);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);
        
        if (this.isChild())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthBaby);
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageBaby);
        }
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Sets the active target the Task system uses for tracking
     */
    public void setAttackTarget(EntityLivingBase p_70624_1_)
    {
        super.setAttackTarget(p_70624_1_);

        if (p_70624_1_ == null)
        {
            this.setAngry(false);
        }
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(INDEX_ANGRY, Byte.valueOf((byte)0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setBoolean("Angry", this.isAngry());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        this.setAngry(p_70037_1_.getBoolean("Angry"));
    }

    protected void func_145780_a(int x, int y, int z, Block block) {
        if (inWater) {
            // no sounds for underwater action
        } else if (this.isChild()) {
            // play default step sound for babies
            super.func_145780_a(x, y, z, block);
        } else {
            // play stomping for bigger dragons
            worldObj.playSoundAtEntity(this, Sound.JakanStep, 0.15F, 1.0F);
        }
    }
	
	protected String getHurtSound()
	{
		return "mob.enderdragon.hit";	
	}
	
    protected String getLivingSound()
    {
        return this.isAngry() ? Sound.KurrGrowl :
        	 this.getHealth() <=10 ? Sound.KurrWhine :
        	(this.rand.nextInt(3) == 0 ? 
        			(Sound.KurrBreathe)
        					: Sound.KurrRoar);
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
		return Sound.KurrDeath;	
	}
	
    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
	@Override
    public int getTalkInterval() {
		if(this.getHealth() <=10){
    		return 20;
    	}else if(this.isAngry()){
    		return 25;
    	}else{
    		return 200;
    	}
    }
    
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        rare = rand.nextInt(20);
            {
                if (this.isBurning())
                {
                    this.dropItem(ModItems.jakanMeatCooked, 1);
                }
                else if (rare <= 12)
                {
                    this.dropItem(ModItems.jakanMeatRaw, 1);
                }
                if(rare <= 6)
                {
                	this.dropItem(ModItems.darkGrain, 1);
                }
                /*if(rare >= 17)
                {
                	this.dropItem(ModItems.darkDust.itemID, 1);
                }*/
                else
                {
                	
                }
                
            }
        }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if(this.entityToAttack != null && this.entityToAttack.isDead) {
            this.entityToAttack = null;
        }
        //Dying
        if(this.getHealth() <=10){
        	double d0 = this.rand.nextGaussian() * 0.04D;
        	double d1 = this.rand.nextGaussian() * 0.04D;
        	double d2 = this.rand.nextGaussian() * 0.04D;
        	worldObj.spawnParticle("witchMagic", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
        //Angry
        if(this.isAngry()){
        	double d0 = this.rand.nextGaussian() * 0.04D;
        	double d1 = this.rand.nextGaussian() * 0.04D;
        	double d2 = this.rand.nextGaussian() * 0.04D;
        	worldObj.spawnParticle("smoke", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (this.field_110191_bu != this.entityToAttack && this.isAngry() && isServer())
        {
            IAttributeInstance attributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            attributeinstance.removeModifier(field_110190_br);

            if (this.entityToAttack != null)
            {
                attributeinstance.applyModifier(field_110190_br);
            }
        }

        this.field_110191_bu = this.entityToAttack;
        super.onUpdate();
    }

    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity entity = p_70097_1_.getEntity();

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
            {
                p_70097_2_ = (p_70097_2_ + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(p_70097_1_, p_70097_2_);
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
                EnchantmentHelper.func_151384_a((EntityLivingBase) victim, this);
            }
            
            EnchantmentHelper.func_151385_b(this, victim);
            
            setLastAttacker(victim);
            
            // play eating sound
            float volume = getSoundVolume() * 0.7f;
            float pitch = getPitch();
            worldObj.playSoundAtEntity(this, "random.eat", volume, pitch);
        }

        return attacked;
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
    
    @Override
        protected void fall(float par1)
        {

        int i = MathHelper.ceiling_float_int(par1 * 0.5F - 3.0F);

        if (i > 0)
        {
            this.attackEntityFrom(DamageSource.fall, (float)i);

            if (this.riddenByEntity != null)
            {
                this.riddenByEntity.attackEntityFrom(DamageSource.fall, (float)i);
            }

            Block block = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 0.2D - (double)this.prevRotationYaw), MathHelper.floor_double(this.posZ));

            if (block.getMaterial() != Material.air)
            {
                Block.SoundType soundtype = block.stepSound;
                this.worldObj.playSoundAtEntity(this, soundtype.getStepResourcePath(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
            }
        }
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
                entityplayer.triggerAchievement(ModAchievements.DragonSlayer);
            }
        }
    }
    
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer) //TODO
    {
    		float volume = getSoundVolume() * 1.0f;
    		float pitch =  getSoundPitch();
            this.playSound(Sound.KurrHiss, volume, pitch);
     
            return super.interact(par1EntityPlayer);
    }
    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 8;
    }

    /**
     * Determines whether this wolf is angry or not.
     */
    public boolean isAngry()
    {
        return (this.dataWatcher.getWatchableObjectByte(INDEX_ANGRY) & 2) != 0;
    }

    /**
     * Sets whether this wolf is angry or not.
     */
    public void setAngry(boolean p_70916_1_)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70916_1_)
        {
            this.dataWatcher.updateObject(INDEX_ANGRY, Byte.valueOf((byte)(b0 | 2)));
        }
        else
        {
            this.dataWatcher.updateObject(INDEX_ANGRY, Byte.valueOf((byte)(b0 & -3)));
        }
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return this.ticksExisted > 2400;
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
}
