package common.zeroquest.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import common.zeroquest.entity.EntityIceZertum;

public class SlotIcePack extends Slot {
	   
	private EntityIceZertum doggy;


    public SlotIcePack(IInventory iinventory, int i, int j, int k, EntityIceZertum entityIceZertum) {
        super(iinventory, i, j, k);
        doggy = entityIceZertum;
    }


    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return 3 != 0;
    }
}