package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PacketInServerSwitch extends Packet {
	@Getter
	private UUID player;
	@Getter
	private String server;

	public void read(DataBuffer buffer) {
		player = buffer.readUUID();
		server = buffer.readString();
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(player);
		buffer.writeString(server);
	}
}
