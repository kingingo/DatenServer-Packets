package dev.wolveringer.dataserver.protocoll.packets;

import java.util.Arrays;

import dev.wolveringer.dataserver.gamestats.GameType;
import dev.wolveringer.dataserver.gamestats.StatsKey;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketOutTopTen extends Packet{
	@AllArgsConstructor
	@Getter
	public static class RankInformation {
		private String player;
		private String topValue;
	}
	
	private GameType game;
	private StatsKey condition;
	private RankInformation[] ranks;
	
	@Override
	public void read(DataBuffer buffer) {
		game = GameType.values()[buffer.readByte()];
		condition = StatsKey.values()[buffer.readByte()];
		ranks = new RankInformation[buffer.readInt()];
		for (int i = 0; i < ranks.length; i++) {
			ranks[i] = new RankInformation(buffer.readString(), buffer.readString());
		}
	}
	public void write(DataBuffer buffer) {
		buffer.writeByte(game.ordinal());
		buffer.writeByte(condition.ordinal());
		buffer.writeInt(ranks.length);
		for (int i = 0; i < ranks.length; i++) {
			buffer.writeString(ranks[i].player);
			buffer.writeString(ranks[i].topValue);
		}
	}
	@Override
	public String toString() {
		return "PacketOutTopTen [game=" + game + ", condition=" + condition + ", ranks=" + Arrays.toString(ranks) + "]";
	}
}
