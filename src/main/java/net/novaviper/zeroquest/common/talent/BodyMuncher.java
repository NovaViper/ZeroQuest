package net.novaviper.zeroquest.common.talent;

import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.novaviper.zeroquest.ModItems;
import net.novaviper.zeroquest.common.api.interfaces.ITalent;
import net.novaviper.zeroquest.common.entity.EntityZertumEntity;
import net.novaviper.zeroquest.common.entity.creature.EntityDarkZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityForisZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityMetalZertum;

/**
 * @author ProPercivalalb
 */
public class BodyMuncher extends ITalent {

	@Override
	public int changeFoodValue(EntityZertumEntity dog, ItemStack stack, int foodValue) {
		int level = dog.talents.getLevel(this);
		if (foodValue == 0) {
			if ((stack.getItem() == Items.fish || stack.getItem() == Items.cooked_fish) && level == 5) {
				foodValue = 30 + 3 * level;
			}

			if (stack.getItem() == Items.rotten_flesh && level >= 3) {
				foodValue = 30 + 3 * level;
			}

			if (dog instanceof EntityForisZertum) {
				if ((stack.getItem() == Items.reeds || stack.getItem() == Items.baked_potato || stack.getItem() == Items.potato || stack.getItem() == Items.wheat || stack.getItem() == Items.wheat_seeds || stack.getItem() == Items.carrot || stack.getItem() == Items.melon || stack.getItem() == Items.melon_seeds || stack.getItem() == Items.pumpkin_pie || stack.getItem() == Items.pumpkin_seeds || stack.getItem() == Items.speckled_melon || stack.getItem() == Items.apple || stack.getItem() == Items.golden_apple || stack.getItem() == ModItems.vitoidFruit)) {
					foodValue = 30 + 3 * level;
				}
			}
			if (dog instanceof EntityDarkZertum) {
				if ((stack.getItem() == ModItems.kurrSeeds)) {
					foodValue = 30 + 3 * level;
				}
			}
			if (dog instanceof EntityMetalZertum) {
				if ((stack.getItem() == Items.gunpowder)) {
					foodValue = 30 + 3 * level;
				}
			}
		}
		else {
			if (stack.getItem() != Items.rotten_flesh && stack.getItem() instanceof ItemFood) {
				ItemFood itemfood = (ItemFood) stack.getItem();

				if (itemfood.isWolfsFavoriteMeat()) {
					foodValue += 4 * level;
				}
			}
		}
		return foodValue;
	}

	@Override
	public String getKey() {
		return "bodymuncher";
	}
}