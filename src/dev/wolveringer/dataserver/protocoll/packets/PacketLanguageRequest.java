package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.player.LanguageType;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PacketLanguageRequest extends Packet{
	private LanguageType type;
	private double version;
	
	@Override
	public void read(DataBuffer buffer) {
		this.type = LanguageType.values()[buffer.readInt()];
		this.version = buffer.readDouble();
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(type.ordinal());
		buffer.writeDouble(version);
	}
}
