package neuromancer.voice;

import java.io.File;

import org.jaudiotagger.tag.FieldKey;

import neuromancer.core.*;
import wintermute.data.*;
import wintermute.music.*;
import wintermute.wikipedia.*;

public class VoiceActor {
	
	@SuppressWarnings("unused")
	public static void act(String input) throws Exception
	{
		String[] cutInput = RefinedVoice.cut(input, " ");
		int verbLoc = findFirst(input, "verb");
		String actionGuess = cutInput[verbLoc];
		switch(actionGuess)
		{
		case "search":
			WikiObj searchWiki;
			if(cutInput[verbLoc+1].equals("Wikipedia"))
				searchWiki = Neuromancer.wintermute.getWiki("wikipedia");
			else{
				Neuromancer.wintermute.addWikiByName(cutInput[verbLoc+1]);
				searchWiki = Neuromancer.wintermute.getWiki(cutInput[verbLoc+1]);
			}
			NodeWiki tmpWiki = new NodeWiki();
			tmpWiki.sectionContents = new String[searchWiki.theWiki.getSectionMap(cutInput[verbLoc+2]).size()-1];
			int index = 0;
			for(String item : tmpWiki.sectionContents)
			{
				String text = searchWiki.sectionContent(cutInput[verbLoc+2],index);
				tmpWiki.sectionContents[index] = Neuromancer.wintermute.formatWikitext(text);
				index++;
			}
			Neuromancer.speechSynth.speak(cutInput[verbLoc+1]+"'s article on "+cutInput[verbLoc+2]+" stored to temporary node.");
			break;
		case "play":
			System.out.println(cutInput[verbLoc+1]);
			NodeMusic musicNode = (NodeMusic) NodeMusic.load(input.substring(input.indexOf(cutInput[verbLoc+1]),input.length())+".node");
			MP3 tmpFile = new MP3(new File(musicNode.path));
			if(!tmpFile.audioTags.getFirst(FieldKey.ARTIST).equals(""))
				Neuromancer.speechSynth.speak("Playing "+tmpFile.title+" by "+tmpFile.audioTags.getFirst(FieldKey.ARTIST));
			else
				Neuromancer.speechSynth.speak("Playing "+tmpFile.title);
			MP3.play(tmpFile);
			break;
		case "":
			System.err.println("[ACT] No verbs were found! Malformed input?");
			break;
		}
	}
	
	public static void actOnRaw(String rawInput) throws Exception
	{
		act(RefinedVoice.refineVoice(rawInput));
	}
	
	private static int findFirst(String input, String type)
	{
		String[] words = RefinedVoice.cut(input, " ");
		int toReturn = 0;
		for(String word : words)
		{
			for(String part : Thesaurus.getPartsOfSpeech(word))
			{
				System.out.println(part+", "+type);
				if(part.equals(type))
				{
					return toReturn;
				}
			}
			toReturn++;
		}
		return -1;
	}
}