package common.zeroquest.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.base.Predicate;
import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.lib.Sound;
import common.zeroquest.util.ItemUtils;

public class EntityMetalZertum extends EntityZertumEntity
{
	
    public static final double maxHealth = 30;
    public static final double attackDamage = 6;
    public static final double speed = 0.30000001192092896;
    public static final double maxHealthTamed = 40;
    public static final double attackDamageTamed = 8;
    public static final double maxHealthBaby = 10;
    public static final double attackDamageBaby = 2;
    
    public static final IAttribute blastResist = (new RangedAttribute((IAttribute)null, "generic.blastResist", 1.0D, 0.1D, 50D)).setDescription("Explosion Resistance").setShouldWatch(true);
    
    public EntityMetalZertum(World worldIn)
    {
        super(worldIn);
        this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityMob.class, false, new Predicate()
        {
            private static final String __OBFID = "CL_00002229";
            public boolean func_180094_a(Entity p_180094_1_)
            {
                return p_180094_1_ instanceof EntityZombie || p_180094_1_ instanceof EntitySkeleton;
            }
            public boolean apply(Object p_apply_1_)
            {
                return this.func_180094_a((Entity)p_apply_1_);
            }
        }));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0F);
        this.getAttributeMap().registerAttribute(blastResist).setBaseValue(5.0D);

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
    
    @Override
    protected void playStepSound(BlockPos p_180429_1_, Block p_180429_2_)
    {
        this.playSound(Sound.MetalZertumStep, 0.15F, 1.0F);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound()
    {
        return this.canSeeCreeper ? Sound.MetalZertumRoar : this.isAngry() ? Sound.MetalZertumGrowl : 
        	this.wantToHowl ? Sound.MetalZertumHowl : (this.rand.nextInt(3) == 0 ? 
        			(this.isTamed() && this.getHealth() <= 10.0F ? Sound.MetalZertumWhine
        					: Sound.MetalZertumPant)
        					: Sound.MetalZertumBark);
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return Sound.MetalZertumHurt;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return Sound.MetalZertumDeath;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume()
    {
        return 2.5F;
    }
    
    public void onLivingUpdate() //TODO
    {
        super.onLivingUpdate();
        if(!this.isChild() && this.getHealth() <=10 && this.isTamed())
        {
       		this.addPotionEffect(new PotionEffect(Potion.resistance.id, 200));
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
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    @Override
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

        if (this.isTamed())
        {
            if (itemstack != null)
            {
                if (itemstack.getItem() instanceof ItemFood)
                {
                    ItemFood itemfood = null;
                    if(getHealthRelative() < 1)
                    {
                    	itemfood = (ItemFood) ItemUtils.consumeEquipped(par1EntityPlayer, Items.fish,
                            Items.porkchop, Items.beef, Items.chicken, Items.rabbit, Items.mutton, Items.cooked_porkchop, Items.cooked_beef,
                            Items.cooked_chicken, Items.cooked_fish, Items.cooked_rabbit, Items.cooked_mutton, ModItems.jakanMeatRaw, 
                            ModItems.jakanMeatCooked, ModItems.zertumMeatRaw, ModItems.zertumMeatCooked);
                        if (itemfood != null) {
                        	float volume = getSoundVolume() * 1.0f;
                        	float pitch =  getPitch();
                        	worldObj.playSoundAtEntity(this, Sound.Chew, volume, pitch);
                            this.heal((float)itemfood.getHealAmount(itemstack));
                        }
                        return true;
                    }
                }
                else if(itemstack.getItem() == Items.stick && canInteract(par1EntityPlayer)) //TODO
                {
                	if(isServer()){
                		par1EntityPlayer.openGui(ZeroQuest.instance, CommonProxy.PetPack, this.worldObj, this.getEntityId(), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
                		this.worldObj.playSoundEffect(this.posX, this.posY + 0.5D, this.posZ, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
                		return true;
                	}
                }
                else if (itemstack.getItem() == Items.dye)
                {
                    EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(itemstack.getMetadata());

                    if (enumdyecolor != this.getCollarColor())
                    {
                        this.setCollarColor(enumdyecolor);

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
            	this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPathEntity();
                this.setAttackTarget((EntityLivingBase)null);
            }
        }
        else if (ItemUtils.consumeEquipped(par1EntityPlayer, ModItems.nileBone) && !this.isAngry())
        {
            if (isServer())
            {
                tamedFor(par1EntityPlayer, rand.nextInt(3) == 0);
            	par1EntityPlayer.triggerAchievement(ModAchievements.ZertTame);
            }
            return true;
        }
        return super.interact(par1EntityPlayer);
    }
    
    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
    public EntityMetalZertum createChild(EntityAgeable p_90011_1_)
    {
    	EntityMetalZertum entitywolf = new EntityMetalZertum(this.worldObj);
        String s = this.getOwnerId();

        if (s != null && s.trim().length() > 0)
        {
            entitywolf.setOwnerId(s);
            entitywolf.setTamed(true);
        }

        return entitywolf;
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
        else if (!(p_70878_1_ instanceof EntityMetalZertum))
        {
            return false;
        }
        else
        {
            EntityMetalZertum entitywolf = (EntityMetalZertum)p_70878_1_;
            return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false : this.isInLove() && entitywolf.isInLove());
        }
    }
}
