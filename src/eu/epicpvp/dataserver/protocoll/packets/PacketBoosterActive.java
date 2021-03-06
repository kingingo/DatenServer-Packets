package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.booster.BoosterType;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketBoosterActive extends Packet{
	private int playerId;
	private int time;
	private BoosterType type;

	@Override
	public void read(DataBuffer buffer) {
		playerId = buffer.readInt();
		time = buffer.readInt();
		type = buffer.readEnum(BoosterType.class);
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(playerId);
		buffer.writeInt(time);
		buffer.writeEnum(type);
	}
}
