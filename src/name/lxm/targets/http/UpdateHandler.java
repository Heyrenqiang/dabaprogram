package name.lxm.targets.http;

import org.json.JSONArray;
import org.json.JSONObject;

import name.lxm.targets.TMApplication;
import name.lxm.targets.model.GroupCollection;
import name.lxm.targets.model.GroupEntity;
import name.lxm.targets.model.TargetEntity;
import name.lxm.targets.model.TargetsCollection;
import name.lxm.targets.model.TraineeEntity;
import name.lxm.targets.model.TraineeEntityCollection;

public class UpdateHandler extends AbstractHandler {
	
	public UpdateHandler() {

	}

	/**
	 * 处理更新事务
	 */
	void doJob() {
		//检查请求是否合法
		if(isValidRequest()){
			getUpdateData();
		}else{
			jsonResponse.put("result", "failed");
			jsonResponse.put("reason", "wrong padid or token not accepted.");
		}
	}
	
	private void getUpdateData()
	{
		//去数据池获取所要发送的状态数据，包括：应用状态，靶子状态集合，人员状态集合，以及组信息
		//然后生成对应的JSON数据
		//然后写入到返回字符串中。
		
		//1: Get State
		int state = TMApplication.INSTANCE.getMode();
		String sState;
		if(state == TMApplication.MODE_INITIALIZED) sState = "initialized";
		else if(state == TMApplication.MODE_READY) sState = "ready";
		else sState = "running";
		
		jsonResponse.put("result", "success");
		jsonResponse.put("state", sState);
		
		//2: Get Target Status Collection
		JSONArray jsonArray = new JSONArray();
		if(TargetsCollection.INSTANCE.getCount() > 0){
			for(TargetEntity te : TargetsCollection.INSTANCE.getTargetsCollection()){
				JSONObject jo = new JSONObject(te);
				jsonArray.put(jo);
			}
		}
		jsonResponse.put("target_status", jsonArray);
		
		//3: Get trainee information
		JSONArray jsonArrayTrainee = new JSONArray();
		if(TraineeEntityCollection.INSTANCE.getSize() > 0){
			for(TraineeEntity te: TraineeEntityCollection.INSTANCE.getCollection()){
				JSONObject jo = new JSONObject(te);
				jsonArrayTrainee.put(jo);
			}
		}
		jsonResponse.put("trainee_status", jsonArrayTrainee);
		
		//4: Group Information, if possible
		int padID = jsonRequest.getInt("padid");
		GroupEntity ge =  GroupCollection.INSTANCE.findGroupByPad(padID);
		if(ge != null){
			JSONObject jo = ge.toJSON();
			jsonResponse.put("group_info", jo);
		}

		//Done!
	}

}
