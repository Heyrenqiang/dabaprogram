package name.lxm.targets.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.json.JSONException;
import org.json.JSONObject;

import name.lxm.targets.model.JsonMessage;
import name.lxm.targets.model.PadsCollection;

public abstract class AbstractHandler implements Handler {

	protected JSONObject jsonRequest;
	protected JSONObject jsonResponse;

	@Override
	public String doProcess(String get)
	{
		try {
			get = get.substring(get.indexOf('?') + 1);
			get = get.trim();
			get = URLDecoder.decode(get, "UTF-8");
			if (!get.isEmpty() && get.startsWith("{") && get.endsWith("}")) {
				// create an Json object
				jsonRequest = new JSONObject(get);
				jsonResponse = new JSONObject();
				// main job
				doJob();
				// set the response
				return jsonResponse.toString();
			}

		} catch (UnsupportedEncodingException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JsonMessage(-1).toJSONString();
	}

	abstract void doJob();
	
	protected boolean isValidRequest()
	{
		int padid = jsonRequest.getInt("padid");
		String token = jsonRequest.getString("token");
		return (PadsCollection.INSTANCE.checkToken(padid, token));
	}
	

}
