package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.skin.Skin;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PacketSkinSet extends Packet {
	public static enum DataType {
		NAME, UUID, PROPS, NONE;

		private DataType() {
		}
	}
	private UUID player;
	private DataType type;
	private String skinName;
	private UUID skinUUID;
	private String rawValue;
	private String signature;

	public PacketSkinSet(UUID player, Skin skin) {
		this(player, skin.getRawData(), skin.getSignature());
	}

	public PacketSkinSet(UUID player, UUID target) {
		type = DataType.UUID;
		this.player = player;
		this.skinUUID = target;
	}

	public PacketSkinSet(UUID player, String target) {
		type = DataType.NAME;
		this.player = player;
		this.skinName = target;
	}

	public PacketSkinSet(UUID player, String rawValue, String signature) {
		type = DataType.PROPS;
		this.player = player;
		this.rawValue = rawValue;
		this.signature = signature;
	}
	
	/**
	 * 
	 * @param player
	 *	Remove costum skin!
	 */
	public PacketSkinSet(UUID player) {
		type = DataType.NONE;
		this.player = player;
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(player);
		buffer.writeByte(type.ordinal());
		switch (type) {
		case NAME:
			buffer.writeString(skinName);
			break;
		case UUID:
			buffer.writeUUID(skinUUID);
			break;
		case PROPS:
			buffer.writeString(rawValue);
			buffer.writeString(signature);
			break;
		case NONE:
			break;
		}
	}
	
	@Override
	public void read(DataBuffer buffer) {
		player = buffer.readUUID();
		type = DataType.values()[buffer.readByte()];
		switch (type) {
		case NAME:
			this.skinName = buffer.readString();
			break;
		case UUID:
			this.skinUUID = buffer.readUUID();
			break;
		case PROPS:
			this.rawValue = buffer.readString();
			this.signature = buffer.readString();
			break;
		case NONE:
			break;
		}
	}
}
