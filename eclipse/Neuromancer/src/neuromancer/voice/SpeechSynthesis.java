package neuromancer.voice;

import java.io.IOException;

public class SpeechSynthesis {
	
	public String exePath = "C:\\Program Files\\eSpeak\\command_line\\espeak.exe";
	
	public SpeechSynthesis(String exePath)
	{
		this.exePath = exePath;
	}
	
	public SpeechSynthesis()
	{
	}
	
	public void speak(String textToSay)
	{
		try {
			@SuppressWarnings("unused")
			Process p = Runtime.getRuntime().exec(this.exePath+" -vmb-en1 -s125 \""+textToSay+"\"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
