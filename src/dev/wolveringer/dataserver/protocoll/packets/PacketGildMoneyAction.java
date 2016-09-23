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
public class PacketGildMoneyAction extends Packet{
	public static enum Action {
		ADD,
		REMOVE,
		GET;
	}
	
	private UUID gilde;
	private GildeType type;
	private Action action;
	private int playerId = -1;
	private int amount;
	private String reason;
	
	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		type = GildeType.values()[buffer.readByte()];
		action = Action.values()[buffer.readByte()];
		if(action != Action.GET){
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
		if(action != Action.GET){
			buffer.writeInt(playerId);
			buffer.writeInt(amount);
			buffer.writeString(reason);
		}
	}
	
	public int getCalculatedAmount(){
		return action == Action.ADD ? Math.abs(amount) : -Math.abs(amount);
	}
}
