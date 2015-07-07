package net.novaviper.zeroquest.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.novaviper.zeroquest.common.api.interfaces.IBits;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.helper.ChatHelper;

/**
 * @author ProPercivalalb
 **/
public class ItemBit extends ItemZQ implements IBits {

	private final int maxLevel;
	private final int minLevel;

	public ItemBit(int minLevel, int maxLevel) {
		super();
		this.minLevel = minLevel;
		this.maxLevel = maxLevel;
	}

	@Override
	public EnumFeedBack canGiveToDog(EntityPlayer player, EntityZertumEntity dog, int level) {
		if (level >= this.minLevel && level < this.maxLevel && dog.getGrowingAge() >= 0) {
			return EnumFeedBack.JUSTRIGHT;
		}
		else if (level < this.minLevel) {
			return EnumFeedBack.LEVELTOOLOW;
		}

		else if (dog.getGrowingAge() < 0) {
			return EnumFeedBack.TOOYOUNG;
		}
		else {
			return EnumFeedBack.LEVELTOOHIGH;
		}
	}

	@Override
	public void giveTreat(EnumFeedBack type, EntityPlayer player, EntityZertumEntity dog) {
		ItemStack stack = player.getCurrentEquippedItem();

		if (type == EnumFeedBack.JUSTRIGHT) {
			if (!player.capabilities.isCreativeMode && --stack.stackSize <= 0) {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
			}

			dog.levels.increaseLevel();
			dog.setHealth(dog.getMaxHealth());
			dog.getSitAI().setSitting(true);
			dog.worldObj.setEntityState(dog, (byte) 7);
			dog.playTameEffect(true);
			if (isServer(player)) {
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + dog.getPetName() + " has leveled up to " + dog.levels.getLevel() + "!"));
			}
		}
		else if (type == EnumFeedBack.TOOYOUNG) {
			if (isServer(player)) {
				dog.playTameEffect(false);
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + dog.getPetName() + " is too young to be learning skills!"));
			}
		}
		else if (type == EnumFeedBack.LEVELTOOLOW) {
			player.worldObj.setEntityState(dog, (byte) 6);
			if (isServer(player)) {
				dog.playTameEffect(false);
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + dog.getPetName() + " can't possibly handle the power in these bits!"));
			}
		}
		else if (type == EnumFeedBack.LEVELTOOHIGH) {
			player.worldObj.setEntityState(dog, (byte) 6);
			if (isServer(player)) {
				dog.playTameEffect(false);
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + dog.getPetName() + " cannot gain anything from these bits anymore!"));
			}
		}
	}

}
