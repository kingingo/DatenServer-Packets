package eu.epicpvp.dataserver.protocoll.packets;

import eu.epicpvp.datenserver.definitions.dataserver.protocoll.DataBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PacketReportEdit extends Packet{
	public static enum EditKey {
		ADD_WORKER,
		DONE_WORKER,
		CLOSE,
		CREATE;
	}
	private EditKey edit;
	private int value;
	private int value2;

	private String reson;
	private String info;

	@Override
	public void read(DataBuffer buffer) {
		edit =buffer.readEnum(EditKey.class);
		value = buffer.readInt();
		value2 = buffer.readInt();
		if(edit == EditKey.CREATE){
			reson = buffer.readString();
			info = buffer.readString();
		}
	}

	@Override
	public void write(DataBuffer buffer) {
		buffer.writeEnum(edit);
		buffer.writeInt(value);
		buffer.writeInt(value2);
		if(edit == EditKey.CREATE){
			buffer.writeString(reson);
			buffer.writeString(info);
		}
	}
}
