package common.zeroquest.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import common.zeroquest.ModItems;

public class DarkQuestTab extends CreativeTabs {

	public DarkQuestTab(int id) {
		super("darktab");
		// this.setBackgroundImageName("item_search.png");
	}

	/**
	 * Determines if the search bar should be shown for this tab.
	 * 
	 * @return True to show the bar
	 */
	/*@Override
	public boolean hasSearchBar() {
		return getTabIndex() == this.getTabIndex();
	}*/

	@Override
	public Item getTabIconItem() {
		return ModItems.darkAxe;
	}
}