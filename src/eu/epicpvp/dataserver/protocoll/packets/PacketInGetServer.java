package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PacketInGetServer extends Packet{
	@Getter
	private int player;
	@Override
	public void read(DataBuffer buffer) {
		player = buffer.readInt();
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(player);
	}
}
