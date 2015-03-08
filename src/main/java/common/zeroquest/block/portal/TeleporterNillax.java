package common.zeroquest.block.portal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class TeleporterNillax extends Teleporter {

	private final WorldServer worldServerInstance;
	private boolean topBlock;

	public TeleporterNillax(WorldServer world, boolean topBlock) {
		super(world);
		this.worldServerInstance = world;
		this.topBlock = topBlock;
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		int i = MathHelper.floor_double(entityIn.posX);
		int j = MathHelper.floor_double(entityIn.posY);
		int k = MathHelper.floor_double(entityIn.posZ);
		
		this.worldServerInstance.getBlockState(new BlockPos(i, j, k)); // dummy load to maybe gen
													// chunk

		int height = 100;
		if (topBlock) {
			//height = this.worldServerInstance.getHeight();// spawn Player on top block
			
		} else {
			height = j + 6;// spawn Player on same height
		}

		entityIn.setPosition(i, height, k);
	}

	public static void teleport(EntityPlayer player, int dim, boolean topBlock) {

		MinecraftServer mServer = MinecraftServer.getServer();
		Side sidex = FMLCommonHandler.instance().getEffectiveSide();
		if (sidex == Side.SERVER) {
			if (player instanceof EntityPlayerMP) {
				EntityPlayerMP playerMP = (EntityPlayerMP) player;
				if (player.ridingEntity == null && player.riddenByEntity == null && player instanceof EntityPlayer) {
					FMLCommonHandler.instance().getMinecraftServerInstance();
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, dim, new TeleporterNillax(mServer.worldServerForDimension(dim), topBlock));

				}

			}
		}
	}

}