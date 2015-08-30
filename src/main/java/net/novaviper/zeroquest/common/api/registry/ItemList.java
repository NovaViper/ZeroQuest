package net.novaviper.zeroquest.common.api.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.novaviper.zeroquest.common.helper.LogHelper;

/**
 * @author ProPercivalalb
 */
public class ItemList {

	private String name;
	private final List<List<Object>> itemlist = new ArrayList<List<Object>>();

	public ItemList(String name) {
		this.name = name;
	}

	public void registerItem(Item item) {
		this.registerItem(item, OreDictionary.WILDCARD_VALUE);
	}

	public void registerItem(Item item, int meta) {
		List array = Arrays.asList(new Object[] { item, meta });
		if (this.itemlist.contains(array)) {
			LogHelper.warn("The item %s meta %s is already registered in the %s item list", item.getUnlocalizedName(), meta, name);
		}
		else {
			this.itemlist.add(array);
			LogHelper.info("The item %s meta %s was register to the %s item list", item.getUnlocalizedName(), meta, name);
		}
	}

	public boolean containsItem(Item item) {
		return this.containsItem(item, OreDictionary.WILDCARD_VALUE);
	}

	public boolean containsItem(ItemStack stack) {
		return this.containsItem(stack.getItem(), stack.getItemDamage());
	}

	public boolean containsItem(Item item, int meta) {
		List array = Arrays.asList(new Object[] { item, meta });
		List array_any = Arrays.asList(new Object[] { item, OreDictionary.WILDCARD_VALUE });
		return this.itemlist.contains(array) || this.itemlist.contains(array_any);
	}
}