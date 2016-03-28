package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.gamestats.GameType;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketOutGammodeChange extends Packet{
	private GameType game;
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeByte(game.ordinal());
	}
	
	@Override
	public void read(DataBuffer buffer) {
		game = GameType.values()[buffer.readInt()];
	}
}
