package eu.epicpvp.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import eu.epicpvp.datenserver.definitions.gilde.GildeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildPermissionEdit extends Packet{
	public static enum Action {
		CHANGE_GROUP,
		ADD_PERMISSION,
		REMOVE_PERMISSION,
		CREATE_GROUP,
		DELETE_GROUP;
	}

	private UUID gilde;
	private GildeType type;
	private Action action;
	private String group;
	private String permission;

	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		type = buffer.readEnum(GildeType.class);
		group = buffer.readString();
		action = buffer.readEnum(Action.class);
		permission = buffer.readString();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeEnum(type);
		buffer.writeString(group);
		buffer.writeEnum(action);
		buffer.writeString(permission);
	}

}
