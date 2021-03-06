package wintermute.data;

import java.io.*;

//Basically a NodeFile
public class DataSet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String setName = "NoName";
	public String setPath = "";
	
	public static final DataSet root = new DataSet(".","root");
	
	public DataSet(String path)
	{
		this.setPath = path;
	}
	
	public DataSet(String path, String name)
	{
		this.setPath = path;
		this.setName = name;
	}

	public void store(String toStore) throws Exception
	{
		ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(new File(toStore)));
		objectStream.writeObject(this);
		objectStream.close();
	}
	
	public static DataSet load(String toLoad) throws Exception
	{
		ObjectInputStream loadStream = new ObjectInputStream(new FileInputStream(new File(toLoad)));
		Object theSet = loadStream.readObject();
		loadStream.close();
		return (DataSet) theSet;
	}

}
