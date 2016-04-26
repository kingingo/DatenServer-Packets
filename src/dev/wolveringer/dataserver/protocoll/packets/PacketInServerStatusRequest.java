package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.gamestats.GameType;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.dataserver.protocoll.packets.PacketOutServerStatus.Action;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketInServerStatusRequest extends Packet{
	private Action action;
	private String value;
	private boolean player;
	private GameType[] games;
	
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
		this.player = buffer.readBoolean();
	}
	
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
		buffer.writeBoolean(player);
	}
}
