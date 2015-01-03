package common.zeroquest.core.handlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import common.zeroquest.ModItems;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() == ModItems.nileCoal) return 200;
		
		return 0;
	}

}
