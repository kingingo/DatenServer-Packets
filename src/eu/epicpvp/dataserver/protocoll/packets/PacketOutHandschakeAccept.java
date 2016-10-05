package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
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
