package common.zeroquest.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import common.zeroquest.ModBlocks;
import common.zeroquest.ZeroQuest;
import common.zeroquest.lib.Constants;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenZQuest implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
       
		switch(world.provider.dimensionId){
			case -1:
				generateNether(world, random, chunkX*16, chunkZ*16);
			case 0:
				generateSurface(world, random, chunkX*16, chunkZ*16);
			case 1:
				generateEnd(world, random, chunkX*16, chunkZ*16);
			case ZeroQuest.NillaxID:
				generateNillaxDimension(world, random, chunkX*16, chunkZ*16);
			case ZeroQuest.DarkaxID:
				if(Constants.DEF_DARKLOAD == true){
				generateDarkaxDimension(world, random, chunkX*16, chunkZ*16);
				}
		}
	}

	private void generateSurface(World world, Random random, int x, int y) {
		this.addOreSpawn(ModBlocks.nileGrainOre, world, random, x, y, 20, 20, 3+random.nextInt(5), 80, 3, 90);
		
		if(Constants.DEF_DARKLOAD == true){
			this.addOreSpawn(ModBlocks.darkGrainOre, world, random, x, y, 16, 16, 2+random.nextInt(5), 60, 0, 97);
		}
	}
	
	private void generateNether(World world, Random random, int x, int y) {
		
	}
	private void generateEnd(World world, Random random, int x, int y) {
		
	}
	
	private void generateNillaxDimension(World world, Random random, int x, int y) {		
		this.addOreSpawn(ModBlocks.nileGrainOre, world, random, x, y, 20, 20, 3+random.nextInt(5), 80, 3, 90);
		this.addOreSpawn(ModBlocks.nileCoalOre, world, random, x, y, 16, 16, 4+random.nextInt(5), 100, 0, 97);
	}
	
	private void generateDarkaxDimension(World world, Random random, int x, int y){
		this.addOreSpawn(ModBlocks.darkGrainOre, world, random, x, y, 16, 16, 2+random.nextInt(5), 60, 0, 97);
	}
	
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
        assert maxY > minY : "The maximum Y must be greater than the Minimum Y";
        assert maxX > 0 && maxX <= 16 : "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
        assert minY > 0 : "addOreSpawn: The Minimum Y must be greater than 0";
        assert maxY < 256 && maxY > 0 : "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
        assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";
 
        int diffBtwnMinMaxY = maxY - minY;
        for (int x = 0; x < chancesToSpawn; x++)
        {
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(diffBtwnMinMaxY);
            int posZ = blockZPos + random.nextInt(maxZ);
            (new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
        }
	}
}