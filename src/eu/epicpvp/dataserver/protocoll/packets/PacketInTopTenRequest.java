package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.gamestats.GameType;
import eu.epicpvp.datenserver.definitions.dataserver.gamestats.StatsKey;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketInTopTenRequest extends Packet{
	private GameType game;
	private StatsKey condition;

	@Override
	public void read(DataBuffer buffer) {
		this.game = GameType.values()[buffer.readByte()];
		this.condition = StatsKey.values()[buffer.readByte()];
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeByte(game.ordinal());
		buffer.writeByte(condition.ordinal());
	}
}
