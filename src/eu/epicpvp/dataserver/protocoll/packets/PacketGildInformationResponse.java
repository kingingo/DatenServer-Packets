package eu.epicpvp.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import eu.epicpvp.datenserver.definitions.gilde.GildeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildInformationResponse extends Packet{
	private UUID gilde;
	private GildeType[] activeSections;
	private String name;
	private String shortName;
	private int ownerId;

	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		activeSections = new GildeType[buffer.readInt()];
		for(int i = 0;i<activeSections.length;i++)
			activeSections[i] = buffer.readEnum(GildeType.class);
		name = buffer.readString();
		shortName = buffer.readString();
		ownerId = buffer.readInt();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeInt(activeSections.length);
		for(GildeType type : activeSections)
			buffer.writeEnum(type);
		buffer.writeString(name);
		buffer.writeString(shortName);
		buffer.writeInt(ownerId);
	}
}
