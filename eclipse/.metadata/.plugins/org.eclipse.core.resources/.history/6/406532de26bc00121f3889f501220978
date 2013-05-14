import java.io.*;

import javax.sound.sampled.AudioFileFormat;

import com.wolfram.alpha.*;
import com.darkprograms.speech.microphone.*;
import com.darkprograms.speech.recognizer.*;

public class AlphaAPISample {
	
    private static String appid = "LXY739-7LAEYLYV67";

    public static void main(String[] args) {
    	
    	Microphone theMicrophone = new Microphone(AudioFileFormat.Type.WAVE);
    	try {
			theMicrophone.captureAudioToFile("tmp.wav");
			System.out.println("Begin Capture");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	String s = "";
    	while(s==""){try{
    	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    	    s = bufferRead.readLine();
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}}
    	
    	theMicrophone.close();
    	System.out.println("End Capture");
    	Recognizer theRecognizer = new Recognizer();
    	
        String input;
		try {
			input = theRecognizer.getRecognizedDataForWave("tmp.wav").getResponse();
		} catch (Exception e1) {
			e1.printStackTrace();
			input = "";
		}
        if (args.length > 0)
            input = args[0];

        WAEngine engine = new WAEngine();

        engine.setAppID(appid);
        engine.addFormat("plaintext");

        WAQuery query = engine.createQuery();
        
        query.setInput(input);
        
        try {
            System.out.println("Query URL:");
            System.out.println(engine.toURL(query));
            System.out.println("");
            
            WAQueryResult queryResult = engine.performQuery(query);
            
            if (queryResult.isError()) {
                System.out.println("Query error");
                System.out.println("  error code: " + queryResult.getErrorCode());
                System.out.println("  error message: " + queryResult.getErrorMessage());
            } else if (!queryResult.isSuccess()) {
                System.out.println("Query was not understood; no results available.");
            } else {
                // Got a result.
                System.out.println("Successful query. Pods follow:\n");
                for (WAPod pod : queryResult.getPods()) {
                    if (!pod.isError()) {
                        System.out.println(pod.getTitle());
                        System.out.println("------------");
                        for (WASubpod subpod : pod.getSubpods()) {
                            for (Object element : subpod.getContents()) {
                                if (element instanceof WAPlainText) {
                                    System.out.println(((WAPlainText) element).getText());
                                    System.out.println("");
                                }
                            }
                        }
                        System.out.println("");
                    }
                }
            }            
        } catch (WAException e) {
            e.printStackTrace();
        }
    }

}
