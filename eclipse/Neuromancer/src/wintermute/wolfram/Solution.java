package wintermute.wolfram;

import java.awt.image.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.wolfram.alpha.*;

public class Solution {
	
	public String equation;
	
	public String[] solutions;
	
	public HashMap<Character,String> varSolutions = new HashMap<Character,String>();
	
	public String[] imageURL;
	
	public Solution(WAImage[] imageParam, String[] solutions)
	{
		System.out.println(imageParam);
		if(imageParam != null)
		{
			System.out.println("Not null");
			int num = 0;
			for(WAImage element : imageParam)
			{
				System.out.println("Element");
				if(element != null)
					num++;
			}
			WAImage[] newImage = new WAImage[num];
			int ID = 0;
			for(WAImage element : imageParam)
			{
				System.out.println("New Element");
				if(element != null){
					newImage[ID] = element;
					ID++;
				}
					
			}
			System.out.println(newImage.length);
			imageParam = newImage;
			System.out.println(imageParam.length);
		}
		
		if(solutions != null)
		{
			int num = 0;
			for(String element : solutions)
			{
				if(element != null)
					num++;
			}
			String[] newSolutions = new String[num];
			int ID = 0;
			for(String element : solutions)
			{
				if(element != null){
					newSolutions[ID] = element;
					ID++;
				}
					
			}
			solutions = newSolutions;
		}
		if(solutions != null && solutions.length != 0)
			System.out.println(solutions[0]);
		if(solutions != null && imageParam.length != 0)
			System.out.println(imageParam[0].getURL());
		int index = 0;
		if(imageParam != null && imageParam.length != 0){
			this.imageURL = new String[imageParam.length];
			System.out.println(imageParam.length);
		for(WAImage element : imageParam)
		{
			this.pushWAImageToIndex(element, index);
			index++;
		}}
		this.solutions = solutions;
	}
	
	public BufferedImage getImageByIndex(int index)
	{
		InputStream input = null;
		try {
			input = new URL(imageURL[index]).openStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return ImageIO.read(input);
		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void pushWAImageToIndex(WAImage theImage, int index)
	{
		imageURL[index]=theImage.getURL();
	}
}
