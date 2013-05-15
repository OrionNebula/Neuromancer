package wintermute.wolfram;

import com.wolfram.alpha.*;

public class WolframObj {
	
	public String APIKey;
	
	public WolframObj(String APIKey)
	{
		this.APIKey = APIKey;
	}
	
	public String[] getElement(String input, String elementParam) throws Exception
	{
		WAEngine engine = new WAEngine();

        engine.setAppID(this.APIKey);
        engine.addFormat("plaintext");

        WAQuery query = engine.createQuery();
        
        query.setInput(input);
        
        String[] theResult = null;
		
		try {
            
            WAQueryResult queryResult = engine.performQuery(query);
            
            if (queryResult.isError()) {
            } else if (!queryResult.isSuccess()) {
                throw new Exception("NORESULT");
            } else {
                // Got a result.
                for (WAPod pod : queryResult.getPods()) {
                    if (!pod.isError()) {
                        String currentElement = pod.getTitle();
                        for (WASubpod subpod : pod.getSubpods()) {
                        	if(currentElement.equals(elementParam))
                        		theResult = new String[subpod.getContents().length];
                        	int ID = 0;
                            for (Object element : subpod.getContents()) {
                                if (element instanceof WAPlainText && currentElement.equals(elementParam)) {
                                	theResult[ID] = ((WAPlainText) element).getText();
                                	ID++;
                                }
                            }
                        }
                    }
                }
            }            
        } catch (WAException e) {
            e.printStackTrace();
        }
		return theResult;
	}
	
	public Solution getSolution(String equation) throws Exception
	{
		WAEngine engine = new WAEngine();

        engine.setAppID(this.APIKey);
        engine.addFormat("plaintext");

        WAQuery query = engine.createQuery();
        
        query.setInput(equation.replace("equals", "=").replace("times", "*").replace("divided by", "/").replace("negative", "-").replace("minus", "-").replace("plus", "+").replace("to the power of ", "^"));
        
        String[] theResult = null;
        WAImage[] images = null;
		
		try {
            
            WAQueryResult queryResult = engine.performQuery(query);
            
            if (queryResult.isError()) {
            } else if (!queryResult.isSuccess()) {
                throw new Exception("NORESULT");
            } else {
                // Got a result.
                for (WAPod pod : queryResult.getPods()) {
                    if (!pod.isError()) {
                        String currentElement = pod.getTitle();
                        for (WASubpod subpod : pod.getSubpods()) {
                        	if(currentElement.startsWith("Result") || currentElement.startsWith("Solution")){
                        		theResult = new String[subpod.getContents().length];
                        		images = new WAImage[subpod.getContents().length];}
                        	int ID = 0;
                            for (Object element : subpod.getContents()) {
                                if (element instanceof WAPlainText && (currentElement.startsWith("Result") || currentElement.startsWith("Solution"))) {
                                	theResult[ID] = ((WAPlainText) element).getText();
                                	ID++;
                                }
                                if (element instanceof WAImage && (currentElement.startsWith("Result") || currentElement.startsWith("Solution"))) {
                                	images[ID] = ((WAImage) element);
                                	ID++;
                                }
                            }
                        }
                    }
                }
            }            
        } catch (WAException e) {
            e.printStackTrace();
        }
		return new Solution(images, theResult);
	}

}
