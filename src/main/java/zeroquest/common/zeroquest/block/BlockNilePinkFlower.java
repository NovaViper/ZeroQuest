package common.zeroquest.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import common.zeroquest.ZeroQuest;
import common.zeroquest.client.particle.EntityDustFX;
import common.zeroquest.client.particle.EntityPDustFX;

public class BlockNilePinkFlower extends BlockBush{

	public BlockNilePinkFlower() {
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
	
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.randomDisplayTick(worldIn, pos, state, rand);

        if (rand.nextInt(10) == 0)
        {
        	EntityPDustFX entityFX = new EntityPDustFX(worldIn, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
        	FMLClientHandler.instance().getClient().effectRenderer.addEffect(entityFX);
    	}
    }
}
