package net.novaviper.zeroquest.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.novaviper.zeroquest.ModAchievements;
import net.novaviper.zeroquest.common.api.interfaces.IBits;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.helper.ChatHelper;
import net.novaviper.zeroquest.common.lib.Constants;

/**
 * @author ProPercivalalb
 **/
public class ItemAlphaBit extends ItemZQ implements IBits {

	public ItemAlphaBit() {
		super();
	}

	@Override
	public EnumFeedBack canGiveToDog(EntityPlayer player, EntityZertumEntity dog, int level) {
		if (level >= 70 && level < Constants.stage2Level && dog.getGrowingAge() >= 0) {
			return EnumFeedBack.JUSTRIGHT;
		}
		else if (dog.getGrowingAge() < 0) {
			return EnumFeedBack.TOOYOUNG;
		}
		else if (level > Constants.stage2Level) {
			return EnumFeedBack.LEVELTOOHIGH;
		}
		else if (level == Constants.stage2Level && !dog.hasEvolved()) {
			return EnumFeedBack.EVOREADY;
		}
		else {
			return EnumFeedBack.LEVELTOOLOW;
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
			dog.getNavigator().clearPathEntity();
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
		else if (type == EnumFeedBack.EVOREADY) {
			player.worldObj.setEntityState(dog, (byte) 6);
			if (isServer(player)) {
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "CONGRATZ! " + dog.getPetName() + " has reached the " + EnumChatFormatting.GREEN + "Alpha Level! Evolution is ready!"));
				player.triggerAchievement(ModAchievements.alphaLevelUp);
			}
		}
	}

}
