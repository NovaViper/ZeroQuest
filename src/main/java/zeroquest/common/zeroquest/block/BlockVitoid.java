package common.zeroquest.block;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import common.zeroquest.ModItems;

public class BlockVitoid extends BlockCrops{

	public BlockVitoid() {
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