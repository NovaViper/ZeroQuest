package net.novaviper.zeroquest.common.block;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.EnumPlantType;
import net.novaviper.zeroquest.ModBlocks;
import net.novaviper.zeroquest.ModItems;

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
}