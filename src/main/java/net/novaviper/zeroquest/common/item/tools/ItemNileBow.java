package net.novaviper.zeroquest.common.item.tools;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.novaviper.zeroquest.common.lib.Constants;

public class ItemNileBow extends ItemBow {
	public String elementType;

	public ItemNileBow(String type, int damage) {
		elementType = type;
		this.setMaxDamage(damage);
	}

	@Override
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {
		ModelResourceLocation modelresourcelocation = new ModelResourceLocation(Constants.modid + ":" + elementType + "_bow", "inventory");

		// System.out.println(player.getItemInUse() +":" + useRemaining); USE
		// FOR DEBUGGING ONLY

		if (stack.getItem() == this && player.getItemInUse() != null) {
			if (useRemaining >= 71987) {
				modelresourcelocation = new ModelResourceLocation(Constants.modid + ":" + elementType + "_bow_pulling_0", "inventory");
			}
			else if (useRemaining > 71980) {
				modelresourcelocation = new ModelResourceLocation(Constants.modid + ":" + elementType + "_bow_pulling_1", "inventory");
			}
			else if (useRemaining <= 71976 || useRemaining >= 71976) {
				modelresourcelocation = new ModelResourceLocation(Constants.modid + ":" + elementType + "_bow_pulling_2", "inventory");
			}
		}
		return modelresourcelocation;
	}
}