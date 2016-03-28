package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.dataserver.ban.BanEntity;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketOutBanStats extends Packet{
	private UUID request;
	private BanEntity e;
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(request);
		if(e != null && e.isActive()){
			buffer.writeBoolean(true);
			
			buffer.writeString(e.getUsernames().size() == 0?null:e.getUsernames().get(0));
			buffer.writeString(e.getUuids().size() == 0?null:e.getUuids().get(0).toString());
			buffer.writeString(e.getIp());
			buffer.writeString(e.getBanner());
			buffer.writeString(e.getBannerIp());
			buffer.writeString(e.getBannerUUID()+"");
			buffer.writeLong(e.getEnd());
			buffer.writeInt(e.getLevel());
			buffer.writeString(e.getReson());
		}
		else
		{
			buffer.writeBoolean(false);
		}
	}
	@Override
	public void read(DataBuffer buffer) {
		request = buffer.readUUID();
		if(buffer.readBoolean()){
			String username = buffer.readString();
			String uuid = buffer.readString();
			String ip = buffer.readString();
			String banner = buffer.readString();
			String bannerIp = buffer.readString();
			String bannerUUID = buffer.readString();
			long end = buffer.readLong();
			Integer level = buffer.readInt();
			String reson = buffer.readString();
			e = new BanEntity(ip, username, uuid, banner, bannerUUID, bannerIp, reson, level, end);
		}
		else
			e = new BanEntity.NotBannedBanEntity();
	}
}
