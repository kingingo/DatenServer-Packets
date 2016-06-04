package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.gilde.GileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildPermissionEdit extends Packet{
	public static enum Action {
		ADD_PERMISSION,
		REMOVE_PERMISSION,
		CREATE_GROUP;
	}
	
	private UUID gilde;
	private GileType type;
	private Action action;
	private String group;
	private String permission;
	
	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		type = buffer.readEnum(GileType.class);
		action = buffer.readEnum(Action.class);
		permission = buffer.readString();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeEnum(type);
		buffer.writeEnum(action);
		buffer.writeString(permission);
	}
	
}
