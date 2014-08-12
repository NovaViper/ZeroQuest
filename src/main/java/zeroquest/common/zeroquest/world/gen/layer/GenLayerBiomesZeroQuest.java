package common.zeroquest.world.gen.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import common.zeroquest.ModBiomes;
import common.zeroquest.lib.Constants;


public class GenLayerBiomesZeroQuest extends GenLayer {

	protected BiomeGenBase[] allowedBiomes = {ModBiomes.bioZone, ModBiomes.blueTaiga, ModBiomes.blueTaigaHills, ModBiomes.blueColdTaiga, ModBiomes.blueColdTaigaHills, ModBiomes.blueMegaTaiga, ModBiomes.blueMegaTaigaHills, ModBiomes.redSeed, ModBiomes.pinkZone, ModBiomes.destroZone, ModBiomes.destroZoneHills, ModBiomes.walRockland, ModBiomes.nileSavanna, ModBiomes.nileSavannaPlateau, ModBiomes.nileJungle, ModBiomes.nileJungleHills, ModBiomes.nileJungleEdge, ModBiomes.nileSwampland, ModBiomes.nileMountains, ModBiomes.nileMountainsEdge, ModBiomes.nileMountainsPlus, ModBiomes.nileMesa, ModBiomes.nileMesaPlateau, ModBiomes.nileMesaPlateau_F};

	public GenLayerBiomesZeroQuest(long seed, GenLayer genlayer, WorldType worldType) {
		super(seed);
		this.parent = genlayer;
	}


	public GenLayerBiomesZeroQuest(long seed) {
		super(seed);
	}


	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width*depth);


		for (int dz=0; dz<depth; dz++)
		{
			for (int dx=0; dx<width; dx++)
			{
				this.initChunkSeed(dx+x, dz+z);
				dest[(dx+dz*width)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
			}
		}
		return dest;
	}
}