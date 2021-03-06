package common.zeroquest.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import common.zeroquest.ModBlocks;
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

public class BiomeGenPinkZone extends BiomeGenBase {
    
    public Random par2Random;

	public BiomeGenPinkZone(int id) {
        super(id);
        //topBlock = (byte)ModBlocks.nileGrass.blockID;
        //fillerBlock = (byte)ModBlocks.nileDirt.blockID;
        this.theBiomeDecorator.generateLakes = true;
        this.theBiomeDecorator.treesPerChunk = 10;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityZertum.class, 90, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 5, 1, 5));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 5, 1, 5));
    }
    
    @SideOnly(Side.CLIENT)
public int getBiomeGrassColor(){return 0xff66ff;}
@SideOnly(Side.CLIENT)
public int getBiomeFoliageColor(){return 0xff66ff;}
@SideOnly(Side.CLIENT)
public int getSkyColorByTemp(float par1){return 0xcc66ff;}
    
	@Override
    public void decorate(World par1World, Random par2Random, int chunk_X, int chunk_Z){
    	super.decorate(par1World, par2Random, chunk_X, chunk_Z);
    	WorldGenFlowers blueFlowerGenerator = new WorldGenFlowers(ModBlocks.nilePinkFlower.blockID);
    	
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
