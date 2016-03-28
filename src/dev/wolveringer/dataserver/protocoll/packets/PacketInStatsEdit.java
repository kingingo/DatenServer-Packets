package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.gamestats.GameType;
import dev.wolveringer.dataserver.gamestats.StatsKey;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PacketInStatsEdit extends Packet {
	@AllArgsConstructor
	@Getter
	public static class EditStats {
		private GameType game;
		private Action action;
		private StatsKey key;
		private Object value;
	}

	public static enum Action {
		ADD, REMOVE, SET;
		private Action() {
		}
	}

	@Getter
	private UUID player;
	@Getter
	private EditStats[] changes;

	@Override
	public void read(DataBuffer buffer) {
		player = buffer.readUUID();
		changes = new EditStats[buffer.readByte()];

		for (int i = 0; i < changes.length; i++) {
			GameType game = GameType.values()[buffer.readByte()];
			Action action = Action.values()[buffer.readByte()];
			StatsKey key = StatsKey.values()[buffer.readByte()];
			Object value = null;
			int id = -1;
			switch (id = buffer.readByte()) { // Value Type
			case 0:
				value = buffer.readInt();
				break;
			case 1:
				value = buffer.readDouble();
				break;
			case 2:
				value = buffer.readString();
				break;
			default:
				System.out.println("Wron stats id: " + id);
				break;
			}
			changes[i] = new EditStats(game, action,key, value);
		}
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(player);
		
		buffer.writeByte(changes.length);
		for(EditStats stats : changes){
			buffer.writeByte(stats.game.ordinal());
			buffer.writeByte(stats.action.ordinal());
			buffer.writeByte(stats.key.ordinal());
			buffer.writeByte(stats.key.getClassId());
			switch (stats.key.getClassId()) { // Value Type
			case 0:
				buffer.writeInt((int) stats.value);
				break;
			case 1:
				buffer.writeDouble((double) stats.value);
				break;
			case 2:
				buffer.writeString((String) stats.value);
				break;
			default:
				System.out.println("Wron stats id: -1");
				break;
			}
		}
	}
}
