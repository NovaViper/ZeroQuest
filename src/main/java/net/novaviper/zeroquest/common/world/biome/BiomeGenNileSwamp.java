package net.novaviper.zeroquest.common.world.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.novaviper.zeroquest.ModBlocks;
import net.novaviper.zeroquest.common.entity.creature.EntityForisZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityZertum;
import net.novaviper.zeroquest.common.entity.mob.EntityRiggator;
import net.novaviper.zeroquest.common.lib.Constants;

public class BiomeGenNileSwamp extends BiomeGenBase
{
    private static final String __OBFID = "CL_00000185";

    public BiomeGenNileSwamp(int p_i1988_1_)
    {
        super(p_i1988_1_);
        this.theBiomeDecorator.treesPerChunk = 2;
        this.theBiomeDecorator.flowersPerChunk = 1;
        this.theBiomeDecorator.deadBushPerChunk = 1;
        this.theBiomeDecorator.mushroomsPerChunk = 8;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.clayPerChunk = 1;
        this.theBiomeDecorator.waterlilyPerChunk = 4;
        this.theBiomeDecorator.sandPerChunk2 = 0;
        this.theBiomeDecorator.sandPerChunk = 0;
        this.theBiomeDecorator.grassPerChunk = 5;
        this.waterColorMultiplier = 14745518;
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 100, 4, 4));
        this.flowers.clear();
        this.addFlower(Blocks.red_flower.getDefaultState(), 10);
        this.addFlower(ModBlocks.nilePinkFlower.getDefaultState(), 20);
    }

    public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_)
    {
        return this.worldGeneratorSwamp;
    }

    public BlockFlower.EnumFlowerType pickRandomFlower(Random p_180623_1_, BlockPos p_180623_2_)
    {
        return BlockFlower.EnumFlowerType.BLUE_ORCHID;
    }

    public void genTerrainBlocks(World worldIn, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_)
    {
        double d1 = GRASS_COLOR_NOISE.func_151601_a((double)p_180622_4_ * 0.25D, (double)p_180622_5_ * 0.25D);

        if (d1 > 0.0D)
        {
            int k = p_180622_4_ & 15;
            int l = p_180622_5_ & 15;

            for (int i1 = 255; i1 >= 0; --i1)
            {
                if (p_180622_3_.getBlockState(l, i1, k).getBlock().getMaterial() != Material.air)
                {
                    if (i1 == 62 && p_180622_3_.getBlockState(l, i1, k).getBlock() != Blocks.water)
                    {
                        p_180622_3_.setBlockState(l, i1, k, Blocks.water.getDefaultState());

                        if (d1 < 0.12D)
                        {
                            p_180622_3_.setBlockState(l, i1 + 1, k, Blocks.waterlily.getDefaultState());
                        }
                    }

                    break;
                }
            }
        }

        this.generateBiomeTerrain(worldIn, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos p_180627_1_)
    {
        double d0 = GRASS_COLOR_NOISE.func_151601_a((double)p_180627_1_.getX() * 0.0225D, (double)p_180627_1_.getZ() * 0.0225D);
        return d0 < -0.1D ? 5011004 : 6975545;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getFoliageColorAtPos(BlockPos p_180625_1_)
    {
        return 0x333300;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    @SideOnly(Side.CLIENT)
    @Override
    public int getModdedBiomeFoliageColor(int p_150571_1_)
    {
        return 0x333300;
    }
}