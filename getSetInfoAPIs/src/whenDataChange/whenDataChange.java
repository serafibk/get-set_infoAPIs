package whenDataChange;

import getInfo.getAllInfo;
import org.json.simple.JSONObject;
public class whenDataChange {

	
	public String checkDataChange(String TagName, String GroupName) {
		getAllInfo requestTag = new getAllInfo();
		JSONObject tag = requestTag.getTag(TagName, GroupName);
		
		int initialVal = (int)tag.get("value");
		int checkVal = initialVal;
		while(checkVal == initialVal) {
			JSONObject checkTag = requestTag.getTag(TagName, GroupName);
			checkVal = (int)checkTag.get("value");
		}
		return "Value of " + tag.get("itemName") + " has changed to " + checkVal;
	}

	
	
	
	
}
