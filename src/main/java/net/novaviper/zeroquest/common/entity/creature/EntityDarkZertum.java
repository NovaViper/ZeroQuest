package net.novaviper.zeroquest.common.entity.creature;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
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

public class EntityDarkZertum extends EntityZertumEntity {

	public EntityDarkZertum(World worldIn) {
		super(worldIn);
		this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate() {
			public boolean isApplicable(Entity entityIn) {
				return entityIn instanceof EntitySheep || entityIn instanceof EntityRabbit || entityIn instanceof EntityZertum;
			}

			@Override
			public boolean apply(Object p_apply_1_) {
				return this.isApplicable((Entity) p_apply_1_);
			}
		}));
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		double d0 = this.rand.nextGaussian() * 0.04D;
		double d1 = this.rand.nextGaussian() * 0.04D;
		double d2 = this.rand.nextGaussian() * 0.04D;
		worldObj.spawnParticle(EnumParticleTypes.TOWN_AURA, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (!TalentHelper.shouldDamageMob(this, entity)) {
			return false;
		}

		int damage = (int) (4 + (this.effectiveLevel() + this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getBaseValue()) / 2);
		damage = TalentHelper.attackEntityAsMob(this, entity, damage);

		if (entity instanceof EntityLivingBase) {
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
		}

		if (entity instanceof EntityZombie) {
			((EntityZombie) entity).setAttackTarget(this);
		}

		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
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
		else if (ItemUtils.consumeEquipped(player, ModItems.darkBone) && !this.isAngry()) {
			if (isServer()) {
				tamedFor(player, rand.nextInt(3) == 0);
			}
			return true;
		}
		return super.interact(player);
	}

	/**
	 * This method gets called when the entity kills another one.
	 */
	@Override
	public void onKillEntity(EntityLivingBase entityLivingIn) {
		super.onKillEntity(entityLivingIn);

		if ((this.worldObj.getDifficulty() == EnumDifficulty.NORMAL || this.worldObj.getDifficulty() == EnumDifficulty.HARD) && entityLivingIn instanceof EntityZertum) {
			EntityZertum zertum = (EntityZertum) entityLivingIn;

			if (this.worldObj.getDifficulty() != EnumDifficulty.HARD && this.rand.nextBoolean()) {
				return;
			}

			EntityDarkZertum entityDarkZertum = new EntityDarkZertum(this.worldObj);
			entityDarkZertum.copyLocationAndAnglesFrom(zertum);
			this.worldObj.removeEntity(entityLivingIn);
			entityDarkZertum.setGender(zertum.getGender());

			if (zertum.isChild()) {
				entityDarkZertum.setGrowingAge(-24000);
			}

			this.worldObj.spawnEntityInWorld(entityDarkZertum);
			this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, new BlockPos((int) this.posX, (int) this.posY, (int) this.posZ), 0);
		}
	}

	/**
	 * This function is used when two same-species animals in 'love mode' breed
	 * to generate the new baby animal.
	 */
	@Override
	public EntityDarkZertum createChild(EntityAgeable p_90011_1_) {
		EntityDarkZertum entitywolf = new EntityDarkZertum(this.worldObj);
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
		else if (!(otherAnimal instanceof EntityDarkZertum)) {
			return false;
		}
		else {
			EntityDarkZertum entityDarkZertum = (EntityDarkZertum) otherAnimal;
			return !entityDarkZertum.isTamed() ? false : (entityDarkZertum.isSitting() ? false
					: this.getGender() == entityDarkZertum.getGender() ? false
							: this.isInLove() && entityDarkZertum.isInLove());
		}
	}
}