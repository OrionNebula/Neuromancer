package wintermute.data;

import java.io.Serializable;

public class NodeFile extends Node implements Serializable {
	private static final long serialVersionUID = 1L;
	public String path = "";
	public String nodeType = "File Node";
	
	public NodeFile(String fullPath)
	{
		this.path = fullPath;
	}
}
