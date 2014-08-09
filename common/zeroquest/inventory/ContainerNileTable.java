package common.zeroquest.inventory;

import common.zeroquest.interfaces.ISlotChanged;
import common.zeroquest.tileentity.TileEntityNileTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;


public class ContainerNileTable extends Container implements ISlotChanged {
	private World worldObj;
	public TileEntityNileTable tileEntity;


	NileTabSlot FabSlot;
	private int posX;
	private int posY;
	private int posZ;


    /** The crafting matrix inventory (3x3). */          //container, width, length
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    private NileTabSlot fabTabSlot;


	public ContainerNileTable(TileEntityNileTable tileFabTable, InventoryPlayer playerInv, World world, int x, int y, int z)
	{
	         worldObj = world;
	         posX = x;
	         posY = y;
	         posZ = z;
	         fabTabSlot = new NileTabSlot(playerInv.player, this.craftMatrix, tileFabTable.craftResult, 0, 124, 35);
	         addSlotToContainer(fabTabSlot);
	         int row;
	         int col;
	         tileEntity = tileFabTable;

	         updateCraftingMatrix();


	         for (row = 0; row < 3; ++row)
	         {
	             for (col = 0; col < 3; ++col)
	             {
	                 this.addSlotToContainer(new Slot(this.craftMatrix, col + row * 3, 30 + col * 18, 17 + row * 18));
	             }
	         }

	         for (row = 0; row < 3; ++row)
	         {
	             for (col = 0; col < 9; ++col)
	             {
	                 this.addSlotToContainer(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
	             }
	         }

	         for (row = 0; row < 9; ++row)
	         {
	             this.addSlotToContainer(new Slot(playerInv, row, 8 + row * 18, 142));
	         }

	         this.onCraftMatrixChanged(this.craftMatrix);
	}




    private void updateCraftingMatrix() {
        for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
            craftMatrix.setInventorySlotContents(i, tileEntity.craftMatrixInventory[i]);
        }
    }




    @Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
	         super.onContainerClosed(par1EntityPlayer);
	         saveCraftingMatrix();
	}


	private void saveCraftingMatrix() {
	    for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
	        tileEntity.craftMatrixInventory[i] = craftMatrix.getStackInSlot(i);
        }
    }




    @Override
	public void onCraftMatrixChanged(IInventory IInv)
	{	
	    tileEntity.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
	}






	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)	
	{
	         return tileEntity.isUseableByPlayer(entityPlayer);


	}


	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int par2)
	{
		ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);
        
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 >= 10 && par2 < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (par2 >= 37 && par2 < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(entityPlayer, itemstack1);
        }

        return itemstack;
    }





	@Override
	public void onSlotChange(Slot slot, int id, ItemStack itemStack) {
		System.out.println("Plans changed");




	}
}

