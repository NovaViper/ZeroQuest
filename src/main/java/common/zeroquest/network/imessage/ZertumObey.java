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

public class ZertumObey implements IMessage{

	public int entityId;
	public boolean obey;
  	 public ZertumObey(){}
	 public ZertumObey(int entityId, boolean obey)
	 {
			this.entityId = entityId;
			this.obey = obey;
	 } 
	
    @Override
    public void fromBytes(ByteBuf buf) {
		this.entityId = buf.readInt();
		this.obey = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entityId);
		buf.writeBoolean(this.obey);
    }
	

    public static class Handler implements IMessageHandler<ZertumObey, IMessage> {
      	 public Handler(){}
		@Override
		public IMessage onMessage(ZertumObey message, MessageContext ctx) {
			
			Entity target = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityId);
	        if(!(target instanceof EntityZertumEntity))
	        	return null;
	        
	        EntityZertumEntity dog = (EntityZertumEntity)target;
	        
			dog.setWillObeyOthers(message.obey);
			return message;
		}
    }
}
