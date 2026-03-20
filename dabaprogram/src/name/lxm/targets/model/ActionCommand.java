package name.lxm.targets.model;

import org.json.JSONObject;

public class ActionCommand {
	
	private long timeStamp;
	private int[] arrayOfTargets;
	
	
	public long getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public int[] getArrayOfTargets() {
		return arrayOfTargets;
	}
	
	
	public void setArrayOfTargets(int[] arrayOfTargets) {
		this.arrayOfTargets = arrayOfTargets;
	}
	
	public ActionCommand(long timestamp, int[] ids)
	{
		this.timeStamp = timestamp;
		this.arrayOfTargets = ids;
	}

	public String toString()
	{
		return new JSONObject(this).toString();
	}
}
