package common.zeroquest.world;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import common.zeroquest.ZeroQuest;
import common.zeroquest.world.gen.ChunkProviderNillax;

public class WorldProviderNillax extends WorldProvider
{	
    /** Array for sunrise/sunset colors (RGBA) */
    private float[] colorsSunriseSunset = new float[4];
	
    @Override
	public void registerWorldChunkManager()
	{
		/** tells Minecraft to use our new WorldChunkManager **/
		this.worldChunkMgr = new WorldChunkMangerNillax(worldObj.getSeed(), worldObj.getWorldInfo().getTerrainType());
		this.hasNoSky = false;
		this.dimensionId = ZeroQuest.NillaxID;
	}
	

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderNillax(this.worldObj, this.worldObj.getSeed(), true, worldObj.getWorldInfo().getGeneratorOptions());
	}
	
	/** Get Provider for dimension **/
	public static WorldProvider getProviderForDimension(int p_76570_0_)
	{
		return DimensionManager.createProviderFor(ZeroQuest.NillaxID);
	}

	@Override
	/** Dimension Name **/
	public String getDimensionName()
	{
		return "Nillax";
	}
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean doesXZShowFog(int par1, int par2){
        return false;
    }
    
    /**
     * the y level at which clouds are rendered.
     */
    /*@SideOnly(Side.CLIENT)
    @Override
    public float getCloudHeight()
    {
        return 128.0F;
    }
    
    @Override
    public int getAverageGroundLevel()
    {
    	return 0;
    }*/
    
    @Override
	/** Welcome message **/
    public String getWelcomeMessage()
    {
        if (this instanceof WorldProviderNillax)
        {
            return "Entering the Nillax Dimension";
        } 
        return null;
    }
    
    @Override
	public String getDepartMessage()
    {
        if (this instanceof WorldProviderNillax)
        {
            return "Leaving the Nillax Dimension";
        } 
        return null;
    }

    @Override
	/** Can player re-spawn here **/
	public boolean canRespawnHere()
	{
		return true;
	}
    
    @Override
	/** Dimension Movement speed **/
	public double getMovementFactor()
	{
		return 10.0;
	}


	@Override
	public String getInternalNameSuffix() {
		// TODO Auto-generated method stub
		return "_nillax";
	}
}