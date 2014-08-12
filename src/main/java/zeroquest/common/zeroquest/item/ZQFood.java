package common.zeroquest.item;

import common.zeroquest.ZeroQuest;

import net.minecraft.item.ItemFood;

public class ZQFood extends ItemFood{

	public ZQFood(int hunger, float saturation, boolean wolffood) {
		super(hunger, saturation, wolffood);
        this.setCreativeTab(ZeroQuest.ZeroTab);
	}
	

}
