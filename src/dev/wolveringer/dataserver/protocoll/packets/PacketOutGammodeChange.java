package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.gamestats.GameType;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketOutGammodeChange extends Packet{
	private GameType game;
	private String subType;
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeByte(game.ordinal());
		buffer.writeString(subType);
	}
	
	@Override
	public void read(DataBuffer buffer) {
		game = GameType.values()[buffer.readInt()];
		subType = buffer.readString();
	}
}
