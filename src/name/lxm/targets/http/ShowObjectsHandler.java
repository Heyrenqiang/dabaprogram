package name.lxm.targets.http;

import org.json.JSONArray;
import org.json.JSONObject;

import name.lxm.targets.model.ActionCommand;
import name.lxm.targets.model.ActionQueue;
import name.lxm.targets.model.GroupCollection;
import name.lxm.targets.model.GroupEntity;
import name.lxm.targets.model.PadEntity;
import name.lxm.targets.model.PadsCollection;
import name.lxm.targets.model.TargetEntity;
import name.lxm.targets.model.TargetsCollection;
import name.lxm.targets.model.TraineeEntity;
import name.lxm.targets.model.TraineeEntityCollection;

public class ShowObjectsHandler extends AbstractHandler {

	@Override
	void doJob() {
		// for debug, no need to check the login status
		String action = jsonRequest.getString("action");
		if (action.equals("showall") || action.equals("showpads")) {

			JSONArray ja1 = new JSONArray();

			if (!PadsCollection.INSTANCE.isEmpty()) {
				for (PadEntity pe : PadsCollection.INSTANCE.getPadsCollection()) {
					ja1.put(new JSONObject(pe));
				}
			}
			jsonResponse.put("pads", ja1);
		}

		if (action.equals("showall") || action.equals("showtargets")) {

			JSONArray ja2 = new JSONArray();
			if (!TargetsCollection.INSTANCE.isEmpty()) {
				for (TargetEntity te : TargetsCollection.INSTANCE.getTargetsCollection()) {
					ja2.put(new JSONObject(te));
				}
			}
			jsonResponse.put("targets", ja2);
		}

		if (action.equals("showall") || action.equals("showactions")) {

			JSONArray ja3 = new JSONArray();
			if (!ActionQueue.INSTANCE.isEmpty()) {
				for (ActionCommand ac : ActionQueue.INSTANCE.getActionCollection()) {
					ja3.put(new JSONObject(ac));
				}
			}
			jsonResponse.put("actions", ja3);
		}
		
		if(action.equals("showall") || action.equals("showtrainees")){
			JSONArray jsonArrayTrainee = new JSONArray();
			if(TraineeEntityCollection.INSTANCE.getSize() > 0){
				for(TraineeEntity te: TraineeEntityCollection.INSTANCE.getCollection()){
					JSONObject jo = new JSONObject(te);
					jsonArrayTrainee.put(jo);
				}
			}
			jsonResponse.put("trainee_status", jsonArrayTrainee);
		}
		
		if(action.equals("showall") || action.equals("showgroups")){
			JSONArray ja = new JSONArray();
			if(GroupCollection.INSTANCE.getSize() > 0){
				for(GroupEntity ge : GroupCollection.INSTANCE.getCollection()){
					JSONObject jo = ge.toJSON();
					ja.put(jo);
				}
			}
			jsonResponse.put("group_info", ja);
		}

	}

}
