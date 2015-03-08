package common.zeroquest.creativetab;

import common.zeroquest.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DarkQuestTab extends CreativeTabs {


	public DarkQuestTab(int id) {
		super("darktab");
	}

	@Override
	public Item getTabIconItem() {
		return ModItems.darkAxe;
	}
}
