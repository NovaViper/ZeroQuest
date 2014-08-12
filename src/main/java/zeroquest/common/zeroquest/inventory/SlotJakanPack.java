package common.zeroquest.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import common.zeroquest.entity.EntityJakan;

public class SlotJakanPack extends Slot {
	   
	private EntityJakan doggy;


    public SlotJakanPack(IInventory iinventory, int i, int j, int k, EntityJakan entityjakan) {
        super(iinventory, i, j, k);
        doggy = entityjakan;
    }


    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return 3 != 0;
    }
}