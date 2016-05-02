package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PacketOutPlayerServer extends Packet{
	@Getter
	private int player;
	@Getter
	private String server;
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(player);
		buffer.writeString(server);
	}
	
	@Override
	public void read(DataBuffer buffer) {
		player = buffer.readInt();
		server = buffer.readString();
	}
}
