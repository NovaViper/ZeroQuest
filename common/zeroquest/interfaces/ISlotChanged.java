package common.zeroquest.interfaces;


import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public interface ISlotChanged {
	public void onSlotChange(Slot slot, int id, ItemStack itemStack);
}

