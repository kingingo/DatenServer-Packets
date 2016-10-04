package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketPlayerIdResponse extends Packet{
	private UUID requestUUID;
	private int[] ids;

	@Override
	public void read(DataBuffer buffer) {
		requestUUID = buffer.readUUID();
		ids = new int[buffer.readInt()];
		for(int i = 0;i<ids.length;i++)
			ids[i] = buffer.readInt();
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(requestUUID);
		buffer.writeInt(ids.length);
		for(int i : ids)
			buffer.writeInt(i);
	}
}
