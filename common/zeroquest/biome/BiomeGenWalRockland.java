package common.zeroquest.biome;

import common.zeroquest.ModBlocks;
import common.zeroquest.entity.EntityJakan;
import common.zeroquest.entity.EntityJakanPrime;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenWalRockland extends BiomeGenBase
{
    public BiomeGenWalRockland(int par1)
    {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
       	this.waterColorMultiplier = 0x7A9999;
        this.spawnableCreatureList.add(new SpawnListEntry(EntityJakan.class, 100, 2, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityJakanPrime.class, 100, 2, 2));
        this.topBlock = (byte)ModBlocks.looseBedrock.blockID;
        this.fillerBlock = (byte)Block.coalBlock.blockID;
        this.canSpawnLightningBolt();
    }


    /**
     * takes temperature, returns color
     */
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1){return 0x5C1F1F;}
}