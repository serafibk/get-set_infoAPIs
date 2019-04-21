## getAllInfo() API
Initial set up - 
The JAR file for this API is stored in Documents-> 02_MDP_Students -> MDP_WN18->CloudTeam -> JARS as getAllInfo.jar
Saved on local testbed under src/SerafinaTest
Add to project build path by right clicking project -> configure build path -> add external JARs -> getAllInfo.jar (from specified spot in first bullet)
Import into class using import getInfo.getAllInfo;
  
## getTag() - 

Takes in tag name and group name of tag as parameters

Returns a JSONObject with all the properties of the given tag (keys):

“clientHandle”, data type = string

“itemName”, data type = string

“active”, data type = boolean

“accessPath”, data type = string

“timeStamp”, data type = gregorian calendar

“value”, data type = Variant

“quality”, data type = boolean

Access information 

JSONObject tag = <name> .getTag(tagName, groupName);

tag.get(“property”) where “property” is anything from the above list (keys). 
  
## getTags() - 

Takes in tag names and group names of tags as parameters

Returns a JSONArray of JSONObjects with all the properties of the given tags (keys):

“clientHandle”, data type = string

“itemName”, data type = string

“active”, data type = boolean

“accessPath”, data type = string

“timeStamp”, data type = gregorian calendar

“value”, data type = Variant

“quality”, data type = boolean

Access information 

List<JSONObject> tags = (JSONObject)<name>.getTags(tagNames, groupNames)

Get information using property names: tags.get(“property”)
  
## getAvailableTagsInGroup() -
Takes in group name as parameter, returns all online tags in given group in List<String> format

Access information

List<String> availableTags = <name>.getAvailableTagsInGroup(groupName)
Print out using availableTags.get(i)

## getExistingGroups() - 
Takes in no parameters, returns a list of all existing groups on the server in List<String> format

Access information

List<String> groups = <name>.getExistingGroups()
Print out using groups.get(i)
	
## setAllInfo() API
Initial set up - 
The JAR file for this API is stored in Documents-> 02_MDP_Students -> MDP_WN18->CloudTeam -> JARS as setAllInfo.jar
Saved on local testbed under src/SerafinaTest
Add to project build path by right clicking project -> configure build path -> add external JARs -> setAllInfo.jar (from specified spot in first bullet)
Import into class using import setInfo.setAllInfo;

## writeTag() - 
Takes in the tag name too modify, its group name and the new value as parameters
New value is a variant, function supports all types of variants (int, boolean, double, float, etc.)
Returns a string that tells you if the writing was successful or not

Use

setAllInfo <name> = new setAllInfo()

Print(<name>.writeTag(tagName, groupName, value)

“Successful writing” if value was changed ,“Unsuccessful writing” if value was incorrectly changed or not changed


