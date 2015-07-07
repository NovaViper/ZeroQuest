package net.novaviper.zeroquest.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.novaviper.zeroquest.ModBlocks;
import net.novaviper.zeroquest.ZeroQuest;

public class BlockBedRock extends Block
{
    public BlockBedRock()
    {
        super(Material.rock);
        this.setCreativeTab(ZeroQuest.ZeroTab);
        this.setHardness(30.0F);
        this.setResistance(6000000.0F);
        this.setStepSound(Block.soundTypeStone);
    }
}