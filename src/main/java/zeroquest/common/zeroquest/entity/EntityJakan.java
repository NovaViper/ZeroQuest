package common.zeroquest.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
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
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.inventory.InventoryJakanPack;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Sound;
import common.zeroquest.util.ItemUtils;



public class EntityJakan extends EntityCustomTameable /*implements IRangedAttackMob*/
{
    public InventoryJakanPack inventory;
    
    public static final double maxHealth = 50;
    public static final double attackDamage = 6;
    public static final double speed = 0.30000001192092896;
    public static final double maxHealthTamed = 60;
    public static final double attackDamageTamed = 8;
    public static final double maxHealthBaby = 10;
    public static final double attackDamageBaby = 2;
    
    // data value IDs
    public static final int INDEX_TAME = 16;
    public static final int INDEX_BREED = 20;
    public static final int INDEX_COLLAR_COLOR = 21;
    public static final int INDEX_SADDLE = 22;
    
	
    public EntityJakan(World p_i1696_1_)
    {
        super(p_i1696_1_);
        this.setSize(2.6F, 2.6F);
        this.isImmuneToFire = true;
        this.stepHeight = 1;
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntitySheep.class, 200, false));
        this.setTamed(false);
        this.inventory = new InventoryJakanPack(this);
    }

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
        else if (!this.isTamed())
        {
            this.setAngry(true);
        }
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(INDEX_BREED, new Byte((byte)0));
        this.dataWatcher.addObject(INDEX_COLLAR_COLOR, new Byte((byte)BlockColored.func_150032_b(1)));
        this.dataWatcher.addObject(INDEX_SADDLE, Byte.valueOf((byte)0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setBoolean("Angry", this.isAngry());
        p_70014_1_.setByte("CollarColor", (byte)this.getCollarColor());
        p_70014_1_.setBoolean("Saddle", this.getSaddled());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        this.setAngry(p_70037_1_.getBoolean("Angry"));
        this.setSaddled(p_70037_1_.getBoolean("Saddle"));

        if (p_70037_1_.hasKey("CollarColor", 99))
        {
            this.setCollarColor(p_70037_1_.getByte("CollarColor"));
        }
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
        return this.canSeeCreeper ? Sound.JakanGrowl : this.isAngry() ? Sound.JakanSnarl :
        	 this.getHealth() <=10 ? Sound.JakanWhine :
        	(this.rand.nextInt(3) == 0 ? 
        			(Sound.JakanBreathe)
        					: Sound.JakanRoar);
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
		return Sound.JakanDeath;	
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
                if(rare <= 6 && !this.isTamed())
                {
                	this.dropItem(ModItems.nileGrain, 1);
                }
                if(this.getSaddled())
                {
                	this.dropItem(Items.saddle, 1);
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

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        
        if (riddenByEntity != null) //check if there is a rider
        {
          //currentTarget = this;
          this.randomYawVelocity = 0; //try not to let the horse control where to look.
          this.rotationYaw = riddenByEntity.rotationYaw;
        }
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
            this.aiSit.setSitting(false);

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
                if(isServer()){
                this.dropChestItems();
                }
            }
        }
    }
    
    public void dropChestItems()
    {
        this.dropItemsInChest(this, this.inventory);
    }
    
    private void dropItemsInChest(Entity par1Entity, InventoryJakanPack inventory2)
    {
        if (inventory2 != null && !this.worldObj.isRemote)
        {
            for (int i = 0; i < inventory2.getSizeInventory(); ++i)
            {
                ItemStack itemstack = inventory2.getStackInSlot(i);

                if (itemstack != null)
                {
                    this.entityDropItem(itemstack, 0.0F);
                }
            }
        }
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer) //TODO
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
                            Items.porkchop, Items.beef, Items.chicken, Items.cooked_porkchop, Items.cooked_beef,
                            Items.cooked_chicken, Items.cooked_fished, ModItems.jakanMeatRaw, ModItems.jakanMeatCooked, 
                            ModItems.zertumMeatRaw, ModItems.zertumMeatCooked, ModItems.vitoidFruit);
                        if (itemfood != null) {
                        	float volume = getSoundVolume() * 1.0f;
                        	float pitch =  getPitch();
                        	worldObj.playSoundAtEntity(this, "random.eat", volume, pitch);
                            this.heal((float)itemfood.func_150905_g(itemstack));
                        }

                        return true;
                    }
                }
                if (!this.isChild() && ItemUtils.hasEquipped(par1EntityPlayer, Items.saddle)) //TODO
                {
                	this.setSaddled(true);
                }
                else if (this.riddenByEntity == null && this.getSaddled() && !this.isChild() && !ItemUtils.hasEquippedUsable(par1EntityPlayer)  && itemstack.getItem() != Items.spawn_egg && itemstack.getItem() != Items.stick && itemstack.getItem() != Items.dye && !this.isBreedingItem(itemstack))
                {
                        par1EntityPlayer.mountEntity(this);
                        this.aiSit.setSitting(false);
                }
                else if(itemstack.getItem() == Items.stick && canInteract(par1EntityPlayer)) //TODO
                {
                 par1EntityPlayer.openGui(ZeroQuest.instance, CommonProxy.JakanPack, this.worldObj, this.getEntityId(), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
                 return true;
                }
                else if (itemstack.getItem() == Items.dye)
                {
                    int i = BlockColored.func_150032_b(itemstack.getItemDamage());

                    if (i != this.getCollarColor())
                    {
                        this.setCollarColor(i);
                        ItemUtils.consumeEquipped(par1EntityPlayer, Items.dye);
                        return true;
                    }
                }
            }

            if (canInteract(par1EntityPlayer) && isServer() && !this.isBreedingItem(itemstack))
            {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.setPathToEntity((PathEntity)null);
                this.setTarget((Entity)null);
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
    @Override
    public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) //TODO
    {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase && this.getSaddled())
        {
            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
            p_70612_1_ = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
            p_70612_2_ = ((EntityLivingBase)this.riddenByEntity).moveForward;

            if (p_70612_2_ <= 0.0F)
            {
                p_70612_2_ *= 0.25F;
            }

            this.stepHeight = 1.0F;
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

            if (isServer())
            {
                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d1 = this.posX - this.prevPosX;
            double d0 = this.posZ - this.prevPosZ;
            float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

            if (f4 > 1.0F)
            {
                f4 = 1.0F;
            }

            this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        }
        else
        {
            this.stepHeight = 0.5F;
            this.jumpMovementFactor = 0.02F;
            super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
        }
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack itemstack) //TODO
    {
    	return itemstack == null ? false : itemstack.getItem() == ModItems.vitoidFruit;
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
        return (this.dataWatcher.getWatchableObjectByte(INDEX_TAME) & 2) != 0;
    }

    /**
     * Sets whether this wolf is angry or not.
     */
    public void setAngry(boolean p_70916_1_)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70916_1_)
        {
            this.dataWatcher.updateObject(INDEX_TAME, Byte.valueOf((byte)(b0 | 2)));
        }
        else
        {
            this.dataWatcher.updateObject(INDEX_TAME, Byte.valueOf((byte)(b0 & -3)));
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
    public void setCollarColor(int p_82185_1_)
    {
        this.dataWatcher.updateObject(INDEX_COLLAR_COLOR, Byte.valueOf((byte)(p_82185_1_ & 15)));
    }
    
    public EntityJakan createChild(EntityAgeable p_90011_1_)
    {
    	EntityJakan entitywolf = new EntityJakan(this.worldObj);
        String s = this.func_152113_b();

        if (s != null && s.trim().length() > 0)
        {
            entitywolf.func_152115_b(s);
            entitywolf.setTamed(true);
        }

        return entitywolf;
    }

    public void func_70918_i(boolean p_70918_1_)
    {
        if (p_70918_1_)
        {
            this.dataWatcher.updateObject(INDEX_BREED, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(INDEX_BREED, Byte.valueOf((byte)0));
        }
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
        else if (!(p_70878_1_ instanceof EntityJakan))
        {
            return false;
        }
        else
        {
        	EntityJakan entitywolf = (EntityJakan)p_70878_1_;
            return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false : this.isInLove() && entitywolf.isInLove());
        }
    }

    public boolean func_70922_bv()
    {
        return this.dataWatcher.getWatchableObjectByte(INDEX_BREED) == 1;
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return !this.isTamed() && this.ticksExisted > 2400;
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_)
    {
        if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast))
        {
            if (p_142018_1_ instanceof EntityJakan)
            {
            	EntityJakan entitywolf = (EntityJakan)p_142018_1_;

                if (entitywolf.isTamed() && entitywolf.getOwner() == p_142018_2_)
                {
                    return false;
                }
            }

            return p_142018_1_ instanceof EntityPlayer && p_142018_2_ instanceof EntityPlayer && !((EntityPlayer)p_142018_2_).canAttackPlayer((EntityPlayer)p_142018_1_) ? false : !(p_142018_1_ instanceof EntityHorse) || !((EntityHorse)p_142018_1_).isTame();
        }
        else
        {
            return false;
        }
    }
}