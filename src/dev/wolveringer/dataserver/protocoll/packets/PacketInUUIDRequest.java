package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PacketInUUIDRequest extends Packet {
	private String[] players;

	@Override
	public void read(DataBuffer buffer) {
		players = new String[buffer.readByte()];
		for(int i = 0;i<players.length;i++)
			players[i] = buffer.readString();
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeByte(players.length);
		for(int i = 0;i<players.length;i++)
			buffer.writeString(players[i]);
	}
}
