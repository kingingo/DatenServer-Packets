package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketReportRequest extends Packet{
	public static enum RequestType {
		PLAYER_OPEN_REPORTS,
		OPEN_REPORTS;
	}

	private RequestType type;
	private int value;

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeEnum(type);
		buffer.writeInt(value);
	}
	@Override
	public void read(DataBuffer buffer) {
		type = buffer.readEnum(RequestType.class);
		value = buffer.readInt();
	}
}
