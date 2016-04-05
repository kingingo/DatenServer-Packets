package dev.wolveringer.dataserver.protocoll.packets;

import java.util.ArrayList;
import java.util.List;

import dev.wolveringer.dataserver.gamestats.GameState;
import dev.wolveringer.dataserver.gamestats.GameType;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacketOutServerStatus extends Packet{
	public static enum Action {
		SERVER,
		BUNGEECORD,
		GENERAL,
		GAMETYPE;
	}
	
	private Action action;
	private GameType[] games;
	private String value;
	private String serverId;
	private boolean visiable;
	private GameState state;
	private int player;
	private int maxPlayers;
	private List<String> players;
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeByte(action.ordinal());
		if(action == Action.BUNGEECORD || action == Action.SERVER)
			buffer.writeString(value);
		else if(action == Action.GAMETYPE){
			buffer.writeByte(games.length);
			for(int i = 0;i<games.length;i++)
				buffer.writeByte(games[i].ordinal());
		}
		
		buffer.writeString(serverId);
		buffer.writeBoolean(visiable);
		buffer.writeByte(state.ordinal());
		buffer.writeInt(player);
		buffer.writeInt(maxPlayers);
		buffer.writeBoolean(players != null);
		if(players != null){
			buffer.writeInt(players.size());
			for(String s : players)
				buffer.writeString(s);
		}
	}
	
	@Override
	public void read(DataBuffer buffer) {
		this.action = Action.values()[buffer.readByte()];
		if(action == Action.BUNGEECORD || action == Action.SERVER)
			this.value = buffer.readString();
		else if(action == Action.GAMETYPE){
			games = new GameType[buffer.readByte()];
			for(int i = 0;i<games.length;i++)
				games[i] = GameType.values()[buffer.readByte()];
		}
		
		this.serverId = buffer.readString();
		this.visiable = buffer.readBoolean();
		this.state = GameState.values()[buffer.readByte()];
		this.player = buffer.readInt();
		this.maxPlayers = buffer.readInt();
		if(buffer.readBoolean()){
			players = new ArrayList<>();
			int length = buffer.readInt();
			for(int i = 0;i<length;i++)
				players.add(buffer.readString());
		}
	}
}
