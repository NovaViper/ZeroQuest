package net.novaviper.zeroquest.common.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.novaviper.zeroquest.ModBlocks;
import net.novaviper.zeroquest.ZeroQuest;
import net.novaviper.zeroquest.common.lib.Constants;
import net.novaviper.zeroquest.common.lib.IDs;

public class WorldGenZQuest implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		if (world.provider.getDimensionId() == -1) { // Nether
			generateNether(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (world.provider.getDimensionId() == 0) { // Overworld
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (world.provider.getDimensionId() == 1) { // End
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (world.provider.getDimensionId() == IDs.Nillax) { // Nillax
			generateNillaxDimension(world, random, chunkX * 16, chunkZ * 16);
		}
		else if (world.provider.getDimensionId() == IDs.Darkax) { // Darkax
			generateDarkaxDimension(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateSurface(World world, Random random, int chunkX, int chunkZ) {
		this.addOreSpawn(ModBlocks.nileGrainOre.getDefaultState(), world, random, chunkZ, chunkZ, 20, 20, 3 + random.nextInt(5), 3, 3, 90);
		this.addOreSpawn(ModBlocks.nileCoalOre.getDefaultState(), world, random, chunkX, chunkZ, 16, 16, 4 + random.nextInt(5), 3, 0, 97);

		if (Constants.DEF_DARKLOAD == true) {
			this.addOreSpawn(ModBlocks.darkGrainOre.getDefaultState(), world, random, chunkZ, chunkZ, 16, 16, 2 + random.nextInt(5), 1, 0, 97);
		}
	}

	private void generateNether(World world, Random random, int chunkX, int chunkZ) {

	}

	private void generateEnd(World world, Random random, int chunkX, int chunkZ) {

	}

	private void generateNillaxDimension(World world, Random random, int chunkX, int chunkZ) {
		this.addOreSpawn(ModBlocks.nileGrainOre.getDefaultState(), world, random, chunkX, chunkZ, 20, 20, 3 + random.nextInt(5), 7, 3, 90);
		this.addOreSpawn(ModBlocks.nileCoalOre.getDefaultState(), world, random, chunkX, chunkZ, 16, 16, 4 + random.nextInt(5), 7, 0, 97);
	}

	private void generateDarkaxDimension(World world, Random random, int chunkX, int chunkZ) {
		this.addOreSpawn(ModBlocks.darkGrainOre.getDefaultState(), world, random, chunkX, chunkZ, 16, 16, 2 + random.nextInt(5), 10, 0, 97);
	}

	//@formatter:off
	/**
	 * Adds an Ore Spawn to Minecraft. Simply register all Ores to spawn with this method in your Generation method in your IWorldGeneration extending Class
	 * 
	 * @param The Block to spawn
	 * @param The World to spawn in
	 * @param A Random object for retrieving random positions within the world to spawn the Block
	 * @param An int for passing the X-Coordinate for the Generation method
	 * @param An int for passing the Z-Coordinate for the Generation method
	 * @param An int for setting the maximum X-Coordinate values for spawning on the X-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum Z-Coordinate values for spawning on the Z-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum size of a vein
	 * @param An int for the Number of chances available for the Block to spawn per-chunk
	 * @param An int for the minimum Y-Coordinate height at which this block may spawn
	 * @param An int for the maximum Y-Coordinate height at which this block may spawn
	 */
	public void addOreSpawn(IBlockState block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY) {
		assert maxY > minY : "The maximum Y must be greater than the Minimum Y";
		assert maxX > 0 && maxX <= 16 : "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
		assert minY > 0 : "addOreSpawn: The Minimum Y must be greater than 0";
		assert maxY < 256 && maxY > 0 : "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
		assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";

		int diffBtwnMinMaxY = maxY - minY;
		for (int x = 0; x < chancesToSpawn; x++) {
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZPos + random.nextInt(maxZ);
			(new WorldGenMinable(block, maxVeinSize)).generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}
}