package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import dev.wolveringer.gilde.GildeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildUpdateSectionStatus extends Packet{
	private UUID gilde;
	private GildeType type;
	private boolean state;

	@Override
	public void read(DataBuffer buffer) {
		this.gilde = buffer.readUUID();
		this.type = buffer.readEnum(GildeType.class);
		this.state = buffer.readBoolean();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeEnum(type);
		buffer.writeBoolean(state);
	}

}
