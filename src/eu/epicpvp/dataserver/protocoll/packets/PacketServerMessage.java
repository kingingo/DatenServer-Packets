package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.connection.ClientType;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PacketServerMessage extends Packet{
	@AllArgsConstructor
	@Getter
	public static class Target {
		private ClientType targetType;
		private String target = null;
	}

	public PacketServerMessage(String channel,String target,DataBuffer message){
		this.channel = channel;
		this.targets = new Target[]{new Target(null, target)};
		this.message = new byte[message.writerIndex()];
		System.arraycopy(message.array(), 0, this.message, 0, message.writerIndex());
	}

	public PacketServerMessage(String channel,ClientType target,DataBuffer message){
		this.channel = channel;
		this.targets = new Target[]{new Target(target, null)};
		this.message = new byte[message.writerIndex()];
		System.arraycopy(message.array(), 0, this.message, 0, message.writerIndex());
	}

	public PacketServerMessage(String channel,ClientType target,int limit,DataBuffer message){
		this.channel = channel;
		this.targets = new Target[]{new Target(target, "targetlimit;"+limit)};
		this.message = new byte[message.writerIndex()];
		System.arraycopy(message.array(), 0, this.message, 0, message.writerIndex());
	}

	@Getter
	private byte[] message;
	@Getter
	private String channel;
	@Getter
	private Target[] targets;

	@Override
	public void read(DataBuffer buffer) {
		targets = new Target[buffer.readByte()];
		for(int i = 0;i<targets.length;i++){
			int btargetType = buffer.readByte();
			targets[i] = new Target(btargetType != -1 ? ClientType.values()[btargetType]:null, buffer.readString());
		}
		channel = buffer.readString();
		message = new byte[buffer.readInt()];
		buffer.readBytes(message);
	}
	public void write(DataBuffer buffer) {
		buffer.writeByte(targets.length);
		for(int i = 0;i<targets.length;i++){
			buffer.writeByte(targets[i].targetType == null ? -1:targets[i].targetType.ordinal());
			buffer.writeString(targets[i].target);
		}
		buffer.writeString(channel);
		buffer.writeInt(message.length);
		buffer.writeBytes(message);
	}
}
