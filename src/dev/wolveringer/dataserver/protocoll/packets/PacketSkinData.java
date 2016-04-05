package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.skin.Skin;
import dev.wolveringer.skin.SteveSkin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketSkinData extends Packet {
	private Skin skin;
	private UUID requestUUID;
	
	@Override
	public void read(DataBuffer buffer) {
		requestUUID = buffer.readUUID();
		if(buffer.readBoolean()){
			skin = new SteveSkin();
			return;
		}
		skin = new Skin(buffer.readString(), buffer.readString());
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(requestUUID);
		buffer.writeBoolean(skin == null || skin instanceof SteveSkin);
		if(!(skin == null || skin instanceof SteveSkin)){
			buffer.writeString(skin.getRawData());
			buffer.writeString(skin.getSignature());
		}
	}
}
