package net.novaviper.zeroquest.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.novaviper.zeroquest.ZeroQuest;
import net.novaviper.zeroquest.common.entity.creature.EntityKortor;

public class KortorJump implements IMessage{

	public int entityId;
  	 public KortorJump(){}
	 public KortorJump(int entityId)
	 {
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
	

    public static class Handler implements IMessageHandler<KortorJump, IMessage> {
      	 public Handler(){}
		@Override
		public IMessage onMessage(KortorJump message, MessageContext ctx) {
			
			Entity target = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityId);
	        if(!(target instanceof EntityKortor))
	        	return null;
	        
	        EntityKortor dog = (EntityKortor)target;
			if(dog.onGround) {
				
				dog.motionY = 2F * 2 * 0.1F;
				if(dog.isPotionActive(Potion.jump))
					dog.motionY += (double)((float)(dog.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
				dog.isAirBorne = true;
			}
			return message;
		}
    }
}
