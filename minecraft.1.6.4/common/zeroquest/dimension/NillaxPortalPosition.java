package common.zeroquest.dimension;

import net.minecraft.util.ChunkCoordinates;

public class NillaxPortalPosition extends ChunkCoordinates
{
	public long field_85087_d;


	final TeleporterNillax field_85088_e;


	public NillaxPortalPosition(TeleporterNillax teleporterNillax, int par2, int par3, int par4, long par5)
	{
		super(par2, par3, par4);
		this.field_85088_e = teleporterNillax;
		this.field_85087_d = par5;
	}
}

