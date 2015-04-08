package common.zeroquest.network.imessage;

import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.EntityZertumEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ZertumMode implements IMessage{

	public int entityId, doggyMode;
  	 public ZertumMode(){}
	 public ZertumMode(int entityId, int doggyMode)
	 {
			this.entityId = entityId;
			this.doggyMode = doggyMode;
	 } 
	
    @Override
    public void fromBytes(ByteBuf buf) {
		this.entityId = buf.readInt();
		this.doggyMode = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entityId);
		buf.writeInt(this.doggyMode);
    }
	

    public static class Handler implements IMessageHandler<ZertumMode, IMessage> {
      	 public Handler(){}
		@Override
		public IMessage onMessage(ZertumMode message, MessageContext ctx) {
			
			Entity target = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityId);
	        if(!(target instanceof EntityZertumEntity))
	        	return null;
	        
	        EntityZertumEntity dog = (EntityZertumEntity)target;
	        
			dog.mode.setMode(message.doggyMode);
			return message;
		}
    }
}
