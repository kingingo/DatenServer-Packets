package dev.wolveringer.dataserver.protocoll.packets;

import java.util.ArrayList;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.events.EventCondition;
import dev.wolveringer.events.EventConditions;
import dev.wolveringer.events.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketEventTypeSettings extends Packet{
	private EventType type;
	private boolean active;
	
	private ArrayList<EventCondition<?>> conditions = new ArrayList<>();
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(type.ordinal());
		buffer.writeBoolean(active);
		
		if(active){
			buffer.writeInt(conditions.size());
			for(EventCondition c : conditions)
				EventConditions.writeCondition(c, buffer);
		}
	}
	
	@Override
	public void read(DataBuffer buffer) {
		type = EventType.values()[buffer.readInt()];
		active = buffer.readBoolean();
		
		if(active){
			int length = buffer.readInt();
			for(int i = 0;i<length;i++)
				conditions.add(EventConditions.readCondition(buffer));
		}
	}
}
