package common.zeroquest.world.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;
import net.minecraft.world.biome.BiomeGenSavanna;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraftforge.event.terraingen.TerrainGen;
import common.zeroquest.ModBlocks;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.entity.zertum.EntityZertum;

public class BiomeGenNileSavanna extends BiomeGenBase
{
    private static final WorldGenSavannaTree field_150627_aC = new WorldGenSavannaTree(false);
    private static final String __OBFID = "CL_00000182";

    public BiomeGenNileSavanna(int p_i45383_1_)
    {
        super(p_i45383_1_);
        this.theBiomeDecorator.treesPerChunk = 1;
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 20;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPig.class, 5, 1, 5)); 
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityCreeper.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
       	this.waterColorMultiplier = 0x0099cc;
    }

    public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_)
    {
        return (WorldGenAbstractTree)(p_150567_1_.nextInt(5) > 0 ? field_150627_aC : this.worldGeneratorTrees);
    }

    public BiomeGenBase createMutatedBiome(int p_180277_1_)
    {
        BiomeGenSavanna.Mutated mutated = new BiomeGenSavanna.Mutated(p_180277_1_, this);
        mutated.temperature = (this.temperature + 1.0F) * 0.5F;
        mutated.minHeight = this.minHeight * 0.5F + 0.3F;
        mutated.maxHeight = this.maxHeight * 0.5F + 1.2F;
        return mutated;
    }

    public void decorate(World worldIn, Random p_180624_2_, BlockPos p_180624_3_)
    {
        DOUBLE_PLANT_GENERATOR.func_180710_a(BlockDoublePlant.EnumPlantType.GRASS);

        for (int i = 0; i < 7; ++i)
        {
            int j = p_180624_2_.nextInt(16) + 8;
            int k = p_180624_2_.nextInt(16) + 8;
            int l = p_180624_2_.nextInt(worldIn.getHorizon(p_180624_3_.add(j, 0, k)).getY() + 32);
            DOUBLE_PLANT_GENERATOR.generate(worldIn, p_180624_2_, p_180624_3_.add(j, l, k));
        }

        super.decorate(worldIn, p_180624_2_, p_180624_3_);
    }

    public static class Mutated extends BiomeGenMutated
        {
            private static final String __OBFID = "CL_00000183";

            public Mutated(int p_i45382_1_, BiomeGenBase p_i45382_2_)
            {
                super(p_i45382_1_, p_i45382_2_);
                this.theBiomeDecorator.treesPerChunk = 2;
                this.theBiomeDecorator.flowersPerChunk = 2;
                this.theBiomeDecorator.grassPerChunk = 5;
            }

            public void genTerrainBlocks(World worldIn, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_)
            {
                this.topBlock = Blocks.grass.getDefaultState();
                this.fillerBlock = Blocks.dirt.getDefaultState();

                if (p_180622_6_ > 1.75D)
                {
                    this.topBlock = Blocks.stone.getDefaultState();
                    this.fillerBlock = Blocks.stone.getDefaultState();
                }
                else if (p_180622_6_ > -0.5D)
                {
                    this.topBlock = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
                }

                this.generateBiomeTerrain(worldIn, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
            }

            public void decorate(World worldIn, Random p_180624_2_, BlockPos p_180624_3_)
            {
                this.theBiomeDecorator.decorate(worldIn, p_180624_2_, this, p_180624_3_);
            }
        }
}