package whenDataChange;

import getInfo.getAllInfo;
import org.json.simple.JSONObject;
public class whenDataChange {

	
	public String checkDataChange(String TagName, String GroupName) throws InterruptedException {
		getAllInfo requestTag = new getAllInfo();
		JSONObject tag = requestTag.getTag(TagName, GroupName);
		
		Object initialVal = tag.get("value");
		Object checkVal = initialVal;
		while(checkVal == initialVal) {
			Thread.sleep(1000);
			JSONObject checkTag = requestTag.getTag(TagName, GroupName);
			checkVal = checkTag.get("value");
		}
		return "Value of " + tag.get("itemName") + " has changed to " + checkVal;
	}

	
	
	
	
}
