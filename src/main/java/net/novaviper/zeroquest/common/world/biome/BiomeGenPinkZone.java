package net.novaviper.zeroquest.common.world.biome;

import java.util.Random;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.novaviper.zeroquest.common.entity.creature.EntityZertum;
import net.novaviper.zeroquest.common.lib.Constants;

public class BiomeGenPinkZone extends BiomeGenBase {
    
    public Random par2Random;

	public BiomeGenPinkZone(int id) {
        super(id);
        this.theBiomeDecorator.generateLakes = true;
        this.theBiomeDecorator.treesPerChunk = 10;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 5, 1, 5));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityCow.class, 5, 1, 5));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
    }
    
@SideOnly(Side.CLIENT)
@Override
public int getModdedBiomeGrassColor(int par1){return 0xFF66FF;}
@SideOnly(Side.CLIENT)
@Override
public int getModdedBiomeFoliageColor(int par1){return 0xCC52CC;}
@SideOnly(Side.CLIENT)
@Override
public int getSkyColorByTemp(float par1){return 0xCC66FF;}
}
