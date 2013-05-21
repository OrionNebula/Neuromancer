package neuromancer.voice;

import com.darkprograms.speech.recognizer.Recognizer;

public class RawVoice {
	
	public static String getVoice(String voiceData)
	{
		Recognizer theRecognizer = new Recognizer();
        String input;
		try {
			input = theRecognizer.getRecognizedDataForWave("tmp.wav").getResponse();
		} catch (Exception e1) {
			input = "";
		}
		return input;
	}

}
