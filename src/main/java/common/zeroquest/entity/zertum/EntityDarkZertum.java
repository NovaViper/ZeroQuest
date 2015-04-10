package common.zeroquest.entity.zertum;

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
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.base.Predicate;
import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import common.zeroquest.api.interfaces.IBits;
import common.zeroquest.api.interfaces.IBits.EnumFeedBack;
import common.zeroquest.core.proxy.CommonProxy;
import common.zeroquest.entity.util.ModeUtil.EnumMode;
import common.zeroquest.entity.util.TalentHelper;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Sound;
import common.zeroquest.util.ItemUtils;

public class EntityDarkZertum extends EntityZertumEntity {

	public static final double maxHealth = 30;
	public static final double attackDamage = 10;
	public static final double speed = 0.30000001192092896;
	public static final double maxHealthTamed = 40;
	public static final double attackDamageTamed = 12;
	public static final double maxHealthBaby = 10;
	public static final double attackDamageBaby = 4;
	public static final double maxHealthEvo = 60;
	public static final double attackDamageEvo = 14;
	public static final double speedEvo = 0.40000001192092896;

	public EntityDarkZertum(World worldIn) {
		super(worldIn);
		this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate() {
			private static final String __OBFID = "CL_00002229";

			public boolean func_180094_a(Entity p_180094_1_) {
				return p_180094_1_ instanceof EntitySheep || p_180094_1_ instanceof EntityRabbit || p_180094_1_ instanceof EntityZertum;
			}

			@Override
			public boolean apply(Object p_apply_1_) {
				return this.func_180094_a((Entity) p_apply_1_);
			}
		}));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);
		this.updateEntityAttributes();
	}

	@Override
	public void updateEntityAttributes() {
		if (this.isTamed()) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthTamed + this.effectiveLevel());
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageTamed);
		}
		else if (this.isChild()) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthBaby);
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageBaby);
		}
		else if (this.hasEvolved()) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthEvo);
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageEvo);
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speedEvo);
		}
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2) {
		rare = rand.nextInt(20);
		{
			if (this.isBurning()) {
				this.dropItem(ModItems.zertumMeatCooked, 1);
			}
			else if (rare <= 12) {
				this.dropItem(ModItems.zertumMeatRaw, 1);
			}
			if (rare <= 6 && !this.isTamed()) {
				this.dropItem(ModItems.darkGrain, 1);
			}
			if (this.isSaddled()) {
				this.dropItem(Items.saddle, 1);
			}
			else {

			}

		}
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() // TODO
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

				if (foodValue != 0 && this.getDogHunger() < 120 && this.canInteract(player)) {
					if (!player.capabilities.isCreativeMode && --stack.stackSize <= 0) {
						player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
					}
					float volume = getSoundVolume() * 1.0f;
					float pitch = getPitch();
					worldObj.playSoundAtEntity(this, Sound.Chew, volume, pitch);
					this.setDogHunger(this.getDogHunger() + foodValue);
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
				else if (stack != null && stack.getItem() == ModItems.evoBit && this.levels.getLevel() == Constants.maxLevel && !this.hasEvolved() && isServer() && this.canInteract(player)) { // TODO
					this.evolveOnClient();
				}
				else if (stack.getItem() == Items.shears && this.isOwner(player)) {
					if (!this.worldObj.isRemote) {
						this.setTamed(false);
						this.setEvolved(false);
						this.navigator.clearPathEntity();
						this.setSitting(false);
						this.setHealth((float) maxHealth);
						this.talents.resetTalents();
						this.setOwnerId("");
						this.setDogName("");
						this.setWillObeyOthers(false);
						this.mode.setMode(EnumMode.DOCILE);
					}
					return true;
				}
				else if (stack.getItem() == Items.stick && canInteract(player)) // TODO
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
				player.triggerAchievement(ModAchievements.ZertTame);
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
	public boolean canMateWith(EntityAnimal p_70878_1_) {
		if (p_70878_1_ == this) {
			return false;
		}
		else if (!this.isTamed()) {
			return false;
		}
		else if (!(p_70878_1_ instanceof EntityDarkZertum)) {
			return false;
		}
		else {
			EntityDarkZertum entitywolf = (EntityDarkZertum) p_70878_1_;
			return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false
					: this.isInLove() && entitywolf.isInLove());
		}
	}
}