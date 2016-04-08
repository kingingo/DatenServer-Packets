package dev.wolveringer.dataserver.protocoll.packets;

import java.lang.reflect.Constructor;
import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.Getter;

public abstract class Packet {
	public static final String PROTOCOLL_VERSION;
	
	static {
		PROTOCOLL_VERSION = PacketVersion.PROTOCOLL_VERSION;
	}
	
	public static enum PacketDirection {
		TO_CLIENT,
		TO_SERVER;
		private PacketDirection() {}
	}
	
	private static Constructor<? extends Packet> clientBoundedPackets[] = new Constructor[256];
	private static Constructor<? extends Packet> serverBoundedPackets[] = new Constructor[256];
	
	public static boolean client = false;
	
	public static Packet createPacket(int id, DataBuffer buffer,PacketDirection direction) {
		try {
			Constructor<? extends Packet> c = direction == PacketDirection.TO_SERVER ? serverBoundedPackets[id] : clientBoundedPackets[id];
			if (c != null) {
				Packet packet = c.newInstance();
				packet.packetUUID = buffer.readUUID();
				packet.read(buffer);
				return packet;
			} else
				System.out.println("Packet 0x" + (Integer.toHexString(id).toUpperCase()) + " not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getPacketId(Packet packet,PacketDirection direction) {
		int i = 0;
		for (Constructor<?> c : direction == PacketDirection.TO_SERVER ? serverBoundedPackets : clientBoundedPackets) {
			if (c != null)
				if (c.getDeclaringClass().equals(packet.getClass()))
					return i;
			i++;
		}
		return -1;
	}
	
	protected static void registerPacket(int id,Class<? extends Packet> packet,PacketDirection direction){
		try {
			if(direction == PacketDirection.TO_SERVER)
				serverBoundedPackets[id] = packet.getConstructors().length == 1 ? (Constructor<? extends Packet>) packet.getConstructors()[0] : packet.getConstructor();
			else
				clientBoundedPackets[id] = packet.getConstructors().length == 1 ? (Constructor<? extends Packet>) packet.getConstructors()[0] : packet.getConstructor();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	static {
		registerPacket(0xFF, PacketDisconnect.class, PacketDirection.TO_CLIENT);
		registerPacket(0xFF, PacketDisconnect.class, PacketDirection.TO_SERVER);
		
		
		registerPacket(0x00, PacketHandschakeInStart.class, PacketDirection.TO_SERVER);
		registerPacket(0x01, PacketInBanStatsRequest.class, PacketDirection.TO_SERVER);
		registerPacket(0x02, PacketInChangePlayerSettings.class, PacketDirection.TO_SERVER);
		registerPacket(0x03, PacketInPlayerSettingsRequest.class, PacketDirection.TO_SERVER);
		registerPacket(0x04, PacketInConnectionStatus.class,  PacketDirection.TO_SERVER);
		registerPacket(0x05, PacketInServerSwitch.class, PacketDirection.TO_SERVER);
		registerPacket(0x06, PacketInStatsEdit.class, PacketDirection.TO_SERVER);
		registerPacket(0x07, PacketInStatsRequest.class, PacketDirection.TO_SERVER);
		registerPacket(0x08, PacketInUUIDRequest.class, PacketDirection.TO_SERVER);
		registerPacket(0x09, PacketInGetServer.class, PacketDirection.TO_SERVER);
		registerPacket(0x0A, PacketInBanPlayer.class, PacketDirection.TO_SERVER);
		registerPacket(0x0B, PacketInNameRequest.class, PacketDirection.TO_SERVER);
		registerPacket(0x0C, PacketServerAction.class, PacketDirection.TO_SERVER);
		registerPacket(0x0D, PacketInServerStatus.class, PacketDirection.TO_SERVER);
		registerPacket(0x0E, PacketInServerStatusRequest.class, PacketDirection.TO_SERVER);
		registerPacket(0x0F, PacketChatMessage.class, PacketDirection.TO_SERVER);
		registerPacket(0x10, PacketServerMessage.class, PacketDirection.TO_SERVER);
		registerPacket(0x11, PacketForward.class, PacketDirection.TO_SERVER);
		registerPacket(0x12, PacketSettingUpdate.class, PacketDirection.TO_SERVER);
		registerPacket(0x13, PacketInLobbyServerRequest.class, PacketDirection.TO_SERVER);
		registerPacket(0x14, PacketInTopTenRequest.class, PacketDirection.TO_SERVER);
		registerPacket(0x15, PacketSkinRequest.class, PacketDirection.TO_SERVER);
		registerPacket(0x16, PacketSkinSet.class, PacketDirection.TO_SERVER);
		registerPacket(0x17, PacketEventCondition.class, PacketDirection.TO_SERVER);
		registerPacket(0x18, PacketEventTypeSettings.class, PacketDirection.TO_SERVER);
		
		registerPacket(0xF0, PacketOutPacketStatus.class, PacketDirection.TO_CLIENT);
		
		registerPacket(0x00, PacketOutHandschakeAccept.class, PacketDirection.TO_CLIENT);
		registerPacket(0x01, PacketOutStats.class, PacketDirection.TO_CLIENT);
		registerPacket(0x02, PacketOutPlayerSettings.class, PacketDirection.TO_CLIENT);
		registerPacket(0x03, PacketOutUUIDResponse.class, PacketDirection.TO_CLIENT);
		registerPacket(0x04, PacketOutPlayerServer.class, PacketDirection.TO_CLIENT);
		registerPacket(0x05, PacketOutBanStats.class, PacketDirection.TO_CLIENT);
		registerPacket(0x06, PacketOutNameResponse.class, PacketDirection.TO_CLIENT);
		registerPacket(0x07, PacketServerAction.class, PacketDirection.TO_CLIENT);
		registerPacket(0x08, PacketOutServerStatus.class, PacketDirection.TO_CLIENT);
		registerPacket(0x09, PacketChatMessage.class, PacketDirection.TO_CLIENT);
		registerPacket(0x0A, PacketOutGammodeChange.class, PacketDirection.TO_CLIENT);
		registerPacket(0x0B, PacketServerMessage.class, PacketDirection.TO_CLIENT);
		registerPacket(0x0C, PacketForward.class, PacketDirection.TO_CLIENT);
		registerPacket(0x0D, PacketSettingUpdate.class, PacketDirection.TO_CLIENT);
		registerPacket(0x0E, PacketOutLobbyServer.class, PacketDirection.TO_CLIENT);
		registerPacket(0x0F, PacketOutTopTen.class, PacketDirection.TO_CLIENT);
		registerPacket(0x10, PacketSkinData.class, PacketDirection.TO_CLIENT);
		registerPacket(0x11, PacketEventFire.class, PacketDirection.TO_CLIENT);
		
		registerPacket(0xFF, PacketDisconnect.class, PacketDirection.TO_CLIENT);
		registerPacket(0xFF, PacketDisconnect.class, PacketDirection.TO_SERVER);
		
		registerPacket(0xFE, PacketPing.class, PacketDirection.TO_CLIENT);
		registerPacket(0xFE, PacketPing.class, PacketDirection.TO_SERVER);
		registerPacket(0xFD, PacketPong.class, PacketDirection.TO_CLIENT);
		registerPacket(0xFD, PacketPong.class, PacketDirection.TO_SERVER);
	}

	@Getter
	private UUID packetUUID = UUID.randomUUID();
	
	public Packet() {
	}
	
	public void read(DataBuffer buffer){
		throw new NullPointerException("Packet is write only");
	}
	public void write(DataBuffer buffer){
		throw new NullPointerException("Packet is read only");
	}
	//TODO
	//Messages
	//Bann
}
