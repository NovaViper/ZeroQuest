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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
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
import common.zeroquest.api.interfaces.IDogTreat;
import common.zeroquest.api.interfaces.IDogTreat.EnumFeedBack;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.entity.util.TalentHelper;
import common.zeroquest.entity.util.ModeUtil.EnumMode;
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
        this.getAttributeMap().registerAttribute(blastResist).setBaseValue(0.1D);
        this.updateEntityAttributes();
    }
    
    @Override
    public void updateEntityAttributes() {
        if (this.isTamed())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthTamed + this.effectiveLevel());
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageTamed);
            this.getEntityAttribute(blastResist).setBaseValue((double)this.talents.getLevel("blastresist"));
            //System.out.println("Blast Reistance Level: " + this.getEntityAttribute(blastResist).getBaseValue());
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
    	String sound = TalentHelper.getLivingSound(this);
    	if(!"".equals(sound))
    		return sound;
    	
        return this.isAngry() ? Sound.MetalZertumGrowl : 
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
	public float getSoundVolume()
    {
        return 2.5F;
    }
    
    public void onLivingUpdate() //TODO
    {
        super.onLivingUpdate();
        TalentHelper.onLivingUpdate(this);
        
        if(!this.isChild() && this.getHealth() <=10 && this.isTamed())
        {
       		this.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 2));
        }
    }
    
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack stack = player.inventory.getCurrentItem();

        if(TalentHelper.interactWithPlayer(this, player))
        	return true;
        
        if (this.isTamed()) {
            if (stack != null) {
            	int foodValue = this.foodValue(stack);
            	
            	if(foodValue != 0 && this.getDogHunger() < 120 && this.canInteract(player)) {
            		 if(!player.capabilities.isCreativeMode && --stack.stackSize <= 0)
                         player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                 	float volume = getSoundVolume() * 1.0f;
                 	float pitch =  getPitch();
                 	worldObj.playSoundAtEntity(this, Sound.Chew, volume, pitch);
                    this.setDogHunger(this.getDogHunger() + foodValue);
                    return true;
                }
            	else if(stack.getItem() == Items.bone && this.canInteract(player)) {
            		if (isServer()) {
                        if(this.ridingEntity != null)
                        	this.mountEntity(null);
                        else
                         	this.mountEntity(player);
                    }
                    return true;
                }
            	else if(stack.getItem() == Item.getItemFromBlock(Blocks.planks) && this.canInteract(player)) {
            		player.openGui(ZeroQuest.instance, CommonProxy.PetInfo, this.worldObj, this.getEntityId(), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
                 	return true;
                }
                else if(stack.getItem() instanceof IDogTreat && this.canInteract(player)) {
                 	IDogTreat treat = (IDogTreat)stack.getItem();
                 	EnumFeedBack type = treat.canGiveToDog(player, this, this.levels.getLevel());
                 	treat.giveTreat(type, player, this);
                 	return true;
                }
                else if(stack.getItem() == Items.shears && this.isOwner(player)) {
                	if(!this.worldObj.isRemote) {
                		this.setTamed(false);
                	    this.navigator.clearPathEntity();
                        this.setSitting(false);
                        this.setHealth(this.getMaxHealth());
                        this.talents.resetTalents();
                        this.setOwnerId("");
                        this.setDogName("");
                        this.setWillObeyOthers(false);
                        this.mode.setMode(EnumMode.DOCILE);
                     }

                	return true;
                }
                else if(stack.getItem() == Items.stick && canInteract(player)) //TODO
                {
                	if(isServer()){
                		player.openGui(ZeroQuest.instance, CommonProxy.PetPack, this.worldObj, this.getEntityId(), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
                		this.worldObj.playSoundEffect(this.posX, this.posY + 0.5D, this.posZ, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
                		return true;
                	}
                }
                else if (stack.getItem() == Items.dye)
                {
                    EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(stack.getMetadata());

                    if (enumdyecolor != this.getCollarColor())
                    {
                        this.setCollarColor(enumdyecolor);

                        if (!player.capabilities.isCreativeMode && --stack.stackSize <= 0)
                        {
                        	player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                        }

                        return true;
                    }
                }
            }

            if (canInteract(player) && isServer() && !this.isBreedingItem(stack))
            {
            	this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPathEntity();
                this.setAttackTarget((EntityLivingBase)null);
            }
        }
        else if (ItemUtils.consumeEquipped(player, ModItems.nileBone) && !this.isAngry())
        {
            if (isServer())
            {
                tamedFor(player, rand.nextInt(3) == 0);
            	player.triggerAchievement(ModAchievements.ZertTame);
            }
            return true;
        }
        return super.interact(player);
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
