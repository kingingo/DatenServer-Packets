package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.gamestats.GameType;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PacketOutLobbyServer extends Packet{
	@AllArgsConstructor
	@Getter
	public static class ServerKey {
		private String serverId;
		private int player;
		private int maxPlayer;
		private String mots;
	}
	
	@AllArgsConstructor
	@Getter
	public static class GameServers {
		private GameType game;
		private ServerKey[] servers;
	}
	
	private GameServers[] response;
	
	@Override
	public void read(DataBuffer buffer) {
		response = new GameServers[buffer.readInt()];
		for (int i = 0; i < response.length; i++){
			GameType game = GameType.values()[buffer.readByte()];
			ServerKey[] servers = new ServerKey[buffer.readInt()];
			for (int j = 0; j < servers.length; j++) {
				servers[j] = new ServerKey(buffer.readString(), buffer.readInt(), buffer.readInt(), buffer.readString());
			}
			response[i] = new GameServers(game, servers);
		}
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(response.length);
		for (int i = 0; i < response.length; i++) {
			buffer.writeByte(response[i].game.ordinal());
			buffer.writeInt(response[i].servers.length);
			for (int j = 0; j < response[i].servers.length; j++) {
				buffer.writeString(response[i].servers[j].serverId);
				buffer.writeInt(response[i].servers[j].player);
				buffer.writeInt(response[i].servers[j].maxPlayer);
				buffer.writeString(response[i].servers[j].mots);
			}
		}
	}
}
