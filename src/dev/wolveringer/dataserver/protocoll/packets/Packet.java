package dev.wolveringer.dataserver.protocoll.packets;

import java.lang.reflect.Constructor;
import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.Getter;

public abstract class Packet {
	public static final String PROTOCOLL_VERSION;

	static {
		PROTOCOLL_VERSION = PacketVersion.PROTOCOLL_VERSION;
	}

	public static enum PacketDirection {
		TO_CLIENT, TO_SERVER;
		private PacketDirection() {
		}
	}

	private static Constructor<? extends Packet> clientBoundedPackets[] = new Constructor[256];
	private static int clientPacketIdIndex = 0;
	private static Constructor<? extends Packet> serverBoundedPackets[] = new Constructor[256];
	private static int serverPacketIdIndex = 0;

	public static boolean client = false;

	public static Packet createPacket(int id, DataBuffer buffer, PacketDirection direction) {
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

	public static int getPacketId(Packet packet, PacketDirection direction) {
		int i = 0;
		for (Constructor<?> c : direction == PacketDirection.TO_SERVER ? serverBoundedPackets : clientBoundedPackets) {
			if (c != null)
				if (c.getDeclaringClass().equals(packet.getClass()))
					return i;
			i++;
		}
		return -1;
	}

	public static void registerPacket(PacketDirection direction) {
		if (direction == PacketDirection.TO_SERVER)
			serverBoundedPackets[serverPacketIdIndex++] = null;
		else
			clientBoundedPackets[clientPacketIdIndex++] = null;
	}

	@SuppressWarnings("unchecked")
	public static void registerPacket(PacketDirection direction, Class<? extends Packet> packet) {
		try {
			if (direction == PacketDirection.TO_SERVER)
				serverBoundedPackets[serverPacketIdIndex++] = packet.getConstructors().length == 1 ? (Constructor<? extends Packet>) packet.getConstructors()[0] : packet.getConstructor();
			else
				clientBoundedPackets[clientPacketIdIndex++] = packet.getConstructors().length == 1 ? (Constructor<? extends Packet>) packet.getConstructors()[0] : packet.getConstructor();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public static void registerPacket(int id,PacketDirection direction, Class<? extends Packet> packet) {
		try {
			if (direction == PacketDirection.TO_SERVER)
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
		System.out.println("Register packets with prot.. version = "+PacketVersion.PROTOCOLL_VERSION);
	    registerPacket(PacketDirection.TO_SERVER, PacketDisconnect.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketHandshakeInStart.class);

	    registerPacket(PacketDirection.TO_SERVER, PacketInBanStatsRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketInChangePlayerSettings.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketInPlayerSettingsRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketInServerSwitch.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketInStatsEdit.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketInStatsRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketInGetServer.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketInBanPlayer.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketServerAction.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketInServerStatus.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketInServerStatusRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketChatMessage.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketServerMessage.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketForward.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketSettingUpdate.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketInLobbyServerRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketInTopTenRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketSkinRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketSkinSet.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketEventCondition.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketEventTypeSettings.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketLanguageRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketPlayerIdRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketReportRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketReportEdit.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketBoosterStatusRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketBoosterActive.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketGildInformationRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketGildMemberRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketGildMemeberAction.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketGildPermissionEdit.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketGildPermissionRequest.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketGildSarch.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketGildCostumDataAction.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketGildUpdateSectionStatus.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketGildAction.class);

	    registerPacket(PacketDirection.TO_SERVER, PacketPing.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketPong.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketTeamspeakRequestAction.class);
	    registerPacket(PacketDirection.TO_SERVER, PacketTeamspeakAction.class);

	    registerPacket(PacketDirection.TO_SERVER, PacketGildMoneyAction.class); //TODO move to others?
	    registerPacket(PacketDirection.TO_SERVER, PacketGildMoneyHistoryAction.class);


	    registerPacket(PacketDirection.TO_CLIENT, PacketDisconnect.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketOutHandschakeAccept.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketOutPacketStatus.class);

	    registerPacket(PacketDirection.TO_CLIENT, PacketOutStats.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketOutPlayerSettings.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketOutPlayerServer.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketOutBanStats.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketServerAction.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketOutServerStatus.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketChatMessage.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketOutGammodeChange.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketServerMessage.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketForward.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketSettingUpdate.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketOutLobbyServer.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketOutTopTen.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketSkinData.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketEventFire.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketLanguageResponse.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketPlayerIdResponse.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketReportResponse.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketBoosterStatusResponse.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketGildPermissionResponse.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketGildMemberResponse.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketGildInformationResponse.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketGildSarchResponse.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketGildCostumDataResponse.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketGildActionResponse.class);

	    registerPacket(PacketDirection.TO_CLIENT, PacketPing.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketPong.class);

	    registerPacket(PacketDirection.TO_CLIENT, PacketGildMoneyHistoryResponse.class);
	    registerPacket(PacketDirection.TO_CLIENT, PacketGildMoneyResponse.class);
	}

	@Getter
	private UUID packetUUID = UUID.randomUUID();

	public Packet() {
	}

	public abstract void read(DataBuffer buffer);

	public abstract void write(DataBuffer buffer);
}
