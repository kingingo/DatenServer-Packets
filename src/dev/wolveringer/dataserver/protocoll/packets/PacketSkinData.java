package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.skin.Skin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketSkinData extends Packet {
	private Skin skin;

	@Override
	public void read(DataBuffer buffer) {
		skin = new Skin(buffer.readString(), buffer.readString());
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeString(skin.getRawData());
		buffer.writeString(skin.getSignature());
	}
}
