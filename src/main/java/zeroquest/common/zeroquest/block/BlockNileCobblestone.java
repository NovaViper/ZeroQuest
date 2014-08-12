package common.zeroquest.block;

import common.zeroquest.ZeroQuest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockNileCobblestone extends Block
{
    public BlockNileCobblestone(Material rock)
    {
        super(Material.rock);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeStone);
        this.setCreativeTab(ZeroQuest.ZeroTab);
    }
}