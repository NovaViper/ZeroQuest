package common.zeroquest.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

import common.zeroquest.ZeroQuest;

public class ZQFood extends ItemFood{

	public ZQFood(int hunger, float saturation, boolean wolffood, CreativeTabs tab) {
		super(hunger, saturation, wolffood);
        this.setCreativeTab(tab);
	}
	

}
