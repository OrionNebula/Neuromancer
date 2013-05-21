package neuromancer.voice;

import java.util.Calendar;

public class SpeechThread extends Thread {
	
	public SpeechSynthesis theSynth = new SpeechSynthesis();
	
	public void run()
	{
	}
	
	public void speak(String textToSay)
	{
		theSynth.speak(textToSay);
	}
	
	@SuppressWarnings("deprecation")
	public void speakTime()
	{
		if(Calendar.getInstance().getTime().getMinutes() < 10){
			if(Calendar.getInstance().getTime().getHours() <= 12)
				this.speak("The time is now "+Calendar.getInstance().getTime().getHours() + " O " + Calendar.getInstance().getTime().getMinutes()+".");
			else
				this.speak("The time is now "+(Calendar.getInstance().getTime().getHours()-12) + " O " + Calendar.getInstance().getTime().getMinutes()+".");
		}else{
			if(Calendar.getInstance().getTime().getHours() <= 12)
				this.speak("The time is now "+Calendar.getInstance().getTime().getHours() + " " + Calendar.getInstance().getTime().getMinutes()+".");
			else
				this.speak("The time is now "+(Calendar.getInstance().getTime().getHours()-12) + " " + Calendar.getInstance().getTime().getMinutes()+".");
		}
	}
}
