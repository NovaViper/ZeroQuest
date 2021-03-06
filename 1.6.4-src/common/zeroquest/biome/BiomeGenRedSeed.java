package common.zeroquest.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import common.zeroquest.ModBlocks;
import common.zeroquest.entity.EntityRedZertum;
import common.zeroquest.entity.EntityZertum;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraftforge.event.terraingen.TerrainGen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenRedSeed extends BiomeGenBase {
    
    public Random par2Random;

	public BiomeGenRedSeed(int id) {
        super(id);
        //topBlock = (byte)ModBlocks.nileGrass.blockID;
        //fillerBlock = (byte)ModBlocks.nileDirt.blockID;
       	this.waterColorMultiplier = 0x003333;
       	this.theBiomeDecorator.treesPerChunk = -999;
       	this.theBiomeDecorator.grassPerChunk = 3;
       	this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.generateLakes = true;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRedZertum.class, 100, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 5, 1, 5));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 5, 1, 5));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 5, 1, 5)); 
    }
    
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(){return 0xFF0033;}
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(){return 0xFF0033;}
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1){return 0x666600;}
    
	@Override
    public void decorate(World par1World, Random par2Random, int chunk_X, int chunk_Z){
    	super.decorate(par1World, par2Random, chunk_X, chunk_Z);
    	WorldGenFlowers blackFlowerGenerator = new WorldGenFlowers(ModBlocks.nileBlackFlower.blockID);
    	
        boolean doGen = TerrainGen.decorate(par1World, par2Random, chunk_X, chunk_Z, FLOWERS);
        for (int j = 0; doGen && j < 30; ++j)
        {
            int k = chunk_X + par2Random.nextInt(16) + 8;
            int l = par2Random.nextInt(128);
            int i1 = chunk_Z + par2Random.nextInt(16) + 8;
            blackFlowerGenerator.generate(par1World, par2Random, k, l, i1);
        }
    }
}
