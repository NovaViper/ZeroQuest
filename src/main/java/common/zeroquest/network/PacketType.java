package common.zeroquest.network;

import common.zeroquest.network.packet.PacketCommand;
import common.zeroquest.network.packet.PacketDogJump;
import common.zeroquest.network.packet.PacketDogMode;
import common.zeroquest.network.packet.PacketDogName;
import common.zeroquest.network.packet.PacketDogObey;
import common.zeroquest.network.packet.PacketDogTalent;
import common.zeroquest.network.packet.PacketJakanJump;
import common.zeroquest.network.packet.PacketKortorJump;

/**
 * @author ProPercivalalb
 */
public enum PacketType {
	
	DOG_NAME(PacketDogName.class),
	DOG_TALENT(PacketDogTalent.class),
	DOG_OBEY(PacketDogObey.class),
	DOG_JUMP(PacketDogJump.class),
	JAKAN_JUMP(PacketJakanJump.class),
	KORTOR_JUMP(PacketKortorJump.class),
	DOG_MODE(PacketDogMode.class),
	COMMAND(PacketCommand.class);
	
    public Class<? extends IPacket> packetClass;

    PacketType(Class<? extends IPacket> packetClass) {
        this.packetClass = packetClass;
    }
	public static byte getIdFromClass(Class<? extends IPacket> packetClass) {
		for(PacketType type : values())
			if(type.packetClass == packetClass)
				return (byte)type.ordinal();
		return -1;
	}
}