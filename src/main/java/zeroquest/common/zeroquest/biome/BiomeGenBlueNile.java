package common.zeroquest.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import common.zeroquest.ModBiomes;
import common.zeroquest.ModBlocks;
import common.zeroquest.ModLiquids;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenBlueNile extends BiomeGenBase
{
    private static final WorldGenTaiga1 field_150639_aC = new WorldGenTaiga1();
    private static final WorldGenTaiga2 field_150640_aD = new WorldGenTaiga2(false);
    private static final WorldGenMegaPineTree field_150641_aE = new WorldGenMegaPineTree(false, false);
    private static final WorldGenMegaPineTree field_150642_aF = new WorldGenMegaPineTree(false, true);
    private static final WorldGenBlockBlob field_150643_aG = new WorldGenBlockBlob(ModBlocks.nileCobblestone, 0);
    private int field_150644_aH;
    private static final String __OBFID = "CL_00000186";

    public BiomeGenBlueNile(int p_i45385_1_, int p_i45385_2_)
    {
        super(p_i45385_1_);
        this.field_150644_aH = p_i45385_2_;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityZertum.class, 8, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 100, 4, 4));
        this.theBiomeDecorator.treesPerChunk = 10;

        if (p_i45385_2_ != 1 && p_i45385_2_ != 2)
        {
            this.theBiomeDecorator.grassPerChunk = 1;
            this.theBiomeDecorator.mushroomsPerChunk = 1;
        }
        else
        {
            this.theBiomeDecorator.grassPerChunk = 7;
            this.theBiomeDecorator.deadBushPerChunk = 1;
            this.theBiomeDecorator.mushroomsPerChunk = 3;
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int par1, int par2, int par3){return 0x0099FF;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeFoliageColor(int par1, int par2, int par3){return 0x0099FF;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1){return 0x9999FF;}

    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return (WorldGenAbstractTree)((this.field_150644_aH == 1 || this.field_150644_aH == 2) && p_150567_1_.nextInt(3) == 0 ? (this.field_150644_aH != 2 && p_150567_1_.nextInt(13) != 0 ? field_150641_aE : field_150642_aF) : (p_150567_1_.nextInt(3) == 0 ? field_150639_aC : field_150640_aD));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_)
    {
        return p_76730_1_.nextInt(5) > 0 ? new WorldGenTallGrass(Blocks.tallgrass, 2) : new WorldGenTallGrass(Blocks.tallgrass, 1);
    }

    public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        int k;
        int l;
        int i1;
        int j1;

        if (this.field_150644_aH == 1 || this.field_150644_aH == 2)
        {
            k = p_76728_2_.nextInt(3);

            for (l = 0; l < k; ++l)
            {
                i1 = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
                j1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
                int k1 = p_76728_1_.getHeightValue(i1, j1);
                field_150643_aG.generate(p_76728_1_, p_76728_2_, i1, k1, j1);
            }
        }

        genTallFlowers.func_150548_a(3);

        for (k = 0; k < 7; ++k)
        {
            l = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            i1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            j1 = p_76728_2_.nextInt(p_76728_1_.getHeightValue(l, i1) + 32);
            genTallFlowers.generate(p_76728_1_, p_76728_2_, l, j1, i1);
        }
        
    	WorldGenFlowers blueFlowerGenerator = new WorldGenFlowers(ModBlocks.nileBlueFlower);
    	
        boolean doGen = TerrainGen.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_, FLOWERS);
        for (int j = 0; doGen && j < 30; ++j)
        {
            int k2 = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            int l2 = p_76728_2_.nextInt(128);
            int i2 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            blueFlowerGenerator.generate(p_76728_1_, p_76728_2_, k2, l2, i2);
        }

        super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
    }

    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        if (this.field_150644_aH == 1 || this.field_150644_aH == 2)
        {
            this.topBlock = Blocks.grass;
            this.field_150604_aj = 0;
            this.fillerBlock = Blocks.dirt;

            if (p_150573_7_ > 1.75D)
            {
                this.topBlock = Blocks.dirt;
                this.field_150604_aj = 1;
            }
            else if (p_150573_7_ > -0.95D)
            {
                this.topBlock = Blocks.dirt;
                this.field_150604_aj = 2;
            }
        }
        
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
                }
            }
        }

    /**
     * Creates a mutated version of the biome and places it into the biomeList with an index equal to the original plus
     * 128
     */
    public BiomeGenBase createMutation()
    {
        return this.biomeID == ModBiomes.blueMegaTaiga.biomeID ? (new BiomeGenBlueNile(this.biomeID + 128, 2)).func_150557_a(5858897, true).setBiomeName("Mega Spruce Blue Taiga").func_76733_a(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(new BiomeGenBase.Height(this.rootHeight, this.heightVariation)) : super.createMutation();
    }
	
}