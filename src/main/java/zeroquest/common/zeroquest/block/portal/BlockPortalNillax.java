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

	public static final PropertyEnum AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class, new EnumFacing.Axis[] {EnumFacing.Axis.X, EnumFacing.Axis.Z});
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
    public boolean func_176548_d(World par1World, BlockPos blockPos)
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
                    par1World.setBlockState(new BlockPos(par2 + b0 * l, par3 + i1, par4 + b1 * l), this.getDefaultState());
                }
            }
 
            return true;
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
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
    
    public static class Size
    {
        private final World world;
        private final EnumFacing.Axis axis;
        private final EnumFacing field_150866_c;
        private final EnumFacing field_150863_d;
        private int field_150864_e = 0;
        private BlockPos field_150861_f;
        private int field_150862_g;
        private int field_150868_h;
        private static final String __OBFID = "CL_00000285";

        public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_)
        {
            this.world = worldIn;
            this.axis = p_i45694_3_;

            if (p_i45694_3_ == EnumFacing.Axis.X)
            {
                this.field_150863_d = EnumFacing.EAST;
                this.field_150866_c = EnumFacing.WEST;
            }
            else
            {
                this.field_150863_d = EnumFacing.NORTH;
                this.field_150866_c = EnumFacing.SOUTH;
            }

            for (BlockPos blockpos1 = p_i45694_2_; p_i45694_2_.getY() > blockpos1.getY() - 21 && p_i45694_2_.getY() > 0 && this.func_150857_a(worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down())
            {
                ;
            }

            int i = this.func_180120_a(p_i45694_2_, this.field_150863_d) - 1;

            if (i >= 0)
            {
                this.field_150861_f = p_i45694_2_.offset(this.field_150863_d, i);
                this.field_150868_h = this.func_180120_a(this.field_150861_f, this.field_150866_c);

                if (this.field_150868_h < 2 || this.field_150868_h > 21)
                {
                    this.field_150861_f = null;
                    this.field_150868_h = 0;
                }
            }

            if (this.field_150861_f != null)
            {
                this.field_150862_g = this.func_150858_a();
            }
        }

        protected int func_180120_a(BlockPos p_180120_1_, EnumFacing p_180120_2_)
        {
            int i;

            for (i = 0; i < 22; ++i)
            {
                BlockPos blockpos1 = p_180120_1_.offset(p_180120_2_, i);

                if (!this.func_150857_a(this.world.getBlockState(blockpos1).getBlock()) || this.world.getBlockState(blockpos1.down()).getBlock() != ModBlocks.nillaxStone)
                {
                    break;
                }
            }

            Block block = this.world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
            return block == ModBlocks.nillaxStone ? i : 0;
        }

        protected int func_150858_a()
        {
            int i;
            label56:

            for (this.field_150862_g = 0; this.field_150862_g < 21; ++this.field_150862_g)
            {
                for (i = 0; i < this.field_150868_h; ++i)
                {
                    BlockPos blockpos = this.field_150861_f.offset(this.field_150866_c, i).up(this.field_150862_g);
                    Block block = this.world.getBlockState(blockpos).getBlock();

                    if (!this.func_150857_a(block))
                    {
                        break label56;
                    }

                    if (block == ModBlocks.portalNillax)
                    {
                        ++this.field_150864_e;
                    }

                    if (i == 0)
                    {
                        block = this.world.getBlockState(blockpos.offset(this.field_150863_d)).getBlock();

                        if (block != ModBlocks.nillaxStone)
                        {
                            break label56;
                        }
                    }
                    else if (i == this.field_150868_h - 1)
                    {
                        block = this.world.getBlockState(blockpos.offset(this.field_150866_c)).getBlock();

                        if (block != ModBlocks.nillaxStone)
                        {
                            break label56;
                        }
                    }
                }
            }

            for (i = 0; i < this.field_150868_h; ++i)
            {
                if (this.world.getBlockState(this.field_150861_f.offset(this.field_150866_c, i).up(this.field_150862_g)).getBlock() != ModBlocks.nillaxStone)
                {
                    this.field_150862_g = 0;
                    break;
                }
            }

            if (this.field_150862_g <= 21 && this.field_150862_g >= 3)
            {
                return this.field_150862_g;
            }
            else
            {
                this.field_150861_f = null;
                this.field_150868_h = 0;
                this.field_150862_g = 0;
                return 0;
            }
        }

        protected boolean func_150857_a(Block p_150857_1_)
        {
            return p_150857_1_.getMaterial() == Material.air || p_150857_1_ == ModBlocks.nileFire || p_150857_1_ == ModBlocks.portalNillax;
        }

        public boolean func_150860_b()
        {
            return this.field_150861_f != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
        }

        public void func_150859_c()
        {
            for (int i = 0; i < this.field_150868_h; ++i)
            {
                BlockPos blockpos = this.field_150861_f.offset(this.field_150866_c, i);

                for (int j = 0; j < this.field_150862_g; ++j)
                {
                    this.world.setBlockState(blockpos.up(j), ModBlocks.portalNillax.getDefaultState().withProperty(BlockPortalNillax.AXIS, this.axis), 2);
                }
            }
        }
    }
}