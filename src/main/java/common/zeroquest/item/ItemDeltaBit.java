package common.zeroquest.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import common.zeroquest.api.interfaces.IBits;
import common.zeroquest.api.interfaces.IBits.EnumFeedBack;
import common.zeroquest.core.helper.ChatHelper;
import common.zeroquest.entity.zertum.EntityZertumEntity;
import common.zeroquest.lib.Constants;

/**
 * @author ProPercivalalb
 **/
public class ItemDeltaBit extends ItemZQ implements IBits {

	public ItemDeltaBit() {
		super();
	}

	@Override
	public EnumFeedBack canGiveToDog(EntityPlayer player, EntityZertumEntity dog, int level) {
		if (level > Constants.stage2Level && level < Constants.stage3Level && dog.getGrowingAge() >= 0) {
			return EnumFeedBack.JUSTRIGHT;
		}
		else if (dog.getGrowingAge() < 0) {
			return EnumFeedBack.TOOYOUNG;
		}
		else if (level > Constants.stage3Level) {
			return EnumFeedBack.LEVELTOOHIGH;
		}
		else if (level < Constants.stage2Level) {
			return EnumFeedBack.LEVELTOOLOW;
		}
		else {
			return EnumFeedBack.COMPLETE;
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
		else if (type == EnumFeedBack.COMPLETE) {
			player.worldObj.setEntityState(dog, (byte) 6);
			if (isServer(player)) {
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "CONGRATZ! " + dog.getPetName() + " has reached the " + EnumChatFormatting.GREEN + "Delta Level! Evolution is ready!"));
			}
		}
	}

}
