package common.zeroquest.block.fluid;

import java.util.Random;

import common.zeroquest.client.particle.EntityAcidFX;
import common.zeroquest.client.particle.EntityPDustFX;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFluidAcid extends BlockFluidClassic{
	
	public BlockFluidAcid(Fluid fluid, Material material) {
		super(fluid, material);
        this.setBlockUnbreakable();
        this.setResistance(6000000.0F);
        this.setLightOpacity(50);
        this.setLightLevel(50.0F);
		this.useNeighborBrightness = true;
		this.disableStats();
	}
	  
    @Override
    public boolean canDisplace(IBlockAccess world, BlockPos pos) {
            if (world.getBlockState(pos).getBlock().getMaterial().isLiquid()) return false;
            return super.canDisplace(world, pos);
    }
   
    @Override
    public boolean displaceIfPossible(World world, BlockPos pos) {
            if (world.getBlockState(pos).getBlock().getMaterial().isLiquid()) return false;
            return super.displaceIfPossible(world, pos);
    }

	@Override
	protected void flowIntoBlock(World world, BlockPos pos, int meta) {
		if (meta < 0) {
			return;
		}
		if (displaceIfPossible(world, pos)) {
			world.setBlockState(pos, this.getBlockState().getBaseState().withProperty(LEVEL, meta), 3);
		}
	}
	
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity){
    	if(entity instanceof EntityLivingBase){
    		((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 200)); //100 = 5 Seconds , 20 = 1 Second
    	}	
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
    		 EntityAcidFX entityFX = new EntityAcidFX(worldIn, d0, d1, d2, d3, d4, d5);
         	FMLClientHandler.instance().getClient().effectRenderer.addEffect(entityFX);
    	 	}
    }
}