package net.novaviper.zeroquest.common.world;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.novaviper.zeroquest.ModBiomes;
import net.novaviper.zeroquest.common.lib.IDs;
import net.novaviper.zeroquest.common.world.gen.ChunkProviderDarkax;

public class WorldProviderDarkax extends WorldProvider {
	@Override
	public void registerWorldChunkManager() {
		/** tells Minecraft to use our new WorldChunkManager **/
		this.worldChunkMgr = new WorldChunkManagerHell(ModBiomes.darkWasteland, 0F);
		this.isHellWorld = true;
		this.hasNoSky = false;
		this.dimensionId = IDs.Darkax;
	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderDarkax(this.worldObj, this.worldObj.getSeed(), true, worldObj.getWorldInfo().getGeneratorOptions());
	}

	@Override
	/** Dimension Name **/
	public String getDimensionName() {
		return "Darkax";
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Return Vec3D with biome specific fog color
	 */
	@Override
	public Vec3 getFogColor(float p_76562_1_, float p_76562_2_) {
		int i = 10518688;
		float f2 = MathHelper.cos(p_76562_1_ * (float) Math.PI * 2.0F) * 2.0F + 0.5F;

		if (f2 < 0.0F) {
			f2 = 0.0F;
		}

		if (f2 > 1.0F) {
			f2 = 1.0F;
		}

		float f3 = (i >> 16 & 255) / 255.0F;
		float f4 = (i >> 8 & 255) / 255.0F;
		float f5 = (i & 255) / 255.0F;
		f3 *= f2 * 0.0F + 0.15F;
		f4 *= f2 * 0.0F + 0.15F;
		f5 *= f2 * 0.0F + 0.15F;
		return new Vec3(f3, f4, f5);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isSkyColored() {
		return false;
	}

	/**
	 * Returns 'true' if in the "main surface world", but 'false' if in the
	 * Nether or End dimensions.
	 */
	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	/**
	 * Will check if the x, z position specified is alright to be set as the map
	 * spawn point
	 */
	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}

	@Override
	/** Welcome message **/
	public String getWelcomeMessage() {
		return "Entering the Darkax Dimension";
	}

	@Override
	public String getDepartMessage() {
		return "Leaving the Darkax Dimension";
	}

	/** Can player re-spawn here **/
	@Override
	public boolean canRespawnHere() {
		return false;
	}

	/** Dimension Movement speed **/
	@Override
	public double getMovementFactor() {
		return 10.0;
	}

	@Override
	public String getInternalNameSuffix() {
		return "_darkax";
	}
}