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
public class PacketDogObey extends IPacket {

	public int entityId;
	public boolean obey;
	
	public PacketDogObey() {}
	public PacketDogObey(int entityId, boolean obey) {
		this();
		this.entityId = entityId;
		this.obey = obey;
	}

	@Override
	public void read(PacketBuffer packetbuffer) throws IOException {
		this.entityId = packetbuffer.readInt();
		this.obey = packetbuffer.readBoolean();
	}

	@Override
	public void write(PacketBuffer packetbuffer) throws IOException {
		packetbuffer.writeInt(this.entityId);
		packetbuffer.writeBoolean(this.obey);
	}

	@Override
	public void execute(EntityPlayer player) {
		Entity target = player.worldObj.getEntityByID(this.entityId);
        if(!(target instanceof EntityZertumEntity))
        	return;
        
        EntityZertumEntity dog = (EntityZertumEntity)target;
        
		dog.setWillObeyOthers(this.obey);
	}

}
