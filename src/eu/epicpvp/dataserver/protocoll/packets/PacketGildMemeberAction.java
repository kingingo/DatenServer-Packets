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
public class PacketGildMemeberAction extends Packet{
	public static enum Action {
		KICK,
		INVITE,
		REMOVE_INVITE,
		ACCEPT_REQUEST,
		//DENY_REQUEST,
		CHANGE_GROUP;
	}

	private UUID gilde;
	private GildeType type;
	private int playerId;
	private Action action;
	private String data;

	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		type = buffer.readEnum(GildeType.class);
		playerId = buffer.readInt();
		action = buffer.readEnum(Action.class);
		data = buffer.readString();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeEnum(type);
		buffer.writeInt(playerId);
		buffer.writeEnum(action);
		buffer.writeString(data);
	}
}
