package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.gilde.GildeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildMemberRequest extends Packet{
	private UUID gilde;
	
	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
	}
}
