package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
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

	@Override
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
