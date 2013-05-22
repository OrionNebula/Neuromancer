package wintermute.data;


public class ExecutionFacilitator {
	
	public static void execute(String fullPath, String[] args) throws Exception
	{
		String clArgs = "";
		for(String item : args)
		{
			clArgs = clArgs+" "+item;
		}
		@SuppressWarnings("unused")
		Process p = Runtime.getRuntime().exec(fullPath+" "+clArgs);
	}
	
	public static void kill(String name) throws Exception
	{
		String[] args = {"-f","-im",name};
		execute("C:\\Windows\\System32\\taskkill.exe",args);
	}
}
