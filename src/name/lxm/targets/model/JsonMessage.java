package name.lxm.targets.model;

import name.lxm.targets.Utility;

/**
 * This is used to transfer data between server and client.
 * 
 * @author Xiaomingli Mar 12, 2017
 *
 */
public class JsonMessage {

	private long seqid; //using timestamp as seqence id.
	private int error; //error code.
	private String dataName; //name of json data.
	private String jsonObject; //value of json data.
	
	public JsonMessage()
	{
		seqid = System.currentTimeMillis();
		error = 0;
		dataName = "";
		jsonObject = "{}";
	}
	
	public JsonMessage(String dataname, String jsonobject)
	{
		seqid = System.currentTimeMillis();
		error = 0;
		dataName = dataname;
		jsonObject = jsonobject;
	}
	
	public JsonMessage(int code)
	{
		seqid = System.currentTimeMillis();
		error = code;
		dataName = "";
		jsonObject = "{}";
	}
	
	public void setErrorCode(int code)
	{
		error = code;
	}
	
	public String toJSONString()
	{
		return "{" + Utility.toJSONStringType("Seqid") + ":" + Long.toString(seqid) + ","
				+ Utility.toJSONStringType("Error") + ":" + Integer.toString(error) + ","
				+ Utility.toJSONStringType("DataName") + ":" + Utility.toJSONStringType(dataName) + ","
				+ Utility.toJSONStringType("value") + ":" + jsonObject
				+"}"; 
	}
}
