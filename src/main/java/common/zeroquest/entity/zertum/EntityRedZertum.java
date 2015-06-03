package common.zeroquest.entity.zertum;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
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
import common.zeroquest.entity.EntityCustomTameable;
import common.zeroquest.entity.zertum.util.TalentHelper;
import common.zeroquest.entity.zertum.util.ModeUtil.EnumMode;
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Sound;
import common.zeroquest.util.ItemUtils;

public class EntityRedZertum extends EntityZertumEntity {

	public EntityRedZertum(World worldIn) {
		super(worldIn);
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
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		double a = 0; // Front and Back //TODO
		double b = 1; // Up and Down
		double c = 20; // Not sure about this
		double x = posX + Math.sin((-renderYawOffset + c) / 360 * Math.PI * 2) * a;
		double y = posY + b;
		double z = posZ + Math.cos((-renderYawOffset + c) / 360 * Math.PI * 2) * a;
		if (this.talents.getLevel("flamingelemental") != 5) {
			worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0, 0.0, 0.0);
			worldObj.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0.0, 0.0, 0.0);
		}
		else {
			worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x, y, z, 0.0, 0.0, 0.0);
			worldObj.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0.0, 0.0, 0.0);
			worldObj.spawnParticle(EnumParticleTypes.LAVA, x, y, z, 0.0, 0.0, 0.0);
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
				else if (stack != null && stack.getItem() == ModItems.evoBit && this.levels.getLevel() == Constants.maxLevel && !this.hasEvolved() && isServer() && this.canInteract(player)) { // TODO
					this.evolveOnClient(player);
				}
				else if (stack.getItem() == Items.shears && this.isOwner(player)) {
					if (isServer()) {
						unTame();
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
			}
			return true;
		}
		return super.interact(player);
	}

	/**
	 * This function is used when two same-species animals in 'love mode' breed
	 * to generate the new baby animal.
	 */
	public EntityCustomTameable spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		double chance = Math.random();

		if (chance < 0.5) {
			EntityRedZertum var3 = new EntityRedZertum(this.worldObj);
			var3.setOwnerId(this.getOwnerId());
			var3.setTamed(true);
			return var3;
		}
		else if (chance < 0.7) {
			EntityZertum var4 = new EntityZertum(this.worldObj);
			var4.setOwnerId(this.getOwnerId());
			var4.setTamed(true);
			return var4;
		}
		else {
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
	@Override
	public boolean canMateWith(EntityAnimal otherAnimal) {
		if (otherAnimal == this) {
			return false;
		}
		else if (!this.isTamed()) {
			return false;
		}
		else if (!(otherAnimal instanceof EntityRedZertum)) {
			return false;
		}
		else {
			EntityRedZertum entityRedZertum = (EntityRedZertum) otherAnimal;
			return !entityRedZertum.isTamed() ? false : (entityRedZertum.isSitting() ? false
					: this.getGender() == entityRedZertum.getGender() ? false
							: this.isInLove() && entityRedZertum.isInLove());
		}
	}
}