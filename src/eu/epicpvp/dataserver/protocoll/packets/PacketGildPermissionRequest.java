package eu.epicpvp.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import eu.epicpvp.datenserver.definitions.gilde.GildeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PacketGildPermissionRequest extends Packet {
	private UUID gilde;
	private GildeType type;
	private String group = null;

	/**
	 * Request all groups from gilde
	 */
	public PacketGildPermissionRequest(UUID gilde,GildeType type) {
		this(gilde, type, null);
	}
	/**
	 * Request all permissions from gilde group
	 */
	public PacketGildPermissionRequest(UUID gilde,GildeType type,String group) {
		this.gilde = gilde;
		this.type = type;
		this.group = group;
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeEnum(type);
		buffer.writeString(group);
	}
	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		type = buffer.readEnum(GildeType.class);
		group = buffer.readString();
	}
}
