package common.zeroquest.entity;

import java.util.List;

import net.minecraft.block.Block;
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
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
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



public class EntityDarkZertum extends EntityZertumEntity
{
    
    public static final double maxHealth = 30;
    public static final double attackDamage = 10;
    public static final double speed = 0.30000001192092896;
    public static final double maxHealthTamed = 40;
    public static final double attackDamageTamed = 12;
    public static final double maxHealthBaby = 10;
    public static final double attackDamageBaby = 4;
    
    public EntityDarkZertum(World worldIn)
    {
        super(worldIn);
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
        double d0 = this.rand.nextGaussian() * 0.04D;
        double d1 = this.rand.nextGaussian() * 0.04D;
        double d2 = this.rand.nextGaussian() * 0.04D;
        worldObj.spawnParticle(EnumParticleTypes.TOWN_AURA, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        
    }

    public boolean attackEntityAsMob(Entity par1Entity)
    {
        float damage = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if (par1Entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
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
                            Items.cooked_chicken, Items.cooked_fish, Items.cooked_rabbit, Items.cooked_mutton, ModItems.jakanMeatRaw, ModItems.jakanMeatCooked, 
                            ModItems.zertumMeatRaw, ModItems.zertumMeatCooked, ModItems.kurrSeeds);
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
        else if (ItemUtils.consumeEquipped(par1EntityPlayer, ModItems.darkBone) && !this.isAngry())
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
    public EntityDarkZertum createChild(EntityAgeable p_90011_1_)
    {
    	EntityDarkZertum entitywolf = new EntityDarkZertum(this.worldObj);
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
        else if (!(p_70878_1_ instanceof EntityDarkZertum))
        {
            return false;
        }
        else
        {
        	EntityDarkZertum entitywolf = (EntityDarkZertum)p_70878_1_;
            return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false : this.isInLove() && entitywolf.isInLove());
        }
    }
}