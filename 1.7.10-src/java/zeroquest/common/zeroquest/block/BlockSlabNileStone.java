package common.zeroquest.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;


public class BlockSlabNileStone extends BlockSlab{
	
	private Block extendingBlock;

	public BlockSlabNileStone(boolean fullBlock, Block block, Material material) {
		super(fullBlock, material);
		this.extendingBlock = block;
		this.blockHardness = 0.4f;
		
		if(!fullBlock){
			this.setCreativeTab(ZeroQuest.ZeroTab);
		}

	}
	
	/*@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register){
		this.blockIcon = register.registerIcon(ZeroQuest.ZeroTab + ":" + this.extendingBlock)
	}*/
	
    public ItemStack createStackedBlock(int par1)
    {
	    return new ItemStack(Item.getItemFromBlock(ModBlocks.nileStoneSlab), 2, par1);
    }

	@Override
	public String func_150002_b(int p_150002_1_) {

		return null;
	}
	
}