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
public class PacketGildMoneyHistoryAction extends Packet{
	public static enum Action {
		ADD,
		//REMOVE,
		GET;
	}

	private UUID gilde;
	private GildeType type;
	private Action action;

	//ADD
	private int playerId;
	private int amount;
	private String reason;
	//-ADD

	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		type = GildeType.values()[buffer.readByte()];
		action = Action.values()[buffer.readByte()];

		if(action == Action.ADD){
			playerId = buffer.readInt();
			amount = buffer.readInt();
			reason = buffer.readString();
		}

	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeByte(type.ordinal());
		buffer.writeByte(action.ordinal());

		if(action == Action.ADD){
			buffer.writeInt(playerId);
			buffer.writeInt(amount);
			buffer.writeString(reason);
		}
	}
}
