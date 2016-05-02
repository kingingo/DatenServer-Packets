package dev.wolveringer.dataserver.protocoll.packets;

import java.util.ArrayList;
import java.util.UUID;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.report.ReportEntity;
import dev.wolveringer.report.ReportWorker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketReportResponse extends Packet{
	private UUID requestPacket;
	private ReportEntity[] entities;
	
	@Override
	public void read(DataBuffer buffer) {
		requestPacket = buffer.readUUID();
		entities = new ReportEntity[buffer.readInt()];
		for(int i = 0;i<entities.length;i++){
			ArrayList<ReportWorker> workers = new ArrayList<>();
			int reportId = buffer.readInt();
			ReportEntity e = new ReportEntity(reportId, buffer.readInt(), buffer.readString(), buffer.readInt(), buffer.readString(), buffer.readString(), buffer.readLong(), buffer.readBoolean(), workers);
			int length = buffer.readInt();
			for(int j= 0;j<length;j++)
				workers.add(new ReportWorker(reportId, buffer.readInt(), buffer.readLong(), buffer.readLong()));
			entities[i] = e;
		}
		
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(requestPacket);
		buffer.writeInt(entities.length);
		for(ReportEntity e : entities){
			buffer.writeInt(e.getReportId());
			buffer.writeInt(e.getReporter());
			buffer.writeString(e.getReporterIp());
			buffer.writeInt(e.getTarget());
			buffer.writeString(e.getReson());
			buffer.writeString(e.getInfos());
			buffer.writeLong(e.getTime());
			buffer.writeBoolean(e.isOpen());
			buffer.writeInt(e.getWorkers().size());
			for(ReportWorker r : e.getWorkers()){
				buffer.writeInt(r.getPlayerId());
				buffer.writeLong(r.getStart());
				buffer.writeLong(r.getEnd());
			}
		}
	}
}
