package common.zeroquest.network.packet;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Potion;

import common.zeroquest.entity.EntityKortor;
import common.zeroquest.network.IPacket;

/**
 * @author ProPercivalalb
 */
public class PacketKortorJump extends IPacket {

	public int entityId;
	
	public PacketKortorJump() {}
	public PacketKortorJump(int entityId) {
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
        if(!(target instanceof EntityKortor))
        	return;
        
        EntityKortor dog = (EntityKortor)target;
		if(dog.onGround) {
			
			dog.motionY = 2F * 2 * 0.1F;
			if(dog.isPotionActive(Potion.jump))
				dog.motionY += (double)((float)(dog.getActivePotionEffect(Potion.jump).getAmplifier() + 3) * 0.1F);
			dog.isAirBorne = true;
		}
	}

}
