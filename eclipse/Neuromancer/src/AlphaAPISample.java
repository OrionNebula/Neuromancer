import java.awt.image.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.imageio.*;
import javax.sound.sampled.AudioFileFormat;
import javax.swing.*;

import com.wolfram.alpha.*;
import com.darkprograms.speech.microphone.*;
import com.darkprograms.speech.recognizer.*;

import neuromancer.voice.AudioInput;
import neuromancer.voice.RawVoice;

import org.w3c.dom.Document;
import org.wikipedia.*;
import org.apache.batik.transcoder.*;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.svg2svg.SVGTranscoder;

import wintermute.wikipedia.*;
import wintermute.wolfram.*;

public class AlphaAPISample {
	
    private static String appid = "LXY739-7LAEYLYV67";

    public static void main(String[] args) {
    	
    	    	AudioInput.startInput("tmp.wav");
    	    	String s = "";
    	    	while(s==""){try{
    	    	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    	    	    s = bufferRead.readLine();
    	    	}
    	    	catch(IOException e)
    	    	{
    	    		e.printStackTrace();
    	    	}}
    	    	AudioInput.stopInput();
    	    	String input = RawVoice.getVoice("tmp.wav");

    	
    	
    	//String input = RawVoice.getVoice("tmp.wav");

        WolframObj theWolfram = new WolframObj(appid);
        try {
			for(String element : theWolfram.getElement(input, "Solution")){
				System.out.println(element);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
        WikiObj theWiki = new WikiObj("en.wikipedia.org");
        System.out.println(theWiki.sectionContent(input,0).replace("[", "").replace("]", "").replace("{", "").replace("}", ""));
        BufferedImage image = theWiki.getFormalImageByIndex(input, 0);
        try{
            JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image));
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
