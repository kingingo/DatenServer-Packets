package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.player.LanguageType;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketLanguageResponse extends Packet {
	private LanguageType type;
	private double version;
	private String fileContains;

	@Override
	public void read(DataBuffer buffer) {
		type = buffer.readEnum(LanguageType.class);
		version = buffer.readDouble();
		if(buffer.readBoolean())
			fileContains = buffer.readString();
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeEnum(type);
		buffer.writeDouble(version);
		buffer.writeBoolean(fileContains != null);
		if(fileContains != null)
			buffer.writeString(fileContains);
	}
}
