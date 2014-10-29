package common.zeroquest;

import common.zeroquest.biome.BiomeGenBioZone;
import common.zeroquest.biome.BiomeGenBlueTaiga;
import common.zeroquest.biome.BiomeGenDarkLand;
import common.zeroquest.biome.BiomeGenDestroZone;
import common.zeroquest.biome.BiomeGenNileBeach;
import common.zeroquest.biome.BiomeGenNileIsland;
import common.zeroquest.biome.BiomeGenNileJungle;
import common.zeroquest.biome.BiomeGenNileMesa;
import common.zeroquest.biome.BiomeGenNileMountains;
import common.zeroquest.biome.BiomeGenNileSavanna;
import common.zeroquest.biome.BiomeGenNileStoneBeach;
import common.zeroquest.biome.BiomeGenNileSwamp;
import common.zeroquest.biome.BiomeGenNiliOcean;
import common.zeroquest.biome.BiomeGenNiliRiver;
import common.zeroquest.biome.BiomeGenPinkZone;
import common.zeroquest.biome.BiomeGenRedSeed;
import common.zeroquest.biome.BiomeGenWalRockland;
import common.zeroquest.lib.Constants;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.biome.BiomeGenMesa;
import net.minecraft.world.biome.BiomeGenSavanna;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.*;

public class ModBiomes 
{
    protected static final BiomeGenBase.Height height_Default = new BiomeGenBase.Height(0.1F, 0.2F);
    protected static final BiomeGenBase.Height height_ShallowWaters = new BiomeGenBase.Height(-0.5F, 0.0F);
    protected static final BiomeGenBase.Height height_Oceans = new BiomeGenBase.Height(-1.0F, 0.1F);
    protected static final BiomeGenBase.Height height_DeepOceans = new BiomeGenBase.Height(-1.8F, 0.1F);
    protected static final BiomeGenBase.Height height_LowPlains = new BiomeGenBase.Height(0.125F, 0.05F);
    protected static final BiomeGenBase.Height height_MidPlains = new BiomeGenBase.Height(0.2F, 0.2F);
    protected static final BiomeGenBase.Height height_LowHills = new BiomeGenBase.Height(0.45F, 0.3F);
    protected static final BiomeGenBase.Height height_HighPlateaus = new BiomeGenBase.Height(1.5F, 0.025F);
    protected static final BiomeGenBase.Height height_MidHills = new BiomeGenBase.Height(1.0F, 0.5F);
    protected static final BiomeGenBase.Height height_Shores = new BiomeGenBase.Height(0.0F, 0.025F);
    protected static final BiomeGenBase.Height height_RockyWaters = new BiomeGenBase.Height(0.1F, 0.8F);
    protected static final BiomeGenBase.Height height_LowIslands = new BiomeGenBase.Height(0.2F, 0.3F);
    protected static final BiomeGenBase.Height height_PartiallySubmerged = new BiomeGenBase.Height(-0.2F, 0.1F);
    protected static final BiomeGenBase.Height height_High = new BiomeGenBase.Height(0.2F, 0.4F);
    protected static final BiomeGenBase.Height height_HighDefault2 = new BiomeGenBase.Height(0.1F, 0.2F);
	public static BiomeGenBase bioZone;
	public static BiomeGenBase redSeed;
	public static BiomeGenBase blueTaiga;
	public static BiomeGenBase blueTaigaHills;
	public static BiomeGenBase blueColdTaiga;
	public static BiomeGenBase blueColdTaigaHills;
	public static BiomeGenBase blueMegaTaiga;
	public static BiomeGenBase blueMegaTaigaHills;	
	public static BiomeGenBase pinkZone;
	public static BiomeGenBase destroZone;
	public static BiomeGenBase destroZoneHills;
	public static BiomeGenBase walRockland;
	public static BiomeGenBase darkWasteland;
	public static BiomeGenBase nileSavanna;
    public static BiomeGenBase nileSavannaPlateau;
    public static BiomeGenBase nileJungle; 
    public static BiomeGenBase nileJungleHills;
    public static BiomeGenBase nileJungleEdge;
    public static BiomeGenBase nileSwampland;
    public static BiomeGenBase niliOcean;
    public static BiomeGenBase niliFrozenOcean;
    public static BiomeGenBase niliDeepOcean;
    public static BiomeGenBase niliRiver;
    public static BiomeGenBase niliFrozenRiver;
    public static BiomeGenBase nileBeach;
    public static BiomeGenBase nileColdBeach;
    public static BiomeGenBase nileStoneBeach;
    public static BiomeGenBase nileIsland;
    public static BiomeGenBase nileIslandShore;
    public static BiomeGenBase nileMesa;
    public static BiomeGenBase nileMesaPlateau_F;
    public static BiomeGenBase nileMesaPlateau;
    public static BiomeGenBase nileMountains;
    public static BiomeGenBase nileMountainsEdge;
    public static BiomeGenBase nileMountainsPlus;
	
