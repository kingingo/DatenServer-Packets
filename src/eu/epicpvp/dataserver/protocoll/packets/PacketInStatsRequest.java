package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.gamestats.GameType;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PacketInStatsRequest extends Packet{
	private int player;
	private GameType game;

	@Override
	public void read(DataBuffer buffer) {
		player = buffer.readInt();
		game = GameType.values()[buffer.readByte()];
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(player);
		buffer.writeByte(game.ordinal());
	}
}
