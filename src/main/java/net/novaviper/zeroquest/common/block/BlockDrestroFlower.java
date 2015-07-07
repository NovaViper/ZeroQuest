package net.novaviper.zeroquest.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.novaviper.zeroquest.ModBlocks;
import net.novaviper.zeroquest.ZeroQuest;

public class BlockDrestroFlower extends BlockBush{

	public BlockDrestroFlower() {
		super();
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setLightLevel(0.2F);
		this.setStepSound(soundTypeGrass);
	}
	
	@Override
    protected boolean canPlaceBlockOn(Block par1)
    {
        return par1 == Blocks.grass
        		|| par1 == Blocks.dirt 
        		|| par1 == Blocks.farmland
				|| par1 == Blocks.sand 
				|| par1 == Blocks.sandstone;
    }
}