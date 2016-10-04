package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildActionResponse extends Packet {
	public static enum Action {
		SUCCESSFULL,
		ERROR;
	}
	private int playerId;
	private UUID uuid;
	private Action action;
	private String message;

	public void read(DataBuffer buffer) {
		this.playerId = buffer.readInt();
		this.uuid = buffer.readUUID();
		this.action = Action.values()[buffer.readByte()];
		this.message = buffer.readString();
	}

	public void write(DataBuffer buffer) {
		buffer.writeInt(this.playerId);
		buffer.writeUUID(this.uuid);
		buffer.writeByte(action.ordinal());
		buffer.writeString(message);
	}

}
