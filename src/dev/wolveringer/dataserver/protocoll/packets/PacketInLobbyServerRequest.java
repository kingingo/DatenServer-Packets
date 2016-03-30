package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.gamestats.GameType;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PacketInLobbyServerRequest extends Packet{
	@AllArgsConstructor
	@Getter
	@Setter
	public static class GameRequest {
		private GameType game;
		private int maxServers;
	}
	
	private GameRequest[] request;
	
	@Override
	public void read(DataBuffer buffer) {
		this.request = new GameRequest[buffer.readByte()];
		for (int i = 0; i < request.length; i++) {
			request[i] = new GameRequest(GameType.values()[buffer.readByte()], buffer.readInt());
		}
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeByte(request.length);
		for(GameRequest r : request){
			buffer.writeByte(r.game.ordinal());
			buffer.writeInt(r.getMaxServers());
		}
	}
}
