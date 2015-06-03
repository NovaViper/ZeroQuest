package common.zeroquest.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
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
import common.zeroquest.inventory.InventoryPack;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.DataValues;
import common.zeroquest.lib.Sound;
import common.zeroquest.util.ItemUtils;

public class EntityJakan extends EntityCustomTameable /* implements
													 * IRangedAttackMob */
{

	public static final double maxHealth = 50;
	public static final double attackDamage = 6;
	public static final double speed = 0.30000001192092896;
	public static final double maxHealthTamed = 60;
	public static final double attackDamageTamed = 8;
	public static final double maxHealthBaby = 10;
	public static final double attackDamageBaby = 2;

	public EntityJakan(World worldIn) {
		super(worldIn);
		this.setSize(2.6F, 2.6F);
		this.isImmuneToFire = true;
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate() {
			private static final String __OBFID = "CL_00002229";

			public boolean func_180094_a(Entity p_180094_1_) {
				return p_180094_1_ instanceof EntitySheep || p_180094_1_ instanceof EntityPig || p_180094_1_ instanceof EntityCow;
			}

			@Override
			public boolean apply(Object p_apply_1_) {
				return this.func_180094_a((Entity) p_apply_1_);
			}
		}));
		this.setTamed(false);
		this.inventory = new InventoryPack(this);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);

		if (this.isTamed()) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthTamed);
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageTamed);
		}
		else if (this.isChild()) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthBaby);
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageBaby);
		}
	}

	/**
	 * Sets the active target the Task system uses for tracking
	 */
	@Override
	public void setAttackTarget(EntityLivingBase p_70624_1_) {
		super.setAttackTarget(p_70624_1_);

		if (p_70624_1_ == null) {
			this.setAngry(false);
		}
		else if (!this.isTamed()) {
			this.setAngry(true);
		}
	}

	@Override
	protected void playStepSound(BlockPos p_180429_1_, Block p_180429_2_) {
		if (inWater) {
			// no sounds for underwater action
		}
		else if (this.isChild()) {
			// play default step sound for babies
			super.playStepSound(p_180429_1_, p_180429_2_);
		}
		else {
			// play stomping for bigger dragons
			worldObj.playSoundAtEntity(this, Sound.Step, 0.15F, 1.0F);
		}
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound() {
		return this.canSeeCreeper ? Sound.JakanGrowl : this.isAngry() ? Sound.JakanSnarl
				: this.getHealth() <= Constants.lowHP ? Sound.JakanWhine
						: (this.rand.nextInt(3) == 0 ? (Sound.JakanBreathe) : Sound.JakanRoar);
	}

	@Override
	public void playLivingSound() {
		String sound = getLivingSound();
		if (sound == null) {
			return;
		}
		if (!this.isChild()) {
			float volume = getSoundVolume() * 1.0f;
			float pitch = getSoundPitch();
			this.playSound(sound, volume, pitch);
		}
		else {
			float volume = getSoundVolume() * 1.0f;
			float pitch = getSoundPitch() * 2;
			this.playSound(sound, volume, pitch);
		}
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound() {
		return "mob.enderdragon.hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound() {
		return Sound.JakanDeath;
	}

	/**
	 * Get number of ticks, at least during which the living entity will be
	 * silent.
	 */
	@Override
	public int getTalkInterval() {
		if (this.canSeeCreeper) {
			return 40;
		}
		else if (this.getHealth() <= Constants.lowHP) {
			return 20;
		}
		else {
			return 200;
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
				this.dropItem(ModItems.jakanMeatCooked, 1);
			}
			else if (rare <= 12) {
				this.dropItem(ModItems.jakanMeatRaw, 1);
			}
			if (rare <= 6 && !this.isTamed()) {
				this.dropItem(ModItems.nileGrain, 1);
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
		if (isServer() && this.getAttackTarget() == null && this.isAngry()) {
			this.setAngry(false);
		}

		if (Constants.DEF_HEALING == true && !this.isChild() && this.getHealth() <= Constants.lowHP && this.isTamed()) {
			this.addPotionEffect(new PotionEffect(10, 200));
		}
		// Dying
		if (this.getHealth() <= Constants.lowHP && this.isTamed()) { // TODO
			double d0 = this.rand.nextGaussian() * 0.04D;
			double d1 = this.rand.nextGaussian() * 0.04D;
			double d2 = this.rand.nextGaussian() * 0.04D;
			worldObj.spawnParticle(EnumParticleTypes.SPELL_WITCH, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
		}
		if (this.getAttackTarget() == null && isTamed() && 15 > 0) {
			List list1 = worldObj.getEntitiesWithinAABB(EntityCreeper.class, AxisAlignedBB.fromBounds(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(sniffRange(), 4D, sniffRange()));

			if (!list1.isEmpty() && !isSitting() && this.getHealth() > Constants.lowHP && !this.isChild()) {
				canSeeCreeper = true;
			}
			else {
				canSeeCreeper = false;
			}
		}
	}

	@Override
	public float getEyeHeight() {
		return this.height * 0.8F;
	}

	@Override
	public int getVerticalFaceSpeed() {
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		}
		else {
			Entity entity = source.getEntity();
			this.aiSit.setSitting(false);

			if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
				amount = (amount + 1.0F) / 2.0F;
			}

			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		float damage = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
		int i = 0;
		int critChance = 5;
		critChance += 2;

		if (rand.nextInt(6) < critChance) { // TODO
			damage += (damage + 3) / 2;
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
		}

		boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);

		if (flag) {
			if (i > 0) {
				par1Entity.addVelocity(-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * i * 0.5F, 0.1D, MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * i * 0.5F);
				this.motionX *= 0.6D;
				this.motionZ *= 0.6D;
			}

			float volume = getSoundVolume() * 0.7f;
			float pitch = getPitch();
			worldObj.playSoundAtEntity(this, "random.eat", volume, pitch);
		}
		return flag;
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow,
	 * gets into the saddle on a pig.
	 */
	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack stack = player.inventory.getCurrentItem();

		if (this.isTamed()) {
			if (!this.isChild() && ItemUtils.consumeEquipped(player, Items.saddle) && !this.isSaddled()) // TODO
			{
				this.setSaddled(true);
				this.playSound("mob.horse.leather", 0.5F, 1.0F);
			}
			if (player.getHeldItem() == null) {
				if (this.isSaddled() && player.ridingEntity == null && !player.onGround && this.isServer()) {
					this.getSitAI().setSitting(false);
					this.setSitting(false);
					player.mountEntity(this);
					player.triggerAchievement(ModAchievements.MountUp);
					return true;
				}
			}
			else if (stack != null && stack.getItem() == Items.stick && canInteract(player)) // TODO
			{
				if (isServer()) {
					player.openGui(ZeroQuest.instance, CommonProxy.PetPack, this.worldObj, this.getEntityId(), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
					this.worldObj.playSoundEffect(this.posX, this.posY + 0.5D, this.posZ, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
					return true;
				}
			}
		}

		if (this.isTamed()) {
			if (stack != null) {
				if (stack.getItem() instanceof ItemFood) {
					ItemFood itemfood = null;
					if (getHealthRelative() < 1) {
						itemfood = (ItemFood) ItemUtils.consumeEquipped(player, Items.fish, Items.porkchop, Items.beef, Items.chicken, Items.rabbit, Items.mutton, Items.cooked_porkchop, Items.cooked_beef, Items.cooked_chicken, Items.cooked_fish, Items.cooked_rabbit, Items.cooked_mutton, ModItems.jakanMeatRaw, ModItems.jakanMeatCooked, ModItems.zertumMeatRaw, ModItems.zertumMeatCooked, ModItems.vitoidFruit);
						if (itemfood != null) {
							float volume = getSoundVolume() * 1.0f;
							float pitch = getPitch();
							worldObj.playSoundAtEntity(this, "random.eat", volume, pitch);
							this.heal(itemfood.getHealAmount(stack));
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

	@Override
	public void moveEntityWithHeading(float strafe, float forward) {
		if (this.riddenByEntity instanceof EntityPlayer) {
			this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
			this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
			strafe = ((EntityPlayer) this.riddenByEntity).moveStrafing * 0.5F;
			forward = ((EntityPlayer) this.riddenByEntity).moveForward;

			if (forward <= 0.0F) {
				forward *= 0.25F;
			}

			if (this.onGround) {
				if (forward > 0.0F) {
					float f2 = MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F);
					float f3 = MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F);
					this.motionX += -0.4F * f2 * 0.15F; // May change
					this.motionZ += 0.4F * f3 * 0.15F;
				}
			}

			this.stepHeight = 1.0F;
			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.2F;

			if (isServer()) {
				this.setAIMoveSpeed((float) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() / 4);
				super.moveEntityWithHeading(strafe, forward);
			}

			if (this.onGround) {
				// this.jumpPower = 0.0F;
				// this.setHorseJumping(false);
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d0 = this.posX - this.prevPosX;
			double d1 = this.posZ - this.prevPosZ;
			float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

			if (f4 > 1.0F) {
				f4 = 1.0F;
			}

			this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		}
		else {
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(strafe, forward);
		}
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
		if (distance > 1.0F) {
			this.playSound(Sound.Land, 0.4F, 1.0F);
		}

		int i = MathHelper.ceiling_float_int((distance * 0.5F - 3.0F) * damageMultiplier);

		if (i > 0) {
			this.attackEntityFrom(DamageSource.fall, i);

			if (this.riddenByEntity != null) {
				this.riddenByEntity.attackEntityFrom(DamageSource.fall, i);
			}

			Block block = this.worldObj.getBlockState(new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ)).getBlock();

			if (block.getMaterial() != Material.air && !this.isSilent()) {
				Block.SoundType soundtype = block.stepSound;
				this.worldObj.playSoundAtEntity(this, soundtype.getStepSound(), soundtype.getVolume() * 0.5F, soundtype.getFrequency() * 0.75F);
			}
		}
	}

	@Override
	public float getAIMoveSpeed() {
		double speed = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();

		speed += 0.2;

		if ((!(this.getAttackTarget() instanceof EntityJakan) && !(this.getAttackTarget() instanceof EntityPlayer)) || this.riddenByEntity instanceof EntityPlayer) {
			speed /= 4;
		}

		return (float) speed;
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed
	 * it (wheat, carrots or seeds depending on the animal type)
	 */
	@Override
	public boolean isBreedingItem(ItemStack itemstack) {
		return itemstack == null ? false : itemstack.getItem() == ModItems.vitoidFruit;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 8;
	}

	@Override
	public EntityJakan createChild(EntityAgeable p_90011_1_) {
		EntityJakan entityJakan = new EntityJakan(this.worldObj);
		String s = this.getOwnerId();

		if (s != null && s.trim().length() > 0) {
			entityJakan.setOwnerId(s);
			entityJakan.setTamed(true);
		}

		return entityJakan;
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
		else if (!(otherAnimal instanceof EntityJakan)) {
			return false;
		}
		else {
			EntityJakan entityJakan = (EntityJakan) otherAnimal;
			return !entityJakan.isTamed() ? false : (entityJakan.isSitting() ? false
					: this.getGender() == entityJakan.getGender() ? false
							: this.isInLove() && entityJakan.isInLove());
		}
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	@Override
	protected boolean canDespawn() {
		return !this.isTamed() && this.ticksExisted > 2400;
	}

	@Override
	public boolean allowLeashing() {
		return !this.isAngry() && super.allowLeashing();
	}
}