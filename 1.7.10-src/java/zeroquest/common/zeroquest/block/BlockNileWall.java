package common.zeroquest.block;

import java.util.List;

import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNileWall extends BlockWall
{
    public static final String[] field_150092_a = new String[] {"nileCobblestone", "looseBedrock"};
    private static final String __OBFID = "CL_00000331";

    public BlockNileWall(Block p_i45435_1_)
    {
        super(p_i45435_1_);
        this.setCreativeTab(ZeroQuest.ZeroTab);
        this.setStepSound(p_i45435_1_.stepSound);
        
        if(p_i45435_1_ == ModBlocks.nileCobblestone){
            this.setHardness(2.0F);
            this.setResistance(10.0F / 3.0F);
            this.setBlockName("nile_cobblestone_wall");
        }else if(p_i45435_1_ == ModBlocks.looseBedrock){
            this.setHardness(30.0F);
            this.setResistance(6000000.0F);
            this.setBlockName("bedrock_loose_wall");
        }
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_2_ == 1 ? ModBlocks.nileCobblestone.getBlockTextureFromSide(p_149691_1_) : ModBlocks.looseBedrock.getBlockTextureFromSide(p_149691_1_);
    }

    /**
     * Return whether an adjacent block can connect to a wall.
     */
    public boolean canConnectWallTo(IBlockAccess p_150091_1_, int p_150091_2_, int p_150091_3_, int p_150091_4_)
    {
        Block block = p_150091_1_.getBlock(p_150091_2_, p_150091_3_, p_150091_4_);
        return block != this && block != Blocks.fence_gate ? (block.getMaterial().isOpaque() && block.renderAsNormalBlock() ? block.getMaterial() != Material.gourd : false) : true;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        return p_149646_5_ == 0 ? super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_) : true;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {}
}