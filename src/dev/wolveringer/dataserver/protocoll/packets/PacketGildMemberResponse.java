package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import dev.wolveringer.gilde.GildeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PacketGildMemberResponse extends Packet{
	@AllArgsConstructor
	@Getter
	public static class MemberInformation {
		private int playerId;
		private GildeType[] member;
		private String[] groups;
	}
	private UUID gilde;
	private MemberInformation[] member;

	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		int length = buffer.readInt();
		member = new MemberInformation[length];
		for(int i = 0;i<length;i++){
			int playerId = buffer.readInt();
			GildeType[] types = new GildeType[buffer.readInt()];
			for(int j = 0;j<types.length;j++)
				types[j] = buffer.readEnum(GildeType.class);
			String[] groups = new String[types.length];
			for(int j = 0;j<types.length;j++)
				groups[j] = buffer.readString();
			member[i] = new MemberInformation(playerId, types,groups);
		}
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeInt(member.length);
		for(MemberInformation i : member){
			buffer.writeInt(i.getPlayerId());
			buffer.writeInt(i.getMember().length);
			if(i.getMember().length != i.getGroups().length)
				throw new IllegalArgumentException("Group length m,ust be equal to member length");
			for(GildeType type : i.getMember())
				buffer.writeEnum(type);
			for(String s : i.getGroups())
				buffer.writeString(s);
		}
	}
}
