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

	private UUID requestUUID;
	private Type type;
	private String name;
	private UUID uuid;

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(requestUUID);
		buffer.writeByte(type.ordinal());
		switch (type) {
		case FROM_PLAYER:
		case UUID:
			buffer.writeUUID(uuid);
			break;
		case NAME:
			buffer.writeString(name);
		default:
			break;
		}
	}

	@Override
	public void read(DataBuffer buffer) {
		requestUUID = buffer.readUUID();
		type = Type.values()[buffer.readByte()];
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
