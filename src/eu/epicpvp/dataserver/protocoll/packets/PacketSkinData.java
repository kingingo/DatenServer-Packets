package eu.epicpvp.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import eu.epicpvp.datenserver.definitions.skin.Skin;
import eu.epicpvp.datenserver.definitions.skin.SteveSkin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketSkinData extends Packet {
	@AllArgsConstructor
	@Getter
	public static class SkinResponse {
		private Skin skin;

		@Override
		public String toString() {
			return "SkinResponse [skin=" + skin + "]";
		}
	}

	private UUID requestUUID;
	private SkinResponse[] reponse;

	@Override
	public void read(DataBuffer buffer) {
		requestUUID = buffer.readUUID();
		reponse = new SkinResponse[buffer.readInt()];
		for(int i = 0;i<reponse.length;i++){
			Skin skin;
			if(buffer.readBoolean()){
				skin = new SteveSkin();
			}
			else
			skin = new Skin(buffer.readString(), buffer.readString());
			reponse[i] = new SkinResponse(skin);
		}
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(requestUUID);
		buffer.writeInt(reponse.length);
		for(int i = 0;i<reponse.length;i++){
			buffer.writeBoolean(reponse[i].getSkin() == null || reponse[i].getSkin() instanceof SteveSkin);
			if(!(reponse[i].getSkin() == null || reponse[i].getSkin() instanceof SteveSkin)){
				buffer.writeString(reponse[i].getSkin().getRawData());
				buffer.writeString(reponse[i].getSkin().getSignature());
			}
		}
	}
}
