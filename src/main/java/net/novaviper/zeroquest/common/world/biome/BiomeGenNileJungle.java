package net.novaviper.zeroquest.common.world.biome;

import java.util.Random;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.novaviper.zeroquest.common.entity.creature.EntityForisZertum;
import net.novaviper.zeroquest.common.entity.creature.EntityKortor;

public class BiomeGenNileJungle extends BiomeGenBase
{
    private boolean field_150614_aC;
    private static final String __OBFID = "CL_00000175";

    public BiomeGenNileJungle(int p_i45379_1_, boolean p_i45379_2_)
    {
        super(p_i45379_1_);
        this.field_150614_aC = p_i45379_2_;
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 100, 4, 4));

        if (p_i45379_2_)
        {
            this.theBiomeDecorator.treesPerChunk = 2;
        }
        else
        {
            this.theBiomeDecorator.treesPerChunk = 50;
        }

        this.theBiomeDecorator.grassPerChunk = 25;
        this.theBiomeDecorator.flowersPerChunk = 4;

        if (!p_i45379_2_)
        {
            this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        }

        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 10, 4, 4));
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getModdedBiomeFoliageColor(int par1){return 0x99FF99;}

    public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_)
    {
        return (WorldGenAbstractTree)(p_150567_1_.nextInt(10) == 0 ? this.worldGeneratorBigTree : (p_150567_1_.nextInt(2) == 0 ? new WorldGenShrub(BlockPlanks.EnumType.JUNGLE.getMetadata(), BlockPlanks.EnumType.OAK.getMetadata()) : (!this.field_150614_aC && p_150567_1_.nextInt(3) == 0 ? new WorldGenMegaJungle(false, 10, 20, BlockPlanks.EnumType.JUNGLE.getMetadata(), BlockPlanks.EnumType.JUNGLE.getMetadata()) : new WorldGenTrees(false, 4 + p_150567_1_.nextInt(7), BlockPlanks.EnumType.JUNGLE.getMetadata(), BlockPlanks.EnumType.JUNGLE.getMetadata(), true))));
    }

    public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_)
    {
        return p_76730_1_.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
    }

    public void decorate(World worldIn, Random p_180624_2_, BlockPos p_180624_3_)
    {
        super.decorate(worldIn, p_180624_2_, p_180624_3_);
        int i = p_180624_2_.nextInt(16) + 8;
        int j = p_180624_2_.nextInt(16) + 8;
        int height = worldIn.getHeight(p_180624_3_.add(i, 0, j)).getY() * 2; // could == 0, which crashes nextInt
        if (height < 1) height = 1;
        int k = p_180624_2_.nextInt(height);
        (new WorldGenMelon()).generate(worldIn, p_180624_2_, p_180624_3_.add(i, k, j));
        WorldGenVines worldgenvines = new WorldGenVines();

        for (j = 0; j < 50; ++j)
        {
            k = p_180624_2_.nextInt(16) + 8;
            boolean flag = true;
            int l = p_180624_2_.nextInt(16) + 8;
            worldgenvines.generate(worldIn, p_180624_2_, p_180624_3_.add(k, 128, l));
        }
    }
}