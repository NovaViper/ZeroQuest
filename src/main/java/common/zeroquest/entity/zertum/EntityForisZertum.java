package common.zeroquest.entity.zertum;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
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
import common.zeroquest.lib.Constants;
import common.zeroquest.lib.Sound;
import common.zeroquest.util.ItemUtils;

public class EntityForisZertum extends EntityZertumEntity {

	private static final Block footprint = Blocks.grass;

	public EntityForisZertum(World worldIn) {
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
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (worldObj.isRemote) {
			ClientProxy.spawnForisParticle(this);
		}

		for (int l = 0; l <= 4; ++l) // TODO
		{
			if (Constants.DEF_GRASSSTEP == true) {
				int x = MathHelper.floor_double(this.posX + (l % 2 * 2 - 1) * 0.25F);
				int y = MathHelper.floor_double(this.posY) - 1;
				int z = MathHelper.floor_double(this.posZ + (l / 2 % 2 * 2 - 1) * 0.25F);
				BlockPos pos = new BlockPos(x, y, z);
				IBlockState state = this.worldObj.getBlockState(pos);
				boolean isAnyDirt = state.getBlock() == Blocks.dirt;
				boolean isRegularDirt = isAnyDirt && state.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT;

				if (isAnyDirt && isRegularDirt) {
					this.worldObj.setBlockState(pos, footprint.getDefaultState());
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
	public EntityForisZertum createChild(EntityAgeable p_90011_1_) {
		EntityForisZertum entitywolf = new EntityForisZertum(this.worldObj);
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
		else if (!(p_70878_1_ instanceof EntityForisZertum)) {
			return false;
		}
		else {
			EntityForisZertum entitywolf = (EntityForisZertum) p_70878_1_;
			return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false
					: this.isInLove() && entitywolf.isInLove());
		}
	}
}