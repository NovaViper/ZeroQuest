package common.zeroquest;

import common.zeroquest.biome.BiomeGenBioZone;
import common.zeroquest.biome.BiomeGenBlueNile;
import common.zeroquest.biome.BiomeGenDarkLand;
import common.zeroquest.biome.BiomeGenDestroZone;
import common.zeroquest.biome.BiomeGenPinkZone;
import common.zeroquest.biome.BiomeGenRedSeed;
import common.zeroquest.biome.BiomeGenWalRockland;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;
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
	public static BiomeGenBase pinkZone;
	public static BiomeGenBase destroZone;
	public static BiomeGenBase darkWasteland;
	public static BiomeGenBase walRockland;
	
	public static void loadBiomes() {
		//But Biomes in GenLayerBiomesZeroQuest
			bioZone = new BiomeGenBioZone(140).setBiomeName("Bio Zone").setMinMaxHeight(0.3F, 1.0F).setTemperatureRainfall(0.8F, 0.4F);
	       GameRegistry.addBiome(bioZone);
	       
	       redSeed = new BiomeGenRedSeed(141).setBiomeName("Red Seed").setMinMaxHeight(0.1F, 0.4F).setTemperatureRainfall(0.8F, 0.4F);
	       GameRegistry.addBiome(redSeed);
	       
	       blueNile = new BiomeGenBlueNile(142).setBiomeName("Blue Nile").setMinMaxHeight(0.3F, 1.0F).setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
	       GameRegistry.addBiome(blueNile);
	       
	       pinkZone = new BiomeGenPinkZone(143).setBiomeName("Pink Zone").setMinMaxHeight(0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.4F);
	       GameRegistry.addBiome(pinkZone);
	       
	       destroZone = new BiomeGenDestroZone(144).setBiomeName("Destro Zone").setMinMaxHeight(0.1F, 0.2F).setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
	       GameRegistry.addBiome(destroZone);
	       
	       walRockland = new BiomeGenWalRockland(145).setBiomeName("Wal Rocklands").setDisableRain();
	       GameRegistry.addBiome(walRockland);

	}
	
	public static void loadDarkBiomes(){
	       darkWasteland = new BiomeGenDarkLand(160).setBiomeName("Dark Wasteland").setMinMaxHeight(0.1F, 0.4F).setTemperatureRainfall(0.8F, 0.7F);
	       GameRegistry.addBiome(darkWasteland);
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
