package wintermute.data;

import java.io.*;

public class NodeWiki extends Node implements Serializable {
	
	private static final long serialVersionUID = 2L;
	public String nodeType = "Wiki Node";
	public String storedSection;
	public String articleName;
	public String wikiName;
	public int sectionNum;
	
	//Usual, constructors
	public NodeWiki(String contents, String wiki, String name, int section)
	{
		this.storedSection = contents;
		this.articleName = name;
		this.nodeName = name;
		this.sectionNum = section;
		this.wikiName = wiki;
	}

	public NodeWiki(String contents) {
		this.storedSection = contents;
	}
}
