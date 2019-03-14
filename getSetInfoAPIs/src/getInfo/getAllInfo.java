/* To be imported on VRMilling2
import javafish.clients.opc.JOpc;
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
	
		//JOpc jopc = new JOpc("localhost", "RSLinx OPC Server", "JOPC1");
		
	   
	    
		/**
		 * @param tagNameGiven a user inputed tag name to request data from
		 * @return a JSON Object with data type and value 
		 */
		@SuppressWarnings("unchecked")
	    public String getTag(String tagNameGiven) {
	    	
			/*try {
	   	      JOpc.coInitialize();
	   	    }
	    	 	catch (CoInitializeException e1) {
	   	      e1.printStackTrace();
	   	    }
	   	    */
	    //create item with given tag name
	    OpcItem item1 = new OpcItem(tagName, true, "AdvManLab");
	    
	    //create opc group
	    OpcGroup group = new OpcGroup("group1", true, 500, 0.0f);
	    group.addItem(item1);
	    
	    //connect to OPC to register group/item
	    try {
	    		jopc.connect();
	    		//System.out.println("OPC connection successful...")
	    		jopc.addGroup(group);
	    		
	    		jopc.registerGroup(group);
	    		//System.out.println("Group registration successful...");
	    		jopc.registerItem(group, item1);
	    		//System.out.println("Item registration successful...");
	    		
	    		OpcItem itemRead = null;
	    		itemRead = jopc.synchReadItem(group, item1);
	    		Variant itemReadValue = itemRead.getValue();
	    		int itemReadDataType = itemRead.getDataType();
	    		//System.out.println("Data Type: " + itemReadDataType + "Value: " itemReadValue);
	    		   
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
	    
	 
	    
	    return "Tag Name Not Found";
	    }
	    
	    
		public ArrayList getTags(ArrayList<String> tagNamesGiven) {
			/*try {
	   	      JOpc.coInitialize();
	   	    }
	    	 	catch (CoInitializeException e1) {
	   	      e1.printStackTrace();
	   	    }
	   	    */
	    //create items with given tag names
		ArrayList<OpcItem> items;
		for(int i = 0; i<tagNamesGiven.size();i++) {
			items.add(new OpcItem(tagNamesGiven[i], true, "AdvManLab"));
		}
	    
	    //create opc group
	    OpcGroup group = new OpcGroup("group1", true, 500, 0.0f);
	    for(int i = 0;i<items.size();i++) {
	    		group.addItem(items[i]);
	    }
	    
	    //connect to OPC to register group/item
	    try {
	    		jopc.connect();
	    		//System.out.println("OPC connection successful...")
	    		jopc.addGroup(group);
	    		
	    		jopc.registerGroup(group);
	    		//System.out.println("Group registration successful...");
	    		for(int i = 0;i<items.size();i++) {
	    			jopc.registerItem(group, items[i]);
		    }
	    		//System.out.println("Item registration successful...");
	    		
	    		OpcItem itemRead = null;
	    		for(int i=0;i<items.size();i++) {
		    		itemRead = jopc.synchReadItem(group, items[i]);
		    		variant itemReadValue = itemRead.getValue();
		    		int itemReadDataType = itemRead.getDataType();
	    		}
	    		//System.out.println("Data Type: " + itemReadDataType + "Value: " itemReadValue);
	    		   
	    		
	    		ArrayList<String> tagNames = new ArrayList<String>();
	    		for(int i = 0; i<tagNames.size();i++) {
	    			JSONObject jObj = new JSONObject();
	    			jObj.put(itemRead, itemReadValue);
	    			tagNames.add(jObj.toJSONString());
	    		}
	    		return tagNames;
	    		
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
	    
	 
	    ArrayList<String> noTags = new ArrayList<String>();
	    noTags.add("Tag Names Not Found");
	    return noTags;
	    }
	   public void getAvailableTagsInGroup(String groupName) {
	    	//to be implemented
	    	
	    }
	   public void getExistingGroups() {
	    	
		   
		   
		   
		   
		   
		   
	    }

}


