package common.zeroquest.world;

import common.zeroquest.ModBiomes;
import common.zeroquest.ZeroQuest;
import common.zeroquest.world.gen.ChunkProviderDarkax;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraftforge.common.DimensionManager;

public class WorldProviderDarkax extends WorldProvider
{	
	@Override
	public void registerWorldChunkManager()
	{
		/** tells Minecraft to use our new WorldChunkManager **/
        this.worldChunkMgr = new WorldChunkManagerHell(ModBiomes.darkWasteland, 0F);
        this.isHellWorld = true;
        this.hasNoSky = false;
        this.dimensionId = ZeroQuest.DarkaxID;
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
        return new ChunkProviderDarkax(this.worldObj, this.worldObj.getSeed(), true);
	}

	@Override
	/** Dimension Name **/
	public String getDimensionName()
	{
		return "Darkax";
	}

    @SideOnly(Side.CLIENT)

    /**
     * Return Vec3D with biome specific fog color
     */
	@Override
    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_)
    {
        int i = 10518688;
        float f2 = MathHelper.cos(p_76562_1_ * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        float f3 = (float)(i >> 16 & 255) / 255.0F;
        float f4 = (float)(i >> 8 & 255) / 255.0F;
        float f5 = (float)(i & 255) / 255.0F;
        f3 *= f2 * 0.0F + 0.15F;
        f4 *= f2 * 0.0F + 0.15F;
        f5 *= f2 * 0.0F + 0.15F;
        return Vec3.createVectorHelper((double)f3, (double)f4, (double)f5);
    }

    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return false;
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
	@Override
    public boolean isSurfaceWorld()
    {
        return false;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
	@Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
	@Override
    public boolean doesXZShowFog(int par1, int par2)
    {
        return false;
    }


    @Override
	/** Welcome message **/
    public String getWelcomeMessage()
    {
        if (this instanceof WorldProviderDarkax)
        {
            return "Entering the Darkax Dimension";
        } 
        return null;
    }
    
    @Override
	public String getDepartMessage()
    {
        if (this instanceof WorldProviderDarkax)
        {
            return "Leaving the Darkax Dimension";
        } 
        return null;
    }


	/** Can player re-spawn here **/
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}


	/** Set user message **/
	// not sure if this works any more ?
	protected synchronized String setUserMessage(String par1Str)
	{
    	return "Building the Darkax Dimension";
	}

	/** Dimension Movement speed **/
	public double getMovementFactor()
	{
		return 10.0;
	}
}