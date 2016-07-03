package dev.wolveringer.dataserver.protocoll.packets;

public class PacketVersion {
	private static final String DEFAULT_PROTOCOLL_VERSION = "undefined";
	public static final String PROTOCOLL_VERSION;
	
	static {
		String newVersion = "e07fbb139590441cd7a0441b149d8fd8f01aee50"; //Will replaced by maven
		if(newVersion.length() >= 9)
			if(newVersion.substring(1, 9).equalsIgnoreCase("buildId"))
				newVersion =  DEFAULT_PROTOCOLL_VERSION;
		PROTOCOLL_VERSION = newVersion;
	}
}
