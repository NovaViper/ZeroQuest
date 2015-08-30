package net.novaviper.zeroquest.common.entity;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.novaviper.zeroquest.client.particle.EntityDustFX;
import net.novaviper.zeroquest.client.particle.EntityHeartFX;
import net.novaviper.zeroquest.common.container.InventoryPack;
import net.novaviper.zeroquest.common.lib.DataValues;

//For NBT Types, look in NBTBase.createNewByType()\\
public abstract class EntityCustomTameable extends EntityTameable {
	/** Boolean for Non-Zertums **/
	protected boolean canSeeCreeper;
	/** Boolean for Howling **/
	protected boolean wantToHowl;
	public int rare;
	/** Cooldown for Ranged Attack **/
	public int cooldown;
	public InventoryPack inventory;

	public EntityCustomTameable(World world) {
		super(world);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(DataValues.gender, new Byte((byte) 0)); // Gender
		this.dataWatcher.addObject(DataValues.saddle, Byte.valueOf((byte) 0)); // Saddle

		boolean isGenderSet = false;

		if (isGenderSet == false) {
			if (rand.nextInt(2) == 0) {
				this.setGender(true);

			}
			else {
				this.setGender(false);
			}
			isGenderSet = true;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
		tagCompound.setBoolean("angry", this.isAngry());
		tagCompound.setBoolean("saddle", this.isSaddled());
		tagCompound.setBoolean("gender", this.getGender());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound) {
		super.readEntityFromNBT(tagCompound);
		this.setAngry(tagCompound.getBoolean("angry"));
		this.setSaddled(tagCompound.getBoolean("saddle"));

		if (tagCompound.hasKey("gender", 1)) {
			this.setGender(tagCompound.getBoolean("gender"));
		}
	}

	/* =======================UNIVERSAL======================= */

	/** Gets the gender of an entity. true = male, false = female */
	public boolean getGender() {
		return (this.dataWatcher.getWatchableObjectByte(DataValues.gender) & 1) != 0;
	}

	/** Sets the gender of an entity. true = male, false = female */
	public void setGender(boolean gender) {
		if (gender) {
			this.dataWatcher.updateObject(DataValues.gender, Byte.valueOf((byte) 1)); // Male
		}
		else {
			this.dataWatcher.updateObject(DataValues.gender, Byte.valueOf((byte) 0)); // Female
		}
	}

	public boolean isAngry() {
		return (this.dataWatcher.getWatchableObjectByte(DataValues.tame) & 2) != 0;
	}

	public void setAngry(boolean p_70916_1_) {
		byte b0 = this.dataWatcher.getWatchableObjectByte(DataValues.tame);

		if (p_70916_1_) {
			this.dataWatcher.updateObject(DataValues.tame, Byte.valueOf((byte) (b0 | 2)));
		}
		else {
			this.dataWatcher.updateObject(DataValues.tame, Byte.valueOf((byte) (b0 & -3)));
		}
	}

	public boolean isSaddled() {
		return (this.dataWatcher.getWatchableObjectByte(DataValues.saddle) & 1) != 0;
	}

	public void setSaddled(boolean p_70900_1_) {
		if (p_70900_1_) {
			this.dataWatcher.updateObject(DataValues.saddle, Byte.valueOf((byte) 1));
		}
		else {
			this.dataWatcher.updateObject(DataValues.saddle, Byte.valueOf((byte) 0));
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
				if (isServer()) {
					this.dropChestItems();
				}
			}
		}
	}

	/**
	 * Play the taming effect, will either be hearts or smoke depending on
	 * status
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void playTameEffect(boolean p_70908_1_) {
		double d0 = this.rand.nextGaussian() * 0.02D;
		double d1 = this.rand.nextGaussian() * 0.02D;
		double d2 = this.rand.nextGaussian() * 0.02D;
		if (isClient()) {
			EntityFX entityFX = new EntityHeartFX(this.worldObj, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);

			if (!p_70908_1_) {
				entityFX = new EntityDustFX(this.worldObj, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
			}

			for (int i = 0; i < 7; ++i) {
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(entityFX);
			}

		}
	}

	/**
	 * Returns the entity's health relative to the maximum health.
	 *
	 * @return health normalized between 0 and 1
	 */
	public double getHealthRelative() {
		return getHealth() / (double) getMaxHealth();
	}

	public void tamedFor(EntityPlayer player, boolean successful) { // NAV: For
		// tame
		// command
		// and
		// taming
		if (successful) {
			this.setTamed(true);
			this.navigator.clearPathEntity();
			this.setAttackTarget((EntityLivingBase) null);
			this.aiSit.setSitting(false);
			this.setOwnerId(player.getUniqueID().toString());
			this.playTameEffect(true);
			this.worldObj.setEntityState(this, (byte) 7);
		}
		else {
			this.playTameEffect(false);
			this.worldObj.setEntityState(this, (byte) 6);
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
	}

	/**
	 * Checks if this entity is running on a client.
	 *
	 * Required since MCP's isClientWorld returns the exact opposite...
	 *
	 * @return true if the entity runs on a client or false if it runs on a
	 *         server
	 */
	public boolean isClient() {
		return worldObj.isRemote;
	}

	/**
	 * Checks if this entity is running on a server.
	 *
	 * @return true if the entity runs on a server or false if it runs on a
	 *         client
	 */
	public boolean isServer() {
		return !worldObj.isRemote;
	}

	public double sniffRange() {
		double d = 0.0D;
		for (int i = 0; i < 15 * 6; i++) {
			d++;
		}
		return d;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getPitch() {
		if (!this.isChild()) {
			return super.getSoundPitch() * -2;
		}
		else {
			return super.getSoundPitch() * 2;
		}
	}

	public boolean canInteract(EntityPlayer player) {
		return this.isOwner(player);
	}

	public void dropChestItems() {
		this.dropItemsInChest(this, this.inventory);
	}

	private void dropItemsInChest(Entity par1Entity, InventoryPack inventory2) {
		if (inventory2 != null && isServer()) {
			for (int i = 0; i < inventory2.getSizeInventory(); ++i) {
				ItemStack itemstack = inventory2.getStackInSlot(i);

				if (itemstack != null) {
					this.entityDropItem(itemstack, 0.0F);
					this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((this.getRNG().nextFloat() - this.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
				}
			}
		}
	}

	public boolean hasItemsinChest() {
		if (this.inventory != null && isServer()) {
			for (int i = 0; i < inventory.getSizeInventory(); ++i) {
				ItemStack itemstack = inventory.getStackInSlot(i);

				if (itemstack != null) {
					return true;
				}
			}
		}
		return false;
	}

	public EntityAISit getSitAI() {
		return this.aiSit;
	}

	/* =======================FOR ZERTUMS ONLY======================= */
}