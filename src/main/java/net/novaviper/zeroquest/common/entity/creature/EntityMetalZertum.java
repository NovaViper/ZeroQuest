package net.novaviper.zeroquest.common.entity.creature;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.novaviper.zeroquest.ModItems;
import net.novaviper.zeroquest.ZeroQuest;
import net.novaviper.zeroquest.common.CommonProxy;
import net.novaviper.zeroquest.common.api.interfaces.IBits;
import net.novaviper.zeroquest.common.api.interfaces.IBits.EnumFeedBack;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.entity.util.TalentHelper;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.lib.Sound;
import net.novaviper.zeroquest.common.util.ItemUtils;

import com.google.common.base.Predicate;

public class EntityMetalZertum extends EntityZertumEntity {

	public EntityMetalZertum(World worldIn) {
		super(worldIn);
		this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityMob.class, false, new Predicate() {
			public boolean isApplicable(Entity entityIn) {
				return entityIn instanceof EntityZombie || entityIn instanceof EntitySkeleton;
			}

			@Override
			public boolean apply(Object p_apply_1_) {
				return this.isApplicable((Entity) p_apply_1_);
			}
		}));
		this.targetTasks.addTask(5, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate() {
			public boolean isApplicable(Entity entityIn) {
				return entityIn instanceof EntitySheep || entityIn instanceof EntityRabbit;
			}

			@Override
			public boolean apply(Object p_apply_1_) {
				return this.isApplicable((Entity) p_apply_1_);
			}
		}));
	}

	@Override
	protected void playStepSound(BlockPos p_180429_1_, Block p_180429_2_) {
		this.playSound(Sound.MetalStep, 0.15F, 1.0F);
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound() {
		this.openMouth();
		String sound = TalentHelper.getLivingSound(this);
		if (!"".equals(sound)) {
			return sound;
		}

		return this.isAngry() ? Sound.MetalZertumGrowl : this.wantToHowl ? Sound.MetalZertumHowl
				: (this.rand.nextInt(3) == 0
				? (this.isTamed() && this.getHealth() <= Constants.lowHP
				? Sound.MetalZertumWhine : Sound.MetalZertumPant)
				: Sound.MetalZertumBark);
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound() {
		this.openMouth();
		return Sound.MetalZertumHurt;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound() {
		this.openMouth();
		return Sound.MetalZertumDeath;
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow,
	 * gets into the saddle on a pig.
	 */
	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack stack = player.inventory.getCurrentItem();

		if (TalentHelper.interactWithPlayer(this, player)) {
			return true;
		}

		if (this.isTamed()) {
			if (stack != null) {
				int foodValue = this.foodValue(stack);

				if (foodValue != 0 && this.getZertumHunger() < Constants.hungerTicks && this.canInteract(player)) {
					if (!player.capabilities.isCreativeMode && --stack.stackSize <= 0) {
						player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
					}
					float volume = getSoundVolume() * 1.0f;
					float pitch = getPitch();
					worldObj.playSoundAtEntity(this, Sound.Chew, volume, pitch);
					this.setZertumHunger(this.getZertumHunger() + foodValue);
					return true;
				}
				else if (stack.getItem() == Item.getItemFromBlock(Blocks.planks) && this.canInteract(player)) {
					player.openGui(ZeroQuest.instance, CommonProxy.PetInfo, this.worldObj, this.getEntityId(), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
					return true;
				}
				else if (stack.getItem() instanceof IBits && this.canInteract(player)) {
					IBits treat = (IBits) stack.getItem();
					EnumFeedBack type = treat.canGiveToDog(player, this, this.levels.getLevel());
					treat.giveTreat(type, player, this);
					return true;
				}
				//IMPRT: Evolve Logic
				else if (stack != null && stack.getItem() == ModItems.evoBit && this.levels.getLevel() == Constants.stage2Level && !this.hasEvolved() && !this.inFinalStage() && isServer() && this.canInteract(player)) {
					this.evolveOnClient(player);
				}
				else if (stack != null && stack.getItem() == ModItems.pettraBit && this.levels.getLevel() == Constants.maxLevel && this.hasEvolved() && !this.inFinalStage() && isServer() && this.canInteract(player)) {
					this.finaEvolveOnClient(player);
				}
				else if (stack.getItem() == Items.shears && this.isOwner(player)) {
					if (isServer()) {
						unTame();
					}
					return true;
				}
				else if (stack.getItem() == Items.stick && canInteract(player))
				{
					if (isServer()) {
						player.openGui(ZeroQuest.instance, CommonProxy.PetPack, this.worldObj, this.getEntityId(), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
						this.worldObj.playSoundEffect(this.posX, this.posY + 0.5D, this.posZ, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
						return true;
					}
				}
				else if (stack.getItem() == Items.dye && this.canInteract(player)) {
					EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(stack.getMetadata());

					if (enumdyecolor != this.getCollarColor()) {
						this.setCollarColor(enumdyecolor);

						if (!player.capabilities.isCreativeMode && --stack.stackSize <= 0) {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
						}

						return true;
					}
				}
			}

			if (canInteract(player) && isServer() && !this.isBreedingItem(stack)) {
				this.aiSit.setSitting(!this.isSitting());
				this.isJumping = false;
				this.navigator.clearPathEntity();
				this.setAttackTarget((EntityLivingBase) null);
			}
		}
		else if (ItemUtils.consumeEquipped(player, ModItems.nileBone) && !this.isAngry()) {
			if (isServer()) {
				tamedFor(player, rand.nextInt(3) == 0);
			}
			return true;
		}
		return super.interact(player);
	}

	/**
	 * This function is used when two same-species animals in 'love mode' breed
	 * to generate the new baby animal.
	 */
	@Override
	public EntityMetalZertum createChild(EntityAgeable p_90011_1_) {
		EntityMetalZertum entitywolf = new EntityMetalZertum(this.worldObj);
		String s = this.getOwnerId();

		if (s != null && s.trim().length() > 0) {
			entitywolf.setOwnerId(s);
			entitywolf.setTamed(true);
		}

		return entitywolf;
	}

	/**
	 * Returns true if the mob is currently able to mate with the specified mob.
	 */
	@Override
	public boolean canMateWith(EntityAnimal otherAnimal) {
		if (otherAnimal == this) {
			return false;
		}
		else if (!this.isTamed()) {
			return false;
		}
		else if (!(otherAnimal instanceof EntityMetalZertum)) {
			return false;
		}
		else {
			EntityMetalZertum entityMetalZertum = (EntityMetalZertum) otherAnimal;
			return !entityMetalZertum.isTamed() ? false : (entityMetalZertum.isSitting() ? false
					: this.getGender() == entityMetalZertum.getGender() ? false
							: this.isInLove() && entityMetalZertum.isInLove());
		}
	}
}
