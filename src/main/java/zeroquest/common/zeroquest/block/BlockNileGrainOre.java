package common.zeroquest.block;

import java.util.ArrayList;
import java.util.Random;

import common.zeroquest.ModItems;
import common.zeroquest.ZeroQuest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockNileGrainOre extends Block
{
    public BlockNileGrainOre(Material rock)
    {
        super(Material.rock);
        this.setHardness(3.5F);
        this.setResistance(5.2F);
        this.setStepSound(soundTypeStone);
        this.setCreativeTab(ZeroQuest.ZeroTab);
        this.setLightLevel(0.2F);
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.nileGrain;
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
    
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.randomDisplayTick(worldIn, pos, state, rand);

        if (rand.nextInt(10) == 0)
        {
            worldIn.spawnParticle(EnumParticleTypes.TOWN_AURA, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }
}
