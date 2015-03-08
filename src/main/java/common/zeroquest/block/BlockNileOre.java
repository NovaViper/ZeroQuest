package common.zeroquest.block;

import java.util.ArrayList;
import java.util.Random;

import common.zeroquest.ModBlocks;
import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockNileOre extends Block
{
    public BlockNileOre(CreativeTabs tab, float hardness, float resistance, float light)
    {
        super(Material.rock);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setStepSound(soundTypeStone);
        this.setCreativeTab(tab);
        this.setLightLevel(light);
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this == ModBlocks.nileCoalOre ? ModItems.nileCoal : (this == ModBlocks.nileGrainOre ? ModItems.nileGrain : (this == ModBlocks.darkGrainOre ? ModItems.darkGrain : Item.getItemFromBlock(this)));
    }
    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    @Override
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
            return this.quantityDropped(par2Random) + par2Random.nextInt(2);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
            return 1 + par1Random.nextInt(3);
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune){
    	super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }
    
    @Override
    public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune)
    {
        IBlockState state = world.getBlockState(pos);
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int j = 0;
            
            if (this == ModBlocks.nileCoalOre)
            {
                j = MathHelper.getRandomIntegerInRange(rand, 0, 2);
            }
            else if (this == ModBlocks.nileGrainOre)
            {
                j = MathHelper.getRandomIntegerInRange(rand, 2, 5);
            }
            else if (this == ModBlocks.darkGrainOre)
            {
                j = MathHelper.getRandomIntegerInRange(rand, 2, 6);
            }

            return j;
        }
        return 0;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	 for (int l = 0; l < 5; ++l)
    	 {
    		 double d0 = (double)((float)pos.getX() + rand.nextFloat());
    		 double d1 = (double)((float)pos.getY() + rand.nextFloat());
    		 double d2 = (double)((float)pos.getZ() + rand.nextFloat());
    		 double d3 = 0.0D;
    		 double d4 = 0.0D;
    		 double d5 = 0.0D;
    		 int i1 = rand.nextInt(2) * 2 - 1;
    		 d3 = ((double)rand.nextFloat() - 0.5D) * 1.9D;
    		 d4 = ((double)rand.nextFloat() - 0.5D) * 1.9D;
    		 d5 = ((double)rand.nextFloat() - 0.5D) * 1.9D;
    		 worldIn.spawnParticle(EnumParticleTypes.TOWN_AURA, d0, d1, d2, d3, d4, d5);
    	 	}
    }
}
