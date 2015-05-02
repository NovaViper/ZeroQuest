package common.zeroquest.entity.zertum;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
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
import common.zeroquest.entity.ai.targeting.EntityAICustomLeapAtTarget;
import common.zeroquest.entity.ai.targeting.EntityAIIceAttack;
import common.zeroquest.entity.projectile.EntityIceball;
import common.zeroquest.entity.util.ModeUtil.EnumMode;
import common.zeroquest.entity.util.TalentHelper;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Sound;
import common.zeroquest.util.ItemUtils;

public class EntityIceZertum extends EntityZertumEntity implements IRangedAttackMob {
	private static final Block footprint = Blocks.snow_layer;
	private static final Block footprint2 = Blocks.ice;

	public EntityIceZertum(World worldIn) {
		super(worldIn);
		this.tasks.removeTask(this.aiLeap);
		this.tasks.addTask(3, new EntityAICustomLeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIIceAttack(this, 1.0D, 10, 30, 15.0F));
		this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate() {
			private static final String __OBFID = "CL_00002229";

			public boolean func_180094_a(Entity p_180094_1_) {
				return p_180094_1_ instanceof EntitySheep || p_180094_1_ instanceof EntityRabbit;
			}

			@Override
			public boolean apply(Object p_apply_1_) {
				return this.func_180094_a((Entity) p_apply_1_);
			}
		}));
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() // TODO
	{
		if (isServer()) {
			if (this.cooldown > 0) {
				this.cooldown--;
				// System.out.println(this.cooldown);
			}
		}

		super.onLivingUpdate();

		for (int l = 0; l < 4; l++) {

			int x = MathHelper.floor_double(this.posX + (l % 2 * 2 - 1) * 0.25F);
			int y = MathHelper.floor_double(this.posY);
			int z = MathHelper.floor_double(this.posZ + (l / 2 % 2 * 2 - 1) * 0.25F);

			if (Constants.DEF_SNOWSTEP == true) {
				if (this.worldObj.getBiomeGenForCoords(new BlockPos(x, 0, z)).getFloatTemperature(new BlockPos(x, y, z)) > 1.0F) {
					if (this.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial() == Material.air && footprint.canPlaceBlockAt(this.worldObj, new BlockPos(x, y, z))) {
						this.worldObj.setBlockState(new BlockPos(x, y, z), footprint.getDefaultState());
					}
					if (this.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial() == Material.water && footprint2.canPlaceBlockAt(this.worldObj, new BlockPos(x, y, z))) {
						this.worldObj.setBlockState(new BlockPos(x, y - 1, z), footprint2.getDefaultState());
					}
				}
			}
		}

		double d0 = this.rand.nextGaussian() * 0.04D;
		double d1 = this.rand.nextGaussian() * 0.04D;
		double d2 = this.rand.nextGaussian() * 0.04D;
		worldObj.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {// TODO
		if (this.isTamed() && this.talents.getLevel("frigidfrost") >= 0) {
			if (this.getAttackTarget() != null) {
				if (cooldown == 0) {
					EntityIceball entityIceball = new EntityIceball(this.worldObj, this);
					double d0 = p_82196_1_.posX - this.posX;
					double d1 = p_82196_1_.posY + p_82196_1_.getEyeHeight() - 1.100000023841858D - entityIceball.posY;
					double d2 = p_82196_1_.posZ - this.posZ;
					float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 0.2F;
					entityIceball.setThrowableHeading(d0, d1 + f1, d2, 1.6F, 12.0F);
					this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1014, new BlockPos(this), 0);
					this.worldObj.spawnEntityInWorld(entityIceball);
					cooldown = 20;
				}
				else {
					this.tasks.addTask(3, new EntityAICustomLeapAtTarget(this, 0.4F));
					this.attackEntityAsMob(p_82196_1_);
				}
			}
		}
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

				if (foodValue != 0 && this.getDogHunger() < Constants.hungerTicks && this.canInteract(player)) {
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
					this.evolveOnClient(player);
				}
				else if (stack.getItem() == Items.shears && this.isOwner(player)) {
					if (!this.worldObj.isRemote) {
						this.setTamed(false);
						this.setEvolved(false);
						this.navigator.clearPathEntity();
						this.setSitting(false);
						this.talents.resetTalents();
						this.setOwnerId("");
						this.setZertumName("");
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
		else if (ItemUtils.consumeEquipped(player, ModItems.nileBone) && !this.isAngry()) {
			if (isServer()) {
				tamedFor(player, rand.nextInt(3) == 0);
				player.triggerAchievement(ModAchievements.ZertTame);
			}
			return true;
		}
		return super.interact(player);
	}

	@Override
	public EntityIceZertum createChild(EntityAgeable p_90011_1_) {
		EntityIceZertum entitywolf = new EntityIceZertum(this.worldObj);
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
		else if (!(p_70878_1_ instanceof EntityIceZertum)) {
			return false;
		}
		else {
			EntityIceZertum entitywolf = (EntityIceZertum) p_70878_1_;
			return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false
					: this.isInLove() && entitywolf.isInLove());
		}
	}
}