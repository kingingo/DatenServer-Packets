package dev.wolveringer.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PacketDisconnect extends Packet{
	@Getter
	private String reson = null;

	@Override
	public void read(DataBuffer buffer) {
		reson = buffer.readString();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeString(reson);
	}
}
