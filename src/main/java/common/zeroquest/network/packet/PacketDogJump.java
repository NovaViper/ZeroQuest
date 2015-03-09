package common.zeroquest.network.packet;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Potion;

import common.zeroquest.entity.EntityZertumEntity;
import common.zeroquest.network.IPacket;

/**
 * @author ProPercivalalb
 */
public class PacketDogJump extends IPacket {

	public int entityId;
	
	public PacketDogJump() {}
	public PacketDogJump(int entityId) {
		this();
		this.entityId = entityId;
	}

	@Override
	public void read(PacketBuffer packetbuffer) throws IOException {
		this.entityId = packetbuffer.readInt();
	}

	@Override
	public void write(PacketBuffer packetbuffer) throws IOException {
		packetbuffer.writeInt(this.entityId);
	}

	@Override
	public void execute(EntityPlayer player) {
		Entity target = player.worldObj.getEntityByID(this.entityId);
        if(!(target instanceof EntityZertumEntity))
        	return;
        
        EntityZertumEntity dog = (EntityZertumEntity)target;
		if(dog.onGround) {
			
			dog.motionY = 2F * dog.talents.getLevel("mount") * 0.1F;
			if(dog.isPotionActive(Potion.jump))
				dog.motionY += (double)((float)(dog.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
			dog.isAirBorne = true;
		}
	}

}
