package common.zeroquest.world.biome;
 
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import common.zeroquest.ModBlocks;
import common.zeroquest.entity.EntityZertum;
 
public class BiomeGenBioZone extends BiomeGenBase {
    
    public Random par2Random;

	public BiomeGenBioZone(int id) {
        super(id);
        this.theBiomeDecorator.generateLakes = true;
        this.theBiomeDecorator.treesPerChunk = 4;
        this.spawnableMonsterList.clear();
        this.addFlower(ModBlocks.nileBlueFlower.getDefaultState(), 90);
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityCreeper.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 100, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityZertum.class, 100, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 5, 1, 5));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 5, 1, 5));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityCow.class, 5, 1, 5));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPig.class, 5, 1, 5)); 
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getModdedBiomeGrassColor(int par1){return 0x009900;}
    @SideOnly(Side.CLIENT)
    @Override
    public int getModdedBiomeFoliageColor(int par1){return 0x009900;}
	@SideOnly(Side.CLIENT)
	@Override
	public int getSkyColorByTemp(float par1){return 0x66CCFF;}
}
