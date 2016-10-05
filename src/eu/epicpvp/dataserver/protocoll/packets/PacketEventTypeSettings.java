package eu.epicpvp.dataserver.protocoll.packets;

import java.util.ArrayList;

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
