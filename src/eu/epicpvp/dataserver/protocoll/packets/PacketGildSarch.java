package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildSarch extends Packet {
	public static enum Action {
		PLAYER, GILDE_NAME, TYPE, OWN_GILD;
	}

	private Action action;
	private String value;

	@Override
	public void read(DataBuffer buffer) {
		action = buffer.readEnum(Action.class);
		value = buffer.readString();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeEnum(action);
		buffer.writeString(value);
	}

}
