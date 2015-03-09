package common.zeroquest.network.packet;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

import common.zeroquest.entity.EntityZertumEntity;
import common.zeroquest.network.IPacket;

/**
 * @author ProPercivalalb
 */
public class PacketDogName extends IPacket {

	public int entityId;
	public String name;
	
	public PacketDogName() {}
	public PacketDogName(int entityId, String name) {
		this();
		this.entityId = entityId;
		this.name = name;
	}

	@Override
	public void read(PacketBuffer packetbuffer) throws IOException {
		this.entityId = packetbuffer.readInt();
		this.name = packetbuffer.readStringFromBuffer(MAX_STRING_LENGTH);
	}

	@Override
	public void write(PacketBuffer packetbuffer) throws IOException {
		packetbuffer.writeInt(this.entityId);
		packetbuffer.writeString(this.name);
	}

	@Override
	public void execute(EntityPlayer player) {
		Entity target = player.worldObj.getEntityByID(this.entityId);
		
        if(!(target instanceof EntityZertumEntity))
        	return;
        
        EntityZertumEntity dog = (EntityZertumEntity)target;
        
		dog.setDogName(this.name);
	}

}
