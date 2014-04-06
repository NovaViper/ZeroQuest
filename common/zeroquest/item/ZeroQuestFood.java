package common.zeroquest.item;

import common.zeroquest.ZeroQuest;

import net.minecraft.item.ItemFood;

public class ZeroQuestFood extends ItemFood{

	public ZeroQuestFood(int id, int hunger, float saturation, boolean wolffood) {
		super(id, hunger, saturation, wolffood);
        this.setCreativeTab(ZeroQuest.ZeroTab);
	}
	

}
