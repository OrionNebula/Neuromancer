package neuromancer.core;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.Scanner;

import neuromancer.voice.*;
import wintermute.core.Wintermute;
import wintermute.data.*;

public class Neuromancer extends Applet {
	
	private static final long serialVersionUID = 1L;
	public static Wintermute wintermute = new Wintermute();
	public static SpeechSynthesis speechSynth = new SpeechSynthesis("C:\\Program Files (x86)\\eSpeak\\command_line\\espeak.exe");
	public static String action = "Ready";
	public static Node nodeCache;
	
	public void init()
	{
		this.setSize(640, 480);
		AudioInput.startInput("tmp.wav");
		Scanner sc = new Scanner(System.in);
	    while(!sc.nextLine().equals(""));
	    sc.close();
	    sc = null;
		AudioInput.stopInput();
		String input = RawVoice.getVoice("tmp.wav");
		System.out.println(input);
		try {
			VoiceActor.actOnRaw(input);
		} catch (Exception e) {
			System.err.println("[NEUROMANCER] Act returned exception! Malformed input?");
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g)
	{
		g.drawString(action, 0, this.getHeight()-5);
	}

}
