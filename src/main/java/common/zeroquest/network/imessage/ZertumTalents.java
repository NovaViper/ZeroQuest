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

public class ZertumTalents implements IMessage{

	public int entityId;
	public String talentId;
	public static int MAX_STRING_LENGTH = Integer.MAX_VALUE / 4;
  	 public ZertumTalents(){}
	 public ZertumTalents(int entityId, String talentId)
	 {
			this.entityId = entityId;
			this.talentId = talentId;
	 } 
	
    @Override
    public void fromBytes(ByteBuf buf) {
		this.entityId = buf.readInt();
		this.talentId = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entityId);
        ByteBufUtils.writeUTF8String(buf, talentId);
    }
	

    public static class Handler implements IMessageHandler<ZertumTalents, IMessage> {
      	 public Handler(){}
		@Override
		public IMessage onMessage(ZertumTalents message, MessageContext ctx) {
			
			Entity target = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityId);
	        if(!(target instanceof EntityZertumEntity))
	        	return null;
	        
	        EntityZertumEntity dog = (EntityZertumEntity)target;
	        
			dog.talents.setLevel(message.talentId, dog.talents.getLevel(message.talentId) + 1);
			return message;
		}
    }
}
