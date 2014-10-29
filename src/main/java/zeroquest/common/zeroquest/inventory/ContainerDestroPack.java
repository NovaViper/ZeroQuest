package common.zeroquest.inventory;

import common.zeroquest.entity.EntityDestroZertum;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDestroPack extends Container {


    private EntityDestroZertum wolf;


    public ContainerDestroPack(InventoryPlayer par1IInventory, EntityDestroZertum entitydtdoggy) {
        wolf = entitydtdoggy;
        int i = 0;
        inventoryItemStacks.clear();
        inventorySlots.clear();


        for (int j = 0; j < 3; j++)
        {
            for (int i1 = 0; i1 < 5; i1++)
            {
                this.addSlotToContainer(new SlotDestroPack(entitydtdoggy.inventory, i, 79 + 18 * i1, 1 + 18 * j + 24, wolf));
                i++;
            }
        }


        int var3;
        int var4;
        
        for (var3 = 0; var3 < 3; ++var3)
        {
            for (var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(par1IInventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }


        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(par1IInventory, var3, 8 + var3 * 18, 142));
        }
    }


    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);


        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();


            if (i < 3 * 5)
            {
                if (!mergeItemStack(itemstack1, 3 * 5, inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, 0, 3 * 5, false))
            {
                return null;
            }


            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }


        return itemstack;
    }


    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return wolf.inventory.isUseableByPlayer(entityplayer);
    }
}
