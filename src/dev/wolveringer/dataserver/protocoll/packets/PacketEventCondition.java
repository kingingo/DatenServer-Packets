package dev.wolveringer.dataserver.protocoll.packets;

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
public class PacketEventCondition extends Packet{
	private EventType eventType;
	private EventConditions type;
	private boolean active;
	private EventCondition condition;
	
	@Override
	public void read(DataBuffer buffer) {
		eventType = EventType.values()[buffer.readInt()];
		type = EventConditions.values()[buffer.readInt()];
		active = buffer.readBoolean();
		
		if(active)
			condition = EventConditions.readCondition(buffer);
	}
	
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(eventType.ordinal());
		buffer.writeInt(type.ordinal());
		buffer.writeBoolean(active);
		if(active)
			EventConditions.writeCondition(condition, buffer);
	}
}
