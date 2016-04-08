package dev.wolveringer.dataserver.protocoll.packets;

import dev.wolveringer.dataserver.protocoll.DataBuffer;
import dev.wolveringer.events.Event;
import dev.wolveringer.events.EventRegistry;

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
