package neuromancer.core;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.Scanner;

import neuromancer.voice.*;
import wintermute.core.Wintermute;
import wintermute.data.*;

public class Neuromancer extends Applet {
	//Required by Applets
	private static final long serialVersionUID = 1L;
	//Wintermute instance, static to allow global access
	public static Wintermute wintermute = new Wintermute();
	//Speech synthesizer
	public static SpeechSynthesis speechSynth = new SpeechSynthesis("C:\\Program Files\\eSpeak\\command_line\\espeak.exe");
	//Displayed constantly
	public static String action = "Ready";
	//Cached node that will be written on request
	public static Node nodeCache;
	
	//Executed by applets at start
	public void init()
	{
		//Resize
		this.setSize(640, 480);
		//Begin microphone capture, wait for end confirmation
		/*AudioInput.startInput("tmp.wav");
		Scanner sc = new Scanner(System.in);
	    while(!sc.nextLine().equals(""));
	    sc.close();
	    sc = null;
		AudioInput.stopInput();*/
		//Cache a node and save it for verb testing
		nodeCache = new NodeMusic("beck.mp3");
		nodeCache.nodeName = "tmp";
		try {
			nodeCache.store("tmp.node");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		nodeCache = null;
		//
		try {
			//VLC.stopAll();
			//VLC.playFile("C:\\Users\\Andrew\\Downloads\\A Portalicious PORTAL 2 Symphonic Orchestra Medley.mp3");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String input = /*RawVoice.getVoice("tmp.wav")*/"search Wikipedia for carbon";
		System.out.println(input);
		try {
			VoiceActor.actOnRaw(input);
		} catch (Exception e) {
			System.err.println("[NEUROMANCER] Act returned exception! Malformed input?");
			e.printStackTrace();
		}
		speechSynth.speak(((NodeWiki)nodeCache).storedSection);
	}
	
	public void paint(Graphics g)
	{
		g.drawString(action, 0, this.getHeight()-5);
	}

}
