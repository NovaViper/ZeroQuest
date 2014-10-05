package common.zeroquest.block.portal;

import java.util.Random;

import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;
import common.zeroquest.client.particle.ParticleEffects;
import common.zeroquest.dimension.TeleporterDarkax;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPortalDarkax extends BlockPortal{

	public BlockPortalDarkax() {
		super();
        this.setStepSound(soundTypeGlass);
	}
	
	 @Override
	 public int getLightValue(IBlockAccess world, int x, int y, int z)
	 {
		 return 14;
	 }
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
		if(entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP) entity;
			FMLCommonHandler.instance().getMinecraftServerInstance();
			MinecraftServer server = MinecraftServer.getServer();
			
			if(player.timeUntilPortal > 0){
				player.timeUntilPortal = 10;
			}else if(player.dimension != ZeroQuest.DarkaxID){
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, ZeroQuest.DarkaxID, new TeleporterDarkax(server.worldServerForDimension(ZeroQuest.DarkaxID)));
			}else{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterDarkax(server.worldServerForDimension(0)));
			}
		}
	}
	

    @Override
    public boolean func_150000_e(World par1World, int par2, int par3, int par4)
    {
        byte b0 = 0;
        byte b1 = 0;
 
        if (par1World.getBlock(par2 - 1, par3, par4) == ModBlocks.darkaxStone || par1World.getBlock(par2 + 1, par3, par4) == ModBlocks.darkaxStone)
        {
            b0 = 1;
        }
 
        if (par1World.getBlock(par2, par3, par4 - 1) == ModBlocks.darkaxStone || par1World.getBlock(par2, par3, par4 + 1) == ModBlocks.darkaxStone)
        {
            b1 = 1;
        }
 
        if (b0 == b1)
        {
            return false;
        }
        else
        {
            if (par1World.isAirBlock(par2 - b0, par3, par4 - b1))
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
                        Block j1 = par1World.getBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);
                        boolean isAirBlock = par1World.isAirBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);
 
                        if (flag)
                        {
                            if (j1 != ModBlocks.darkaxStone)
                            {
                                return false;
                            }
                        }
                        else if (!isAirBlock && j1 != ModBlocks.darkaxFire)
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
                    par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l, this, 0, 2);
                }
            }
 
            return true;
        }
    }
    
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par5Random.nextInt(100) == 0)
        {
        	par1World.playSound((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int l = 0; l < 4; ++l)
        {
            double d0 = (double)((float)par2 + par5Random.nextFloat());
            double d1 = (double)((float)par3 + par5Random.nextFloat());
            double d2 = (double)((float)par4 + par5Random.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = par5Random.nextInt(2) * 2 - 1;
            d3 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;

            if (par1World.getBlock(par2 - 1, par3, par4) != this && par1World.getBlock(par2 + 1, par3, par4) != this)
            {
                d0 = (double)par2 + 0.5D + 0.25D * (double)i1;
                d3 = (double)(par5Random.nextFloat() * 2.0F * (float)i1);
            }
            else
            {
                d2 = (double)par4 + 0.5D + 0.25D * (double)i1;
                d5 = (double)(par5Random.nextFloat() * 2.0F * (float)i1);
            }

            ParticleEffects.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
        }
    }

      @Override
	  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5Block)
	    {
	        byte b0 = 0;
	        byte b1 = 1;

	        if (par1World.getBlock(par2 - 1, par3, par4) == this || par1World.getBlock(par2 + 1, par3, par4) == this)
	        {
	            b0 = 1;
	            b1 = 0;
	        }

	        int i1;

	        for (i1 = par3; par1World.getBlock(par2, i1 - 1, par4) == this; --i1)
	        {
	            ;
	        }

	        if (par1World.getBlock(par2, i1 - 1, par4) != ModBlocks.darkaxStone)
	        {
	            par1World.setBlockToAir(par2, par3, par4);
	        }
	        else
	        {
	            int j1;

	            for (j1 = 1; j1 < 4 && par1World.getBlock(par2, i1 + j1, par4) == this; ++j1)
	            {
	                ;
	            }

	            if (j1 == 3 && par1World.getBlock(par2, i1 + j1, par4) == ModBlocks.darkaxStone)
	            {
	                boolean flag = par1World.getBlock(par2 - 1, par3, par4) == this || par1World.getBlock(par2 + 1, par3, par4) == this;
	                boolean flag1 = par1World.getBlock(par2, par3, par4 - 1) == this || par1World.getBlock(par2, par3, par4 + 1) == this;

	                if (flag && flag1)
	                {
	                    par1World.setBlockToAir(par2, par3, par4);
	                }
	                else
	                {
	                    if ((par1World.getBlock(par2 + b0, par3, par4 + b1) != ModBlocks.darkaxStone || par1World.getBlock(par2 - b0, par3, par4 - b1) != this) && (par1World.getBlock(par2 - b0, par3, par4 - b1) != ModBlocks.darkaxStone || par1World.getBlock(par2 + b0, par3, par4 + b1) != this))
	                    {
	                        par1World.setBlockToAir(par2, par3, par4);
	                    }
	                }
	            }
	            else
	            {
	                par1World.setBlockToAir(par2, par3, par4);
	            }
	        }
	    }
      
	  @SideOnly(Side.CLIENT)
	  @Override
	  public void registerBlockIcons(IIconRegister par1IconRegister)
	      {
	          this.blockIcon = par1IconRegister.registerIcon(ZeroQuest.modid + ":" + "portalDarkax");
	      }
}
