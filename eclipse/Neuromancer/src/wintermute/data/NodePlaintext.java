package wintermute.data;

import java.io.Serializable;

public class NodePlaintext extends Node implements Serializable {
	private static final long serialVersionUID = 1L;
	public String nodeName = "Plaintext Node";
	public String nodeContents = "";
	public NodePlaintext(String contents)
	{
		this.nodeContents = contents;
	}
}
