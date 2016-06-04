package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.gilde.GileType;
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
	private GileType[] activeSections;
	private String name;
	private String shortName;
	
	@Override
	public void read(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeInt(activeSections.length);
		for(GileType type : activeSections)
			buffer.writeEnum(type);
		buffer.writeString(name);
		buffer.writeString(shortName);
	}
	
	@Override
	public void write(DataBuffer buffer) {
		gilde = buffer.readUUID();
		activeSections = new GileType[buffer.readInt()];
		for(int i = 0;i<activeSections.length;i++)
			activeSections[i] = buffer.readEnum(GileType.class);
		name = buffer.readString();
		shortName = buffer.readString();
	}
}
