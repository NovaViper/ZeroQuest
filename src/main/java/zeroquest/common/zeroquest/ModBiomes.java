package common.zeroquest;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import common.zeroquest.world.biome.BiomeGenBioZone;
import common.zeroquest.world.biome.BiomeGenBlueTaiga;
import common.zeroquest.world.biome.BiomeGenDarkLand;
import common.zeroquest.world.biome.BiomeGenDestroZone;
import common.zeroquest.world.biome.BiomeGenNileJungle;
import common.zeroquest.world.biome.BiomeGenNileMesa;
import common.zeroquest.world.biome.BiomeGenNileMountains;
import common.zeroquest.world.biome.BiomeGenNileSavanna;
import common.zeroquest.world.biome.BiomeGenNileSwamp;
import common.zeroquest.world.biome.BiomeGenPinkZone;
import common.zeroquest.world.biome.BiomeGenRedSeed;
import common.zeroquest.world.biome.BiomeGenWalRockland;

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
    public static BiomeGenBase nileMesa;
    public static BiomeGenBase nileMesaPlateau_F;
    public static BiomeGenBase nileMesaPlateau;
    public static BiomeGenBase nileMountains;
    public static BiomeGenBase nileMountainsEdge;
    public static BiomeGenBase nileMountainsPlus;
	
	public static BiomeGenBase[] biomes = {bioZone, redSeed, blueTaiga, blueTaigaHills, blueColdTaiga, 
			blueColdTaigaHills, blueMegaTaiga, blueMegaTaigaHills, pinkZone, destroZone, destroZoneHills,
			walRockland, nileSavanna, nileSavannaPlateau, nileJungle, nileJungleHills, nileJungleEdge,
			nileSwampland, nileMesa, nileMesaPlateau_F, nileMesaPlateau, nileMountains, nileMountainsEdge,
			nileMountainsPlus};
    
	public static void loadBiomes() {
		//Put biomes in biomes list//
		   bioZone = new BiomeGenBioZone(110).setBiomeName("Bio Zone").setHeight(height_MidHills).setTemperatureRainfall(0.8F, 0.4F);	       
	       redSeed = new BiomeGenRedSeed(111).setBiomeName("Red Seed").setHeight(height_LowPlains).setTemperatureRainfall(0.8F, 0.4F);	       
	       blueTaiga = new BiomeGenBlueTaiga(112, 0).setBiomeName("Blue Taiga").setFillerBlockMetadata(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(height_MidPlains);
	       blueTaigaHills = new BiomeGenBlueTaiga(113, 0).setBiomeName("Blue Taiga Hills").setFillerBlockMetadata(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(height_LowHills);
	       blueColdTaiga = new BiomeGenBlueTaiga(114, 0).setBiomeName("Cold Blue Taiga").setFillerBlockMetadata(5159473).setEnableSnow().setTemperatureRainfall(-0.5F, 0.4F).setHeight(height_MidPlains).func_150563_c(16777215);
	       blueColdTaigaHills = new BiomeGenBlueTaiga(115, 0).setBiomeName("Cold Blue Taiga Hills").setFillerBlockMetadata(5159473).setEnableSnow().setTemperatureRainfall(-0.5F, 0.4F).setHeight(height_LowHills).func_150563_c(16777215);
	       blueMegaTaiga = new BiomeGenBlueTaiga(116, 1).setBiomeName("Mega Blue Taiga").setFillerBlockMetadata(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_MidPlains);
	       blueMegaTaigaHills = new BiomeGenBlueTaiga(117, 1).setBiomeName("Mega Blue Taiga Hills").setFillerBlockMetadata(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_LowHills);
	       pinkZone = new BiomeGenPinkZone(118).setBiomeName("Pink Zone").setHeight(height_HighDefault2).setTemperatureRainfall(0.8F, 0.4F);
	       destroZone = new BiomeGenDestroZone(119).setBiomeName("Destro Zone").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
	       destroZoneHills = new BiomeGenDestroZone(120).setBiomeName("Destro Zone Hills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setHeight(height_LowHills);
	       walRockland = new BiomeGenWalRockland(121).setBiomeName("Wal Rocklands").setHeight(height_Default).setDisableRain().setTemperatureRainfall(1.0F, 0F);
	       nileSavanna = new BiomeGenNileSavanna(122).setBiomeName("Nile Savanna").setHeight(height_LowPlains).setTemperatureRainfall(1.2F, 0.0F).setDisableRain().setColor(0x12431967);
	       nileSavannaPlateau = new BiomeGenNileSavanna(123).setBiomeName("Nile Savanna Plateau").setHeight(height_HighPlateaus).setTemperatureRainfall(1.0F, 0.0F).setDisableRain().setColor(0x10984804);
	       nileJungle = new BiomeGenNileJungle(124, false).setColor(5470985).setBiomeName("Nile Jungle").setFillerBlockMetadata(5470985).setTemperatureRainfall(0.95F, 0.9F);
	       nileJungleHills = new BiomeGenNileJungle(125, false).setColor(0x33CC33).setBiomeName("Nile Jungle Hills").setFillerBlockMetadata(5470985).setTemperatureRainfall(0.95F, 0.9F).setHeight(height_LowHills);
	       nileJungleEdge = new BiomeGenNileJungle(126, true).setColor(0x5CD65C).setBiomeName("Nile Jungle Edge").setFillerBlockMetadata(5470985).setTemperatureRainfall(0.95F, 0.8F);
	       nileSwampland = new BiomeGenNileSwamp(127).setBiomeName("Nile Swampland").setFillerBlockMetadata(9154376).setHeight(height_PartiallySubmerged).setTemperatureRainfall(0.8F, 0.9F);
	       nileMesa = new BiomeGenNileMesa(138, false, false).setColor(14238997).setBiomeName("Nile Mesa");
	       nileMesaPlateau_F = new BiomeGenNileMesa(139, false, true).setColor(11573093).setBiomeName("Nile Mesa Plateau F").setHeight(height_HighPlateaus);
	       nileMesaPlateau = new BiomeGenNileMesa(140, false, false).setColor(13274213).setBiomeName("Nile Mesa Plateau").setHeight(height_HighPlateaus);
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
	}
	
	public static void loadDarkBiomes(){    	    
		darkWasteland = new BiomeGenDarkLand(100).setBiomeName("Darkax").setHeight(height_MidHills).setTemperatureRainfall(2.0F, 0.0F);
	    BiomeManager.addSpawnBiome(darkWasteland);
	}
}
