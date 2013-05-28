package wintermute.wikipedia;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.*;

import org.apache.batik.transcoder.*;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.wikipedia.*;

public class WikiObj {
	
	//Library implementation
	public Wiki theWiki;
	
	public WikiObj(String wikiURL)
	{
		this.theWiki = new Wiki(wikiURL);
		try {
			this.theWiki.getScriptPath();
		} catch (Exception e) {
			System.out.println("Couldn't find script path, assuming blank");
			this.theWiki = new Wiki(wikiURL, "");
		}
	}
	
	public WikiObj(String wikiURL, String scriptPath)
	{
		this.theWiki = new Wiki(wikiURL, scriptPath);
	}
	
	//Return the content by section
	public String articleContent(String pageName)
	{
		try {
			return theWiki.getRenderedText(pageName);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Get the full text of a page
	public String content(String pageName)
	{
		try {
			return theWiki.getRenderedText(pageName);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//List the names of all images in the article
	public String[] rawImageList(String pageName)
	{
		try {
			return theWiki.getImagesOnPage(pageName);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Generate an image from a specific place in an article
	public BufferedImage getFormalImageByIndex(String pageName, int imageID)
	{
		//Attempt to pull the requested image into a byte array
		InputStream is = null;
		String theImage = null;
		try {
			theImage = theWiki.getImagesOnPage(pageName)[imageID];
			is = new ByteArrayInputStream(theWiki.getImage(theImage));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Determine if type is compatable
        BufferedImage image = null;
    	System.out.println(theImage.substring(theImage.length()-3, theImage.length()));
    	if(theImage.endsWith("svg")){
    		System.out.println("svg to convert");
    		//It isn't so it must be converted
    		// Create a PNG transcoder
            PNGTranscoder t = new PNGTranscoder();

            // Create the transcoder input.
            TranscoderInput input = new TranscoderInput(is);

            // Create the transcoder output.
            OutputStream ostream = null;
			try {
				ostream = new FileOutputStream("out.png");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
            TranscoderOutput output = new TranscoderOutput(ostream);
            // Save the image.
            try {
            	System.out.println("Begin transcode");
				t.transcode(input, output);
				System.out.println("End transcode");
			} catch (TranscoderException e) {
				e.printStackTrace();
			}

            // Flush and close the stream.
            try {
				ostream.flush();
				ostream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		try {
				image = ImageIO.read(new FileInputStream("out.png"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			};
    	}else{
    		//Already compatable, formatting for output
    		try {
    			System.out.println("start read");
				image = ImageIO.read(is);
				System.out.println("end read");
			} catch (IOException e) {
				e.printStackTrace();
			}}
    	return image;
	}
}
