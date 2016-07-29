package dev.wolveringer.dataserver.protocoll.packets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildSarchResponse extends Packet {
	private UUID requested;

	private HashMap<UUID, String> response = new HashMap<UUID, String>();

	public PacketGildSarchResponse(UUID packetUUID, List<Entry<UUID, String>> response) {
		for(Entry<UUID, String> e : response)
			this.response.put(e.getKey(), e.getValue());
		this.requested = packetUUID;
	}
	
	public void read(DataBuffer buffer) {
		this.requested = buffer.readUUID();
		int length = buffer.readInt();
		for (int i = 0; i < length; i++) {
			this.response.put(buffer.readUUID(), buffer.readString());
		}
	}

	public void write(DataBuffer buffer) {
		buffer.writeUUID(this.requested);
		buffer.writeInt(this.response.size());
		for (Map.Entry<UUID, String> e : this.response.entrySet()) {
			buffer.writeUUID((UUID) e.getKey());
			buffer.writeString((String) e.getValue());
		}
	}
}
