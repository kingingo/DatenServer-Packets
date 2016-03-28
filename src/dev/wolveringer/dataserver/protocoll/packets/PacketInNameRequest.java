package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PacketInNameRequest extends Packet{
	@Getter
	private UUID[] uuids;
	
	@Override
	public void read(DataBuffer buffer) {
		uuids = new UUID[buffer.readByte()];
		for(int i = 0;i<uuids.length;i++)
			uuids[i] = buffer.readUUID();
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeByte(uuids.length);
		for(UUID u : uuids)
			buffer.writeUUID(u);
	}
}
