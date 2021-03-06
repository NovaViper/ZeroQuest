package common.zeroquest.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import common.zeroquest.ZeroQuest;

public class ItemNiliBucket extends ItemBucket{

	private Block liquidID;
	
	public ItemNiliBucket(Block liquidID) {
		super(liquidID);
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setMaxStackSize(1);
		this.setContainerItem(Items.bucket);
		this.liquidID = liquidID;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack p_77613_1_){
		return EnumRarity.rare;	
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
