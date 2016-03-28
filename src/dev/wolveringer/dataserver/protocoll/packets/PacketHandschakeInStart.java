package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.client.connection.ClientType;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PacketHandschakeInStart extends Packet{
	@Getter
	private String host;
	@Getter
	private String name;
	@Getter
	private byte[] password;
	@Getter
	private ClientType type;
	@Override
	public void read(DataBuffer buffer) {
		host = buffer.readString();
		name = buffer.readString();
		password = new byte[buffer.readByte()];
		buffer.readBytes(password);
		type = ClientType.values()[buffer.readByte()];
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeString(host);
		buffer.writeString(name);
		buffer.writeByte(password.length);
		buffer.writeBytes(password);
		buffer.writeByte(type.ordinal());
	}
}
