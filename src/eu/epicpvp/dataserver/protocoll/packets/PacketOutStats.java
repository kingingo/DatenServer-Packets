package eu.epicpvp.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.gamestats.GameType;
import eu.epicpvp.datenserver.definitions.dataserver.gamestats.StatsKey;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import eu.epicpvp.datenserver.definitions.gamestats.Statistic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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
