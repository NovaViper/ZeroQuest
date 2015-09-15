package net.novaviper.zeroquest.common.entity;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
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
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.novaviper.zeroquest.ModAchievements;
import net.novaviper.zeroquest.ModItems;
import net.novaviper.zeroquest.client.model.ModelZertumStage2;
import net.novaviper.zeroquest.client.model.ModelZertumStage3;
import net.novaviper.zeroquest.common.api.ZeroQuestAPI;
import net.novaviper.zeroquest.common.container.InventoryPack;
import net.novaviper.zeroquest.common.entity.ai.EntityAIBeg;
import net.novaviper.zeroquest.common.entity.ai.EntityAIFetchToy;
import net.novaviper.zeroquest.common.entity.ai.EntityAIFollowOwner;
import net.novaviper.zeroquest.common.entity.ai.EntityAIModeAttackTarget;
import net.novaviper.zeroquest.common.entity.ai.EntityAIOwnerHurtByTarget;
import net.novaviper.zeroquest.common.entity.ai.EntityAIOwnerHurtTarget;
import net.novaviper.zeroquest.common.entity.ai.EntityAIRoundUp;
import net.novaviper.zeroquest.common.entity.creature.EntityDarkZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityDestroZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityForisZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityIceZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityMetalZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityRedZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityZertum;
import net.novaviper.zeroquest.common.entity.util.LevelUtil;
import net.novaviper.zeroquest.common.entity.util.ModeUtil;
import net.novaviper.zeroquest.common.entity.util.ModeUtil.EnumMode;
import net.novaviper.zeroquest.common.entity.util.TalentHelper;
import net.novaviper.zeroquest.common.entity.util.TalentUtil;
import net.novaviper.zeroquest.common.helper.ChatHelper;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.lib.DataValues;
import net.novaviper.zeroquest.common.lib.Sound;

public abstract class EntityZertumEntity extends EntityCustomTameable {

