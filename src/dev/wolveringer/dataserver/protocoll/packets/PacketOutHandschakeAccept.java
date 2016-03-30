package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PacketOutHandschakeAccept extends Packet{
	@Override
	public void write(DataBuffer buffer) {}
	
	@Override
	public void read(DataBuffer buffer) {}
}
