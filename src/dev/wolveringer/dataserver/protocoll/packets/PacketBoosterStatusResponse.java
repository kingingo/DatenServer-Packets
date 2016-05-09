package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.booster.BoosterType;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketBoosterStatusResponse extends Packet{
	private int playerId;
	private BoosterType type;
	private long start;
	private int time;
	
	@Override
	public void read(DataBuffer buffer) {
		playerId = buffer.readInt();
		type = buffer.readEnum(BoosterType.class);
		start = buffer.readLong();
		time = buffer.readInt();
	}
	public void write(DataBuffer buffer) {
		buffer.writeInt(playerId);
		buffer.writeEnum(type);
		buffer.writeLong(start);
		buffer.writeInt(time);
	};
}