	protected EntityAILeapAtTarget aiLeap = new EntityAILeapAtTarget(this, 0.4F);
	public EntityAIWatchClosest aiStareAtPlayer = new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F);
	public EntityAIWatchClosest aiGlareAtCreeper = new EntityAIWatchClosest(this, EntityCreeper.class, this.talents.getLevel("creeperspotter") * 6);
	public EntityAIFetchToy aiFetchBone;

	private float timeDogBegging;
	private float prevTimeDogBegging;
	public float headRotationCourse;
	public float headRotationCourseOld;
	public boolean isWet;
	public boolean isShaking;
	public float timeWolfIsShaking;
	public float prevTimeWolfIsShaking;
	private int hungerTick;
	private int prevHungerTick;
	private int healingTick;
	private int prevHealingTick;
	private int regenerationTick;
	private int prevRegenerationTick;
	public TalentUtil talents;
	public LevelUtil levels;
	public ModeUtil mode;
	public Map<String, Object> objects;
	private boolean hasToy;
	private float timeWolfIsHappy;
	private float prevTimeWolfIsHappy;
	private boolean isWolfHappy;
	public boolean hiyaMaster;
	private float mouthOpenness;
	private float prevMouthOpenness;
	private int openMouthCounter;

	public EntityZertumEntity(World worldIn) {
		super(worldIn);
		objects = new HashMap<String, Object>();
		((PathNavigateGround) getNavigator()).setAvoidsWater(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, aiSit);
		tasks.addTask(3, aiLeap);
		tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
		tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		tasks.addTask(6, aiFetchBone = new EntityAIFetchToy(this, 1.0D, 0.5F, 20.0F));
		tasks.addTask(7, new EntityAIMate(this, 1.0D));
		tasks.addTask(8, new EntityAIWander(this, 1.0D));
		tasks.addTask(9, new EntityAIBeg(this, 8.0F));
		tasks.addTask(10, aiStareAtPlayer);
		tasks.addTask(10, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIModeAttackTarget(this));
		targetTasks.addTask(4, new EntityAIHurtByTarget(this, true));
		setTamed(false);
		setEvolved(false);
		setFinalStage(false);
		inventory = new InventoryPack(this);
		targetTasks.addTask(6, new EntityAIRoundUp(this, EntityAnimal.class, 0, false));
		TalentHelper.onClassCreation(this);

		if (!hasEvolved() && !inFinalStage()) {
			setSize(0.6F, 1.5F);
		}
		else if (hasEvolved() && inFinalStage()) {
			setSize(2F, 3F);
		}
	}

	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(wildHealth());
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(wildDamage());
		updateEntityAttributes();
	}

	public void updateEntityAttributes() {
		if (isTamed()) {
			if (!isChild() && !hasEvolved() && !inFinalStage()) {
				getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(tamedHealth() + effectiveLevel());
				getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(tamedDamage());
			}
			else if (!isChild() && hasEvolved() && !inFinalStage()) {
				getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(evoHealth() + effectiveLevel());
				getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(tamedDamage());
			}
			else if (!isChild() && hasEvolved() && inFinalStage()) {
				getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(finalEvoHealth() + effectiveLevel());
				getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(finalEvoDamage());
			}
			else {
				getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(babyHealth());
				getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(babyDamage());
			}
		}
		else {
			if (isChild()) {
				getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(babyHealth());
				getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(babyDamage());
			}
		}
	}

	@Override
	public void setTamed(boolean p_70903_1_) {
		super.setTamed(p_70903_1_);
		updateEntityAttributes();
	}

	public double tamedHealth() {
		if (this instanceof EntityZertum || this instanceof EntityRedZertum) {
			return 35;
		}
		else if (this instanceof EntityMetalZertum || this instanceof EntityIceZertum || this instanceof EntityForisZertum || this instanceof EntityDestroZertum || this instanceof EntityDarkZertum) {
			return 40;
		}
		return 0;
	}

	public double tamedDamage() {
		if (this instanceof EntityZertum || this instanceof EntityRedZertum || this instanceof EntityMetalZertum || this instanceof EntityIceZertum || this instanceof EntityForisZertum) {
			return 8;
		}
		else if (this instanceof EntityDestroZertum) {
			return 10;
		}
		else if (this instanceof EntityDarkZertum) {
			return 12;
		}
		return 0;
	}

	public double evoHealth() {
		if (this instanceof EntityZertum || this instanceof EntityRedZertum || this instanceof EntityIceZertum) {
			return 45;
		}
		else if (this instanceof EntityMetalZertum || this instanceof EntityForisZertum || this instanceof EntityDestroZertum) {
			return 50;
		}
		else if (this instanceof EntityDarkZertum) {
			return 60;
		}
		return 0;
	}

	public double finalEvoHealth() {
		if (this instanceof EntityZertum || this instanceof EntityRedZertum || this instanceof EntityIceZertum) {
			return 55;
		}
		else if (this instanceof EntityMetalZertum || this instanceof EntityForisZertum || this instanceof EntityDestroZertum) {
			return 60;
		}
		else if (this instanceof EntityDarkZertum) {
			return 70;
		}
		return 0;
	}

	public double finalEvoDamage() {
		if (this instanceof EntityZertum || this instanceof EntityRedZertum || this instanceof EntityMetalZertum || this instanceof EntityIceZertum || this instanceof EntityForisZertum) {
			return 12;
		}
		else if (this instanceof EntityDestroZertum) {
			return 18;
		}
		else if (this instanceof EntityDarkZertum) {
			return 22;
		}
		return 0;
	}

	public double wildHealth() {
		if (this instanceof EntityZertum || this instanceof EntityRedZertum || this instanceof EntityForisZertum) {
			return 25;
		}
		else if (this instanceof EntityMetalZertum || this instanceof EntityDestroZertum || this instanceof EntityDarkZertum) {
			return 30;
		}
		else if (this instanceof EntityIceZertum) {
			return 35;
		}
		return 0;
	}

	public double wildDamage() {
		if (this instanceof EntityZertum || this instanceof EntityRedZertum || this instanceof EntityMetalZertum || this instanceof EntityIceZertum || this instanceof EntityForisZertum) {
			return 6;
		}
		else if (this instanceof EntityDestroZertum) {
			return 8;
		}
		else if (this instanceof EntityDarkZertum) {
			return 10;
		}
		return 0;
	}

	public double babyHealth() {
		return 11;
	}

	public double babyDamage() {
		if (this instanceof EntityZertum || this instanceof EntityRedZertum || this instanceof EntityMetalZertum) {
			return 2;
		}
		else if (this instanceof EntityIceZertum || this instanceof EntityForisZertum || this instanceof EntityDarkZertum) {
			return 4;
		}
		else if (this instanceof EntityDestroZertum) {
			return 3;
		}
		return 0;
	}

	/**
	 * Sets the active target the Task system uses for tracking
	 */
	@Override
	public void setAttackTarget(EntityLivingBase p_70624_1_) {
		super.setAttackTarget(p_70624_1_);
		if (p_70624_1_ == null) {
			setAngry(false);
		}
		else if (!isTamed()) {
			setAngry(true);
		}
	}

	@Override
	public String getCommandSenderName() {
		String name = getPetName();
		if (name != "" && isTamed()) {
			return name;
		}
		else {
			return super.getCommandSenderName();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean getAlwaysRenderNameTagForRender() {
		return true;
	}

	//@formatter:off
	@Override
	protected void entityInit() {
		super.entityInit();
		talents = new TalentUtil(this);
		levels = new LevelUtil(this);
		mode = new ModeUtil(this);
		dataWatcher.addObject(DataValues.ownerName, new String("")); //Owner Name
		dataWatcher.addObject(DataValues.ownerID, new String("")); //Owner Id
		dataWatcher.addObject(DataValues.collarCollar, new Byte((byte) EnumDyeColor.RED.getMetadata())); //Collar
		dataWatcher.addObject(DataValues.dogName, new String("")); //Dog Name
		dataWatcher.addObject(DataValues.talentData, new String("")); //Talent Data
		dataWatcher.addObject(DataValues.hungerTicks, new Integer(Constants.hungerTicks)); //Dog Hunger
		dataWatcher.addObject(DataValues.levelData, new String("0:0")); //Level Data
		dataWatcher.addObject(DataValues.evolve, Byte.valueOf((byte)0)); //Evolution
		dataWatcher.addObject(DataValues.obeyOthers, new Integer(0)); //Obey Others
		dataWatcher.addObject(DataValues.zertumMode, new Integer(0)); //Zertum Mode
		dataWatcher.addObject(DataValues.mouth, Integer.valueOf(0)); //Mouth
		dataWatcher.addObject(DataValues.beg, new Byte((byte) 0)); //Begging
	}
	//@formatter:on
	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
		tagCompound.setString("ownerId", getOwnerID());
		tagCompound.setString("ownerName", getOwnerName());
		tagCompound.setByte("collarColor", (byte) getCollarColor().getDyeDamage());
		tagCompound.setBoolean("evolve", hasEvolved());
		tagCompound.setBoolean("finalEvolve", inFinalStage());
		tagCompound.setString("version", Constants.version);
		tagCompound.setString("dogName", getPetName());
		tagCompound.setInteger("dogHunger", getZertumHunger());
		tagCompound.setBoolean("willObey", willObeyOthers());
		tagCompound.setBoolean("dogBeg", isBegging());

		talents.writeTalentsToNBT(tagCompound);
		levels.writeTalentsToNBT(tagCompound);
		mode.writeToNBT(tagCompound);
		TalentHelper.writeToNBT(this, tagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound) {
		super.readEntityFromNBT(tagCompound);
		saveOwnerName(tagCompound.getString("ownerName"));
		saveOwnerID(tagCompound.getString("ownerId"));
		setEvolved(tagCompound.getBoolean("evolve"));
		setFinalStage(tagCompound.getBoolean("finalEvolve"));

		if (tagCompound.hasKey("collarColor", 99)) {
			setCollarColor(EnumDyeColor.byDyeDamage(tagCompound.getByte("collarColor")));
		}

		String lastVersion = tagCompound.getString("version");
		setPetName(tagCompound.getString("dogName"));
		setZertumHunger(tagCompound.getInteger("dogHunger"));
		setWillObeyOthers(tagCompound.getBoolean("willObey"));
		setBegging(tagCompound.getBoolean("dogBeg"));
		talents.readTalentsFromNBT(tagCompound);
		levels.readTalentsFromNBT(tagCompound);
		mode.readFromNBT(tagCompound);
		TalentHelper.readFromNBT(this, tagCompound);
	}

	@Override
	protected void playStepSound(BlockPos p_180429_1_, Block p_180429_2_) {
		playSound("mob.wolf.step", 0.15F, 1.0F);
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound() {
		openMouth();
		String sound = TalentHelper.getLivingSound(this);
		if (!"".equals(sound)) {
			return sound;
		}

		// if(!this.inFinalStage()){
		return isAngry() ? "mob.wolf.growl" : wantToHowl ? Sound.ZertumHowl : rand.nextInt(3) == 0
				? isTamed() && getHealth() <= Constants.lowHP ? "mob.wolf.whine"
						: "mob.wolf.panting" : "mob.wolf.bark";
		/* }else{ return Sound.; } */
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound() {
		openMouth();
		return "mob.wolf.hurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound() {
		openMouth();
		return "mob.wolf.death";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	public float getSoundVolume() {
		if (!inFinalStage()) {
			return 1F;
		}
		else {
			return 2F;
		}
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	@Override
	public float getPitch() {
		if (!isChild()) {
			return super.getSoundPitch();
		}
		else {
			return super.getSoundPitch() * 1;
		}
	}

	/**
	 * Get number of ticks, at least during which the living entity will be
	 * silent.
	 */
	@Override
	public int getTalkInterval() {
		int ticks = TalentHelper.getTalkInterval(this);
		if (ticks != 0) {
			return ticks;
		}
		else if (wantToHowl) {
			return 150;
		}
		else if (getHealth() <= Constants.lowHP) {
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
			if (isBurning()) {
				dropItem(ModItems.zertumMeatCooked, 1);
			}
			else if (rare <= 12) {
				dropItem(ModItems.zertumMeatRaw, 1);
			}
			if (rare <= 6 && !isTamed() && !(this instanceof EntityDarkZertum)) {
				dropItem(ModItems.nileGrain, 1);
			}
			if (rare <= 6 && !isTamed() && this instanceof EntityDarkZertum) {
				dropItem(ModItems.darkGrain, 1);
			}
			if (isSaddled()) {
				dropItem(Items.saddle, 1);
			}
			else {

			}

		}
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	@Override
	public void onDeath(DamageSource par1DamageSource) {
		super.onDeath(par1DamageSource);
		if (par1DamageSource.getEntity() instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) par1DamageSource.getEntity();
			{
				entityplayer.triggerAchievement(ModAchievements.zertumKill);
				dropChestItems();
			}
		}
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() // NAV: Living Updates
	{
		super.onLivingUpdate();
		if (isServer() && isWet && !isShaking && !hasPath() && onGround) {
			isShaking = true;
			timeWolfIsShaking = 0.0F;
			prevTimeWolfIsShaking = 0.0F;
			worldObj.setEntityState(this, (byte) 8);
		}

		if (Constants.DEF_IS_HUNGER_ON) {
			prevHungerTick = hungerTick;

			if (riddenByEntity == null && !isSitting()) {
				hungerTick += 1;
			}

			hungerTick += TalentHelper.onHungerTick(this, hungerTick - prevHungerTick);

			if (hungerTick > 400) {
				setZertumHunger(getZertumHunger() - 1);
				hungerTick -= 400;
			}
		}

		if (getHealth() != Constants.lowHP) {
			prevHealingTick = healingTick;
			healingTick += nourishment();

			if (healingTick >= 6000) {
				if (getHealth() < getMaxHealth()) {
					setHealth(getHealth() + 1);
				}

				healingTick = 0;
			}
		}

		if (getZertumHunger() == 0 && worldObj.getWorldInfo().getWorldTime() % 100L == 0L && getHealth() > Constants.lowHP) {
			attackEntityFrom(DamageSource.generic, 1);
		}

		if (isServer() && (getAttackTarget() == null || getAttackTarget().isDead) && isAngry()) {
			setAngry(false);
		}

		if (Constants.DEF_HOWL == true) {
			if (isServer()) {
				if (rand.nextInt(10) == 4) {
					if (!worldObj.isDaytime() && !isChild()) {
						wantToHowl = true;
					}
					else {
						wantToHowl = false;
					}
				}
			}
		}
		TalentHelper.onLivingUpdate(this);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		prevTimeDogBegging = timeDogBegging;

		if (isBegging()) {
			timeDogBegging += (1.0F - timeDogBegging) * 0.4F;
		}
		else {
			timeDogBegging += (0.0F - timeDogBegging) * 0.4F;
		}

		if (openMouthCounter > 0 && ++openMouthCounter > 30) {
			openMouthCounter = 0;
			setHorseWatchableBoolean(128, false);
		}

		prevMouthOpenness = mouthOpenness;

		if (getHorseWatchableBoolean(128)) {
			mouthOpenness += (1.0F - mouthOpenness) * 0.7F + 0.05F;

			if (mouthOpenness > 1.0F) {
				mouthOpenness = 1.0F;
			}
		}
		else {
			mouthOpenness += (0.0F - mouthOpenness) * 0.7F - 0.05F;

			if (mouthOpenness < 0.0F) {
				mouthOpenness = 0.0F;
			}
		}
		headRotationCourseOld = headRotationCourse;

		if (isBegging()) {
			headRotationCourse += (1.0F - headRotationCourse) * 0.4F;
		}
		else {
			headRotationCourse += (0.0F - headRotationCourse) * 0.4F;
		}

		if (isWet()) {
			isWet = true;
			isShaking = false;
			timeWolfIsShaking = 0.0F;
			prevTimeWolfIsShaking = 0.0F;
		}
		else if ((isWet || isShaking) && isShaking) {
			if (timeWolfIsShaking == 0.0F) {
				playSound("mob.wolf.shake", getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
			}

			prevTimeWolfIsShaking = timeWolfIsShaking;
			timeWolfIsShaking += 0.05F;

			if (prevTimeWolfIsShaking >= 2.0F) {
				if (rand.nextInt(15) < talents.getLevel("fishing") * 2) {
					if (rand.nextInt(15) < talents.getLevel("flamingelemental") * 2 && this instanceof EntityRedZertum) {
						if (isServer()) {
							dropItem(Items.cooked_fish, 1);
						}
					}
					else {
						if (isServer()) {
							dropItem(Items.fish, 1);
						}
					}
				}
				isWet = false;
				isShaking = false;
				prevTimeWolfIsShaking = 0.0F;
				timeWolfIsShaking = 0.0F;
			}

			if (timeWolfIsShaking > 0.4F) {
				float f = (float) getEntityBoundingBox().minY;
				int i = (int) (MathHelper.sin((timeWolfIsShaking - 0.4F) * (float) Math.PI) * 7.0F);

				for (int j = 0; j < i; ++j) {
					float f1 = (rand.nextFloat() * 2.0F - 1.0F) * width * 0.5F;
					float f2 = (rand.nextFloat() * 2.0F - 1.0F) * width * 0.5F;
					worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, posX + f1, f + 0.8F, posZ + f2, motionX, motionY, motionZ, new int[0]);
				}
			}
		}

		if (rand.nextInt(200) == 0 && hasEvolved()) {
			hiyaMaster = true;
		}

		if ((isBegging() || hiyaMaster) && !isWolfHappy && hasEvolved()) {
			isWolfHappy = true;
			timeWolfIsHappy = 0.0F;
			prevTimeWolfIsHappy = 0.0F;
		}
		else {
			hiyaMaster = false;
		}
		if (isWolfHappy) {
			if (timeWolfIsHappy % 1.0F == 0.0F) {
				if (!(this instanceof EntityMetalZertum)) {
					openMouth();
					worldObj.playSoundAtEntity(this, "mob.wolf.panting", getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
				}
				else if (this instanceof EntityMetalZertum) {
					openMouth();
					worldObj.playSoundAtEntity(this, Sound.MetalZertumPant, getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
				}
			}
			prevTimeWolfIsHappy = timeWolfIsHappy;
			timeWolfIsHappy += 0.05F;
			if (prevTimeWolfIsHappy >= 8.0F) {
				isWolfHappy = false;
				prevTimeWolfIsHappy = 0.0F;
				timeWolfIsHappy = 0.0F;
			}
		}

		if (isTamed()) {
			EntityPlayer player = (EntityPlayer) getOwner();

			if (player != null) {
				float distanceToOwner = player.getDistanceToEntity(this);

				if (distanceToOwner <= 2F && hasToy()) {
					if (isServer()) {
						entityDropItem(new ItemStack(ModItems.toy, 1, 1), 0.0F);
					}
					setHasToy(false);
				}
			}
		}

		TalentHelper.onUpdate(this);
	}

	public float getWagAngle(float f, float f1) {
		float f2 = (prevTimeWolfIsHappy + (timeWolfIsHappy - prevTimeWolfIsHappy) * f + f1) / 2.0F;
		if (f2 < 0.0F) {
			f2 = 0.0F;
		}
		else if (f2 > 2.0F) {
			f2 %= 2.0F;
		}
		return MathHelper.sin(f2 * (float) Math.PI * 5.0F) * 0.3F * (float) Math.PI;
	}

	@Override
	public void moveEntityWithHeading(float strafe, float forward) {
		if (riddenByEntity instanceof EntityPlayer) {
			prevRotationYaw = rotationYaw = riddenByEntity.rotationYaw;
			rotationPitch = riddenByEntity.rotationPitch * 0.5F;
			setRotation(rotationYaw, rotationPitch);
			rotationYawHead = renderYawOffset = rotationYaw;
			strafe = ((EntityPlayer) riddenByEntity).moveStrafing * 0.5F;
			forward = ((EntityPlayer) riddenByEntity).moveForward;

			if (forward <= 0.0F) {
				forward *= 0.25F;
			}

			if (onGround) {
				if (forward > 0.0F) {
					float f2 = MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F);
					float f3 = MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F);
					motionX += -0.4F * f2 * 0.15F; // May change
					motionZ += 0.4F * f3 * 0.15F;
				}
			}

			stepHeight = 1.0F;
			jumpMovementFactor = getAIMoveSpeed() * 0.2F;

			if (isServer()) {
				setAIMoveSpeed((float) getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() / 4);
				super.moveEntityWithHeading(strafe, forward);
			}

			if (onGround) {
				// this.jumpPower = 0.0F;
				// this.setHorseJumping(false);
			}

			prevLimbSwingAmount = limbSwingAmount;
			double d0 = posX - prevPosX;
			double d1 = posZ - prevPosZ;
			float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

			if (f4 > 1.0F) {
				f4 = 1.0F;
			}

			limbSwingAmount += (f4 - limbSwingAmount) * 0.4F;
			limbSwing += limbSwingAmount;
		}
		else {
			stepHeight = 0.5F;
			jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(strafe, forward);
		}
	}

	@Override
	public float getAIMoveSpeed() {
		double speed = getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
		speed += TalentHelper.addToMoveSpeed(this);

		if (!(getAttackTarget() instanceof EntityZertumEntity) && !(getAttackTarget() instanceof EntityPlayer) || riddenByEntity instanceof EntityPlayer) {
			if (levels.getLevel() == Constants.stage2Level && hasEvolved()) {
				speed += 0.3D;
			}
			else if (hasEvolved() && levels.getLevel() != Constants.stage2Level) {
				speed += 0.3D;
			}
		}

		if (riddenByEntity instanceof EntityPlayer) {
			speed /= 4;
		}

		return (float) speed;
	}

	public float getAIAttackDamage() {
		double damage = getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
		damage += TalentHelper.addToAttackDamage(this);

		if (!(getAttackTarget() instanceof EntityZertumEntity) && !(getAttackTarget() instanceof EntityPlayer)) {
			if (levels.getLevel() == Constants.stage2Level && hasEvolved() && !inFinalStage()) {
				damage += 1.0D;
			}
			else if (levels.getLevel() == Constants.maxLevel && hasEvolved() && inFinalStage()) {
				damage += 3.0D;
			}
		}
		return (float) damage;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
		if (distance > 1.0F) {
			if (!inFinalStage()) {
				playSound("game.neutral.hurt.fall.small", 0.4F, 1.0F);
			}
			else {
				playSound(Sound.Land, 0.4F, 1.0F);
			}
		}

		int i = MathHelper.ceiling_float_int((distance * 0.5F - 3.0F - TalentHelper.fallProtection(this)) * damageMultiplier);

		if (i > 0 && !TalentHelper.isImmuneToFalls(this) && !inFinalStage()) {
			attackEntityFrom(DamageSource.fall, i);

			if (riddenByEntity != null) {
				riddenByEntity.attackEntityFrom(DamageSource.fall, i);
			}

			Block block = worldObj.getBlockState(new BlockPos(posX, posY - 0.2D - prevRotationYaw, posZ)).getBlock();

			if (block.getMaterial() != Material.air && !isSilent()) {
				Block.SoundType soundtype = block.stepSound;
				worldObj.playSoundAtEntity(this, soundtype.getStepSound(), soundtype.getVolume() * 0.5F, soundtype.getFrequency() * 0.75F);
			}
		}
		else if (i > 3 && !TalentHelper.isImmuneToFalls(this) && inFinalStage()) {
			attackEntityFrom(DamageSource.fall, i);

			if (riddenByEntity != null) {
				riddenByEntity.attackEntityFrom(DamageSource.fall, i);
			}

			Block block = worldObj.getBlockState(new BlockPos(posX, posY - 0.2D - prevRotationYaw, posZ)).getBlock();

			if (block.getMaterial() != Material.air && !isSilent()) {
				Block.SoundType soundtype = block.stepSound;
				worldObj.playSoundAtEntity(this, soundtype.getStepSound(), soundtype.getVolume() * 0.5F, soundtype.getFrequency() * 0.75F);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean isWolfWet() {
		return isWet;
	}

	@SideOnly(Side.CLIENT)
	public float getShadingWhileWet(float p_70915_1_) {
		return 0.75F + (prevTimeWolfIsShaking + (timeWolfIsShaking - prevTimeWolfIsShaking) * p_70915_1_) / 2.0F * 0.25F;
	}

	@SideOnly(Side.CLIENT)
	public float getShakeAngle(float p_70923_1_, float p_70923_2_) {
		float f2 = (prevTimeWolfIsShaking + (timeWolfIsShaking - prevTimeWolfIsShaking) * p_70923_1_ + p_70923_2_) / 1.8F;

		if (f2 < 0.0F) {
			f2 = 0.0F;
		}
		else if (f2 > 1.0F) {
			f2 = 1.0F;
		}

		return MathHelper.sin(f2 * (float) Math.PI) * MathHelper.sin(f2 * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
	}

	@SideOnly(Side.CLIENT)
	public float getInterestedAngle(float partialTickTime) {
		return (prevTimeDogBegging + (timeDogBegging - prevTimeDogBegging) * partialTickTime) * 0.15F * (float) Math.PI;
	}

	@Override
	public float getEyeHeight() { // NAV
		return height;
	}

	@Override
	public int getVerticalFaceSpeed() {
		return isSitting() ? 20 : super.getVerticalFaceSpeed();
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damage) {
		if (isEntityInvulnerable(damageSource)) {
			return false;
		}
		else {
			if (!TalentHelper.attackEntityFrom(this, damageSource, damage)) {
				return false;
			}

			Entity entity = damageSource.getEntity();
			aiSit.setSitting(false);

			if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
				damage = (damage + 1.0F) / 2.0F;
			}

			return super.attackEntityFrom(damageSource, damage);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (!TalentHelper.shouldDamageMob(this, entity)) {
			return false;
		}

		int damage = (int) (4 + getEntityAttribute(SharedMonsterAttributes.attackDamage).getBaseValue() / 2);
		damage = TalentHelper.attackEntityAsMob(this, entity, damage);

		if (entity instanceof EntityZombie) {
			((EntityZombie) entity).setAttackTarget(this);
		}

		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
	}

	@Override
	protected boolean isMovementBlocked() {
		return isPlayerSleeping() || ridingEntity != null || riddenByEntity instanceof EntityPlayer || super.isMovementBlocked();
	}

	@Override
	public double getYOffset() {
		return ridingEntity instanceof EntityPlayer ? 0.5D : 0.0D;
	}

	@Override
	public boolean isPotionApplicable(PotionEffect potionEffect) {
		if (getHealth() <= Constants.lowHP) {
			return false;
		}

		if (!TalentHelper.isPostionApplicable(this, potionEffect)) {
			return false;
		}

		return true;
	}

	@Override
	public void setFire(int amount) {
		if (TalentHelper.setFire(this, amount)) {
			super.setFire(amount);
		}
	}

	public int foodValue(ItemStack stack) {
		if (stack == null || stack.getItem() == null) {
			return 0;
		}

		int foodValue = 0;

		Item item = stack.getItem();

		if (stack.getItem() != Items.rotten_flesh && item instanceof ItemFood) {
			ItemFood itemfood = (ItemFood) item;

			if (itemfood.isWolfsFavoriteMeat()) {
				foodValue = 40;
			}
		}

		foodValue = TalentHelper.changeFoodValue(this, stack, foodValue);

		return foodValue;
	}

	public int masterOrder() { // NAV: Master Order
		int order = 0;
		EntityPlayer player = (EntityPlayer) getOwner();

		if (player != null) {

			float distanceAway = player.getDistanceToEntity(this);
			ItemStack itemstack = player.inventory.getCurrentItem();

			if (itemstack != null && itemstack.getItem() instanceof ItemTool && distanceAway <= 20F) {
				order = 1;
			}

			if (itemstack != null && (itemstack.getItem() instanceof ItemSword || itemstack.getItem() instanceof ItemBow)) {
				order = 2;
			}

			if (itemstack != null && itemstack.getItem() == Items.wheat) {
				order = 3;
			}

			if (itemstack != null && itemstack.getItem() == Items.bone) {
				order = 4;
			}
		}

		return order;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return TalentHelper.canBreatheUnderwater(this);
	}

	@Override
	public boolean canInteract(EntityPlayer player) {
		return isOwner(player) || willObeyOthers();
	}

	public int nourishment() {
		int amount = 0;

		if (getZertumHunger() > 0) {
			amount = 40 + 4 * (effectiveLevel() + 1);

			if (isSitting() && talents.getLevel("rapidregen") == 5) {
				amount += 20 + 2 * (effectiveLevel() + 1);
			}

			if (!isSitting()) {
				amount *= 5 + talents.getLevel("rapidregen");
				amount /= 10;
			}
		}

		return amount;
	}

	public int effectiveLevel() {
		return levels.getLevel() / 10;
	}

	public String getPetName() {
		return dataWatcher.getWatchableObjectString(DataValues.dogName);
	}

	public void setPetName(String var1) {
		dataWatcher.updateObject(DataValues.dogName, var1);
	}

	public void setWillObeyOthers(boolean flag) {
		dataWatcher.updateObject(DataValues.obeyOthers, flag ? 1 : 0);
	}

	public boolean willObeyOthers() {
		return dataWatcher.getWatchableObjectInt(DataValues.obeyOthers) != 0;
	}

	public int points() {
		return levels.getLevel() + (getGrowingAge() < 0 ? 0 : Constants.startingPoints);
	}

	public int spendablePoints() {
		return points() - usedPoints();
	}

	public int usedPoints() {
		return TalentHelper.getUsedPoints(this);
	}

	public int getZertumHunger() {
		return dataWatcher.getWatchableObjectInt(DataValues.hungerTicks);
	}

	public void setZertumHunger(int par1) {
		dataWatcher.updateObject(DataValues.hungerTicks, MathHelper.clamp_int(par1, 0, Constants.hungerTicks));
	}

	@Override
	public boolean func_142018_a(EntityLivingBase entityToAttack, EntityLivingBase owner) {
		if (TalentHelper.canAttackEntity(this, entityToAttack)) {
			return true;
		}

		if (!(entityToAttack instanceof EntityCreeper) && !(entityToAttack instanceof EntityGhast)) {
			if (entityToAttack instanceof EntityZertumEntity) {
				EntityZertumEntity entityZertum = (EntityZertumEntity) entityToAttack;

				if (entityZertum.isTamed() && entityZertum.getOwner() == owner) {
					return false;
				}
			}

			return entityToAttack instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer) owner).canAttackPlayer((EntityPlayer) entityToAttack)
					? false
					: !(entityToAttack instanceof EntityHorse) || !((EntityHorse) entityToAttack).isTame();
		}
		else {
			return false;
		}
	}

	@Override
	public boolean canAttackClass(Class p_70686_1_) {
		if (TalentHelper.canAttackClass(this, p_70686_1_)) {
			return true;
		}

		return super.canAttackClass(p_70686_1_);
	}

	public void setHasToy(boolean hasToy) {
		this.hasToy = hasToy;
	}

	public boolean hasToy() {
		return hasToy;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte p_70103_1_) {
		if (p_70103_1_ == 8) {
			isShaking = true;
			timeWolfIsShaking = 0.0F;
			prevTimeWolfIsShaking = 0.0F;
		}
		else {
			super.handleHealthUpdate(p_70103_1_);
		}
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed
	 * it (wheat, carrots or seeds depending on the animal type)
	 */
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack != null && ZeroQuestAPI.breedList.containsItem(stack);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 8;
	}

	public EnumDyeColor getCollarColor() {
		return EnumDyeColor.byDyeDamage(dataWatcher.getWatchableObjectByte(DataValues.collarCollar) & 15);
	}

	public void setCollarColor(EnumDyeColor collarcolor) {
		dataWatcher.updateObject(DataValues.collarCollar, Byte.valueOf((byte) (collarcolor.getDyeDamage() & 15)));
	}

	private boolean getHorseWatchableBoolean(int p_110233_1_) {
		return (dataWatcher.getWatchableObjectInt(DataValues.mouth) & p_110233_1_) != 0;
	}

	private void setHorseWatchableBoolean(int p_110208_1_, boolean p_110208_2_) {
		int j = dataWatcher.getWatchableObjectInt(DataValues.mouth);

		if (p_110208_2_) {
			dataWatcher.updateObject(DataValues.mouth, Integer.valueOf(j | p_110208_1_));
		}
		else {
			dataWatcher.updateObject(DataValues.mouth, Integer.valueOf(j & ~p_110208_1_));
		}
	}

	@SideOnly(Side.CLIENT)
	public float getMouthOpennessAngle(float p_110201_1_) {
		return prevMouthOpenness + (mouthOpenness - prevMouthOpenness) * p_110201_1_;
	}

	public void openMouth() {
		if (isServer()) {
			openMouthCounter = 1;
			setHorseWatchableBoolean(128, true);
		}
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	@Override
	protected boolean canDespawn() {
		return !isTamed() && ticksExisted > 2400;
	}

	@Override
	public boolean allowLeashing() {
		return !isAngry() && super.allowLeashing();
	}

	public void setBegging(boolean flag) {
		dataWatcher.updateObject(DataValues.beg, Byte.valueOf((byte) (flag ? 1 : 0)));
	}

	public boolean isBegging() {
		return dataWatcher.getWatchableObjectByte(DataValues.beg) == 1;
	}

	public boolean hasEvolved() {
		return (dataWatcher.getWatchableObjectByte(DataValues.evolve) & 4) != 0;
	}

	public void setEvolved(boolean evolved) {
		byte b0 = dataWatcher.getWatchableObjectByte(DataValues.evolve);

		if (evolved) {
			dataWatcher.updateObject(DataValues.evolve, Byte.valueOf((byte) (b0 | 4)));
		}
		else {
			dataWatcher.updateObject(DataValues.evolve, Byte.valueOf((byte) (b0 & -5)));
		}
		updateEntityAttributes();
	}

	public boolean inFinalStage() {
		return (dataWatcher.getWatchableObjectByte(DataValues.evolve) & 2) != 0;
	}

	public void setFinalStage(boolean finalStage) {
		byte b0 = dataWatcher.getWatchableObjectByte(DataValues.evolve);

		if (finalStage) {
			dataWatcher.updateObject(DataValues.evolve, Byte.valueOf((byte) (b0 | 2)));
		}
		else {
			dataWatcher.updateObject(DataValues.evolve, Byte.valueOf((byte) (b0 & -3)));
		}
		updateEntityAttributes();
	}

	public String getOwnerName() {
		return dataWatcher.getWatchableObjectString(DataValues.ownerName);
	}

	public void saveOwnerName(String name) {
		dataWatcher.updateObject(DataValues.ownerName, name);
	}

	public String getOwnerID() {
		return dataWatcher.getWatchableObjectString(DataValues.ownerID);
	}

	public void saveOwnerID(String id) {
		dataWatcher.updateObject(DataValues.ownerID, id);
	}

	/** Custom Zertum Taming Code */
	@Override
	public void tamedFor(EntityPlayer player, boolean successful) {
		if (successful) {
			setTamed(true);
			updateEntityAttributes();
			navigator.clearPathEntity();
			setAttackTarget((EntityLivingBase) null);
			aiSit.setSitting(false);
			setOwnerId(player.getUniqueID().toString());
			playTameEffect(true);
			worldObj.setEntityState(this, (byte) 7);
			saveOwnerName(player.getDisplayNameString());
			saveOwnerID(player.getUniqueID().toString());
			if (!(this instanceof EntityDarkZertum)) {
				player.triggerAchievement(ModAchievements.zertumTame);
			}
			else if (this instanceof EntityDarkZertum) {
				player.triggerAchievement(ModAchievements.darkZertumTame);
			}
			//@formatter:off
			//Debuging Owner name and ID
			//System.out.println("ID: " + zertum.getOwnerID() + ", Name: " + zertum.getOwnerName());
			//@formatter:on
		}
		else {
			playTameEffect(false);
			worldObj.setEntityState(this, (byte) 6);
		}
	}

	public void unTame() {
		setTamed(false);
		setEvolved(false);
		setFinalStage(false);
		navigator.clearPathEntity();
		setSitting(false);
		talents.resetTalents();
		levels.resetLevel();
		setOwnerId("");
		saveOwnerName("");
		saveOwnerID("");
		setPetName("");
		setWillObeyOthers(false);
		mode.setMode(EnumMode.DOCILE);
		updateEntityAttributes();
	}

	public void evolveOnClient(EntityPlayer player) {
		setEvolved(true);
		worldObj.playBroadcastSound(1013, new BlockPos(this), 0);
		updateEntityAttributes();
		setHealth(getMaxHealth());
		player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + getPetName() + " has been evolved!"));
	}

	public void evolveOnServer(EntityZertumEntity entity, EntityPlayer player) {
		entity.setEvolved(true);
		entity.worldObj.playBroadcastSound(1013, new BlockPos(entity), 0);
		updateEntityAttributes();
		entity.setHealth(entity.getMaxHealth());
		player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + entity.getPetName() + " has been evolved!"));
	}

	public void finaEvolveOnClient(EntityPlayer player) {
		setFinalStage(true);
		worldObj.playBroadcastSound(1013, new BlockPos(this), 0);
		updateEntityAttributes();
		setHealth(getMaxHealth());
		player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + getPetName() + " has been evolved to the final stage!"));
	}

	public void finaEvolveOnServer(EntityZertumEntity entity, EntityPlayer player) {
		entity.setFinalStage(true);
		entity.worldObj.playBroadcastSound(1013, new BlockPos(entity), 0);
		updateEntityAttributes();
		entity.setHealth(entity.getMaxHealth());
		player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + entity.getPetName() + " has been evolved to the final stage!"));
	}

	public void devolveOnServer(EntityZertumEntity entity, EntityPlayer player) {
		if (entity.hasEvolved() && !entity.inFinalStage()) {
			entity.setEvolved(false);
		}
		else if (entity.hasEvolved() && entity.inFinalStage()) {
			entity.setFinalStage(false);
		}
		entity.worldObj.playBroadcastSound(1013, new BlockPos(entity), 0);
		updateEntityAttributes();
		entity.setHealth(entity.getMaxHealth());
		player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.DARK_RED + entity.getPetName() + " has been devolved!"));
	}

	public String genderPronoun() {
		if (getGender() == true) {
			return "him";
		}
		else {
			return "her";
		}
	}

	public String genderSubject() {
		if (getGender() == true) {
			return "he";
		}
		else {
			return "she";
		}
	}

	public void doNotOwnMessage(EntityZertumEntity zertum, EntityPlayer player) {
		player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + "You do not own " + zertum.getPetName() + " and " + zertum.getOwnerName() + " doesn't allow " + zertum.genderPronoun() + EnumChatFormatting.RED + " to" + EnumChatFormatting.RED + " obey" + EnumChatFormatting.RED + "non-owners!"));
	}
}