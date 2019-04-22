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
import javafish.clients.opc.exception.UnableBrowseLeafException;
import javafish.clients.opc.variant.Variant;
import javafish.clients.opc.variant.VariantList;
import javafish.clients.opc.browser.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class getAllInfo {
	
		private JOpc jopc = new JOpc("localhost", "RSLinx OPC Server", "JOPC1");
		
	   
	    
		/**
		 * @param tagNameGiven a user inputed tag name to request data from
		 * @return a JSON Object with tag values (name, active, access path, value, timestamp) 
		 */
		@SuppressWarnings("unchecked")
	    public JSONObject getTag(String tagNameGiven, String groupName) {
	    	
			try {
	   	      JOpc.coInitialize();
	   	    }
	    	catch (CoInitializeException e1) {
	   	      e1.printStackTrace();
	   	    }
	   	    
	    //create item with given tag name
	    OpcItem item1 = new OpcItem(tagNameGiven, true, groupName);
	    
	    //create opc group
	    OpcGroup group = new OpcGroup("group1", true, 500, 0.0f);
	    group.addItem(item1);
	    
	    //connect to OPC to register group/item
	    try {
	    		//connect to opc
	    		jopc.connect();
	    		
	    		//add group given
	    		jopc.addGroup(group);
	    		
	    		//register group given
	    		jopc.registerGroup(group);
	    	
	    		//register given item to given group
	    		jopc.registerItem(group, item1);
	    		
	    		//read tag from server
	    		OpcItem itemRead = null;
	    		itemRead = jopc.synchReadItem(group, item1);
	  
	    		String name = Variant.getVariantName(itemRead.getDataType());
	    		
	    		//create new JSONObject to store tag info
	    		JSONObject jObj = new JSONObject();
	    		//add client handle
	    		jObj.put("clientHandle", itemRead.getClientHandle());
	    		//add item name
	    		jObj.put("itemName", itemRead.getItemName());
	    		//add item  activity
	    		jObj.put("active", itemRead.isActive());
	    		//add access path
	    		jObj.put("accessPath", itemRead.getAccessPath());
	    		//add time stamp
	    		jObj.put("timeStamp", itemRead.getTimeStamp().getTime());
	    		//add item data type
	    		jObj.put("dataType", name);
	    		//add item value
	    		jObj.put("value", itemRead.getValue());
	    		//add item quality
	    		jObj.put("quality", itemRead.isQuality());
	    		return jObj;
	    		
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
	    
	    return null;
	    }
	    
		
		/**
		 * 
		 * @param tagNamesGiven
		 * @return an ArrayList of tags with their values 
		 */
		@SuppressWarnings("unchecked")
		public JSONArray getTags(String[] tagNamesGiven, String[] groupNamesGiven) {
			try {
	   	      JOpc.coInitialize();
	   	    }
	    	catch (CoInitializeException e1) {
	   	      e1.printStackTrace();
	   	    }
		   	    
		    //create items with given tag names
			ArrayList<OpcItem> items = new ArrayList<OpcItem>();
			for(int i = 0; i<tagNamesGiven.length;i++) {
				items.add(new OpcItem(tagNamesGiven[i], true, groupNamesGiven[i]));
			}
		    
		    //create opc group
		    OpcGroup group = new OpcGroup("group1", true, 500, 0.0f);
		    for(int i = 0;i<items.size();i++) {
		    		group.addItem(items.get(i));
		    }
		    
		    //connect to OPC to register group/item
		    try {
		    		//connect to opc
		    		jopc.connect();
		    		
		    		//add given group
		    		jopc.addGroup(group);
		    		
		    		//register given group
		    		jopc.registerGroup(group);
		    		
		    		//register each tag to group
		    		for(int i = 0;i<items.size();i++) {
		    			jopc.registerItem(group, items.get(i));
			    }
		    		
		    		//read tags and create JSONArray to store information
		    		OpcItem itemRead = null;
		    		JSONArray tagNames = new JSONArray();
		    		for(int i=0;i<items.size();i++) {
		    			//read data
			    		itemRead = jopc.synchReadItem(group, items.get(i));
			    		
			    		String name = Variant.getVariantName(itemRead.getDataType());
			    		
			    		//store data in json object then arraylist
			    		JSONObject jObj = new JSONObject();
			    		//add client handle
			    		jObj.put("clientHandle", itemRead.getClientHandle());
			    		//add item name
			    		jObj.put("itemName", itemRead.getItemName());
			    		//add item  activity
			    		jObj.put("active", itemRead.isActive());
			    		//add access path
			    		jObj.put("accessPath", itemRead.getAccessPath());
			    		//add time stamp
			    		jObj.put("timeStamp", itemRead.getTimeStamp().getTime());
			    		//add item data type
			    		jObj.put("dataType", name);
			    		//add item value
			    		jObj.put("value", itemRead.getValue());
			    		//add item quality
			    		jObj.put("quality", itemRead.isQuality());
		    			tagNames.add(jObj);
		    		}
		    		
		    		JOpc.coUninitialize(); 
		    		
		    	
		    		return null;
		    		
		    		
		    		
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
			    return null;
		    
	   }
		
		
	   /**
	    * 
	    * @param groupName
	    * @return array of tag names in given group
	    */
	   public List<String> getAvailableTagsInGroup(String groupName) {
	   
		   JOpcBrowser browser = new JOpcBrowser("localhost", "RSLinx OPC Server", "BROWSER");
		  
		   try {
			   JOpcBrowser.coInitialize();
		   }
		   catch(CoInitializeException e) {
			   e.printStackTrace();
		   }
		   
		   //try connecting to JOPCBrowser and return all online tags in a given group
		   try {
			   browser.connect();
			   browser.getOpcBranch("");
			   String[] items = browser.getOpcItems(groupName + ".Online", true);
			   if(items.length == 0) {
				   List<String> noItems = new ArrayList<String>();
				   noItems.add("All tags are offline in " + groupName);
			   }
			   return Arrays.asList(items);
		   }
		   catch(ConnectivityException | UnableBrowseLeafException | UnableIBrowseException | UnableAddGroupException | UnableAddItemException | UnableBrowseBranchException e) {
			   e.printStackTrace();
		   }
		  
		   JOpcBrowser.coUninitialize();
		   
		   
		   List<String> noItems = new ArrayList<String>();
		   noItems.add("There are no existing items in this group"); 
		   return noItems;
		   
	    }
	   
	   /**
	    * 
	    * 
	    * @return an array of group names
	    */
	   public List<String> getExistingGroups() {
	   
		   JOpcBrowser browser = new JOpcBrowser("localhost", "RSLinx OPC Server", "JOPCBROWSER1");
		   
		   try {
			   JOpcBrowser.coInitialize();
		   }
		   catch(CoInitializeException e) {
			   e.printStackTrace();
		   }
		   
		   //try connecting to JOPCBrowser and return all branches
		   try {
			   browser.connect();
			   String[] groups = browser.getOpcBranch("");
			   return Arrays.asList(groups);
		   }
		   catch(ConnectivityException | UnableBrowseBranchException | UnableIBrowseException e) {
			   e.printStackTrace();
		   }
		   
		   JOpcBrowser.coUninitialize();
		   
		   List<String> noGroups = new ArrayList<String>();
		   noGroups.add("There are no existing groups"); 
		   return noGroups;
	   
	    }
	   

}