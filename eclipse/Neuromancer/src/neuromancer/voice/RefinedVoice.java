package neuromancer.voice;

public class RefinedVoice {
	
	//A chained replace statement
	public static String refineVoice(String input)
	{
		return input.replace(" the ", " ").replace(" for ", " ").replace(" a ", " ").replace(" node "," ").replace("&", "and");
	}
	
	//cut up a string by a delimeter. Almost useless
	public static String[] cut(String input, String delimeter)
	{
		return input.split(delimeter);
	}

}
