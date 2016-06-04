package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildInformationRequest extends Packet{
	private UUID gild;
	
	@Override
	public void read(DataBuffer buffer) {
		gild = buffer.readUUID();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gild);
	}

}
