
/* To be imported on VRMilling2
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.CoInitializeException;
import javafish.clients.opc.exception.CoUninitializeException;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.SynchWriteException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.UnableRemoveGroupException;
import javafish.clients.opc.exception.UnableRemoveItemException;
import javafish.clients.opc.variant.Variant;
import javafish.clients.opc.variant.VariantList;
 */
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class getAllInfo {
	
	public static void main(String[] args) {
		//JOpc jopc = new JOpc("localhost", "RSLinx OPC Server", "JOPC1");
		
	    /*try {
	      JOpc.coInitialize();
	    }
	    catch (CoInitializeException e1) {
	      e1.printStackTrace();
	    }*/
	    
		/**
		 * @param tagNameGiven a user inputed tag name to request data from
		 * @return a JSON Object with data type and value 
		 */
	    public String getTag(String tagNameGiven) {
	    
	    //create item with given tag name
	    OpcItem item1 = new OpcItem(tagName, true, "AdvManLab");
	    
	    //create opc group
	    OpcGroup group = new OpcGroup("group1", true, 500, 0.0f);
	    group.addItem(item1);
	    
	    //connect to OPC to register group/item
	    try {
	    		jopc.connect();
	    		System.out.println("OPC connection successful...")
	    		jopc.addGroup(group);
	    		
	    		jopc.registerGroup(group);
	    		System.out.println("Group registration successful...");
	    		jopc.registerItem(group, item1);
	    		System.out.println("Item registration successful...");
	    		
	    		OpcItem itemRead = null;
	    		itemRead = jopc.synchReadItem(group, item1);
	    		itemReadValue = itemRead.getValue();
	    		itemReadDataType = itemRead.getDataType();
	    		System.out.println("Data Type: " + itemReadDataType + "Value: " itemReadValue);
	    		   
	    		JSONObject jObj = new JSONObject();
	    		jObj.put(itemRead, itemReadValue);
	    		return jObj.toJSONString();
	    		
	    }
	    catch (ConnectivityException e) {
	        e.printStackTrace();
	    }
        catch (ComponentNotFoundException e) {
          e.printStackTrace();
        } 
        catch (UnableAddGroupException e) {
          e.printStackTrace();
        }  
        catch (UnableAddItemException e) {
          e.printStackTrace();
        }
        catch (SynchReadException e) {
          e.printStackTrace();
        }
        catch (CoUninitializeException e) {
          e.printStackTrace();
        }
	    
	 
	    
	    
	    }
	    
	    
	    public ArrayList getTags(ArrayList<String> tagNamesGiven) {
	    	
	    	//to be implemented
	    }
	    public void getAvailableTagsInGroup(String groupName) {
	    	//to be implemented
	    	
	    }
	    public void getExistingGroups() {
	    	//to be implemented
	    }

	}

}
