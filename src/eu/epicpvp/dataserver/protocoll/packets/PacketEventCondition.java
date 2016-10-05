package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import eu.epicpvp.datenserver.definitions.events.EventCondition;
import eu.epicpvp.datenserver.definitions.events.EventConditions;
import eu.epicpvp.datenserver.definitions.events.EventType;
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
