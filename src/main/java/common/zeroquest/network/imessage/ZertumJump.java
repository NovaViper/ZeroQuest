package common.zeroquest.network.imessage;

import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.zertum.EntityZertumEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ZertumJump implements IMessage {

	public int entityId;

	public ZertumJump() {}

	public ZertumJump(int entityId) {
		this.entityId = entityId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		entityId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entityId);
	}

	public static class Handler implements IMessageHandler<ZertumJump, IMessage> {
		public Handler() {}

		@Override
		public IMessage onMessage(ZertumJump message, MessageContext ctx) {

			Entity target = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityId);
			if (!(target instanceof EntityZertumEntity)) {
				return null;
			}

			EntityZertumEntity dog = (EntityZertumEntity) target;
			if (dog.onGround) {

				dog.motionY = 1.2F * dog.talents.getLevel("mount") * 0.1F;
				if (dog.isPotionActive(Potion.jump)) {
					dog.motionY += (dog.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F;
				}
				dog.isAirBorne = true;
			}
			return message;
		}
	}
}