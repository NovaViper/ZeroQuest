package common.zeroquest.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import common.zeroquest.ZeroQuest;

public class BlockNileB2Flower extends BlockBush{

	public BlockNileB2Flower() {
		super();
		this.setCreativeTab(ZeroQuest.ZeroTab);
		this.setLightLevel(0F);
		this.setStepSound(soundTypeGrass);
	}
	
	@Override
    protected boolean canPlaceBlockOn(Block par1)
    {
        return par1 == Blocks.grass 
        		|| par1 == Blocks.dirt 
        		|| par1 == Blocks.farmland;
    }
	
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	par1World.spawnParticle("townaura", (double)par2 + Math.random(), (double)par3 + 1.2D, (double)par4 + Math.random(), 0.0D, 0.0D, 0.0D);
    }
}
