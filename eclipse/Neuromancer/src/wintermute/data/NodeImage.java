package wintermute.data;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class NodeImage extends Node implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public BufferedImage nodeImage;
	
	public NodeImage(String imagePath)
	{
		try {
			nodeImage = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
