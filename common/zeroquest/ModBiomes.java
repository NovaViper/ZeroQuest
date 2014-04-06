package common.zeroquest;

import common.zeroquest.biome.BiomeGenBioZone;
import common.zeroquest.biome.BiomeGenBlueNile;
import common.zeroquest.biome.BiomeGenDarkLand;
import common.zeroquest.biome.BiomeGenDestroZone;
import common.zeroquest.biome.BiomeGenPinkZone;
import common.zeroquest.biome.BiomeGenRedSeed;

import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.*;


public class ModBiomes 
{
	//Put biomes in GenLayerBiomesZeroQuest/WorldChunkManager//
	public static BiomeGenBase bioZone;
	public static BiomeGenBase redSeed;
	public static BiomeGenBase blueNile;
	public static BiomeGenBase darkWasteland;
	public static BiomeGenBase pinkZone;
	public static BiomeGenBase destroZone;
	
	public static void loadBiomes() {
		//But Biomes in GenLayerBiomesZeroQuest
			bioZone = new BiomeGenBioZone(150).setBiomeName("Bio Zone").setMinMaxHeight(0.3F, 1.0F).setTemperatureRainfall(0.8F, 0.4F);
	       GameRegistry.addBiome(bioZone);
	       
	       redSeed = new BiomeGenRedSeed(151).setBiomeName("Red Seed").setMinMaxHeight(0.1F, 0.4F).setTemperatureRainfall(0.8F, 0.4F);
	       GameRegistry.addBiome(redSeed);
	       
	       blueNile = new BiomeGenBlueNile(152).setBiomeName("Blue Nile").setMinMaxHeight(0.3F, 1.0F).setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
	       GameRegistry.addBiome(blueNile);
	       
	       darkWasteland = new BiomeGenDarkLand(153).setBiomeName("Dark Wasteland").setMinMaxHeight(0.2F, 0.4F).setTemperatureRainfall(0.8F, 0.7F);
	       GameRegistry.addBiome(darkWasteland);
	       
	       pinkZone = new BiomeGenPinkZone(154).setBiomeName("Pink Zone").setMinMaxHeight(0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.4F);
	       GameRegistry.addBiome(pinkZone);
	       
	       destroZone = new BiomeGenDestroZone(155).setBiomeName("Destro Zone").setMinMaxHeight(0.1F, 0.2F).setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
	       GameRegistry.addBiome(destroZone);

	}
	
	/*public static void loadBiomeDictionary() {
	       //BiomeManager.addSpawnBiome(bioZone);
 	       //BiomeManager.addSpawnBiome(redSeed);
	       //BiomeManager.addSpawnBiome(blueNile);
	       
	       BiomeDictionary.registerBiomeType(bioZone, Type.MOUNTAIN, Type.PLAINS);
	       BiomeDictionary.registerBiomeType(redSeed, Type.PLAINS);
	       BiomeDictionary.registerBiomeType(blueNile, Type.MOUNTAIN, Type.FROZEN);
	       BiomeDictionary.registerBiomeType(darkWasteland, Type.PLAINS, Type.WASTELAND);
	}*/
}
