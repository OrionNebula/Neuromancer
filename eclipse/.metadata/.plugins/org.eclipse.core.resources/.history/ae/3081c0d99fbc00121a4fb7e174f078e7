package neuromancer.voice;

import java.io.*;

import javax.sound.sampled.AudioFileFormat;

import com.darkprograms.speech.microphone.Microphone;

public class AudioInput {
	
	public static Microphone theMic = null;
	
	public static void startInput(String toStore)
	{
		theMic = new Microphone(AudioFileFormat.Type.WAVE);
    	try {
			theMic.captureAudioToFile(toStore);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void stopInput()
	{
		theMic.close();
	}

}
