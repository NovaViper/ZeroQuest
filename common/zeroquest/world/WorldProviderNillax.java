package common.zeroquest.world;

import common.zeroquest.ZeroQuest;
import common.zeroquest.world.gen.ChunkProviderNillax;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

public class WorldProviderNillax extends WorldProvider
{	
	public void registerWorldChunkManager()
	{
		/** tells Minecraft to use our new WorldChunkManager **/
		this.worldChunkMgr = new WorldChunkMangerNillax(worldObj.getSeed(), terrainType);
		this.hasNoSky = false;
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderNillax(this.worldObj, this.worldObj.getSeed(), false);
	}

	@Override
	/** Dimension Name **/
	public String getDimensionName()
	{
		return "Nillax";
	}

	/** Get Provider for dimension **/
	public static WorldProvider getProviderForDimension(int id)
	{
		return DimensionManager.createProviderFor(ZeroQuest.NillaxID);
	}


	/** Welcome message **/
	public String getWelcomeMessage()
	{
    	return "Entering the Nillax Dimension";
	}
	
	public String getDepartMessage()
	{
    	return "Leaving the Nillax Dimension";
	}


	/** Can player re-spawn here **/
	public boolean canRespawnHere()
	{
		return true;
	}


	/** Set user message **/
	// not sure if this works any more ?
	protected synchronized String setUserMessage(String par1Str)
	{
    	return "Building the Nillax Dimension";
	}

	/** Determines the dimension the player will be respawned in **/
	/*public int getRespawnDimension(EntityPlayerMP player)
	{
		return 0;
	}*/


	/** Dimension Movement speed **/
	public double getMovementFactor()
	{
		return 10.0;
	}
}
