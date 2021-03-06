package neuromancer.core;

import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import neuromancer.voice.*;
import wintermute.core.Wintermute;
import wintermute.data.*;
import wintermute.music.MP3;

import com.melloware.jintellitype.*;

public class Neuromancer extends Applet implements HotkeyListener{
	//Required by applets
	private static final long serialVersionUID = 1L;
	//Wintermute instance, static to allow global access
	public static Wintermute wintermute = new Wintermute();
	//Speech synthesizer
	public static SpeechSynthesis speechSynth = new SpeechSynthesis("C:\\Program Files (x86)\\eSpeak\\command_line\\espeak.exe");
	//Displayed constantly
	public static String action = "Ready";
	//Array of named nodes to be cached
	public static HashMap<String, Node> nodes = new HashMap<String, Node>();
	//Status color, changed based on action
	public static Color statusColor = Color.green;
	//Cached graphics object
	public static Graphics theGraphics;
	//The current operating DataSet
	public static DataSet theSet = DataSet.root;
	//The image to be drawn
	public static NodeImage anImage;
	//last mouse coords
	private int x;
	private int y;
	
	//Executed by applets at start
	public void init()
	{
		//Resize
		this.setSize(640, 480);
		//Register the [WINDOWS-KEY]+N hotkey
		JIntellitype.setLibraryLocation(new File("JIntellitype64.dll"));
		JIntellitype.getInstance().registerHotKey(1, JIntellitype.MOD_WIN, (int)'N');
		JIntellitype.getInstance().addHotKeyListener(this);
	}
	
	//Draw the new status color and string
	public void paint(Graphics g)
	{
		theGraphics = g;
		g.drawString(action, 0, this.getHeight()-5);
		g.setColor(statusColor);
		g.fillRect(0, 0, 50, 50);
		if(anImage != null)
			g.drawImage(anImage.nodeImage.getImage(), 50, 0, null);
		theGraphics.setColor(Color.red);
		theGraphics.drawOval(x-5, y-5, 10, 10);
	}
	
	//Movement
	public boolean mouseMove(Event e, int x, int y)
	{
		this.x = x;
		this.y = y;
		this.repaint();
		return true;
	}
	
	//Handle buttons
	public boolean mouseDown(Event e, int x,int y)
	{
		Rectangle rect = new Rectangle(50,50);
		//Stop listening if currently listening
		if(rect.contains(x, y) && action.equals("Listening"))
		{
			statusColor = Color.yellow;
			action = "Acting";
			this.repaint();
			AudioInput.stopInput();
			MP3.resume();
			try {
				VoiceActor.actOnRaw(RawVoice.getVoice("tmp.wav"));
			} catch (Exception e1) {
				e1.printStackTrace();
				speechSynth.speak("A "+e1.getClass().getName()+" was caught trying to preform your action.");
			}
			System.out.println("Done acting");
			statusColor = Color.green;
			action = "Ready";
			this.repaint();
		}else
		//Listen if stopped and not just stopped
			if(rect.contains(x, y) && action.equals("Ready"))
			{
				MP3.pause();
				statusColor = Color.red;
				action = "Listening";
				this.repaint();
				AudioInput.startInput("tmp.wav");
			}
		return true;
	}

	@Override
	//Simulate button press on hotkey
	public void onHotKey(int identifier) {
		switch(identifier)
		{
		case 1:
			this.mouseDown(null, 1, 1);
			break;
		default:
			System.err.println("We were given a strange hotkey!");
			break;
		}
	}
}
