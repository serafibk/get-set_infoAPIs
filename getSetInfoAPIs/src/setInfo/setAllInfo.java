package setInfo;

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


public class setAllInfo {

	
	private JOpc jopc = new JOpc("localhost", "RSLinx OPC Server", "JOPC");
	
	/**
	 * 
	 * @param Tag Name (string)
	 * @param Group Name (string)
	 * @param Tag Value (integer)
	 * @return String that tells if write was successful or not
	 */
	public String writeTag(String tagName, String groupName, int value) {
		String success; 
		try {
			JOpc.coInitialize();
		}
		catch(CoInitializeException e1) {
			e1.printStackTrace();
		}
		
		//create OpcItem
		OpcItem item1 = new OpcItem(tagName, true, groupName);
		
		//create OpcGroup
		OpcGroup group1 = new OpcGroup(groupName, 500, true, 0.0f);
		group1.addItem(item1);
		
		//connect to OPC and register group/item
		try {
			jopc.connect();
			
			//add group
			jopc.addGroup(group1);
			
			//register group
			jopc.registerGroup(group1);
			
			//register item
			jopc.registerItem(group1, item1);
			
			//set value of tag
			Variant var = new Variant(value);
			item1.setValue(var);
			
			//write tag to server
			jopc.synchWriteItem(group1, item1);
			
			//wait 2 sec
			Thread.sleep(2000);
			
			//check to see if tag was written successfully
			if(jopc.synchReadItem(group1, item1) != null) {
				success = "successful writing";
			}
			else {
				success = "unsuccessful writing";
			}
			
			//unregister item
			jopc.unregisterItem(group1, item1);
			
			//unregister group
			jopc.unregiserGroup(group1);
			
			JOpc.coUninitialize();
			
		}
		catch(ConnectivityException | ComponentNotFoundException | UnableAddGroupException | UnableAddItemException | SynchWriteException | CoUninitializeException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 
	 * @param Tag Name (string)
	 * @param Group Name (string)
	 * @param Tag Value (double)
	 * @return String that tells if write was successful or not
	 */
	public String writeTag(String tagName, String groupName, double value) {
		String success; 
		try {
			JOpc.coInitialize();
		}
		catch(CoInitializeException e1) {
			e1.printStackTrace();
		}
		
		//create OpcItem
		OpcItem item1 = new OpcItem(tagName, true, groupName);
		
		//create OpcGroup
		OpcGroup group1 = new OpcGroup(groupName, 500, true, 0.0f);
		group1.addItem(item1);
		
		//connect to OPC and register group/item
		try {
			jopc.connect();
			
			//add group
			jopc.addGroup(group1);
			
			//register group
			jopc.registerGroup(group1);
			
			//register item
			jopc.registerItem(group1, item1);
			
			//set value of tag
			Variant var = new Variant(value);
			item1.setValue(var);
			
			//write tag to server
			jopc.synchWriteItem(group1, item1);
			
			//wait 2 sec
			Thread.sleep(2000);
			
			//check to see if tag was written successfully
			if(jopc.synchReadItem(group1, item1) != null) {
				success = "successful writing";
			}
			else {
				success = "unsuccessful writing";
			}
			
			//unregister item
			jopc.unregisterItem(group1, item1);
			
			//unregister group
			jopc.unregiserGroup(group1);
			
			JOpc.coUninitialize();
			
		}
		catch(ConnectivityException | ComponentNotFoundException | UnableAddGroupException | UnableAddItemException | SynchWriteException | CoUninitializeException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 
	 * @param Tag Name (string)
	 * @param Group Name (string)
	 * @param Tag Value (string)
	 * @return String that tells if write was successful or not
	 */
	public String writeTag(String tagName, String groupName, String value) {
		String success; 
		try {
			JOpc.coInitialize();
		}
		catch(CoInitializeException e1) {
			e1.printStackTrace();
		}
		
		//create OpcItem
		OpcItem item1 = new OpcItem(tagName, true, groupName);
		
		//create OpcGroup
		OpcGroup group1 = new OpcGroup(groupName, 500, true, 0.0f);
		group1.addItem(item1);
		
		//connect to OPC and register group/item
		try {
			jopc.connect();
			
			//add group
			jopc.addGroup(group1);
			
			//register group
			jopc.registerGroup(group1);
			
			//register item
			jopc.registerItem(group1, item1);
			
			//set value of tag
			Variant var = new Variant(value);
			item1.setValue(var);
			
			//write tag to server
			jopc.synchWriteItem(group1, item1);
			
			//wait 2 sec
			Thread.sleep(2000);
			
			//check to see if tag was written successfully
			if(jopc.synchReadItem(group1, item1) != null) {
				success = "successful writing";
			}
			else {
				success = "unsuccessful writing";
			}
			
			//unregister item
			jopc.unregisterItem(group1, item1);
			
			//unregister group
			jopc.unregiserGroup(group1);
			
			JOpc.coUninitialize();
			
		}
		catch(ConnectivityException | ComponentNotFoundException | UnableAddGroupException | UnableAddItemException | SynchWriteException | CoUninitializeException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 
	 * @param Tag Name (string)
	 * @param Group Name (string)
	 * @param Tag Value (float)
	 * @return String that tells if write was successful or not
	 */
	public String writeTag(String tagName, String groupName, float value) {
		String success; 
		try {
			JOpc.coInitialize();
		}
		catch(CoInitializeException e1) {
			e1.printStackTrace();
		}
		
		//create OpcItem
		OpcItem item1 = new OpcItem(tagName, true, groupName);
		
		//create OpcGroup
		OpcGroup group1 = new OpcGroup(groupName, 500, true, 0.0f);
		group1.addItem(item1);
		
		//connect to OPC and register group/item
		try {
			jopc.connect();
			
			//add group
			jopc.addGroup(group1);
			
			//register group
			jopc.registerGroup(group1);
			
			//register item
			jopc.registerItem(group1, item1);
			
			//set value of tag
			Variant var = new Variant(value);
			item1.setValue(var);
			
			//write tag to server
			jopc.synchWriteItem(group1, item1);
			
			//wait 2 sec
			Thread.sleep(2000);
			
			//check to see if tag was written successfully
			if(jopc.synchReadItem(group1, item1) != null) {
				success = "successful writing";
			}
			else {
				success = "unsuccessful writing";
			}
			
			//unregister item
			jopc.unregisterItem(group1, item1);
			
			//unregister group
			jopc.unregiserGroup(group1);
			
			JOpc.coUninitialize();
			
		}
		catch(ConnectivityException | ComponentNotFoundException | UnableAddGroupException | UnableAddItemException | SynchWriteException | CoUninitializeException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 
	 * @param Tag Name (string)
	 * @param Group Name (string)
	 * @param Tag Value (byte)
	 * @return String that tells if write was successful or not
	 */
	public String writeTag(String tagName, String groupName, byte value) {
		String success; 
		try {
			JOpc.coInitialize();
		}
		catch(CoInitializeException e1) {
			e1.printStackTrace();
		}
		
		//create OpcItem
		OpcItem item1 = new OpcItem(tagName, true, groupName);
		
		//create OpcGroup
		OpcGroup group1 = new OpcGroup(groupName, 500, true, 0.0f);
		group1.addItem(item1);
		
		//connect to OPC and register group/item
		try {
			jopc.connect();
			
			//add group
			jopc.addGroup(group1);
			
			//register group
			jopc.registerGroup(group1);
			
			//register item
			jopc.registerItem(group1, item1);
			
			//set value of tag
			Variant var = new Variant(value);
			item1.setValue(var);
			
			//write tag to server
			jopc.synchWriteItem(group1, item1);
			
			//wait 2 sec
			Thread.sleep(2000);
			
			//check to see if tag was written successfully
			if(jopc.synchReadItem(group1, item1) != null) {
				success = "successful writing";
			}
			else {
				success = "unsuccessful writing";
			}
			
			//unregister item
			jopc.unregisterItem(group1, item1);
			
			//unregister group
			jopc.unregiserGroup(group1);
			
			JOpc.coUninitialize();
			
		}
		catch(ConnectivityException | ComponentNotFoundException | UnableAddGroupException | UnableAddItemException | SynchWriteException | CoUninitializeException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 
	 * @param Tag Name (string)
	 * @param Group Name (string)
	 * @param Tag Value (short)
	 * @return String that tells if write was successful or not
	 */
	public String writeTag(String tagName, String groupName, short value) {
		String success; 
		try {
			JOpc.coInitialize();
		}
		catch(CoInitializeException e1) {
			e1.printStackTrace();
		}
		
		//create OpcItem
		OpcItem item1 = new OpcItem(tagName, true, groupName);
		
		//create OpcGroup
		OpcGroup group1 = new OpcGroup(groupName, 500, true, 0.0f);
		group1.addItem(item1);
		
		//connect to OPC and register group/item
		try {
			jopc.connect();
			
			//add group
			jopc.addGroup(group1);
			
			//register group
			jopc.registerGroup(group1);
			
			//register item
			jopc.registerItem(group1, item1);
			
			//set value of tag
			Variant var = new Variant(value);
			item1.setValue(var);
			
			//write tag to server
			jopc.synchWriteItem(group1, item1);
			
			//wait 2 sec
			Thread.sleep(2000);
			
			//check to see if tag was written successfully
			if(jopc.synchReadItem(group1, item1) != null) {
				success = "successful writing";
			}
			else {
				success = "unsuccessful writing";
			}
			
			//unregister item
			jopc.unregisterItem(group1, item1);
			
			//unregister group
			jopc.unregiserGroup(group1);
			
			JOpc.coUninitialize();
			
		}
		catch(ConnectivityException | ComponentNotFoundException | UnableAddGroupException | UnableAddItemException | SynchWriteException | CoUninitializeException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 
	 * @param Tag Name (string)
	 * @param Group Name (string)
	 * @param Tag Value (boolean)
	 * @return String that tells if write was successful or not
	 */
	public String writeTag(String tagName, String groupName, boolean value) {
		String success; 
		try {
			JOpc.coInitialize();
		}
		catch(CoInitializeException e1) {
			e1.printStackTrace();
		}
		
		//create OpcItem
		OpcItem item1 = new OpcItem(tagName, true, groupName);
		
		//create OpcGroup
		OpcGroup group1 = new OpcGroup(groupName, 500, true, 0.0f);
		group1.addItem(item1);
		
		//connect to OPC and register group/item
		try {
			jopc.connect();
			
			//add group
			jopc.addGroup(group1);
			
			//register group
			jopc.registerGroup(group1);
			
			//register item
			jopc.registerItem(group1, item1);
			
			//set value of tag
			Variant var = new Variant(value);
			item1.setValue(var);
			
			//write tag to server
			jopc.synchWriteItem(group1, item1);
			
			//wait 2 sec
			Thread.sleep(2000);
			
			//check to see if tag was written successfully
			if(jopc.synchReadItem(group1, item1) != null) {
				success = "successful writing";
			}
			else {
				success = "unsuccessful writing";
			}
			
			//unregister item
			jopc.unregisterItem(group1, item1);
			
			//unregister group
			jopc.unregiserGroup(group1);
			
			JOpc.coUninitialize();
			
		}
		catch(ConnectivityException | ComponentNotFoundException | UnableAddGroupException | UnableAddItemException | SynchWriteException | CoUninitializeException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
	
	
}
