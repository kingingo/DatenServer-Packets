package dev.wolveringer.dataserver.protocoll.packets;

public class PacketVersion {
	private static final String DEFAULT_PROTOCOLL_VERSION = "undefined";
	public static final String PROTOCOLL_VERSION;
	
	static {
		String newVersion = "ac719a7424283ef925a5d387dcb06547c929e60b"; //Will replaced by maven
		if(newVersion.length() >= 9)
			if(newVersion.substring(1, 9).equalsIgnoreCase("buildId"))
				newVersion =  DEFAULT_PROTOCOLL_VERSION;
		PROTOCOLL_VERSION = newVersion;
	}
}
