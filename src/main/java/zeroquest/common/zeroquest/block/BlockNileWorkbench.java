package common.zeroquest.block;


import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import common.zeroquest.ZeroQuest;
import common.zeroquest.tileentity.TileEntityNileWorkbench;

public class BlockNileWorkbench extends BlockContainer{


	public BlockNileWorkbench() {
		super(Material.wood);
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setHardness(2F);
		this.setResistance(12.5F);
		this.setStepSound(soundTypeWood);
	}
    private Random rand = new Random();

    @Override
	public TileEntity createNewTileEntity(World world, int par1) {
		return new TileEntityNileWorkbench();
	}

    @Override
	public int getRenderType(){ //RenderBlocks for Render type//
		return -1;		
	}
    
    @Override
	public boolean isOpaqueCube(){
		return false;		
	}
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityNileWorkbench)
            {
                playerIn.displayGUIChest((TileEntityNileWorkbench)tileentity);
            }

            return true;
        }
    }
    
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }
}