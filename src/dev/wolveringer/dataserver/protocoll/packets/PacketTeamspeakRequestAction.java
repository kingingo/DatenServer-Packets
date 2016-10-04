package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketTeamspeakRequestAction extends Packet{
	private UUID request;
	private boolean accept;

	@Override
	public void read(DataBuffer buffer) {
		request = buffer.readUUID();
		accept = buffer.readBoolean();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(request);
		buffer.writeBoolean(accept);
	}

}
