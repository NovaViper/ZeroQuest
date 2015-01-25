package common.zeroquest.block.flora;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.EnumPlantType;

import common.zeroquest.ModBlocks;
import common.zeroquest.ModItems;

public class BlockVitoidCrop extends BlockCrops{

	public BlockVitoidCrop() {
		super();
	}
	
	@Override
	public Item getSeed(){
		return ModItems.vitoidSeed;
	}
	
	@Override
	public Item getCrop(){
		return ModItems.vitoidFruit;
	}
	
    @Override
    public EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Crop;
    }
}