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
    	
    	
    	String input = RawVoice.getVoice("tmp.wav");

        WolframObj theWolfram = new WolframObj(appid);
        try {
			for(String element : theWolfram.getElement(input, "Input interpretation"))
			{
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
    
    public static BufferedImage svgToPNG(InputStream source) throws Exception
    {
    	// Create a JPEG transcoder
        JPEGTranscoder t = new JPEGTranscoder();

        // Set the transcoding hints.
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
                             new Float(.8));

        // Create the transcoder input.
        TranscoderInput input = new TranscoderInput(source);

        // Create the transcoder output.
        OutputStream ostream = new FileOutputStream("out.jpg");
        TranscoderOutput output = new TranscoderOutput(ostream);
        // Save the image.
        t.transcode(input, output);

        // Flush and close the stream.
        ostream.flush();
        ostream.close();
        return ImageIO.read(new FileInputStream("out.jpg"));
    }

}
