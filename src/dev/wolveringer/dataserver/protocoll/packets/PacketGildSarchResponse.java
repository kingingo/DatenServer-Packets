package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildSarchResponse extends Packet{
	private UUID requested;
	private String name;
	private UUID uuid;
	
	@Override
	public void read(DataBuffer buffer) {
		requested = buffer.readUUID();
		name = buffer.readString();
		uuid = buffer.readUUID();
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(requested);
		buffer.writeString(name);
		buffer.writeUUID(uuid);
	}
}
