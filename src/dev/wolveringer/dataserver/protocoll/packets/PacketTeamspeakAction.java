package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketTeamspeakAction extends Packet {
	public static enum Action {
		UPDATE_AVATAR, UPDATE_GROUPS, UNLINK;
	}

	private int playerId;
	private Action action;
	private String data;

	@Override
	public void read(DataBuffer buffer) {
		playerId = buffer.readInt();
		action = buffer.readEnum(Action.class);
		data = buffer.readString();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(playerId);
		buffer.writeEnum(action);
		buffer.writeString(data);
	}

}
