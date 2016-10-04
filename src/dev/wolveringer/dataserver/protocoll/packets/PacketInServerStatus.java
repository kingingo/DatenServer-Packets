package dev.wolveringer.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.gamestats.GameState;
import eu.epicpvp.datenserver.definitions.dataserver.gamestats.GameType;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacketInServerStatus extends Packet{
	private int bitmask = 0; //TODO minimize data

	private int players;
	private int maxPlayers;
	private String mots; //Message of the server :D equals? <-> Message of the day (MOTD)
	private GameType typ;
	private GameState state;
	private String substate;
	private boolean listed;
	private String serverId;

	@Override
	public void read(DataBuffer buffer) {
		bitmask = buffer.readByte();
		players = buffer.readInt();
		maxPlayers = buffer.readInt();
		mots = buffer.readString();
		typ = GameType.values()[buffer.readByte()];
		substate = buffer.readString();
		state = GameState.values()[buffer.readByte()];
		serverId = buffer.readString();
		listed = buffer.readBoolean();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeByte(bitmask);
		buffer.writeInt(players);
		buffer.writeInt(maxPlayers);
		buffer.writeString(mots);
		buffer.writeByte(typ.ordinal());
		buffer.writeString(substate);
		buffer.writeByte(state.ordinal());
		buffer.writeString(serverId);
		buffer.writeBoolean(listed);
	}
}
