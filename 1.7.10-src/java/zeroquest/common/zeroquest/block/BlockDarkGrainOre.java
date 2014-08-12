package common.zeroquest.block;

import java.util.Random;

import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockDarkGrainOre extends Block
{
    public BlockDarkGrainOre(Material rock)
    {
        super(Material.rock);
        this.setHardness(3.1F);
        this.setResistance(5.2F);
        this.setStepSound(soundTypeStone);
        this.setCreativeTab(ZeroQuest.DarkTab);
        this.setLightLevel(0.5F);
    }
    
    public Item idDropped(int metadata, Random par2Random, int fortune){
        return ModItems.darkGrain;
    }
    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
            return this.quantityDropped(par2Random) + par2Random.nextInt(1);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
            return 1 + par1Random.nextInt(2);
    }

    
    public void dropBlockAsItemWithChance(World world, int par2, int par3, int par4, int par5, float par6, int par7){
    	super.dropBlockAsItemWithChance(world, par2, par3, par4, par5, par6, par7);
    	}
}