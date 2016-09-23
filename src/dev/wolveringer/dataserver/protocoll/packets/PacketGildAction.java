package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildAction extends Packet {
	public static enum Action {
		CREATE,
		DELETE,
		RENAME_SHORT,
		RENAME_LONG;
	}
	private int playerId;
	private UUID gilde;
	private Action action;
	private String value;

	public void read(DataBuffer buffer) {
		this.playerId = buffer.readInt();
		this.gilde = buffer.readUUID();
		this.action = Action.values()[buffer.readByte()];
		this.value = buffer.readString();
	}

	public void write(DataBuffer buffer) {
		buffer.writeInt(this.playerId);
		buffer.writeUUID(gilde);
		buffer.writeByte(action.ordinal());
		buffer.writeString(value);
	}
}
