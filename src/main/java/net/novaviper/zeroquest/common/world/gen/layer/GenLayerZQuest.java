package net.novaviper.zeroquest.common.world.gen.layer;

import java.util.concurrent.Callable;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;


public abstract class GenLayerZQuest extends GenLayer
{
    public GenLayerZQuest(long p_i2125_1_) {
		super(p_i2125_1_);

	}

    /**
     * the first array item is a linked list of the bioms, the second is the zoom function, the third is the same as the
     * first.
     */
    public static GenLayer[] initializeAllBiomeGenerators(long p_75901_0_, WorldType p_75901_2_)
    {
    	GenLayer biomes = new GenLayerBiomesZeroQuest(1L);
    	// more GenLayerZoom = bigger biomes
    	biomes = new GenLayerZoom(1000L, biomes);
    	biomes = new GenLayerZoom(1001L, biomes);
    	biomes = new GenLayerZoom(1002L, biomes);
    	biomes = new GenLayerZoom(1003L, biomes);
    	biomes = new GenLayerZoom(1004L, biomes);
    	biomes = new GenLayerZoom(1005L, biomes);
    	GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);
    	biomes.initWorldGenSeed(p_75901_0_);
    	genlayervoronoizoom.initWorldGenSeed(p_75901_0_);
    	return new GenLayer[] {biomes, genlayervoronoizoom};
    }
}