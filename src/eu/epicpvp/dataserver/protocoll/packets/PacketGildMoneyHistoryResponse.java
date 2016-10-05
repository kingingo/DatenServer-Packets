package eu.epicpvp.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import eu.epicpvp.datenserver.definitions.gilde.GildeType;
import eu.epicpvp.datenserver.definitions.gilde.MoneyLogRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildMoneyHistoryResponse extends Packet{
	private UUID gilde;
	private GildeType type;
	private MoneyLogRecord[] records;

	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		type = buffer.readEnum(GildeType.class);
		records = new MoneyLogRecord[buffer.readInt()];
		for(int i = 0;i<records.length;i++)
			records[i] = new MoneyLogRecord(buffer.readLong(), buffer.readInt(), buffer.readInt(), buffer.readString());
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeEnum(type);
		buffer.writeInt(records.length);
		for(MoneyLogRecord r : records){
			buffer.writeLong(r.getDate());
			buffer.writeInt(r.getPlayerId());
			buffer.writeInt(r.getAmount());
			buffer.writeString(r.getMessage());
		}
	}
}
