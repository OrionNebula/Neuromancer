package neuromancer.voice;

import javax.sound.sampled.AudioFileFormat;

import com.darkprograms.speech.microphone.Microphone;

public class AudioInput {
	
	public static Microphone theMic = null;
	
	//Duh
	public static void startInput(String toStore)
	{
		Microphone theMicrophone = new Microphone(AudioFileFormat.Type.WAVE);
		theMic = theMicrophone;
    	try {
			theMicrophone.captureAudioToFile("tmp.wav");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	//Also, duh
	public static void stopInput()
	{
		theMic.close();
	}

}
