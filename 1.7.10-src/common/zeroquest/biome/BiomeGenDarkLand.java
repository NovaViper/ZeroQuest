package common.zeroquest.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraftforge.event.terraingen.TerrainGen;
import common.zeroquest.ModBlocks;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityKurr;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenDarkLand extends BiomeGenBase {
    
    public Random par2Random;

	public BiomeGenDarkLand(int id) {
        super(id);
       	this.waterColorMultiplier = 0x666600;
       	this.theBiomeDecorator.treesPerChunk = 1;
       	this.theBiomeDecorator.grassPerChunk = 1;
       	this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.generateLakes = true;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityDarkZertum.class, 100, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 60, 1, 5));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPigZombie.class, 20, 2, 3));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGhast.class, 20, 1, 2));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityKurr.class, 10, 2, 2));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int par1, int par2, int par3){return 0x660066;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeFoliageColor(int par1, int par2, int par3){return 0x660066;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1){return 0x333333;}
    
	@Override
    public void decorate(World par1World, Random par2Random, int chunk_X, int chunk_Z){
    	super.decorate(par1World, par2Random, chunk_X, chunk_Z);
    	WorldGenFlowers blueFlowerGenerator = new WorldGenFlowers(ModBlocks.nileBlackFlower);
    	
        boolean doGen = TerrainGen.decorate(par1World, par2Random, chunk_X, chunk_Z, FLOWERS);
        for (int j = 0; doGen && j < 10; ++j)
        {
            int k = chunk_X + par2Random.nextInt(16) + 8;
            int l = par2Random.nextInt(128);
            int i1 = chunk_Z + par2Random.nextInt(16) + 8;
            blueFlowerGenerator.generate(par1World, par2Random, k, l, i1);
        }
    }
}