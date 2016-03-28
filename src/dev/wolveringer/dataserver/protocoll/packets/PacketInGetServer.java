package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PacketInGetServer extends Packet{
	@Getter
	private UUID player;
	@Override
	public void read(DataBuffer buffer) {
		player = buffer.readUUID();
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(player);
	}
}
