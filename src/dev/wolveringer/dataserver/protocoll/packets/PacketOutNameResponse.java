package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.dataserver.protocoll.packets.PacketOutUUIDResponse.UUIDKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PacketOutNameResponse extends Packet{
	private UUIDKey[] response;
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeByte(response.length);
		for(UUIDKey key : response){
			buffer.writeString(key.getName());
			buffer.writeUUID(key.getUuid());
		}
	}
	
	@Override
	public void read(DataBuffer buffer) {
		response = new UUIDKey[buffer.readByte()];
		for (int i = 0; i < response.length; i++) {
			response[i] = new UUIDKey(buffer.readString(), buffer.readUUID());
		}
	}

	public String[] getNames() {
		String[] out = new String[response.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = response[i].getName();
		}
		return out;
	}

	public UUID[] getUUIDs() {
		UUID[] out = new UUID[response.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = response[i].getUuid();
		}
		return out;
	}
}
