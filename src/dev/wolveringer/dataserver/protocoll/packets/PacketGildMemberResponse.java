package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.gilde.GileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PacketGildMemberResponse extends Packet{
	@AllArgsConstructor
	@Getter
	private static class MemeberInformation {
		private int playerId;
		private GileType[] member;
	}
	private UUID gilde;
	private MemeberInformation[] member;
	
	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		int length = buffer.readInt();
		member = new MemeberInformation[length];
		for(int i = 0;i<length;i++){
			int playerId = buffer.readInt();
			GileType[] types = new GileType[buffer.readInt()];
			for(int j = 0;j<types.length;j++)
				types[j] = buffer.readEnum(GileType.class);
			member[i] = new MemeberInformation(playerId, types);
		}
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeInt(member.length);
		for(MemeberInformation i : member){
			buffer.writeInt(i.getPlayerId());
			buffer.writeInt(i.getMember().length);
			for(GileType type : i.getMember())
				buffer.writeEnum(type);
		}
	}
}
