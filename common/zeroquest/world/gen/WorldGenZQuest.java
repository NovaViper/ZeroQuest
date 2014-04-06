package common.zeroquest.world.gen;

import java.util.Random;

import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraftforge.common.BiomeDictionary.*;

public class WorldGenZQuest implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
       
		switch(world.provider.dimensionId){
			case 0:
				generateNether(world, random, chunkX*16, chunkZ*16);
			case -1:
				generateSurface(world, random, chunkX*16, chunkZ*16);
			case 1:
				generateEnd(world, random, chunkX*16, chunkZ*16);
			case ZeroQuest.NillaxID:
				generateNillaxDimension(world, random, chunkX*16, chunkZ*16);
				
		
		}

	}

	private void generateSurface(World world, Random random, int x, int y) {

	}
	
	private void generateNether(World world, Random random, int x, int y) {
		
	}
	private void generateEnd(World world, Random random, int x, int y) {
		
	}
	
	private void generateNillaxDimension(World world, Random random, int x, int y) {
		this.addOreSpawn(ModBlocks.nileGrainOre, world, random, x, y, 20, 20, 3+random.nextInt(5), 80, 3, 90);
		this.addOreSpawn(ModBlocks.nileCoalOre, world, random, x, y, 16, 16, 4+random.nextInt(5), 100, 0, 97);
		/*for (int i = 0; i < 50; i++)
		{
			int xCoord = x + random.nextInt(16);
			int yCoord = random.nextInt(128);
			int zCoord = y + random.nextInt(16);
			new WorldGenMinableZQuest(ModBlocks.nileGrainOre.blockID, 15).generate(world, random, xCoord, yCoord, zCoord);
		}
		for (int i = 0; i < 50; i++)
		{
			int xCoord = x + random.nextInt(16);
			int yCoord = random.nextInt(128);
			int zCoord = y + random.nextInt(16);
			new WorldGenMinableZQuest(ModBlocks.nileGrainOre.blockID, 15).generate(world, random, xCoord, yCoord, zCoord);
		}*/

	}
	
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY){
		for(int i = 0; i < chancesToSpawn; i++){
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = blockZPos + random.nextInt(maxZ);
			(new WorldGenMinableZQuest(block.blockID, maxVeinSize)).generate(world, random, posX, posY, posZ);
			
		}
	}
}
