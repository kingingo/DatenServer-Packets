package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PacketOutUUIDResponse extends Packet{
	@Getter
	@AllArgsConstructor
	public static class UUIDKey {
		private String name;
		private UUID uuid;
	}
	
	private UUIDKey[] uuids = null;
	private String[] names = null;
	
	public PacketOutUUIDResponse(UUIDKey[] uuids) {
		this.uuids = uuids;
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeByte(uuids.length);
		for(UUIDKey k : uuids){
			buffer.writeString(k.name);
			buffer.writeUUID(k.uuid);
		}
	}
	
	@Override
	public void read(DataBuffer buffer) {
		uuids = new UUIDKey[buffer.readByte()];
		names = new String[uuids.length];
		for(int i = 0;i<uuids.length;i++){
			uuids[i] = new UUIDKey(names[i] = buffer.readString(), buffer.readUUID());
		}
	}
}
