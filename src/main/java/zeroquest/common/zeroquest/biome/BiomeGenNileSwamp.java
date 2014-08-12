package common.zeroquest.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import common.zeroquest.ModBlocks;
import common.zeroquest.ModLiquids;
import common.zeroquest.entity.EntityZertum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityZertum.class, 20, 3, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 100, 4, 4));
        this.flowers.clear();
        this.addFlower(Blocks.red_flower, 1, 10);
        this.addFlower(ModBlocks.nilePinkFlower, 1, 20);
    }

    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return this.worldGeneratorSwamp;
    }

    public String func_150572_a(Random p_150572_1_, int p_150572_2_, int p_150572_3_, int p_150572_4_)
    {
        return BlockFlower.field_149859_a[1];
    }
    
    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        boolean flag = true;
        Block block = this.topBlock;
        byte b0 = (byte)(this.field_150604_aj & 255);
        Block block1 = this.fillerBlock;
        int k = -1;
        int l = (int)(p_150573_7_ / 3.0D + 3.0D + p_150573_2_.nextDouble() * 0.25D);
        int i1 = p_150573_5_ & 15;
        int j1 = p_150573_6_ & 15;
        int k1 = p_150573_3_.length / 256;

        for (int l1 = 255; l1 >= 0; --l1)
        {
            int i2 = (j1 * 16 + i1) * k1 + l1;

            if (l1 <= 0 + p_150573_2_.nextInt(5))
            {
                p_150573_3_[i2] = Blocks.bedrock;
            }
            else
            {
                Block block2 = p_150573_3_[i2];

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
                                if (this.getFloatTemperature(p_150573_5_, l1, p_150573_6_) < 0.15F)
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
                                p_150573_3_[i2] = block;
                                p_150573_4_[i2] = b0;
                            }
                            else if (l1 < 56 - l)
                            {
                                block = null;
                                block1 = ModBlocks.nileStone;
                                p_150573_3_[i2] = Blocks.gravel;
                            }
                            else
                            {
                                p_150573_3_[i2] = block1;
                            }
                        }
                        else if (k > 0)
                        {
                            --k;
                            p_150573_3_[i2] = block1;

                            if (k == 0 && block1 == Blocks.sand)
                            {
                                k = p_150573_2_.nextInt(4) + Math.max(0, l1 - 63);
                                block1 = Blocks.sandstone;
                            }
                        }
                    }
                }
                else
                {
                    k = -1;
                }
    	
        double d1 = plantNoise.func_151601_a((double)p_150573_5_ * 0.25D, (double)p_150573_6_ * 0.25D);

        if (d1 > 0.0D)
        {
            int k2 = p_150573_5_ & 15;
            int l2 = p_150573_6_ & 15;
            int i3= p_150573_3_.length / 256;

            for (int j2 = 255; j1 >= 0; --j1)
            {
                int k3 = (l * 16 + k) * i1 + j1;

                if (p_150573_3_[k1] == null || p_150573_3_[k1].getMaterial() != Material.air)
                {
                    if (j1 == 62 && p_150573_3_[k1] != ModLiquids.niliBlock)
                    {
                        p_150573_3_[k1] = ModLiquids.niliBlock;

                        if (d1 < 0.12D)
                        {
                            p_150573_3_[k1 + 1] = Blocks.waterlily;
                        }
                    }

                    break;
                }
            }
        }

        this.genBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
        }        
    }
}

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        double d0 = plantNoise.func_151601_a((double)p_150558_1_ * 0.0225D, (double)p_150558_3_ * 0.0225D);
        return d0 < -0.1D ? 5011004 : 6975545;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_)
    {
        return 0x333300;
    }
}