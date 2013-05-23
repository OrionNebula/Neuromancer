package wintermute.music;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class MP3 {
	
	public String title;
	public File path;
	public Tag audioTags;
	
	public static MediaPlayer thePlayer;
	
	public MP3(File pathParam)
	{
		this.path = pathParam;
		AudioFile audio = null;
		try {
			audio = AudioFileIO.read(pathParam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.audioTags = audio.getTag();
		this.title = this.audioTags.getFirst(FieldKey.TITLE);
	}
	
	public static void play(MP3 mp3File)
	{
		new JFXPanel();
		Media media = new Media(mp3File.path.toURI().toString());
		thePlayer = new MediaPlayer(media);
		thePlayer.play();
	}
	
	public static void stop()
	{
		thePlayer.stop();
	}
}
