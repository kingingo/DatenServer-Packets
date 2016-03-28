package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class PacketInBanPlayer extends Packet{
	private String name;
	private String ip;
	private String uuid;
	
	private String bannerName;
	private String bannerIp;
	private String bannerUuid;
	
	private long end;
	private int level;
	
	private String reson;
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeString(name);
		buffer.writeString(ip);
		buffer.writeString(uuid);
		buffer.writeString(bannerName);
		buffer.writeString(bannerIp);
		buffer.writeString(bannerUuid);
		buffer.writeLong(end);
		buffer.writeInt(level);
		buffer.writeString(reson);
	}
	
	@Override
	public void read(DataBuffer buffer) {
		name = buffer.readString();
		ip = buffer.readString();
		uuid = buffer.readString();
		bannerName = buffer.readString();
		bannerIp = buffer.readString();
		bannerUuid = buffer.readString();
		end = buffer.readLong();
		level = buffer.readInt();
		reson = buffer.readString();
	}
	
}
