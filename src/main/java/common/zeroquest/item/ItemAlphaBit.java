package common.zeroquest.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import common.zeroquest.api.interfaces.IBits;
import common.zeroquest.core.helper.ChatHelper;
import common.zeroquest.entity.zertum.EntityZertumEntity;
import common.zeroquest.lib.Constants;

/**
 * @author ProPercivalalb
 **/
public class ItemAlphaBit extends Item implements IBits {

	public ItemAlphaBit() {
		super();
	}

	@Override
	public EnumFeedBack canGiveToDog(EntityPlayer player, EntityZertumEntity dog, int level) {
		if (level >= 70 && level < Constants.maxLevel && dog.getGrowingAge() >= 0) {
			return EnumFeedBack.JUSTRIGHT;
		}
		else if (dog.getGrowingAge() < 0) {
			return EnumFeedBack.TOOYOUNG;
		}
		else if (level < 70) {
			return EnumFeedBack.LEVELTOOHIGH;
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
			if (!player.worldObj.isRemote) {
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + dog.getZertumName() + " has leveled up to " + dog.levels.getLevel() + "!"));
			}
		}
		else if (type == EnumFeedBack.TOOYOUNG) {
			if (!player.worldObj.isRemote) {
				dog.playTameEffect(false);
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + dog.getZertumName() + " is too young to be learning skills!"));
			}
		}
		else if (type == EnumFeedBack.LEVELTOOHIGH) {
			player.worldObj.setEntityState(dog, (byte) 6);
			if (!player.worldObj.isRemote) {
				dog.playTameEffect(false);
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.RED + dog.getZertumName() + " can't possibly handle the power in these bits!"));
			}
		}
		else if (type == EnumFeedBack.COMPLETE) {
			player.worldObj.setEntityState(dog, (byte) 6);
			if (!player.worldObj.isRemote) {
				player.addChatMessage(ChatHelper.getChatComponent(EnumChatFormatting.GREEN + "CONGRATZ! " + dog.getZertumName() + " has reached the " + EnumChatFormatting.GREEN + "Alpha Level! Evolution is ready!"));
			}
		}
	}

}
