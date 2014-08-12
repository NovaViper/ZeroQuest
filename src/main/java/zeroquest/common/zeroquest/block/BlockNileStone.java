package common.zeroquest.block;

import java.util.Random;

import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class BlockNileStone extends BlockStone
{
    private static final String __OBFID = "CL_00000317";
	
    public BlockNileStone()
    {
        super();
        this.setCreativeTab(ZeroQuest.ZeroTab);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(Block.soundTypeStone);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(ModBlocks.nileCobblestone);
    }
}