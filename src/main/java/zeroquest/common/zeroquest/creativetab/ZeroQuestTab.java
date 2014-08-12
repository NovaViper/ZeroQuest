package common.zeroquest.creativetab;

import common.zeroquest.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ZeroQuestTab extends CreativeTabs {


	public ZeroQuestTab(int id) {
		super("zerotab");
	}


	@Override
	public Item getTabIconItem() {
		
		return ModItems.nileSword;
	}
}
