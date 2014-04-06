package common.zeroquest.block;

import java.util.Random;

import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockNileStone extends Block
{
    public BlockNileStone(int par1)
    {
        super(par1, Material.rock);
        this.setCreativeTab(ZeroQuest.ZeroTab);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setStepSound(Block.soundStoneFootstep);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return ModBlocks.nileCobblestone.blockID;
    }
}