	public static void loadBiomes() {
		//Put biomes in GenLayerBiomesZeroQuest/WorldChunkManager//
		   bioZone = new BiomeGenBioZone(110).setBiomeName("Bio Zone").setHeight(height_MidHills).setTemperatureRainfall(0.8F, 0.4F);	       
	       redSeed = new BiomeGenRedSeed(111).setBiomeName("Red Seed").setHeight(height_LowPlains).setTemperatureRainfall(0.8F, 0.4F);	       
	       blueTaiga = new BiomeGenBlueTaiga(112, 0).setBiomeName("Blue Taiga").func_76733_a(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(height_MidPlains);
	       blueTaigaHills = new BiomeGenBlueTaiga(113, 0).setBiomeName("Blue Taiga Hills").func_76733_a(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(height_LowHills);
	       blueColdTaiga = new BiomeGenBlueTaiga(114, 0).setBiomeName("Cold Blue Taiga").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(-0.5F, 0.4F).setHeight(height_MidPlains).func_150563_c(16777215);
	       blueColdTaigaHills = new BiomeGenBlueTaiga(115, 0).setBiomeName("Cold Blue Taiga Hills").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(-0.5F, 0.4F).setHeight(height_LowHills).func_150563_c(16777215);
	       blueMegaTaiga = new BiomeGenBlueTaiga(116, 1).setBiomeName("Mega Blue Taiga").func_76733_a(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_MidPlains);
	       blueMegaTaigaHills = new BiomeGenBlueTaiga(117, 1).setBiomeName("Mega Blue Taiga Hills").func_76733_a(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_LowHills);
	       pinkZone = new BiomeGenPinkZone(118).setBiomeName("Pink Zone").setHeight(height_HighDefault2).setTemperatureRainfall(0.8F, 0.4F);
	       destroZone = new BiomeGenDestroZone(119).setBiomeName("Destro Zone").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
	       destroZoneHills = new BiomeGenDestroZone(120).setBiomeName("Destro Zone Hills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setHeight(height_LowHills);
	       walRockland = new BiomeGenWalRockland(121).setBiomeName("Wal Rocklands").setHeight(height_Default).setDisableRain().setTemperatureRainfall(1.0F, 0F);
	       nileSavanna = new BiomeGenNileSavanna(122).setBiomeName("Nile Savanna").setHeight(height_LowPlains).setTemperatureRainfall(1.2F, 0.0F).setDisableRain().setColor(0x12431967);
	       nileSavannaPlateau = new BiomeGenNileSavanna(123).setBiomeName("Nile Savanna Plateau").setHeight(height_HighPlateaus).setTemperatureRainfall(1.0F, 0.0F).setDisableRain().setColor(0x10984804);
	       nileJungle = new BiomeGenNileJungle(124, false).setColor(5470985).setBiomeName("Nile Jungle").func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.9F);
	       nileJungleHills = new BiomeGenNileJungle(125, false).setColor(0x33CC33).setBiomeName("Nile Jungle Hills").func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.9F).setHeight(height_LowHills);
	       nileJungleEdge = new BiomeGenNileJungle(126, true).setColor(0x5CD65C).setBiomeName("Nile Jungle Edge").func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.8F);
	       nileSwampland = new BiomeGenNileSwamp(127).setBiomeName("Nile Swampland").func_76733_a(9154376).setHeight(height_PartiallySubmerged).setTemperatureRainfall(0.8F, 0.9F);
	       //Waters
	       niliOcean = new BiomeGenNiliOcean(128).setColor(112).setBiomeName("Nili Ocean").setHeight(height_Oceans);
	       niliFrozenOcean = new BiomeGenNiliOcean(129).setColor(9474208).setBiomeName("Frozen Nili Ocean").setEnableSnow().setHeight(height_Oceans).setTemperatureRainfall(0.0F, 0.5F);
	       niliDeepOcean = new BiomeGenNiliOcean(130).setColor(48).setBiomeName("Deep Ocean").setHeight(height_DeepOceans);
	       niliRiver = new BiomeGenNiliRiver(131).setColor(255).setBiomeName("Nili River").setHeight(height_ShallowWaters);
	       niliFrozenRiver = new BiomeGenNiliRiver(132).setColor(10526975).setBiomeName("FrozenRiver").setEnableSnow().setHeight(height_ShallowWaters).setTemperatureRainfall(0.0F, 0.5F);
	       //Shores
	       nileBeach = new BiomeGenNileBeach(133).setColor(16440917).setBiomeName("Beach").setTemperatureRainfall(0.8F, 0.4F).setHeight(height_Shores);
	       nileColdBeach = new BiomeGenNileBeach(134).setColor(16445632).setBiomeName("Cold Nile Beach").setTemperatureRainfall(0.05F, 0.3F).setHeight(height_Shores).setEnableSnow();
	       nileStoneBeach = new BiomeGenNileStoneBeach(135).setColor(10658436).setBiomeName("Nile Stone Beach").setTemperatureRainfall(0.2F, 0.3F).setHeight(height_RockyWaters);
	       //Island
	       nileIsland = new BiomeGenNileIsland(136).setColor(16711935).setBiomeName("Nile Island").setTemperatureRainfall(0.9F, 1.0F).setHeight(height_LowIslands);
	       nileIslandShore = new BiomeGenNileIsland(137).setColor(10486015).setBiomeName("NileIslandShore").setTemperatureRainfall(0.9F, 1.0F).setHeight(height_Shores);

	       nileMesa = new BiomeGenNileMesa(138, false, false).setColor(14238997).setBiomeName("Nile Mesa");
	       nileMesaPlateau_F = new BiomeGenNileMesa(139, false, true).setColor(11573093).setBiomeName("Mesa Plateau F").setHeight(height_HighPlateaus);
	       nileMesaPlateau = new BiomeGenNileMesa(140, false, false).setColor(13274213).setBiomeName("Mesa Plateau").setHeight(height_HighPlateaus);
	       nileMountains = new BiomeGenNileMountains(141, false).setColor(6316128).setBiomeName("Nile Mountain").setHeight(height_MidHills).setTemperatureRainfall(0.2F, 0.3F);
	       nileMountainsEdge = new BiomeGenNileMountains(142, true).setColor(7501978).setBiomeName("Nile Mountain Edge").setHeight(height_MidHills.attenuate()).setTemperatureRainfall(0.2F, 0.3F);
	       nileMountainsPlus = new BiomeGenNileMountains(143, true).setColor(5271632).setBiomeName("Nile Mountain+").setHeight(height_MidHills).setTemperatureRainfall(0.2F, 0.3F);
	       BiomeManager.addSpawnBiome(bioZone);
	       BiomeManager.addSpawnBiome(redSeed);
	       BiomeManager.addSpawnBiome(blueTaiga);
	       BiomeManager.addSpawnBiome(blueTaigaHills);
	       BiomeManager.addSpawnBiome(blueColdTaiga);
	       BiomeManager.addSpawnBiome(blueMegaTaiga);
	       BiomeManager.addSpawnBiome(blueMegaTaigaHills);
	       BiomeManager.addSpawnBiome(pinkZone);
	       BiomeManager.addVillageBiome(destroZone, true);
	       BiomeManager.addSpawnBiome(destroZoneHills);
	       BiomeManager.addVillageBiome(walRockland, true);
	       BiomeManager.addVillageBiome(nileSavanna, true);
	       BiomeManager.addSpawnBiome(nileSavannaPlateau);
	       BiomeManager.addSpawnBiome(nileJungle);
	       BiomeManager.addSpawnBiome(nileJungleHills);
	       BiomeManager.addSpawnBiome(nileJungleEdge);
	       BiomeManager.addSpawnBiome(nileSwampland);
	       BiomeManager.oceanBiomes.add(niliOcean);
	       BiomeManager.oceanBiomes.add(niliFrozenOcean);
	       BiomeManager.oceanBiomes.add(niliDeepOcean);
	}
	
	public static void loadDarkBiomes(){    	    
		darkWasteland = new BiomeGenDarkLand(100).setBiomeName("Darkax").setHeight(height_MidHills).setTemperatureRainfall(2.0F, 0.0F);
	    BiomeManager.addSpawnBiome(darkWasteland);
	}
}
