package common.zeroquest.block;

import java.util.Random;

import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBedRock extends Block
{
    public BlockBedRock(int par1)
    {
        super(par1, Material.rock);
        this.setCreativeTab(ZeroQuest.ZeroTab);
        this.setHardness(30.0F);
        this.setResistance(6000000.0F);
        this.setStepSound(Block.soundStoneFootstep);
    }
}