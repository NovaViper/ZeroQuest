package common.zeroquest.block.portal;

import java.util.Random;

import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPortalNillax extends BlockPortal{

	public BlockPortalNillax() {
		super();
        this.setStepSound(soundTypeGlass);
	}
	
	 @Override
	 public int getLightValue(IBlockAccess world, BlockPos pos){ return 14; }
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity){
		if(entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP) entity;
			FMLCommonHandler.instance().getMinecraftServerInstance();
			MinecraftServer server = MinecraftServer.getServer();
			
			if(player.timeUntilPortal > 0){
				player.timeUntilPortal = 10;
			}else if(player.dimension != ZeroQuest.NillaxID){
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, ZeroQuest.NillaxID, new TeleporterNillax(server.worldServerForDimension(ZeroQuest.NillaxID)));
			}else{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterNillax(server.worldServerForDimension(0)));
			}
		}
	}
	
	@Override
    public boolean func_176548_d(World par1World, BlockPos blockPos) //func_176548_d
    {
        byte b0 = 0;
        byte b1 = 0;
        
        int par2= blockPos.getX();
        int par3= blockPos.getY();
        int par4= blockPos.getZ();
 
        if (par1World.getBlockState(new BlockPos(par2 - 1, par3, par4)).getBlock() == ModBlocks.nillaxStone || par1World.getBlockState(new BlockPos(par2 + 1, par3, par4)).getBlock() == ModBlocks.nillaxStone)
        {
            b0 = 1;
        }
 
        if (par1World.getBlockState(new BlockPos(par2, par3, par4 - 1)).getBlock() == ModBlocks.nillaxStone || par1World.getBlockState(new BlockPos(par2, par3, par4 + 1)).getBlock() == ModBlocks.nillaxStone)
        {
            b1 = 1;
        }
 
        if (b0 == b1)
        {
            return false;
        }
        else
        {
            if (par1World.isAirBlock(new BlockPos(par2 - b0, par3, par4 - b1)))
            {
                par2 -= b0;
                par4 -= b1;
            }
 
            int l;
            int i1;
 
            for (l = -1; l <= 2; ++l)
            {
                for (i1 = -1; i1 <= 3; ++i1)
                {
                    boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;
 
                    if (l != -1 && l != 2 || i1 != -1 && i1 != 3)
                    {
                        Block j1 = par1World.getBlockState(new BlockPos(par2 + b0 * l, par3 + i1, par4 + b1 * l)).getBlock();
                        boolean isAirBlock = par1World.isAirBlock(new BlockPos(par2 + b0 * l, par3 + i1, par4 + b1 * l));
 
                        if (flag)
                        {
                            if (j1 != ModBlocks.nillaxStone)
                            {
                                return false;
                            }
                        }
                        else if (!isAirBlock && j1 != ModBlocks.nileFire)
                        {
                            return false;
                        }
                    }
                }
            }
 
            for (l = 0; l < 2; ++l)
            {
                for (i1 = 0; i1 < 3; ++i1)
                {
                    par1World.setBlockState(new BlockPos(par2 + b0 * l, par3 + i1, par4 + b1 * l), ModBlocks.portalNillax.getDefaultState());
                }
            }
 
            return true;
        }
    }
    @Override
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	
        int par2= pos.getX();
        int par3= pos.getY();
        int par4= pos.getZ();
    	
        if (rand.nextInt(100) == 0)
        {
        	worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, "portal.portal", 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; ++i)
        {
            double d0 = (double)((float)pos.getX() + rand.nextFloat());
            double d1 = (double)((float)pos.getY() + rand.nextFloat());
            double d2 = (double)((float)pos.getZ() + rand.nextFloat());
            double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            int j = rand.nextInt(2) * 2 - 1;

            if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this)
            {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
            }
            else
            {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
            }

            worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5, new int[0]);
        }
    }

    @Override
	public void onNeighborBlockChange(World par1World, BlockPos blockPos, IBlockState state, Block neighborBlock)
    	{
        byte b0 = 0;
        byte b1 = 1;
        
        int par2= blockPos.getX();
        int par3= blockPos.getY();
        int par4= blockPos.getZ();

	        if (par1World.getBlockState(new BlockPos(par2 - 1, par3, par4)) == this || par1World.getBlockState(new BlockPos(par2 + 1, par3, par4)) == this)
	        {
	            b0 = 1;
	            b1 = 0;
	        }

	        int i1;

	        for (i1 = par3; par1World.getBlockState(new BlockPos(par2, i1 - 1, par4)) == this; --i1)
	        {
	            ;
	        }

	        if (par1World.getBlockState(new BlockPos(par2, i1 - 1, par4)) != ModBlocks.nillaxStone)
	        {
	            par1World.setBlockToAir(new BlockPos(par2, par3, par4));
	        }
	        else
	        {
	            int j1;

	            for (j1 = 1; j1 < 4 && par1World.getBlockState(new BlockPos(par2, i1 + j1, par4)) == this; ++j1)
	            {
	                ;
	            }

	            if (j1 == 3 && par1World.getBlockState(new BlockPos(par2, i1 + j1, par4)) == ModBlocks.nillaxStone)
	            {
	                boolean flag = par1World.getBlockState(new BlockPos(par2 - 1, par3, par4)) == this || par1World.getBlockState(new BlockPos(par2 + 1, par3, par4)) == this;
	                boolean flag1 = par1World.getBlockState(new BlockPos(par2, par3, par4 - 1)) == this || par1World.getBlockState(new BlockPos(par2, par3, par4 + 1)) == this;

	                if (flag && flag1)
	                {
	                    par1World.setBlockToAir(new BlockPos(par2, par3, par4));
	                }
	                else
	                {
	                    if ((par1World.getBlockState(new BlockPos(par2 + b0, par3, par4 + b1)) != ModBlocks.nillaxStone || par1World.getBlockState(new BlockPos(par2 - b0, par3, par4 - b1)) != this) && (par1World.getBlockState(new BlockPos(par2 - b0, par3, par4 - b1)) != ModBlocks.nillaxStone || par1World.getBlockState(new BlockPos(par2 + b0, par3, par4 + b1)) != this))
	                    {
	                        par1World.setBlockToAir(new BlockPos(par2, par3, par4));
	                    }
	                }
	            }
	            else
	            {
	                par1World.setBlockToAir(new BlockPos(par2, par3, par4));
	            }
	        }
	    }
}