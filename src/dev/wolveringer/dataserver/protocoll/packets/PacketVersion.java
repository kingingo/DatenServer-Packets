package dev.wolveringer.dataserver.protocoll.packets;

public class PacketVersion {
	private static final String DEFAULT_PROTOCOLL_VERSION = "undefined";
	public static final String PROTOCOLL_VERSION;
	
	static {
		String newVersion = "54594b7daed52e6fa2128e5a11ee91e85a377e94"; //Will replaced by maven
		if(newVersion.length() >= 9)
			if(newVersion.substring(1, 9).equalsIgnoreCase("buildId"))
				newVersion =  DEFAULT_PROTOCOLL_VERSION;
		PROTOCOLL_VERSION = newVersion;
	}
}
