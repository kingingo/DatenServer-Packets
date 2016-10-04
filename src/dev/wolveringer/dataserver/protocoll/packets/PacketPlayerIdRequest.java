package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PacketPlayerIdRequest extends Packet{
	private String[] names;
	private UUID[] uuids;

	public PacketPlayerIdRequest(String...names){
		this.names = names;
	}

	public PacketPlayerIdRequest(UUID...uuid){
		this.uuids = uuid;
	}

	@Override
	public void read(DataBuffer buffer) {
		switch (buffer.readInt()) {
		case 0:
			names = new String[buffer.readInt()];
			for(int i = 0;i<names.length;i++)
				names[i] = buffer.readString();
			break;
		case 1:
			uuids = new UUID[buffer.readInt()];
			for(int i = 0;i<uuids.length;i++)
				uuids[i] = buffer.readUUID();
			break;
		default:
			break;
		}
	}
	public void write(DataBuffer buffer) {
		if(names != null){
			buffer.writeInt(0);
			buffer.writeInt(names.length);
			for(String name : names)
				buffer.writeString(name);
		}
		else if(uuids != null){
			buffer.writeInt(1);
			buffer.writeInt(uuids.length);
			for(UUID uuid : uuids)
				buffer.writeUUID(uuid);
		}
	};
}
