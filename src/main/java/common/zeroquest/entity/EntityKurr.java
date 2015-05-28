package common.zeroquest.entity;

import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import common.zeroquest.ModAchievements;
import common.zeroquest.ModItems;
import common.zeroquest.lib.DataValues;
import common.zeroquest.lib.Sound;
import common.zeroquest.util.ItemUtils;

public class EntityKurr extends EntityCustomMob /* implements IRangedAttackMob */
{
	private static final UUID field_110189_bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
	private static final AttributeModifier field_110190_br = (new AttributeModifier(field_110189_bq, "Attacking speed boost", 0.05D, 0)).setSaved(false);
	private int angerLevel;
	private int randomSoundDelay;
	private UUID field_175459_bn;

	public static final double maxHealth = 80;
	public static final double attackDamage = 14;
	public static final double speed = 0.30000001192092896;
	public static final double maxHealthBaby = 10;
	public static final double attackDamageBaby = 2;

	public EntityKurr(World p_i1696_1_) {
		super(p_i1696_1_);
		this.setSize(2.6F, 2.6F);
		this.stepHeight = 1;
		this.canBreatheUnderwater();
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiAvoidExplodingCreepers);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		this.experienceValue = 50;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(20F);

		if (this.isChild()) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthBaby);
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageBaby);
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

	@Override
	protected String getHurtSound() {
		return "mob.enderdragon.hit";
	}

	@Override
	protected String getLivingSound() {
		return this.getHealth() <= DataValues.lowHP ? Sound.KurrWhine : (this.rand.nextInt(3) == 0
				? (Sound.KurrBreathe) : Sound.KurrRoar);
	}

	@Override
	protected String getDeathSound() {
		return Sound.KurrDeath;
	}

	/**
	 * Get number of ticks, at least during which the living entity will be
	 * silent.
	 */
	@Override
	public int getTalkInterval() {
		if (this.getHealth() <= DataValues.lowHP) {
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
			else if (rare <= 18) {
				this.dropItem(ModItems.kurrSeeds, 1);
			}
			if (rare <= 6) {
				this.dropItem(ModItems.darkGrain, 1);
			}
			if (rare >= 17) {
				this.dropItem(ModItems.darkEssence, 1);
			}
			else {

			}

		}
	}

	@Override
	public void setRevengeTarget(EntityLivingBase p_70604_1_) {
		super.setRevengeTarget(p_70604_1_);

		if (p_70604_1_ != null) {
			this.field_175459_bn = p_70604_1_.getUniqueID();
		}
	}

	protected void func_175456_n() {
		this.targetTasks.addTask(1, new EntityKurr.AIHurtByAggressor());
		this.targetTasks.addTask(2, new EntityKurr.AITargetAggressor());
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	protected void updateAITasks() {
		IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);

		if (this.func_175457_ck()) {
			if (!this.isChild() && !iattributeinstance.func_180374_a(field_110190_br)) {
				iattributeinstance.applyModifier(field_110190_br);
				this.addPotionEffect(new PotionEffect(Potion.resistance.id, 9999999, 2)); // 100
																							// =
																							// 5
																							// Seconds
																							// ,
																							// 20
																							// =
																							// 1
																							// Second
			}

			--this.angerLevel;
		}
		else if (iattributeinstance.func_180374_a(field_110190_br)) {
			iattributeinstance.removeModifier(field_110190_br);
			this.removePotionEffect(Potion.resistance.id);
		}

		if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
			this.playSound(Sound.KurrGrowl, this.getSoundVolume() * 2.0F, getSoundPitch());
		}

		if (this.angerLevel > 0 && this.field_175459_bn != null && this.getAITarget() == null) {
			EntityPlayer entityplayer = this.worldObj.getPlayerEntityByUUID(this.field_175459_bn);
			this.setRevengeTarget(entityplayer);
			this.attackingPlayer = entityplayer;
			this.recentlyHit = this.getRevengeTimer();
		}

		super.updateAITasks();
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	@Override
	public void onDeath(DamageSource par1DamageSource) // TODO
	{
		super.onDeath(par1DamageSource);
		if (par1DamageSource.getEntity() instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) par1DamageSource.getEntity();
			{
				entityplayer.triggerAchievement(ModAchievements.DragonSlayer);
			}
		}
	}

	@Override
	public boolean getCanSpawnHere() {
		return this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL;
	}

	@Override
	public boolean handleLavaMovement() {
		return this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox());
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
		tagCompound.setShort("Anger", (short) this.angerLevel);

		if (this.field_175459_bn != null) {
			tagCompound.setString("HurtBy", this.field_175459_bn.toString());
		}
		else {
			tagCompound.setString("HurtBy", "");
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund) {
		super.readEntityFromNBT(tagCompund);
		this.angerLevel = tagCompund.getShort("Anger");
		String s = tagCompund.getString("HurtBy");

		if (s.length() > 0) {
			this.field_175459_bn = UUID.fromString(s);
			EntityPlayer entityplayer = this.worldObj.getPlayerEntityByUUID(this.field_175459_bn);
			this.setRevengeTarget(entityplayer);

			if (entityplayer != null) {
				this.attackingPlayer = entityplayer;
				this.recentlyHit = this.getRevengeTimer();
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		}
		else {
			Entity entity = source.getEntity();

			if (entity instanceof EntityPlayer) {
				this.becomeAngryAt(entity);
			}

			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) // TODO
	{
		float damage = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
		int knockback = 5;

		if (par1Entity instanceof EntityLivingBase) {
			((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 200));
			// knockback += EnchantmentHelper.getKnockbackModifier(this,
			// (EntityLivingBase) par1Entity);
		}

		boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);

		if (flag) {
			if (knockback > 0) {
				par1Entity.addVelocity(-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F, 0.1D, MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F);
				this.motionX *= 0.6D;
				this.motionZ *= 0.6D;
			}
		}

		float volume = getSoundVolume() * 0.7f;
		float pitch = getPitch();
		worldObj.playSoundAtEntity(this, "random.eat", volume, pitch);

		return flag;
	}

	private void becomeAngryAt(Entity p_70835_1_) {
		this.angerLevel = 400 + this.rand.nextInt(400);
		this.randomSoundDelay = this.rand.nextInt(40);

		if (p_70835_1_ instanceof EntityLivingBase) {
			this.setRevengeTarget((EntityLivingBase) p_70835_1_);
		}
	}

	public boolean func_175457_ck() {
		return this.angerLevel > 0;
	}

	@Override
	public boolean interact(EntityPlayer p_70085_1_) {
		return false;
	}

	class AIHurtByAggressor extends EntityAIHurtByTarget {
		private static final String __OBFID = "CL_00002206";

		public AIHurtByAggressor() {
			super(EntityKurr.this, true, new Class[0]);
		}

		@Override
		protected void func_179446_a(EntityCreature p_179446_1_, EntityLivingBase p_179446_2_) {
			super.func_179446_a(p_179446_1_, p_179446_2_);

			if (p_179446_1_ instanceof EntityKurr) {
				((EntityKurr) p_179446_1_).becomeAngryAt(p_179446_2_);
			}
		}
	}

	class AITargetAggressor extends EntityAINearestAttackableTarget {
		private static final String __OBFID = "CL_00002207";

		public AITargetAggressor() {
			super(EntityKurr.this, EntityPlayer.class, true);
		}

		@Override
		public boolean shouldExecute() {
			return ((EntityKurr) this.taskOwner).func_175457_ck() && super.shouldExecute();
		}
	}
}