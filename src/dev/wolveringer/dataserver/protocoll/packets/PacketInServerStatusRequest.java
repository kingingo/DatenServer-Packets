package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.dataserver.protocoll.packets.PacketOutServerStatus.Action;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketInServerStatusRequest extends Packet{
	private Action action;
	private String value;
	private boolean player;
	
	@Override
	public void read(DataBuffer buffer) {
		this.action = Action.values()[buffer.readByte()];
		this.value = buffer.readString();
		this.player = buffer.readBoolean();
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeByte(action.ordinal());
		buffer.writeString(value);
		buffer.writeBoolean(player);
	}
}
