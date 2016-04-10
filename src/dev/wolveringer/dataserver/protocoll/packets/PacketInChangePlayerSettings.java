package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.player.Setting;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PacketInChangePlayerSettings extends Packet{
	@Getter
	private int player;
	@Getter
	private Setting setting;
	@Getter
	private String value;
	
	@Override
	public void read(DataBuffer buffer) {
		player = buffer.readInt();
		setting = Setting.values()[buffer.readByte()];
		value = buffer.readString();
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(player);
		buffer.writeByte(setting.ordinal());
		buffer.writeString(value);
	}
}
