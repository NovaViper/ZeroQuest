package common.zeroquest.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraftforge.event.terraingen.TerrainGen;
import common.zeroquest.ModBlocks;
import common.zeroquest.ModLiquids;
import common.zeroquest.entity.EntityZertum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenPinkZone extends BiomeGenBase {
    
    public Random par2Random;

	public BiomeGenPinkZone(int id) {
        super(id);
        this.theBiomeDecorator.generateLakes = true;
        this.theBiomeDecorator.treesPerChunk = 10;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityZertum.class, 90, 4, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 5, 1, 5));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityCow.class, 5, 1, 5));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
    }
    
@SideOnly(Side.CLIENT)
@Override
public int getBiomeGrassColor(int par1, int par2, int par3){return 0xff66ff;}
@SideOnly(Side.CLIENT)
@Override
public int getBiomeFoliageColor(int par1, int par2, int par3){return 0xff66ff;}
@SideOnly(Side.CLIENT)
@Override
public int getSkyColorByTemp(float par1){return 0xcc66ff;}
    
	@Override
    public void decorate(World par1World, Random par2Random, int chunk_X, int chunk_Z){
    	super.decorate(par1World, par2Random, chunk_X, chunk_Z);
    	WorldGenFlowers blueFlowerGenerator = new WorldGenFlowers(ModBlocks.nilePinkFlower);
    	
        boolean doGen = TerrainGen.decorate(par1World, par2Random, chunk_X, chunk_Z, FLOWERS);
        for (int j = 0; doGen && j < 10; ++j)
        {
            int k = chunk_X + par2Random.nextInt(16) + 8;
            int l = par2Random.nextInt(128);
            int i1 = chunk_Z + par2Random.nextInt(16) + 8;
            blueFlowerGenerator.generate(par1World, par2Random, k, l, i1);
        }
    }
	
	@Override
	public void genTerrainBlocks(World world, Random random, Block[] blocks, byte[] bytes, int int1, int int2, double d)
    {
        boolean flag = true;
        Block block = this.topBlock;
        byte b0 = (byte)(this.field_150604_aj & 255);
        Block block1 = this.fillerBlock;
        int k = -1;
        int l = (int)(d / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int i1 = int1 & 15;
        int j1 = int2 & 15;
        int k1 = blocks.length / 256;

        for (int l1 = 255; l1 >= 0; --l1)
        {
            int i2 = (j1 * 16 + i1) * k1 + l1;

            if (l1 <= 0 + random.nextInt(5))
            {
                blocks[i2] = Blocks.bedrock;
            }
            else
            {
                Block block2 = blocks[i2];

                if (block2 != null && block2.getMaterial() != Material.air)
                {
                    if (block2 == ModBlocks.nileStone)
                    {
                        if (k == -1)
                        {
                            if (l <= 0)
                            {
                                block = null;
                                b0 = 0;
                                block1 = ModBlocks.nileStone;
                            }
                            else if (l1 >= 59 && l1 <= 64)
                            {
                                block = this.topBlock;
                                b0 = (byte)(this.field_150604_aj & 255);
                                block1 = this.fillerBlock;
                            }

                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air))
                            {
                                if (this.getFloatTemperature(int1, l1, int2) < 0.15F)
                                {
                                    block = Blocks.ice;
                                    b0 = 0;
                                }
                                else
                                {
                                    block = ModLiquids.niliBlock;
                                    b0 = 0;
                                }
                            }

                            k = l;

                            if (l1 >= 62)
                            {
                                blocks[i2] = block;
                                bytes[i2] = b0;
                            }
                            else if (l1 < 56 - l)
                            {
                                block = null;
                                block1 = ModBlocks.nileStone;
                                blocks[i2] = Blocks.gravel;
                            }
                            else
                            {
                                blocks[i2] = block1;
                            }
                        }
                        else if (k > 0)
                        {
                            --k;
                            blocks[i2] = block1;

                            if (k == 0 && block1 == Blocks.sand)
                            {
                                k = random.nextInt(4) + Math.max(0, l1 - 63);
                                block1 = Blocks.sandstone;
                            }
                        }
                    }
                }
                else
                {
                    k = -1;
                }
            }
        }
    }
}
