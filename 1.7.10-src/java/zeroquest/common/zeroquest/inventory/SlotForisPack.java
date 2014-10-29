package common.zeroquest.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import common.zeroquest.entity.EntityForisZertum;

public class SlotForisPack extends Slot {
	   
	private EntityForisZertum doggy;


    public SlotForisPack(IInventory iinventory, int i, int j, int k, EntityForisZertum entityForisZertum) {
        super(iinventory, i, j, k);
        doggy = entityForisZertum;
    }


    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return 3 != 0;
    }
}