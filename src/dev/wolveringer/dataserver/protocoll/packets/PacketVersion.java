package dev.wolveringer.dataserver.protocoll.packets;

public class PacketVersion {
	private static final String DEFAULT_PROTOCOLL_VERSION = "undefined";
	public static final String PROTOCOLL_VERSION;
	
	static {
		String newVersion = "ae227243dbff701d037b81566480f680a2d9db38"; //Will replaced by maven
		if(newVersion.length() >= 9)
			if(newVersion.substring(1, 9).equalsIgnoreCase("buildId"))
				newVersion =  DEFAULT_PROTOCOLL_VERSION;
		PROTOCOLL_VERSION = newVersion;
	}
}
