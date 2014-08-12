package common.zeroquest.inventory;


import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import common.zeroquest.entity.EntityDarkZertum;

public class SlotDarkPack extends Slot {
	   
		private EntityDarkZertum doggy;


	    public SlotDarkPack(IInventory iinventory, int i, int j, int k, EntityDarkZertum entitydtdoggy) {
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
