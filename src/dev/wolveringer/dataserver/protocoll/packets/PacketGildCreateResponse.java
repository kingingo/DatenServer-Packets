package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildCreateResponse extends Packet {
	private int playerId;
	private UUID uuid;

	public void read(DataBuffer buffer) {
		this.playerId = buffer.readInt();
		this.uuid = buffer.readUUID();
	}

	public void write(DataBuffer buffer) {
		buffer.writeInt(this.playerId);
		buffer.writeUUID(this.uuid);
	}

}