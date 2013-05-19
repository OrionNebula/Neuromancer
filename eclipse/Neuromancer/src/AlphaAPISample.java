import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import neuromancer.voice.*;



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
    	    	System.out.println(input);
    	    	SpeechSynthesis theSpeech = new SpeechSynthesis();
    	    	
    	    	theSpeech.speek(input);
    	
    	/*//String input = RawVoice.getVoice("tmp.wav");
    	    	Solution theAnswer = null;
        WolframObj theWolfram = new WolframObj(appid);
        try {
			theAnswer = theWolfram.getSolution(input);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        while(theAnswer.imageURL == null){}
        BufferedImage simage = theAnswer.getImageByIndex(0);
        try{
            JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(simage));
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        WikiObj theWiki = new WikiObj("en.wikipedia.org");
        System.out.println(theWiki.sectionContent(input,0).replace("[", "").replace("]", "").replace("{", "").replace("}", ""));
        BufferedImage image = theWiki.getFormalImageByIndex(input, 0);
        try{
            JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image));
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }*/
    }

}
