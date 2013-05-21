package neuromancer.core;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.Scanner;

import neuromancer.voice.AudioInput;
import neuromancer.voice.RawVoice;
import neuromancer.voice.RefinedVoice;
import neuromancer.voice.SpeechThread;
import wintermute.core.Wintermute;

public class Neuromancer extends Applet {
	
	private static final long serialVersionUID = 1L;
	public Wintermute wintermute = new Wintermute();
	public SpeechThread speechThread;
	
	public void init()
	{
		this.setSize(640, 480);
		speechThread = (new SpeechThread());
		speechThread.start();
		AudioInput.startInput("tmp.wav");
		Scanner sc = new Scanner(System.in);
	    while(!sc.nextLine().equals(""));
		AudioInput.stopInput();
		String input = RawVoice.getVoice("tmp.wav");
		System.err.println("INPUT: "+input);
		String[] wikiName = RefinedVoice.cut(RefinedVoice.refineVoice(input), "\\ ");
		System.out.println(wikiName);
		wintermute.addWikiByName(wikiName[1]+wikiName[2]);
		String speak = wintermute.formatWikitext(wintermute.wikiList.get(wikiName[1]+wikiName[2]).sectionContent(wikiName[3], 0));
		speechThread.speak(speak);
	}
	
	public void paint(Graphics g)
	{
		
	}

}
