package common.zeroquest.item;

import common.zeroquest.ZeroQuest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.world.World;

public class ItemAcidBucket extends ItemBucket{

	private Block liquidID;
	
	public ItemAcidBucket(Block liquidID) {
		super(liquidID);
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setMaxStackSize(1);
		this.setContainerItem(Items.bucket);
		this.liquidID = liquidID;
	}

	@Override
	public boolean tryPlaceContainedLiquid(World world, int x, int y, int z){
	    {
	        if (this.liquidID == Blocks.air)
	        {
	            return false;
	        }
	        else
	        {
	            Material material = world.getBlock(x, y, z).getMaterial();
	            boolean flag = !material.isSolid();

	            if (!world.isAirBlock(x, y, z) && !flag)
	            {
	                return false;
	            }
	            else
	            {
	                    if (!world.isRemote && flag && !material.isLiquid())
	                    {
	                    	world.func_147480_a(x, y, z, true);
	                    }

	                    world.setBlock(x, y, z, this.liquidID, 0, 3);
	                }

	                return true;
	            }
	        }
	    }
}
