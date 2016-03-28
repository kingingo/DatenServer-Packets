package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.gamestats.GameType;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PacketInStatsRequest extends Packet{
	private UUID player;
	private GameType game;
	
	@Override
	public void read(DataBuffer buffer) {
		player = buffer.readUUID();
		game = GameType.values()[buffer.readByte()];
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(player);
		buffer.writeByte(game.ordinal());
	}
}
