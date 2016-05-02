package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PacketOutHandschakeAccept extends Packet{
	private String wm = "Welcome to the datenserver"; //Welcome message
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeString(wm);
	}
	
	@Override
	public void read(DataBuffer buffer) {
		wm = buffer.readString();
	}
}
