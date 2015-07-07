package net.novaviper.zeroquest.common.handlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.novaviper.zeroquest.ModItems;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() == ModItems.nileCoal) return 2000;
		
		return 0;
	}

}
