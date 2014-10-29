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
import net.minecraft.entity.ai.EntityAIBreakDoor;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.client.particle.ParticleEffects;
import common.zeroquest.core.helper.ChatHelper;
import common.zeroquest.entity.ai.tameable.EntityCustomAIFollowOwner;
import common.zeroquest.entity.ai.tameable.EntityCustomAIOwnerHurtByTarget;
import common.zeroquest.entity.ai.tameable.EntityCustomAIOwnerHurtTarget;
import common.zeroquest.entity.ai.tameable.EntityCustomAITargetNonTamed;
import common.zeroquest.entity.projectile.EntityFlamingPoisonball;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Sound;
import common.zeroquest.util.ItemUtils;
import cpw.mods.fml.common.FMLLog;

public class EntityKurr extends EntityCustomMob /*implements IRangedAttackMob*/
{
    public int rare;
    
    private static final UUID field_110189_bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private static final AttributeModifier field_110190_br = (new AttributeModifier(field_110189_bq, "Attacking speed boost", 0.5D, 0)).setSaved(false);
    private final EntityAIBreakDoor field_146075_bs = new EntityAIBreakDoor(this);
    
    private boolean field_146076_bu = false;
    public static final double maxHealth = 80;
    public static final double attackDamage = 14;
    public static final double speed = 0.30000001192092896;
    public static final double maxHealthBaby = 10;
    public static final double attackDamageBaby = 2;
    
    /** A random delay until this Kurr next makes a sound. */
    private int randomSoundDelay;
    private Entity field_110191_bu;
    /** Above zero if this Kurr is Angry. */
    private int angerLevel;
	
    public EntityKurr(World p_i1696_1_)
    {
        super(p_i1696_1_);
        this.setSize(2.6F, 2.6F);
        this.getNavigator().setBreakDoors(true);
        this.canBreatheUnderwater();
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.experienceValue = 50;
        
        addImmunity(DamageSource.magic);
        addImmunity(DamageSource.lava);
        addImmunity(DamageSource.inFire);
        addImmunity(DamageSource.onFire);
        addImmunity(DamageSource.cactus);
        addImmunity(DamageSource.drown);
        addImmunity(DamageSource.fall);
        addImmunity(DamageSource.inWall);
        addImmunity(DamageSource.wither);
        addImmunity(DamageSource.starve);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(20F);
        
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

    public boolean func_146072_bX()
    {
        return this.field_146076_bu;
    }

    public void func_146070_a(boolean p_146070_1_)
    {
        if (this.field_146076_bu != p_146070_1_)
        {
            this.field_146076_bu = p_146070_1_;

            if (p_146070_1_)
            {
                this.tasks.addTask(1, this.field_146075_bs);
            }
            else
            {
                this.tasks.removeTask(this.field_146075_bs);
            }
        }
    }
    
    protected void entityInit()
    {
        super.entityInit();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setShort("Anger", (short)this.angerLevel);
    }
    
    

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        this.angerLevel = p_70037_1_.getShort("Anger");
    }
    
    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        return this.angerLevel == 0 ? null : super.findPlayerToAttack();
    }

    protected void func_145780_a(int x, int y, int z, Block block) {
        if (inWater) {
            // no sounds for underwater action
        } else if (this.isChild()) {
            // play default step sound for babies
            super.func_145780_a(x, y, z, block);
        } else {
            // play stomping for bigger dragons
            worldObj.playSoundAtEntity(this, Sound.Step, 0.15F, 1.0F);
        }
    }
	
	protected String getHurtSound()
	{
		return "mob.enderdragon.hit";	
	}
	
    protected String getLivingSound()
    {
        return this.getHealth() <=10 ? Sound.KurrWhine :
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
                else if (rare <= 18)
                {
                    this.dropItem(ModItems.kurrSeeds, 1);
                }
                if(rare <= 6)
                {
                	this.dropItem(ModItems.darkGrain, 1);
                }
                if(rare >= 17)
                {
                	this.dropItem(ModItems.darkEssence, 1);
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
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if(this.entityToAttack != null && this.entityToAttack.isDead) {
            this.entityToAttack = null;
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() //TODO
    {	
        if (this.field_110191_bu != this.entityToAttack && this.angerLevel > 0 && isServer())
        {
            IAttributeInstance attributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            attributeinstance.removeModifier(field_110190_br);

            if (this.entityToAttack != null)
            {
                attributeinstance.applyModifier(field_110190_br);
            	this.addPotionEffect(new PotionEffect(Potion.resistance.id, 9999999, 2));  //100 = 5 Seconds , 20 = 1 Second
            }
        }
        
        if(this.angerLevel < 0 || this.entityToAttack == null){
        	this.removePotionEffect(Potion.resistance.id);
        }

        this.field_110191_bu = this.entityToAttack;

        if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0)
        {
            this.playSound(Sound.KurrGrowl, this.getSoundVolume() * 2.0F, getSoundPitch());
        }
        
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

            if (entity instanceof EntityPlayer)
            {
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));

                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity1 = (Entity)list.get(i);

                    if (entity1 instanceof EntityKurr)
                    {
                        EntityKurr entitykurr = (EntityKurr)entity1;
                        entitykurr.becomeAngryAt(entity);
                    }
                }

                this.becomeAngryAt(entity);
            }

            return super.attackEntityFrom(p_70097_1_, p_70097_2_);
        }
    }
    
    /**
     * Causes this PigZombie to become angry at the supplied Entity (which will be a player).
     */
    private void becomeAngryAt(Entity p_70835_1_)
    {
        this.entityToAttack = p_70835_1_;
        this.angerLevel = 400 + this.rand.nextInt(400);
        this.randomSoundDelay = this.rand.nextInt(40);
    }

    public boolean attackEntityAsMob(Entity victim) { //TODO
        float attackDamage = (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int knockback = 5;

        if (victim instanceof EntityLivingBase) {
            attackDamage += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) victim);
            ((EntityLivingBase)victim).addPotionEffect(new PotionEffect(Potion.blindness.id, 200));
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

            int fireAspect = 0 + rand.nextInt(3);

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
    	if(!ItemUtils.hasEquippedUsable(par1EntityPlayer)){
    		float volume = getSoundVolume() * 1.0f;
    		float pitch =  getSoundPitch();
            this.playSound(Sound.KurrHiss, volume, pitch);
    	}
            return super.interact(par1EntityPlayer);
    }
}