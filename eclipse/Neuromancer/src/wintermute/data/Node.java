package wintermute.data;

import java.io.*;

public class Node implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String nodeName = "NoName";
	
	public String nodeType = "Node";
	
	public void store(String toStore) throws Exception
	{
		ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(new File(toStore)));
		objectStream.writeObject(this);
		objectStream.close();
	}
	
	@SuppressWarnings("resource")
	public static Node load(String toLoad) throws Exception
	{
		return (Node) new ObjectInputStream(new FileInputStream(new File(toLoad))).readObject();
	}
}
