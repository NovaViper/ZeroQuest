package common.zeroquest.block;

import java.util.Random;

import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockNileCoalOre extends Block
{
    public BlockNileCoalOre(int par1, Material rock)
    {
        super(par1, Material.rock);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundStoneFootstep);
        this.setCreativeTab(ZeroQuest.ZeroTab);
        this.setLightValue(0.3F);
    }
    
    public int idDropped(int metadata, Random par2Random, int fortune){
        return ModItems.nileCoal.itemID;
    }
    
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {

        for (int l = 0; l < 5; ++l)
        {
            double d0 = (double)((float)par2 + par5Random.nextFloat());
            double d1 = (double)((float)par3 + par5Random.nextFloat());
            double d2 = (double)((float)par4 + par5Random.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = par5Random.nextInt(2) * 2 - 1;
            d3 = ((double)par5Random.nextFloat() - 0.5D) * 1.9D;
            d4 = ((double)par5Random.nextFloat() - 0.5D) * 1.9D;
            d5 = ((double)par5Random.nextFloat() - 0.5D) * 1.9D;
            par1World.spawnParticle("townaura", d0, d1, d2, d3, d4, d5);
        }
    }
    
    public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7){
    	super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);
    	if(this.idDropped(par5, world.rand, par7) != this.blockID){
    		int j1 = 0;
    		j1 = MathHelper.getRandomIntegerInRange(world.rand, 2, 16);
    		this.dropXpOnBlockBreak(world, par2, par3, par4, j1);
    		
    	}
    	
    }
}