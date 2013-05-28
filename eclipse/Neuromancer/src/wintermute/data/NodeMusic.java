package wintermute.data;

import java.io.Serializable;

//This is almost entireley useless. It might do something later.
public class NodeMusic extends NodeFile implements Serializable {
	private static final long serialVersionUID = 1L;
	public String nodeType = "Music Node";
	public NodeMusic(String fullPath) {
		super(fullPath);
	}

}
