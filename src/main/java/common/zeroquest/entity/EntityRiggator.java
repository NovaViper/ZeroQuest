package common.zeroquest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import common.zeroquest.ModItems;
import common.zeroquest.entity.zertum.EntityZertum;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.DataValues;
import common.zeroquest.lib.Sound;

public class EntityRiggator extends EntityCustomMob {
	private float mouthOpenness;
	private float prevMouthOpenness;
	private int openMouthCounter;

	public static final double maxHealth = 50;
	public static final double attackDamage = 8;
	public static final double speed = 0.40000001192092896;
	public static final double maxHealthBaby = 10;
	public static final double attackDamageBaby = 2;

	public EntityRiggator(World p_i1696_1_) {
		super(p_i1696_1_);
		this.setSize(0.6F, 2.6F);
		this.stepHeight = 1;
		this.isImmuneToFire = true;
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(1, this.aiAvoidExplodingCreepers);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZertum.class, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
		this.experienceValue = 5;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);

		if (this.isChild()) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealthBaby);
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attackDamageBaby);
		}
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DataValues.mouth, Integer.valueOf(0));
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound() {
		this.openMouth();
		return this.getHealth() <= Constants.lowHP ? Sound.RiggatorWhine
				: (this.rand.nextInt(3) == 0 ? (Sound.RiggatorGrowl) : Sound.RiggatorHiss);
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
		this.openMouth();
		return Sound.RiggatorHit;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound() {
		this.openMouth();
		return Sound.RiggatorDeath;
	}

	/**
	 * Get number of ticks, at least during which the living entity will be
	 * silent.
	 */
	@Override
	public int getTalkInterval() {
		if (this.getHealth() <= Constants.lowHP) {
			return 20;
		}
		else {
			return 50;
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
				this.dropItem(Items.cooked_beef, 1);
			}
			else if (rare <= 12) {
				this.dropItem(Items.beef, 1);
			}
			if (rare <= 6) {
				this.dropItem(ModItems.nileGrain, 1);
			}
			else {

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

			if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
				amount = (amount + 1.0F) / 2.0F;
			}

			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) // TODO
	{
		float damage = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
		int knockback = 2;

		if (par1Entity instanceof EntityLivingBase) {
			((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 200, 2));
			((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 200));
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

		return flag;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (this.openMouthCounter > 0 && ++this.openMouthCounter > 30) {
			this.openMouthCounter = 0;
			this.setWatchableBoolean(128, false);
		}

		this.prevMouthOpenness = this.mouthOpenness;

		if (this.getWatchableBoolean(128)) {
			this.mouthOpenness += (1.0F - this.mouthOpenness) * 0.7F + 0.05F;

			if (this.mouthOpenness > 1.0F) {
				this.mouthOpenness = 1.0F;
			}
		}
		else {
			this.mouthOpenness += (0.0F - this.mouthOpenness) * 0.7F - 0.05F;

			if (this.mouthOpenness < 0.0F) {
				this.mouthOpenness = 0.0F;
			}
		}
	}

	private boolean getWatchableBoolean(int p_110233_1_) // TODO
	{
		return (this.dataWatcher.getWatchableObjectInt(DataValues.mouth) & p_110233_1_) != 0;
	}

	private void setWatchableBoolean(int p_110208_1_, boolean p_110208_2_) {
		int j = this.dataWatcher.getWatchableObjectInt(DataValues.mouth);

		if (p_110208_2_) {
			this.dataWatcher.updateObject(DataValues.mouth, Integer.valueOf(j | p_110208_1_));
		}
		else {
			this.dataWatcher.updateObject(DataValues.mouth, Integer.valueOf(j & ~p_110208_1_));
		}
	}

	@SideOnly(Side.CLIENT)
	public float func_110201_q(float p_110201_1_) {
		return this.prevMouthOpenness + (this.mouthOpenness - this.prevMouthOpenness) * p_110201_1_;
	}

	private void openMouth() // TODO
	{
		if (this.isServer()) {
			this.openMouthCounter = 1;
			this.setWatchableBoolean(128, true);
		}
	}

	public boolean isMouthOpen() {
		if (this.getWatchableBoolean(128) == true) {
			return true;
		}
		return false;
	}
}