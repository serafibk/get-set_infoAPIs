package getInfo;


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
import javafish.clients.opc.exception.UnableBrowseBranchException;
import javafish.clients.opc.exception.UnableIBrowseException;
import javafish.clients.opc.exception.HostException;
import javafish.clients.opc.exception.NotFoundServersException;
import javafish.clients.opc.variant.Variant;
import javafish.clients.opc.variant.VariantList;
import javafish.clients.opc.browser;

import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
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
	    	
			try {
	   	      JOpc.coInitialize();
	   	    }
	    	 	catch (CoInitializeException e1) {
	   	      e1.printStackTrace();
	   	    }
	   	    
	    //create item with given tag name
	    OpcItem item1 = new OpcItem(tagNameGiven, true, "AdvManLab");
	    
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
	    
	    JOpc.coUninitialize();	 
	    
	    return "Tag Name Not Found";
	    }
	    
		
		/**
		 * 
		 * @param tagNamesGiven
		 * @return an ArrayList of tags with their values 
		 */
		public ArrayList<String> getTags(String[] tagNamesGiven) {
			try {
	   	      JOpc.coInitialize();
	   	    }
	    	 	catch (CoInitializeException e1) {
	   	      e1.printStackTrace();
	   	    }
		   	    
		    //create items with given tag names
			ArrayList<OpcItem> items = new ArrayList<OpcItem>();
			for(int i = 0; i<tagNamesGiven.length;i++) {
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
		    		ArrayList<String> tagNames = new ArrayList<String>();
		    		for(int i=0;i<items.size();i++) {
		    			//read data
			    		itemRead = jopc.synchReadItem(group, items.get(i));
			    		Variant itemReadValue = itemRead.getValue();
			    		int itemReadDataType = itemRead.getDataType();
			    		//store data in json object then arraylist
			    		JSONObject jObj = new JSONObject();
		    			jObj.put(itemRead, itemReadValue);
		    			tagNames.add(jObj.toJSONString());
		    		}
		    		//System.out.println("Data Type: " + itemReadDataType + "Value: " itemReadValue);
		    		   
		    		return tagNames;
		    		
		    		JOpc.coUninitialize();
		    		
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
	    
		    finally {
			    ArrayList<String> noTags = new ArrayList<String>();
			    noTags.add("Tag Names Not Found");
			    return noTags;
		    }
	   }
		
		
	   /**
	    * 
	    * @param groupName
	    * @return array of tag names in given group
	    */
	   public String[] getAvailableTagsInGroup(String groupName) {
	   
		   JOpcBrowser browser = new JOpcBrowser("localhost", "RSLinx OPC Server", "JOPCBROWSER1");
		   
		   try {
			   browser.connect();
			   String[] items = browser.getOpcItems(groupName);
			   return items;
		   }
		   catch(ConnectivityException | UnableBrowseBranchException | UnableIBrowseException e) {
			   e.printStackTrace();
		   }
		   String[] noItems = new String[]{"There are no existing items in this group"}; 
		   return noItems;
	    }
	   
	   /**
	    * 
	    * 
	    * @return an array of group names
	    */
	   public String[] getExistingGroups() {
	   
		   JOpcBrowser browser = new JOpcBrowser("localhost", "RSLinx OPC Server", "JOPCBROWSER1");
		   
		   try {
			   browser.connect();
			   String[] groups = browser.getOpcBranch();
			   return groups;
		   }
		   catch(ConnectivityException | UnableBrowseBranchException | UnableIBrowseException e) {
			   e.printStackTrace();
		   }
		   String[] noGroups = new String[]{"There are no existing groups"}; 
		   return noGroups;
	    }
	   

}


