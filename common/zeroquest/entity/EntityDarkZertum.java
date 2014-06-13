package common.zeroquest.entity;

import java.util.List;

import net.minecraft.block.BlockColored;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.ai.EntityAITamed;
import common.zeroquest.entity.ai.EntityCustomAIFollowOwner;
import common.zeroquest.entity.ai.EntityCustomAIOwnerHurtByTarget;
import common.zeroquest.entity.ai.EntityCustomAIOwnerHurtTarget;
import common.zeroquest.entity.ai.EntityCustomAISit;
import common.zeroquest.entity.ai.EntityCustomAITargetNonTamed;
import common.zeroquest.entity.ai.EntityDZAIBeg;
import common.zeroquest.inventory.InventoryDarkPack;
import common.zeroquest.inventory.InventoryRedPack;
import common.zeroquest.lib.Constants;
import common.zeroquest.particle.ParticleEffects;
import common.zeroquest.proxy.CommonProxy;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityDarkZertum extends EntityCustomTameable
{
    private float field_70926_e;
    private float field_70924_f;

    /** true is the wolf is wet else false */
    private boolean isShaking;
    private boolean field_70928_h;
    private boolean canSeeCreeper;
    
    /**
     * This time increases while wolf is shaking and emitting water particles.
     */
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;
    public int rare;
    public InventoryDarkPack inventory;
    
    public static final double maxHealth = 30;
    public static final double attackDamage = 10;
    public static final double speed = 0.30000001192092896;
    public static final double maxHealthTamed = 40;
    public static final double attackDamageTamed = 12;
    public static final double maxHealthBaby = 10;
    public static final double attackDamageBaby = 4;
    
    public EntityDarkZertum(World par1World)
    {
        super(par1World);
        this.setSize(0.6F, 0.8F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiCSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityCustomAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityDZAIBeg(this, 8.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityCustomAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityCustomAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAITamed(this, EntityMob.class, 200, false));
        this.targetTasks.addTask(6, new EntityCustomAITargetNonTamed(this, EntityPlayer.class, 200, false));
        this.setTamed(false);
        this.inventory = new InventoryDarkPack(this);
    }
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.experienceValue = 5;
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(speed);

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
	

	
    /**
     * Returns true if the newer Entity AI code should be run
     */
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
            this.setAngry(false);
        }
        else if (!this.isTamed())
        {
            this.setAngry(true);
        }
    }

    /**
     * main AI tick function, replaces updateEntityActionState
     */
    protected void updateAITick()
    {
        this.dataWatcher.updateObject(18, Float.valueOf(this.getHealth()));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(18, new Float(this.getHealth()));
        this.dataWatcher.addObject(19, new Byte((byte)0));
        this.dataWatcher.addObject(20, new Byte((byte)BlockColored.getBlockFromDye(1)));
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.wolf.step", 0.15F, 1.0F);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("Angry", this.isAngry());
        par1NBTTagCompound.setByte("CollarColor", (byte)this.getCollarColor());
        this.inventory.writeToNBT(par1NBTTagCompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setAngry(par1NBTTagCompound.getBoolean("Angry"));
        this.inventory.readFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("CollarColor"))
        {
            this.setCollarColor(par1NBTTagCompound.getByte("CollarColor"));
        }
    }


    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return this.canSeeCreeper ? "mob.wolf.growl" : this.isAngry() ? "mob.wolf.growl" : 
        	(this.rand.nextInt(3) == 0 ? 
        			(this.isTamed() && this.dataWatcher.getWatchableObjectFloat(18) < 10.0F ? "mob.wolf.whine"
        					: "mob.wolf.panting")
        					: "mob.wolf.bark");
    }


    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.wolf.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.5F;
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
                    this.dropItem(ModItems.zertumMeatCooked.itemID, 1);
                }
                else if (rare <= 12)
                {
                    this.dropItem(ModItems.zertumMeatRaw.itemID, 1);
                }
                if(rare <= 6 && !this.isTamed())
                {
                	this.dropItem(ModItems.darkGrain.itemID, 1);
                }
                if(rare >= 17 && !this.isTamed())
                {
                	this.dropItem(ModItems.darkDust.itemID, 1);
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

        if (!this.worldObj.isRemote && this.isShaking && !this.field_70928_h && !this.hasPath() && this.onGround)
        {
            this.field_70928_h = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
            this.worldObj.setEntityState(this, (byte)8);
        }
        
        if(this.getHealth() <=10 && this.isTamed())
        {
       		this.addPotionEffect(new PotionEffect(10, 200));
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
        
        if(Constants.DEF_DPARTICLESLOAD == true){ //TODO
        	double d0 = this.rand.nextGaussian() * 0.04D;
        	double d1 = this.rand.nextGaussian() * 0.04D;
        	double d2 = this.rand.nextGaussian() * 0.04D;
        	worldObj.spawnParticle("townaura", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
        
        this.field_70924_f = this.field_70926_e;

        if (this.func_70922_bv())
        {
            this.field_70926_e += (1.0F - this.field_70926_e) * 0.4F;
        }
        else
        {
            this.field_70926_e += (0.0F - this.field_70926_e) * 0.4F;
        }

        if (this.func_70922_bv())
        {
            this.numTicksToChaseTarget = 10;
        }

        if (this.isWet() || this.isBurning())
        {
            this.isShaking = true;
            this.field_70928_h = false;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        }
        

        else if ((this.isShaking || this.field_70928_h) && this.field_70928_h)
        {
            if (this.timeWolfIsShaking == 0.0F)
            {
                this.playSound("mob.wolf.shake", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
            this.timeWolfIsShaking += 0.05F;
            
            if (this.prevTimeWolfIsShaking >= 1.0F)
            {
            	
                this.isShaking = false;
                this.field_70928_h = false;
                this.prevTimeWolfIsShaking = 0.0F;
                this.timeWolfIsShaking = 0.0F;
            }

            if (this.timeWolfIsShaking > 0.4F)
            {
                float f = (float)this.boundingBox.minY;
                int i = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * (float)Math.PI) * 7.0F);

                for (int j = 0; j < i; ++j)
                	if(!this.isBurning()) {
                    float f1 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    this.worldObj.spawnParticle("splash", this.posX + (double)f1, (double)(f + 0.8F), this.posZ + (double)f2, this.motionX, this.motionY, this.motionZ);
                }
            }else{
           		this.addPotionEffect(new PotionEffect(12, 20));
            }
        }
    }

    @Override
    protected void playTameEffect(boolean par1)
    {
        String s = "heart";

        if (!par1)
        {
        	s = "blackdust";
        }

        for (int i = 0; i < 7; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            ParticleEffects.spawnParticle(s, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean getWolfShaking()
    {
        return this.isShaking;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Used when calculating the amount of shading to apply while the wolf is shaking.
     */
    public float getShadingWhileShaking(float par1)
    {
        return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1) / 2.0F * 0.25F;
    }

    @SideOnly(Side.CLIENT)
    public float getShakeAngle(float par1, float par2)
    {
        float f2 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1 + par2) / 1.8F;

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
    public float getInterestedAngle(float par1)
    {
        return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * par1) * 0.15F * (float)Math.PI;
    }

    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    /**
     * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
     * use in wolves.
     */
    public int getVerticalFaceSpeed()
    {
        return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
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
    
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if (par1Entity instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)par1Entity);
            ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
        }

        boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);

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
    
    public void dropChestItems()
    {
        this.dropItemsInChest(this, this.inventory);
    }
    
    private void dropItemsInChest(Entity par1Entity, InventoryDarkPack par2AnimalChest)
    {
        if (par2AnimalChest != null && !this.worldObj.isRemote)
        {
            for (int i = 0; i < par2AnimalChest.getSizeInventory(); ++i)
            {
                ItemStack itemstack = par2AnimalChest.getStackInSlot(i);

                if (itemstack != null)
                {
                    this.entityDropItem(itemstack, 0.0F);
                }
            }
        }
    }

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

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
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

                    if (itemfood.isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectFloat(18) < 20.0F)
                    {
                        if (!par1EntityPlayer.capabilities.isCreativeMode)
                        {
                            --itemstack.stackSize;
                        }

                        this.heal((float)itemfood.getHealAmount());

                        if (itemstack.stackSize <= 0)
                        {
                            par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                        }

                        return true;
                    }
                }
                else if(itemstack.itemID == Item.stick.itemID && canInteract(par1EntityPlayer) && !this.isChild())//TODO Inventory
                {
                   	par1EntityPlayer.openGui(ZeroQuest.instance, CommonProxy.DarkZertumPack, this.worldObj, this.entityId, MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
                   	//par1EntityPlayer.triggerAchievement(par1StatBase);
                	return true;
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

            if (par1EntityPlayer.getCommandSenderName().equalsIgnoreCase(this.getOwnerName()) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack))
            {
                this.aiCSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.setPathToEntity((PathEntity)null);
                this.setTarget((Entity)null);
                this.setAttackTarget((EntityLivingBase)null);
            }
        }
        else if (itemstack != null && itemstack.itemID == ModItems.darkNileBone.itemID && !this.isAngry())
        {
            if (!par1EntityPlayer.capabilities.isCreativeMode)
            {
                --itemstack.stackSize;
            }

            if (itemstack.stackSize <= 0)
            {
                par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
            }

            if (!this.worldObj.isRemote)
            {
                if (this.rand.nextInt(3) == 0)
                {
                	par1EntityPlayer.triggerAchievement(ModAchievements.ZertTame);
                    this.setTamed(true);
                    this.setPathToEntity((PathEntity)null);
                    this.setAttackTarget((EntityLivingBase)null);
                    this.aiCSit.setSitting(true);
                    this.setHealth(40.0F);
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

    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte par1)
    {
        if (par1 == 8)
        {
            this.field_70928_h = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        }
        else
        {
            super.handleHealthUpdate(par1);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public float getTailRotation()
    {
        return this.isAngry() ? 1.5393804F : (this.isTamed() ? (0.55F - (20.0F - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * (float)Math.PI : ((float)Math.PI / 5F));
    }
    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return par1ItemStack == null ? false : par1ItemStack.itemID == ModItems.dogTreat.itemID;
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
        return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    /**
     * Sets whether this wolf is angry or not.
     */
    public void setAngry(boolean par1)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 2)));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -3)));
        }
    }

    /**
     * Return this wolf's collar color.
     */
    public int getCollarColor()
    {
        return this.dataWatcher.getWatchableObjectByte(20) & 15;
    }

    /**
     * Set this wolf's collar color.
     */
    public void setCollarColor(int par1)
    {
        this.dataWatcher.updateObject(20, Byte.valueOf((byte)(par1 & 15)));
    }
    
    public double sniffRange(){//TODO
        double d = 0.0D;
        for (int i = 0; i < 15 * 6; i++)
        {
            d++;
        }
        return d;
    }

	 public void func_70918_i(boolean par1) //TODO Breeding stuff
	 {
		 byte var2 = this.dataWatcher.getWatchableObjectByte(19);


		 if (par1)
		 {
			 this.dataWatcher.updateObject(19, Byte.valueOf((byte)1));
		 }
		 else
		 {
			 this.dataWatcher.updateObject(19, Byte.valueOf((byte)0));
		 }
	 }


  
  /**
   * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
   */
	    public EntityCustomTameable spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	    {
	        EntityDarkZertum entitydarkzertum = new EntityDarkZertum(this.worldObj);
	        String s = this.getOwnerName();

	        if (s != null && s.trim().length() > 0)
	        {
	        	entitydarkzertum.setOwner(s);
	        	entitydarkzertum.setTamed(true);
	        }

	        return entitydarkzertum;
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
		 else if (!(par1EntityAnimal instanceof EntityDarkZertum))
		 {
			 return false;
		 }
		 else
		 {
			 EntityDarkZertum var2 = (EntityDarkZertum)par1EntityAnimal;
			 return !var2.isTamed() ? false : (var2.isSitting() ? false : this.isInLove() && var2.isInLove());
		 }
	 }
	 
		@Override
		public EntityAgeable createChild(EntityAgeable entityageable) {
			return spawnBabyAnimal(entityageable);
		}

    public boolean func_70922_bv()
    {
        return this.dataWatcher.getWatchableObjectByte(19) == 1;
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return !this.isTamed() && this.ticksExisted > 2400;
    }
}