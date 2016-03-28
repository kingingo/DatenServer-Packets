package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.gamestats.GameType;
import dev.wolveringer.dataserver.gamestats.StatsKey;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.gamestats.Statistic;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketOutStats extends Packet{
	private UUID player;
	private GameType game;
	private Statistic[] stats;
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(player);
		buffer.writeByte(game.ordinal());
		buffer.writeByte(stats.length);
		
		for(Statistic stat : stats){
			if(stat == null){
				System.out.println("Stat null");
				continue;
			}
			else if(stat.getStatsKey() == null){
				System.out.println("Key = null");
				continue;
			}
			buffer.writeByte(stat.getStatsKey().ordinal());
			buffer.writeByte(stat.getTypeId());
			switch (stat.getTypeId()) {
			case 0:
				buffer.writeInt(stat.asInt());
				break;
			case 1:
				buffer.writeDouble(stat.asDouble());
				break;
			case 2:
				buffer.writeString(stat.asString());
				break;
			default:
				System.out.println("Wron stats id: "+stat.getTypeId());
				break;
			}
		}
	}
	
	@Override
	public void read(DataBuffer buffer) {
		player = buffer.readUUID();
		game = GameType.values()[buffer.readByte()];
		stats = new Statistic[buffer.readByte()];
		
		for(int i = 0;i<stats.length;i++){
			StatsKey key = StatsKey.values()[buffer.readByte()];
			int value = buffer.readByte();
			Object val = null;
			switch (value) {
			case 0:
				val = buffer.readInt();
				break;
			case 1:
				val = buffer.readDouble();
				break;
			case 2:
				val = buffer.readString();
				break;
			default:
				System.out.println("Wron stats id: "+value);
				break;
			}
			stats[i] = new Statistic(key, val);
		}
	}
}
