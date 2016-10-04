package dev.wolveringer.dataserver.protocoll.packets;

import java.util.UUID;

import dev.wolveringer.client.connection.ClientType;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PacketForward extends Packet{
	private Packet packet;

	@Getter
	private String target;
	@Getter
	private ClientType ctarget;

	private byte[] data;

	public PacketForward(String target,Packet packet){
		if(Packet.getPacketId(packet,PacketDirection.TO_CLIENT) == -1)
			throw new RuntimeException("Packet not found");
		DataBuffer buffer = new DataBuffer();
		buffer.writeInt(Packet.getPacketId(packet,PacketDirection.TO_CLIENT));
		buffer.writeUUID(UUID.randomUUID());
		packet.write(buffer);
		data = new byte[buffer.writerIndex()];
		System.arraycopy(buffer.array(), 0, data, 0, buffer.writerIndex());
		buffer.release();
		this.target = target;

		if(target == null)
			throw new NullPointerException();
	}

	public PacketForward(ClientType target,Packet packet){
		if(Packet.getPacketId(packet,PacketDirection.TO_CLIENT) == -1)
			throw new RuntimeException("Packet not found");
		DataBuffer buffer = new DataBuffer();
		buffer.writeInt(Packet.getPacketId(packet,PacketDirection.TO_CLIENT));
		buffer.writeUUID(UUID.randomUUID());
		packet.write(buffer);
		data = new byte[buffer.writerIndex()];
		System.arraycopy(buffer.array(), 0, data, 0, buffer.writerIndex());
		buffer.release();
		this.ctarget = target;

		if(ctarget == null)
			throw new NullPointerException();
	}


	@Override
	public void read(DataBuffer buffer) {
		target = buffer.readString();
		int var0 = buffer.readByte();
		if(var0 != -1)
			ctarget = ClientType.values()[var0];
		data = new byte[buffer.readInt()];
		buffer.readBytes(data);
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeString(target);
		if(ctarget == null)
			buffer.writeByte(-1);
		else
			buffer.writeByte(ctarget.ordinal());
		buffer.writeInt(data.length);
		buffer.writeBytes(data);
	}

	public Packet getPacket(){
		if(packet == null){
			DataBuffer buff = new DataBuffer(data);
			packet = Packet.createPacket(buff.readInt(), buff, PacketDirection.TO_CLIENT);
		}
		return packet;
	}
}
