package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.player.Setting;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketSettingUpdate extends Packet{
	private UUID player;
	private Setting setting;
	private String value;
	
	public void read(dev.wolveringer.dataserver.protocoll.DataBuffer buffer) {
		player = buffer.readUUID();
		setting = Setting.values()[buffer.readInt()];
		value = buffer.readString();
	};
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(player);
		buffer.writeInt(setting.ordinal());
		buffer.writeString(value);
	}
}
