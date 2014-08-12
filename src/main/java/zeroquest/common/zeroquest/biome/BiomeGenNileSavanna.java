package common.zeroquest.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraftforge.event.terraingen.TerrainGen;
import common.zeroquest.ModBlocks;
import common.zeroquest.ModLiquids;
import common.zeroquest.entity.EntityKortor;
import common.zeroquest.entity.EntityZertum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityZertum.class, 100, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityKortor.class, 100, 2, 3));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPig.class, 5, 1, 5)); 
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
       	this.waterColorMultiplier = 0x0099cc;
    }
    
@SideOnly(Side.CLIENT)
@Override
public int getSkyColorByTemp(float par1){return 0x6699FF;}

    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return (WorldGenAbstractTree)(p_150567_1_.nextInt(5) > 0 ? field_150627_aC : this.worldGeneratorTrees);
    }

    /**
     * Creates a mutated version of the biome and places it into the biomeList with an index equal to the original plus
     * 128
     */
    public BiomeGenBase createMutation()
    {
        BiomeGenNileSavanna.Mutated mutated = new BiomeGenNileSavanna.Mutated(this.biomeID + 128, this);
        mutated.temperature = (this.temperature + 1.0F) * 0.5F;
        mutated.rootHeight = this.rootHeight * 0.5F + 0.3F;
        mutated.heightVariation = this.heightVariation * 0.5F + 1.2F;
        return mutated;
    }

    public void decorate(World par1World, Random par2Random, int chunk_X, int chunk_Z)
    {
        genTallFlowers.func_150548_a(2);

        for (int k = 0; k < 7; ++k)
        {
            int l = chunk_X + par2Random.nextInt(16) + 8;
            int i1 = chunk_Z + par2Random.nextInt(16) + 8;
            int j1 = par2Random.nextInt(par1World.getHeightValue(l, i1) + 32);
            genTallFlowers.generate(par1World, par2Random, l, j1, i1);
        }

        super.decorate(par1World, par2Random, chunk_X, chunk_Z);
    	WorldGenFlowers blueFlowerGenerator = new WorldGenFlowers(ModBlocks.nileBlackFlower);
    	
        boolean doGen = TerrainGen.decorate(par1World, par2Random, chunk_X, chunk_Z, FLOWERS);
        for (int j = 0; doGen && j < 30; ++j)
        {
            int k = chunk_X + par2Random.nextInt(16) + 8;
            int l = par2Random.nextInt(128);
            int i1 = chunk_Z + par2Random.nextInt(16) + 8;
            blueFlowerGenerator.generate(par1World, par2Random, k, l, i1);
        }
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

        	public void genTerrainBlocks(World world, Random random, Block[] blocks, byte[] bytes, int int1, int int2, double d)
            {
                boolean flag = true;
                Block block = this.topBlock;
                byte b0 = (byte)(this.field_150604_aj & 255);
                Block block1 = this.fillerBlock;
                int k = -1;
                int l = (int)(d / 3.0D + 3.0D + random.nextDouble() * 0.25D);
                int i1 = int1 & 15;
                int j1 = int2 & 15;
                int k1 = blocks.length / 256;

                for (int l1 = 255; l1 >= 0; --l1)
                {
                    int i2 = (j1 * 16 + i1) * k1 + l1;

                    if (l1 <= 0 + random.nextInt(5))
                    {
                        blocks[i2] = Blocks.bedrock;
                    }
                    else
                    {
                        Block block2 = blocks[i2];

                        if (block2 != null && block2.getMaterial() != Material.air)
                        {
                            if (block2 == ModBlocks.nileStone)
                            {
                                if (k == -1)
                                {
                                    if (l <= 0)
                                    {
                                        block = null;
                                        b0 = 0;
                                        block1 = ModBlocks.nileStone;
                                    }
                                    else if (l1 >= 59 && l1 <= 64)
                                    {
                                        block = this.topBlock;
                                        b0 = (byte)(this.field_150604_aj & 255);
                                        block1 = this.fillerBlock;
                                    }

                                    if (l1 < 63 && (block == null || block.getMaterial() == Material.air))
                                    {
                                        if (this.getFloatTemperature(int1, l1, int2) < 0.15F)
                                        {
                                            block = Blocks.ice;
                                            b0 = 0;
                                        }
                                        else
                                        {
                                            block = ModLiquids.niliBlock;
                                            b0 = 0;
                                        }
                                    }

                                    k = l;

                                    if (l1 >= 62)
                                    {
                                        blocks[i2] = block;
                                        bytes[i2] = b0;
                                    }
                                    else if (l1 < 56 - l)
                                    {
                                        block = null;
                                        block1 = ModBlocks.nileStone;
                                        blocks[i2] = Blocks.gravel;
                                    }
                                    else
                                    {
                                        blocks[i2] = block1;
                                    }
                                }
                                else if (k > 0)
                                {
                                    --k;
                                    blocks[i2] = block1;

                                    if (k == 0 && block1 == Blocks.sand)
                                    {
                                        k = random.nextInt(4) + Math.max(0, l1 - 63);
                                        block1 = Blocks.sandstone;
                                    }
                                }
                            }
                        }
                        else
                        {
                            k = -1;
                        }
                    }
                }
            }

            public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
            {
                this.theBiomeDecorator.decorateChunk(p_76728_1_, p_76728_2_, this, p_76728_3_, p_76728_4_);
            }
        }
    
	@Override
	public void genTerrainBlocks(World world, Random random, Block[] blocks, byte[] bytes, int int1, int int2, double d)
    {
        boolean flag = true;
        Block block = this.topBlock;
        byte b0 = (byte)(this.field_150604_aj & 255);
        Block block1 = this.fillerBlock;
        int k = -1;
        int l = (int)(d / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int i1 = int1 & 15;
        int j1 = int2 & 15;
        int k1 = blocks.length / 256;

        for (int l1 = 255; l1 >= 0; --l1)
        {
            int i2 = (j1 * 16 + i1) * k1 + l1;

            if (l1 <= 0 + random.nextInt(5))
            {
                blocks[i2] = Blocks.bedrock;
            }
            else
            {
                Block block2 = blocks[i2];

                if (block2 != null && block2.getMaterial() != Material.air)
                {
                    if (block2 == ModBlocks.nileStone)
                    {
                        if (k == -1)
                        {
                            if (l <= 0)
                            {
                                block = null;
                                b0 = 0;
                                block1 = ModBlocks.nileStone;
                            }
                            else if (l1 >= 59 && l1 <= 64)
                            {
                                block = this.topBlock;
                                b0 = (byte)(this.field_150604_aj & 255);
                                block1 = this.fillerBlock;
                            }

                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air))
                            {
                                if (this.getFloatTemperature(int1, l1, int2) < 0.15F)
                                {
                                    block = Blocks.ice;
                                    b0 = 0;
                                }
                                else
                                {
                                    block = ModLiquids.niliBlock;
                                    b0 = 0;
                                }
                            }

                            k = l;

                            if (l1 >= 62)
                            {
                                blocks[i2] = block;
                                bytes[i2] = b0;
                            }
                            else if (l1 < 56 - l)
                            {
                                block = null;
                                block1 = ModBlocks.nileStone;
                                blocks[i2] = Blocks.gravel;
                            }
                            else
                            {
                                blocks[i2] = block1;
                            }
                        }
                        else if (k > 0)
                        {
                            --k;
                            blocks[i2] = block1;

                            if (k == 0 && block1 == Blocks.sand)
                            {
                                k = random.nextInt(4) + Math.max(0, l1 - 63);
                                block1 = Blocks.sandstone;
                            }
                        }
                    }
                }
                else
                {
                    k = -1;
                }
            }
        }
    }
}