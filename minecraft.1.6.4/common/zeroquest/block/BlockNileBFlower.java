package common.zeroquest.block;

import java.util.Random;

import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;
import common.zeroquest.particle.ParticleEffects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.world.World;

public class BlockNileBFlower extends BlockFlower{

	public BlockNileBFlower(int par1) {
		super(par1);
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setLightValue(0.4F);
		this.setStepSound(soundGrassFootstep);
	}
	
	@Override
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == Block.grass.blockID 
        		|| par1 == Block.dirt.blockID 
        		|| par1 == Block.tilledField.blockID;
    }
	
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	ParticleEffects.spawnParticle("bluedust", (double)par2 + Math.random(), (double)par3 + 1.2D, (double)par4 + Math.random(), 0.0D, 0.0D, 0.0D);
    }
}
