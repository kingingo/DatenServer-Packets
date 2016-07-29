package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildCreate extends Packet {
	private int playerId;
	private String name;

	public void read(DataBuffer buffer) {
		this.playerId = buffer.readInt();
		this.name = buffer.readString();
	}

	public void write(DataBuffer buffer) {
		buffer.writeInt(this.playerId);
		buffer.writeString(this.name);
	}
}
