package net.novaviper.zeroquest.common.item.food;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.novaviper.zeroquest.ZeroQuest;

public class Food extends ItemFood {

	public Food(int hunger, float saturation, boolean wolffood, CreativeTabs tab) {
		super(hunger, saturation, wolffood);
		this.setCreativeTab(tab);
	}
}
