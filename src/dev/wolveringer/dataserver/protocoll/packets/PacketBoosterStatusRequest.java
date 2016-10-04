package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.booster.BoosterType;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PacketBoosterStatusRequest extends Packet{
	private int playerId;
	private BoosterType type;

	public PacketBoosterStatusRequest(int playerId, BoosterType type) {
		this.playerId = playerId;
		this.type = type;
	}
	public PacketBoosterStatusRequest(BoosterType type) {
		this(-1,type);
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(playerId);
		buffer.writeEnum(type);
	}

	@Override
	public void read(DataBuffer buffer) {
		playerId = buffer.readInt();
		type = buffer.readEnum(BoosterType.class);
	}
}
