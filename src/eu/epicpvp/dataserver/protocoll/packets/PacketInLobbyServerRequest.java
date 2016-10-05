package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.gamestats.GameType;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
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
			if(r == null)
				throw new NullPointerException("GameRequest is null!");
			if(r.getGame() == null)
				throw new NullPointerException("GameRequest->getGame() is null!");
			buffer.writeByte(r.getGame().ordinal());
			buffer.writeInt(r.getMaxServers());
		}
	}
}
