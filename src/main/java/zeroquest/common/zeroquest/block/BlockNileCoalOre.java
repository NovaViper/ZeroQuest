package common.zeroquest.block;

import java.util.ArrayList;
import java.util.Random;

import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class BlockNileCoalOre extends Block
{
    public BlockNileCoalOre(Material rock)
    {
        super(Material.rock);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundTypeStone);
        this.setCreativeTab(ZeroQuest.ZeroTab);
        this.setLightLevel(0.2F);
    }
    
    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return ModItems.nileCoal;
    }
    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    @Override
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
            return this.quantityDropped(par2Random) + par2Random.nextInt(1);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
            return 1 + par1Random.nextInt(2);
    }

    @Override
    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
    {
        if (!p_149690_1_.isRemote)
        {
            ArrayList<ItemStack> items = getDrops(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_7_);
            p_149690_6_ = ForgeEventFactory.fireBlockHarvesting(items, p_149690_1_, this, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_7_, p_149690_6_, false, harvesters.get());

            for (ItemStack item : items)
            {
                if (p_149690_1_.rand.nextFloat() <= p_149690_6_)
                {
                    this.dropBlockAsItem(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, item);
                }
            }
        }
    }
    
    @Override
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
}