package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketSkinRequest extends Packet {
	public static enum Type {
		UUID, NAME, FROM_PLAYER;
	}

	@AllArgsConstructor
	@Getter
	public static class SkinRequest {
		private Type type;
		private String name;
		private UUID uuid;
	}

	private UUID requestUUID;
	private SkinRequest[] requests;
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(requestUUID);
		buffer.writeInt(requests.length);
		for(SkinRequest r : requests){
			buffer.writeByte(r.getType().ordinal());
			switch (r.getType()) {
			case FROM_PLAYER:
			case UUID:
				buffer.writeUUID(r.getUuid());
				break;
			case NAME:
				buffer.writeString(r.getName());
			default:
				break;
			}
		}
	}

	@Override
	public void read(DataBuffer buffer) {
		requestUUID = buffer.readUUID();
		requests = new SkinRequest[buffer.readInt()];
		for(int i = 0;i<requests.length;i++){
			Type type = Type.values()[buffer.readByte()];
			UUID uuid = null;
			String name = null;
			switch (type) {
			case FROM_PLAYER:
			case UUID:
				uuid = buffer.readUUID();
				break;
			case NAME:
				name = buffer.readString();
				break;
			default:
				break;
			}
			requests[i] = new SkinRequest(type, name, uuid);
		}
	}

	public static void main(String[] args) {
		switch (Type.FROM_PLAYER) {
		case FROM_PLAYER:
		case UUID:
			System.out.println("X");
			break;
		case NAME:
			System.out.println("Y");
		default:
			break;
		}
	}
}
