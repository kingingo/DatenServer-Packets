package dev.wolveringer.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.gamestats.GameType;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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
		game = GameType.values()[buffer.readByte()];
		subType = buffer.readString();
	}
}
