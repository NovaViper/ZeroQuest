package common.zeroquest.item;

import common.zeroquest.ZeroQuest;

import net.minecraft.item.Item;

public class ItemNileBone extends Item {

	public ItemNileBone(int par1) {
		super(par1);
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.canRepair = false;
		this.maxStackSize = 64;
	}

}
