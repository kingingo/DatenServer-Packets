package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.gilde.GileType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PacketGildPermissionRequest extends Packet {
	private UUID gilde;
	private GileType type;
	private String group = null;
	
	/**
	 * Request all groups from gilde
	 */
	public PacketGildPermissionRequest(UUID gilde,GileType type) {
		this(gilde, type, null);
	}
	/**
	 * Request all permissions from gilde group
	 */
	public PacketGildPermissionRequest(UUID gilde,GileType type,String group) {
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
		type = buffer.readEnum(GileType.class);
		group = buffer.readString();
	}
}
