package wintermute.core;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.io.*;
import java.net.*;
import java.util.*;

import org.jsoup.Jsoup;

import wintermute.wikipedia.WikiObj;
import wintermute.wolfram.WolframObj;

import com.google.gson.Gson;

public class Wintermute {
	
	public String apiKey = "LXY739-7LAEYLYV67";
	public WolframObj theWolfram;
	public HashMap<String, WikiObj> wikiList = new HashMap<String, WikiObj>();
	
	//Some constructors and whatnot
	
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
	
	//Add a wiki straight up
	public void addWiki(String name, String wikiURL)
	{
		wikiList.put(name, new WikiObj(wikiURL));
	}
	
	//Add a wiki by common name. Google the input and produce a wiki with the same name
	public void addWikiByName(String name)
	{
		String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	    String charset = "UTF-8";

	    URL url;
	    Reader reader = null;
		try {
			url = new URL(google + URLEncoder.encode(name, charset));
			reader = new InputStreamReader(url.openStream(), charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
	    
		wikiList.put(name, new WikiObj(results.getResponseData().getResults().get(0).getUrl().substring(7, results.getResponseData().getResults().get(0).getUrl().length()-1)));
		System.out.println(results.getResponseData().getResults().get(0).getUrl().substring(7, results.getResponseData().getResults().get(0).getUrl().length()-1));
	}
	
	//Google and add a wiki by its common name, while specifing a script path.
	@Deprecated
	public void addWikiByName(String name, String scriptPath)
	{
		String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	    String charset = "UTF-8";

	    URL url;
	    Reader reader = null;
		try {
			url = new URL(google + URLEncoder.encode(name, charset));
			reader = new InputStreamReader(url.openStream(), charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
	    
		wikiList.put(name, new WikiObj(results.getResponseData().getResults().get(0).getUrl().substring(7, results.getResponseData().getResults().get(0).getUrl().length()-1), scriptPath));
		System.out.println(results.getResponseData().getResults().get(0).getUrl().substring(7, results.getResponseData().getResults().get(0).getUrl().length()-1));
	}
	
	//Retrieve wiki by name. Almost useless
	public WikiObj getWiki(String name)
	{
		return wikiList.get(name);
	}
	
	//Return the rendered plaintext of a wikipedia article
	public String getArticle(WikiObj theWiki, String article)
	{
		return Jsoup.parse(theWiki.content(article)).text();
	}

	//Return current clipboard contents
	public static String getClipboard()
	{
		try {
			return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Output a string to the file of your choice
	public void writeStringFile(File file, String textToWrite) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(file)); 
		out.print(textToWrite);
		out.close();
	}
}
//Class that contains methods for googling
class GoogleResults {

    private ResponseData responseData;
    public ResponseData getResponseData() { return responseData; }
    public void setResponseData(ResponseData responseData) { this.responseData = responseData; }
    public String toString() { return "ResponseData[" + responseData + "]"; }

    static class ResponseData {
        private List<Result> results;
        public List<Result> getResults() { return results; }
        public void setResults(List<Result> results) { this.results = results; }
        public String toString() { return "Results[" + results + "]"; }
    }

    static class Result {
        private String url;
        private String title;
        public String getUrl() { return url; }
        public String getTitle() { return title; }
        public void setUrl(String url) { this.url = url; }
        public void setTitle(String title) { this.title = title; }
        public String toString() { return "Result[url:" + url +",title:" + title + "]"; }
    }

}
