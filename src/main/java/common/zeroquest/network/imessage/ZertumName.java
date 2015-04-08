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

public class ZertumName implements IMessage{

	public int entityId;
	public String name;
	public static int MAX_STRING_LENGTH = Integer.MAX_VALUE / 4;
  	 public ZertumName(){}
	 public ZertumName(int entityId, String talentId)
	 {
			this.entityId = entityId;
			this.name = talentId;
	 } 
	
    @Override
    public void fromBytes(ByteBuf buf) {
		this.entityId = buf.readInt();
		this.name = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entityId);
        ByteBufUtils.writeUTF8String(buf, name);
    }
	

    public static class Handler implements IMessageHandler<ZertumName, IMessage> {
      	 public Handler(){}
		@Override
		public IMessage onMessage(ZertumName message, MessageContext ctx) {
			
			Entity target = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityId);
	        if(!(target instanceof EntityZertumEntity))
	        	return null;
	        
	        EntityZertumEntity dog = (EntityZertumEntity)target;
	        
			dog.setDogName(message.name);
			return message;
		}
    }
}
