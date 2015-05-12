package common.zeroquest.network.imessage;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FireSound implements IMessage {

	private String text;
	private String player;
	private int face;
	private int x;
	private int y;
	private int z;

	public FireSound() {}

	public FireSound(EntityPlayer playerIn, EnumFacing faceIn, BlockPos posIn) {
		player = playerIn.getName().toString();
		face = faceIn.getIndex();
		x = posIn.getX();
		y = posIn.getY();
		z = posIn.getZ();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		player = ByteBufUtils.readUTF8String(buf);
		face = ByteBufUtils.readVarInt(buf, 5);
		x = ByteBufUtils.readVarInt(buf, 5);
		y = ByteBufUtils.readVarInt(buf, 5);
		z = ByteBufUtils.readVarInt(buf, 5);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, player);
		ByteBufUtils.writeVarInt(buf, face, 5);
		ByteBufUtils.writeVarInt(buf, x, 5);
		ByteBufUtils.writeVarInt(buf, y, 5);
		ByteBufUtils.writeVarInt(buf, z, 5);
	}

	public static class Handler implements IMessageHandler<FireSound, IMessage> {

		@Override
		public IMessage onMessage(FireSound message, MessageContext ctx) {
			//System.out.println(String.format("Received %s from %s", message.face, ctx.getServerHandler().playerEntity.getDisplayName()));
			EnumFacing face = EnumFacing.getFront(message.face);
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			World world = player.worldObj;
			BlockPos pos = new BlockPos(message.x, message.y, message.z);

			world.playSoundEffect(player.posX, player.posY, player.posZ, "random.fizz", 1.0F, 1.0F);
			world.setBlockToAir(pos);
			return null;
		}
	}
}
