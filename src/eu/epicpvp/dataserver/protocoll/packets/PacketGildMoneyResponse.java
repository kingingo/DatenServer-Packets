package eu.epicpvp.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import eu.epicpvp.datenserver.definitions.gilde.GildeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildMoneyResponse extends Packet{
	private UUID gilde;
	private GildeType type;
	private int playerId; //Unused?
	private int response;

	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		type = buffer.readEnum(GildeType.class);
		playerId = buffer.readInt();
		response = buffer.readInt();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeEnum(type);
		buffer.writeInt(playerId);
		buffer.writeInt(response);
	}
}
