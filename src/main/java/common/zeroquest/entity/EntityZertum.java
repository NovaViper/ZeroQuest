package common.zeroquest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.base.Predicate;

import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import common.zeroquest.api.interfaces.IBits;
import common.zeroquest.api.interfaces.IBits.EnumFeedBack;
import common.zeroquest.core.proxy.ClientProxy;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.entity.util.ModeUtil.EnumMode;
import common.zeroquest.entity.util.TalentHelper;
import common.zeroquest.lib.Sound;
import common.zeroquest.util.ItemUtils;


public class EntityZertum extends EntityZertumEntity
{
    public static final double maxHealth = 25;
    public static final double attackDamage = 6;
    public static final double speed = 0.30000001192092896;
    public static final double maxHealthTamed = 35;
    public static final double attackDamageTamed = 8;
    public static final double maxHealthBaby = 10;
    public static final double attackDamageBaby = 2;
    public static final double maxHealthEvo = 45;
    public static final double attackDamageEvo = 10;
    public static final double speedEvo = 0.40000001192092896;
 
    public EntityZertum(World worldIn)
    {
        super(worldIn);
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
    
    @Override
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
        else if(this.hasEvolved()){
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthEvo + this.effectiveLevel());
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageEvo);
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speedEvo);
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
            	else if(stack.getItem() == Item.getItemFromBlock(Blocks.planks) && this.canInteract(player)) {
            		player.openGui(ZeroQuest.instance, CommonProxy.PetInfo, this.worldObj, this.getEntityId(), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
                 	return true;
                }
                else if(stack.getItem() instanceof IBits && this.canInteract(player)) {
                 	IBits treat = (IBits)stack.getItem();
                 	EnumFeedBack type = treat.canGiveToDog(player, this, this.levels.getLevel());
                 	treat.giveTreat(type, player, this);
                 	return true;
                }
                else if(stack != null && stack.getItem() == ModItems.evoBit && this.levels.getLevel() == 120 && isServer() && this.canInteract(player)) {
                	this.setEvolved(true);
                    this.worldObj.playBroadcastSound(1013, new BlockPos(this), 0);
                }
                else if(stack.getItem() == Items.shears && this.isOwner(player)) {
                	if(!this.worldObj.isRemote) {
                		this.setTamed(false);
                	    this.navigator.clearPathEntity();
                        this.setSitting(false);
                        this.setHealth((float)maxHealth);
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
                else if (stack.getItem() == Items.dye && this.canInteract(player))
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
 	 public EntityCustomTameable spawnBabyAnimal(EntityAgeable par1EntityAgeable)
 	 {
 		 double chance = Math.random();

 		 if (chance < 0.5){
 			 EntityZertum var3 = new EntityZertum(this.worldObj);
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
        else if (!(p_70878_1_ instanceof EntityZertum))
        {
            return false;
        }
        else
        {
        	EntityZertum entitywolf = (EntityZertum)p_70878_1_;
            return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false : this.isInLove() && entitywolf.isInLove());
        }
    }
}