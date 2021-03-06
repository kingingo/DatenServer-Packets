package eu.epicpvp.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import eu.epicpvp.datenserver.definitions.gilde.GildeType;
import eu.epicpvp.nbt.NBTTagCompound;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PacketGildCostumDataAction extends Packet{
	public static enum Action {
		GET,
		SET;
	}
	private UUID gilde;
	private GildeType type;
	private Action action;
	private NBTTagCompound data;

	public PacketGildCostumDataAction(UUID gilde,GildeType type) {
		this.gilde = gilde;
		this.type = type;
		action = Action.GET;
		data = null;
	}
	public PacketGildCostumDataAction(UUID gilde,GildeType type,NBTTagCompound data) {
		this.gilde = gilde;
		this.type = type;
		action = Action.SET;
		this.data = data;
	}


	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeEnum(type);
		buffer.writeEnum(action);
		buffer.writeNBTTag(data);
	}

	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		type = buffer.readEnum(GildeType.class);
		action = buffer.readEnum(Action.class);
		data = buffer.readNBTTag();
	}
}
