package common.zeroquest.biome;

import java.util.Random;

import common.zeroquest.ModBlocks;
import common.zeroquest.ModLiquids;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenNileMountains extends BiomeGenBase
{
    private WorldGenerator theWorldGenerator;
    private WorldGenTaiga2 field_150634_aD;
    private int field_150635_aE;
    private int field_150636_aF;
    private int field_150637_aG;
    private int field_150638_aH;
    private static final String __OBFID = "CL_00000168";

    public BiomeGenNileMountains(int p_i45373_1_, boolean p_i45373_2_)
    {
        super(p_i45373_1_);
        this.theWorldGenerator = new WorldGenMinable(Blocks.monster_egg, 8);
        this.field_150634_aD = new WorldGenTaiga2(false);
        this.field_150635_aE = 0;
        this.field_150636_aF = 1;
        this.field_150637_aG = 2;
        this.field_150638_aH = this.field_150635_aE;
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 100, 4, 4));

        if (p_i45373_2_)
        {
            this.theBiomeDecorator.treesPerChunk = 3;
            this.field_150638_aH = this.field_150636_aF;
        }
    }

    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return (WorldGenAbstractTree)(p_150567_1_.nextInt(3) > 0 ? this.field_150634_aD : super.func_150567_a(p_150567_1_));
    }

    public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
        int k = 3 + p_76728_2_.nextInt(6);
        int l;
        int i1;
        int j1;

        for (l = 0; l < k; ++l)
        {
            i1 = p_76728_3_ + p_76728_2_.nextInt(16);
            j1 = p_76728_2_.nextInt(28) + 4;
            int k1 = p_76728_4_ + p_76728_2_.nextInt(16);

            if (p_76728_1_.getBlock(i1, j1, k1).isReplaceableOreGen(p_76728_1_, i1, j1, k1, Blocks.stone))
            {
                p_76728_1_.setBlock(i1, j1, k1, Blocks.emerald_ore, 0, 2);
            }
        }

        for (k = 0; k < 7; ++k)
        {
            l = p_76728_3_ + p_76728_2_.nextInt(16);
            i1 = p_76728_2_.nextInt(64);
            j1 = p_76728_4_ + p_76728_2_.nextInt(16);
            this.theWorldGenerator.generate(p_76728_1_, p_76728_2_, l, i1, j1);
        }
    }
	
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int par1, int par2, int par3){return 0x99FFCC;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeFoliageColor(int par1, int par2, int par3){return 0x99FFCC;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1){return 0x99FF33;}

    /**
     * this creates a mutation specific to Hills biomes
     */
    public BiomeGenNileMountains mutateHills(BiomeGenBase p_150633_1_)
    {
        this.field_150638_aH = this.field_150637_aG;
        this.func_150557_a(p_150633_1_.color, true);
        this.setBiomeName(p_150633_1_.biomeName + " M");
        this.setHeight(new BiomeGenBase.Height(p_150633_1_.rootHeight, p_150633_1_.heightVariation));
        this.setTemperatureRainfall(p_150633_1_.temperature, p_150633_1_.rainfall);
        return this;
    }

    /**
     * Creates a mutated version of the biome and places it into the biomeList with an index equal to the original plus
     * 128
     */
    public BiomeGenBase createMutation()
    {
        return (new BiomeGenNileMountains(this.biomeID + 128, false)).mutateHills(this);
    }
}
