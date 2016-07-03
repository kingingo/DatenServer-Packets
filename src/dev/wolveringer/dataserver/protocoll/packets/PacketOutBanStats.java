package dev.wolveringer.dataserver.protocoll.packets;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dev.wolveringer.dataserver.ban.BanEntity;
import dev.wolveringer.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PacketOutBanStats extends Packet{
	private UUID request;
	private List<BanEntity> entries;
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(request);
		buffer.writeInt(entries.size());
		for(BanEntity e :  entries){
			buffer.writeString(e.getUsernames().size() == 0?null:e.getUsernames().get(0));
			buffer.writeString(e.getUuids().size() == 0?null:e.getUuids().get(0).toString());
			buffer.writeString(e.getIp());
			buffer.writeString(e.getBanner());
			buffer.writeString(e.getBannerIp());
			buffer.writeString(e.getBannerUUID()+"");
			buffer.writeLong(e.getEnd());
			buffer.writeInt(e.getLevel());
			buffer.writeString(e.getReson());
			buffer.writeLong(e.getDate());
		}
	}
	@Override
	public void read(DataBuffer buffer) {
		request = buffer.readUUID();
		int length = buffer.readInt();
		entries = new ArrayList<>(length);
		for(int i = 0;i<length;i++){
			String username = buffer.readString();
			String uuid = buffer.readString();
			String ip = buffer.readString();
			String banner = buffer.readString();
			String bannerIp = buffer.readString();
			String bannerUUID = buffer.readString();
			long end = buffer.readLong();
			Integer level = buffer.readInt();
			String reson = buffer.readString();
			String date = Long.toString(buffer.readLong());
			entries.add(new BanEntity(ip, username, uuid, banner, bannerUUID, bannerIp, date, reson, level, end));
		}
	}
}
