package common.zeroquest.biome;

import java.util.Random;

import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import common.zeroquest.ModBlocks;
import common.zeroquest.entity.EntityDarkZertum;
import common.zeroquest.entity.EntityKurr;

public class BiomeGenDarkLand extends BiomeGenBase {
    
    public Random par2Random;

	public BiomeGenDarkLand(int id) {
        super(id);
       	this.waterColorMultiplier = 0x666600;
       	this.theBiomeDecorator.treesPerChunk = 1;
       	this.theBiomeDecorator.grassPerChunk = 1;
       	this.theBiomeDecorator.flowersPerChunk = -999;
       	this.addFlower(ModBlocks.nileBlackFlower.getDefaultState(), 100);
        this.theBiomeDecorator.generateLakes = true;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityDarkZertum.class, 100, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 60, 1, 5));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityPigZombie.class, 20, 2, 3));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGhast.class, 20, 1, 2));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityKurr.class, 10, 2, 2));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getModdedBiomeGrassColor(int par1){return 0x660066;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getModdedBiomeFoliageColor(int par1){return 0x660066;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1){return 0x333333;}
}