package name.lxm.targets.model;

import java.util.ArrayList;
import java.util.ListIterator;

import org.json.JSONObject;

public class GroupEntity {
	
	private int groupID;
	
	private String groupName;
	
	private ArrayList<Integer> arrayOfTargets = new ArrayList<>();
	
	private int padID;
	
	public GroupEntity(int groupID, int padID, String groupName)
	{
		this.groupID = groupID;
		this.padID = padID;
		this.groupName = groupName;
	}
	
	public int getGroupID() {
		return groupID;
	}

	public String getGroupName() {
		return groupName;
	}

	public int getPadID() {
		return padID;
	}
	
	public void addTargets(int targetID)
	{
		arrayOfTargets.add(targetID);
	}
	
	public void removeTargets(int targetID)
	{
		arrayOfTargets.remove(targetID);
	}
	
	public ListIterator<Integer> getListOfTargets()
	{
		return arrayOfTargets.listIterator();
	}
	
	public JSONObject toJSON()
	{
		JSONObject jo = new JSONObject();
		jo.put("groupid", groupID);
		jo.put("padid", padID);
		jo.put("groupname", groupName);
		jo.put("targets", arrayOfTargets);
		return jo;
	}
}
