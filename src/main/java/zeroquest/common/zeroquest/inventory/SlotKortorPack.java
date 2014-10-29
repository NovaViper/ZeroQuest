package common.zeroquest.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import common.zeroquest.entity.EntityKortor;

public class SlotKortorPack extends Slot {
	   
	private EntityKortor doggy;


    public SlotKortorPack(IInventory iinventory, int i, int j, int k, EntityKortor entityKortor) {
        super(iinventory, i, j, k);
        doggy = entityKortor;
    }


    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return 3 != 0;
    }
}