package wintermute.core;

import java.util.HashMap;

import wintermute.wolfram.*;
import wintermute.wikipedia.*;

public class Wintermute {
	
	public String apiKey = "LXY739-7LAEYLYV67";
	public WolframObj theWolfram;
	public HashMap<String, WikiObj> wikiList = new HashMap<String, WikiObj>();
	
	public Wintermute()
	{
		this.theWolfram = new WolframObj(this.apiKey);
		wikiList.put("wikipedia", new WikiObj("en.wikipedia.org"));
	}
	
	public Wintermute(WolframObj wolframParam)
	{
		this.theWolfram = wolframParam;
		wikiList.put("wikipedia", new WikiObj("en.wikipedia.org"));
	}
	
	public Wintermute(String wikiURL)
	{
		this.theWolfram = new WolframObj(this.apiKey);
		wikiList.put("wikipedia", new WikiObj(wikiURL));
	}
	
	public void addWiki(String name, String wikiURL)
	{
		wikiList.put(name, new WikiObj(wikiURL));
	}
	
	public WikiObj getWiki(String name)
	{
		return wikiList.get(name);
	}
}
