package name.lxm.targets.http;

import org.json.JSONArray;
import name.lxm.targets.model.ActionCommand;
import name.lxm.targets.model.ActionQueue;

public class ActionHandler extends AbstractHandler {


	void doJob() {
		/*check the identity*/
		if(isValidRequest()){
			recieveActionCommand();
			
		}else{
			jsonResponse.put("result", "failed");
			jsonResponse.put("reason", "wrong padid or token not accepted.");
		}
	}
	
	private void recieveActionCommand() {
		JSONArray ja = (JSONArray) jsonRequest.get("targets");
		int[] a = new int[ja.length()];
		for(int i=0; i<ja.length(); i++){
			a[i] = ja.getInt(i);
		}
		ActionCommand ac = new ActionCommand(System.currentTimeMillis(), a);
		System.out.println(ac.toString());
		ActionQueue.INSTANCE.push(ac);
		
		jsonResponse.put("result", "success");
		jsonResponse.put("reason", "");
	}
}
