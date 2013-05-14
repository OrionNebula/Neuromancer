package neuromancer.voice;

import com.darkprograms.speech.recognizer.Recognizer;

public class RawVoice {
	
	public static String getVoice(String voiceData)
	{
		Recognizer theRecognizer = new Recognizer();
        String input;
		try {
			input = theRecognizer.getRecognizedDataForWave(voiceData).getResponse();
		} catch (Exception e1) {
			e1.printStackTrace();
			input = "";
		}
		return input;
	}

}
