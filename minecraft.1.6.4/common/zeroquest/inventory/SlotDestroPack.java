package common.zeroquest.inventory;

import common.zeroquest.entity.EntityDestroZertum;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDestroPack extends Slot {
	   
		private EntityDestroZertum doggy;


	    public SlotDestroPack(IInventory iinventory, int i, int j, int k, EntityDestroZertum entitydtdoggy) {
	        super(iinventory, i, j, k);
	        doggy = entitydtdoggy;
	    }


	    /**
	     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
	     */
	    @Override
	    public boolean isItemValid(ItemStack itemstack) {
	        return 3 != 0;
	    }
	}
