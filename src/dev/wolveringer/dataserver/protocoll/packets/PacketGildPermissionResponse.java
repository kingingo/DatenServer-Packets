package dev.wolveringer.dataserver.protocoll.packets;

import java.util.ArrayList;
import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.gilde.GileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PacketGildPermissionResponse extends Packet{
	private UUID gilde;
	private GileType type;
	private String group;
	private ArrayList<String> response = new ArrayList<>();
	
	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		type = buffer.readEnum(GileType.class);
		int length = buffer.readInt();
		for(int i = 0;i<length;i++)
			response.add(buffer.readString());
	}
		 
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeEnum(type);
		buffer.writeInt(response.size());
		for(String s : response)
			buffer.writeString(s);
	}
}
