package wintermute.music;

import wintermute.data.ExecutionFacilitator;

public class VLC {
	
	public static void playFile(String fullPath) throws Exception
	{
		String[] args = {"-vvv",fullPath};
		ExecutionFacilitator.execute("C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe", args);
	}
	
	public static void stopAll() throws Exception
	{
		ExecutionFacilitator.kill("vlc.exe");
	}
}
