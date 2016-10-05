package eu.epicpvp.dataserver.protocoll.packets;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import eu.epicpvp.datenserver.definitions.gilde.GildeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PacketGildPermissionResponse extends Packet{
	private UUID gilde;
	private GildeType type;
	private String group;
	private List<String> response = new ArrayList<>();

	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		type = buffer.readEnum(GildeType.class);
		group = buffer.readString();
		int length = buffer.readInt();
		for(int i = 0;i<length;i++)
			response.add(buffer.readString());
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeEnum(type);
		buffer.writeString(group);
		buffer.writeInt(response.size());
		for(String s : response)
			buffer.writeString(s);
	}
}
