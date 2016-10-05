package eu.epicpvp.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PacketInBanStatsRequest extends Packet{
	@Getter
	private UUID player;
	@Getter
	private String ip;
	@Getter
	private String name;
	@Getter
	private int deep;

	@Override
	public void read(DataBuffer buffer) {
		player = buffer.readUUID();
		ip = buffer.readString();
		name = buffer.readString();
		deep = buffer.readInt();
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(player);
		buffer.writeString(ip);
		buffer.writeString(name);
		buffer.writeInt(deep);
	}
}
