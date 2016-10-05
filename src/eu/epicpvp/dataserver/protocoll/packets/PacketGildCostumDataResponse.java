package eu.epicpvp.dataserver.protocoll.packets;

import java.util.UUID;

import eu.epicpvp.nbt.NBTTagCompound;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import eu.epicpvp.datenserver.definitions.gilde.GildeType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketGildCostumDataResponse extends Packet{
	private UUID gilde;
	private GildeType type;
	private NBTTagCompound comp;

	@Override
	public void read(DataBuffer buffer) {
		gilde = buffer.readUUID();
		type = buffer.readEnum(GildeType.class);
		comp = buffer.readNBTTag();
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeUUID(gilde);
		buffer.writeEnum(type);
		buffer.writeNBTTag(comp);
	}
}
