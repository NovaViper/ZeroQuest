package common.zeroquest.world.biome;

import java.util.Random;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import common.zeroquest.ModBlocks;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.lib.Constants;

public class BiomeGenRedSeed extends BiomeGenBase {
    
    public Random par2Random;
    protected boolean field_150628_aC;
    private static final String __OBFID = "CL_00000180";

	public BiomeGenRedSeed(int id) {
        super(id);
       	this.waterColorMultiplier = 0x003333;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 10;
        this.flowers.clear();
        this.addFlower(Blocks.red_flower.getDefaultState(), 4);
        this.addFlower(ModBlocks.nileBlackFlower.getDefaultState(), 90);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 5, 1, 5));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityCow.class, 5, 1, 5));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPig.class, 5, 1, 5)); 
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getModdedBiomeGrassColor(int par1){return 0xB20024;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getModdedBiomeFoliageColor(int par1){return 0xFF0033;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1){return 0x666600;}
    
    public void decorate(World worldIn, Random p_180624_2_, BlockPos p_180624_3_)
    {
        double d0 = field_180281_af.func_151601_a((double)(p_180624_3_.getX() + 8) / 200.0D, (double)(p_180624_3_.getZ() + 8) / 200.0D);
        int i;
        int j;
        int k;
        int l;

        if (d0 < -0.8D)
        {
            this.theBiomeDecorator.flowersPerChunk = 15;
            this.theBiomeDecorator.grassPerChunk = 5;
        }
        else
        {
            this.theBiomeDecorator.flowersPerChunk = 4;
            this.theBiomeDecorator.grassPerChunk = 10;
            DOUBLE_PLANT_GENERATOR.func_180710_a(BlockDoublePlant.EnumPlantType.GRASS);

            for (i = 0; i < 7; ++i)
            {
                j = p_180624_2_.nextInt(16) + 8;
                k = p_180624_2_.nextInt(16) + 8;
                l = p_180624_2_.nextInt(worldIn.getHorizon(p_180624_3_.add(j, 0, k)).getY() + 32);
                DOUBLE_PLANT_GENERATOR.generate(worldIn, p_180624_2_, p_180624_3_.add(j, l, k));
            }
        }

        if (this.field_150628_aC)
        {
            DOUBLE_PLANT_GENERATOR.func_180710_a(BlockDoublePlant.EnumPlantType.SUNFLOWER);

            for (i = 0; i < 10; ++i)
            {
                j = p_180624_2_.nextInt(16) + 8;
                k = p_180624_2_.nextInt(16) + 8;
                l = p_180624_2_.nextInt(worldIn.getHorizon(p_180624_3_.add(j, 0, k)).getY() + 32);
                DOUBLE_PLANT_GENERATOR.generate(worldIn, p_180624_2_, p_180624_3_.add(j, l, k));
            }
        }

        super.decorate(worldIn, p_180624_2_, p_180624_3_);
    }

    public BiomeGenBase createMutatedBiome(int p_180277_1_)
    {
    	BiomeGenRedSeed biomegenplains = new BiomeGenRedSeed(p_180277_1_);
        biomegenplains.setBiomeName("Sunflower Plains");
        biomegenplains.field_150628_aC = true;
        biomegenplains.setColor(9286496);
        biomegenplains.field_150609_ah = 14273354;
        return biomegenplains;
    }
}