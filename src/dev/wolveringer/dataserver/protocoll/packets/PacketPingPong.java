package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PacketPingPong extends Packet{
	private long time;
	
	@Override
	public void read(DataBuffer buffer) {
		time = buffer.readLong();
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeLong(time);
	}
}
