package common.zeroquest.handlers;

import common.zeroquest.ModItems;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.itemID == ModItems.nileCoal.itemID) return 200;
		
		return 0;
	}

}
