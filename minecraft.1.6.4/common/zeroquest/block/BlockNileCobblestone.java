package common.zeroquest.block;

import common.zeroquest.ZeroQuest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockNileCobblestone extends Block
{
    public BlockNileCobblestone(int par1, Material rock)
    {
        super(par1, Material.rock);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundStoneFootstep);
        this.setCreativeTab(ZeroQuest.ZeroTab);
    }
}