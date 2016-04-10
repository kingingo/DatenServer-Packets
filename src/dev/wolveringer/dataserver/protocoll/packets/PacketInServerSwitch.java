package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PacketInServerSwitch extends Packet {
	@Getter
	private int player;
	@Getter
	private String server;

	public void read(DataBuffer buffer) {
		player = buffer.readInt();
		server = buffer.readString();
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(player);
		buffer.writeString(server);
	}
}
