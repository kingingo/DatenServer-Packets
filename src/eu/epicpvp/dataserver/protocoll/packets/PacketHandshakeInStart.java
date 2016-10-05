package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.connection.ClientType;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PacketHandshakeInStart extends Packet{
	@Getter
	private String host;
	@Getter
	private String name;
	@Getter
	private byte[] password;
	@Getter
	private ClientType type;
	@Getter
	private String protocollVersion;

	@Override
	public void read(DataBuffer buffer) {
		host = buffer.readString();
		name = buffer.readString();
		password = new byte[buffer.readByte()];
		buffer.readBytes(password);
		type = ClientType.values()[buffer.readByte()];
		protocollVersion = buffer.readString();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeString(host);
		buffer.writeString(name);
		buffer.writeByte(password.length);
		buffer.writeBytes(password);
		buffer.writeByte(type.ordinal());
		buffer.writeString(protocollVersion);
	}
}
