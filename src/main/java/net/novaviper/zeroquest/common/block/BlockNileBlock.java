package net.novaviper.zeroquest.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockNileBlock extends Block
{
    public BlockNileBlock(CreativeTabs tab, float hardness, float resistance, float light)
    {
        super(Material.rock);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setStepSound(soundTypeMetal);
        this.setCreativeTab(tab);
        this.setLightLevel(light);
    }
}

