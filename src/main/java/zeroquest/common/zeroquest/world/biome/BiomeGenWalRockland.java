package common.zeroquest.world.biome;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import common.zeroquest.ModBlocks;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityMetalZertum;

public class BiomeGenWalRockland extends BiomeGenBase
{
    public BiomeGenWalRockland(int par1)
    {
        super(par1);
        this.topBlock = ModBlocks.looseBedrock.getDefaultState();
        this.fillerBlock = Blocks.soul_sand.getDefaultState();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
       	this.waterColorMultiplier = 0x7A9999;
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
    }
    
    /**
     * takes temperature, returns color
     */
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float par1){return 0x5C1F1F;}
}