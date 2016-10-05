package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import eu.epicpvp.datenserver.definitions.events.Event;
import eu.epicpvp.datenserver.definitions.events.EventRegistry;

public class PacketEventFire extends Packet{
	private Event event;

	public PacketEventFire() {}

	public PacketEventFire(Event event) {
		this.event = event;
	}

	public Event getEvent() {
		return event;
	}
	@Override
	public void read(DataBuffer buffer) {
		event = EventRegistry.createEvent(buffer.readInt(), buffer);
	}
	@Override
	public void write(DataBuffer buffer) {
		buffer.writeInt(EventRegistry.getEventId(event));
		event.write(buffer);
	}
}
