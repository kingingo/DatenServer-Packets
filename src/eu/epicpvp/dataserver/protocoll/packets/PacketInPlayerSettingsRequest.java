package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.player.Setting;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PacketInPlayerSettingsRequest extends Packet{
	@Getter
	private int player;
	@Getter
	private Setting[] settings;

	@Override
	public void read(DataBuffer buffer) {
		player = buffer.readInt();
		settings = new Setting[buffer.readByte()];
		for(int i = 0;i<settings.length;i++)
			settings[i] = Setting.values()[buffer.readByte()];
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(player);
		buffer.writeByte(settings.length);
		for(Setting s : settings)
			buffer.writeByte(s.ordinal());
	}
}
