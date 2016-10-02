package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.player.Setting;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PacketOutPlayerSettings extends Packet{
	@AllArgsConstructor
	@Getter
	public static class SettingValue {
		private Setting setting;
		private String value;
	}
	private int player;
	private SettingValue[] values;
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(player);
		buffer.writeByte(values.length);
		for(SettingValue val : values){
			buffer.writeByte(val.setting.ordinal());
			buffer.writeString(val.value);
		}
	}
	
	@Override
	public void read(DataBuffer buffer) {
		player = buffer.readInt();
		values = new SettingValue[buffer.readByte()];
		for(int i = 0;i<values.length;i++)
			values[i] = new SettingValue(Setting.values()[buffer.readByte()],buffer.readString());
	}
}
