package common.zeroquest.block;

import java.util.Random;

import common.zeroquest.ZeroQuest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.world.World;

public class BlockDrestroFlower extends BlockFlower{

	public BlockDrestroFlower(int par1) {
		super(par1);
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setLightValue(0.2F);
		this.setStepSound(soundGrassFootstep);
	}
	
	@Override
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == Block.grass.blockID 
        		|| par1 == Block.dirt.blockID 
        		|| par1 == Block.tilledField.blockID
				|| par1 == Block.sand.blockID 
				|| par1 == Block.sandStone.blockID;
    }
}