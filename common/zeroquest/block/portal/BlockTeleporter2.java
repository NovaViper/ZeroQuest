package common.zeroquest.block.portal;

import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;
import common.zeroquest.dimension.TeleporterNillax;
import net.minecraft.block.BlockPortal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class BlockTeleporter2 extends BlockPortal{

	public BlockTeleporter2(int par1) {
		super(par1);
	}
	
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
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
	

	public boolean tryToCreatePortal(World world, int x, int y, int z){
		{
			byte b0 = 0;
			byte b1 = 0;
			if (world.getBlockId(x - 1, y, z) == ModBlocks.darlaxFire.blockID || world.getBlockId(x + 1, y, z) == ModBlocks.darlaxFire.blockID)
			{
				b0 = 1;
			}
			if (world.getBlockId(x, y, z - 1) == ModBlocks.darlaxFire.blockID || world.getBlockId(x, y, z + 1) == ModBlocks.darlaxFire.blockID)
			{
				b1 = 1;
			}
			if (b0 == b1)
			{
				return false;
			}
			else
			{
				if (world.getBlockId(x - b0, y, z - b1) == 0)
				{
					x -= b0;
					z -= b1;
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
							int j1 = world.getBlockId(x + b0 * l, y + i1, z + b1 * l);
							if (flag)
							{
								if (j1 != ModBlocks.darlaxFire.blockID)
								{
									return false;
								}
							}
							else if (j1 != 0 && j1 != ModBlocks.darlaxFire.blockID)
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
						world.setBlock(x + b0 * l, y + i1, z + b1 * l, ModBlocks.blockTeleporter2.blockID, 0, 2);
					}
				}
				return true;
			}
		}

	}
	

	  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	    {
	        byte b0 = 0;
	        byte b1 = 1;

	        if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
	        {
	            b0 = 1;
	            b1 = 0;
	        }

	        int i1;

	        for (i1 = par3; par1World.getBlockId(par2, i1 - 1, par4) == this.blockID; --i1)
	        {
	            ;
	        }

	        if (par1World.getBlockId(par2, i1 - 1, par4) != ModBlocks.darlaxStone.blockID)
	        {
	            par1World.setBlockToAir(par2, par3, par4);
	        }
	        else
	        {
	            int j1;

	            for (j1 = 1; j1 < 4 && par1World.getBlockId(par2, i1 + j1, par4) == this.blockID; ++j1)
	            {
	                ;
	            }

	            if (j1 == 3 && par1World.getBlockId(par2, i1 + j1, par4) == ModBlocks.darlaxStone.blockID)
	            {
	                boolean flag = par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID;
	                boolean flag1 = par1World.getBlockId(par2, par3, par4 - 1) == this.blockID || par1World.getBlockId(par2, par3, par4 + 1) == this.blockID;

	                if (flag && flag1)
	                {
	                    par1World.setBlockToAir(par2, par3, par4);
	                }
	                else
	                {
	                    if ((par1World.getBlockId(par2 + b0, par3, par4 + b1) != ModBlocks.darlaxStone.blockID || par1World.getBlockId(par2 - b0, par3, par4 - b1) != this.blockID) && (par1World.getBlockId(par2 - b0, par3, par4 - b1) != ModBlocks.darlaxStone.blockID || par1World.getBlockId(par2 + b0, par3, par4 + b1) != this.blockID))
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

	
}
