package name.lxm.targets.http;

import java.util.UUID;

import org.json.JSONException;
import name.lxm.targets.ConfDoc;
import name.lxm.targets.model.PadEntity;
import name.lxm.targets.model.PadsCollection;

/**
 * <p>
 * This class is used to process the login request from pad controller.
 * </p>
 * 
 * 
 * @author Xiaoming Li Mar 14, 2017
 *
 */
public class LoginHandler extends AbstractHandler {

	public LoginHandler() {

	}

	void doJob() {
		try {
			String operation = jsonRequest.optString("operation");
			if (operation.equals("logout")) {
				doLogout();
			} else {
				doLogin();
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * logout
	 */
	private void doLogout() {
		int padid = jsonRequest.getInt("padid");
		String padName = jsonRequest.optString("pad-name");
		if (padName.isEmpty())
			padName = "未命名设备";
		String token = jsonRequest.getString("token");
		// check the token
		if (token.equals(PadsCollection.INSTANCE.get(padid).getToken())) {
			// remove or set the state of the pad controller
			PadsCollection.INSTANCE.get(padid).setState(0);
			jsonResponse.put("result", "success");
			return;
		}
		jsonResponse.put("result", "failed");
		jsonResponse.put("reason", "Not registered.");
	}

	private void doLogin() {
		int padid = jsonRequest.getInt("padid");
		String padName = jsonRequest.optString("pad-name");
		if (padName.isEmpty())
			padName = "未命名设备";
		String token = jsonRequest.getString("token");
		// check the token
		if (!token.equals(ConfDoc.INSTANCE.getDefaultToken())) {
			jsonResponse.put("result", "failed.");
			jsonResponse.put("reason", "wrong token.");
			return;
		}
		;
		// create the pad-controller entity
		PadEntity pe = new PadEntity(padid, padName, 1, System.currentTimeMillis());
		// add it pad queue
		PadsCollection.INSTANCE.put(pe);
		// set a new token for future interaction
		pe.setToken(UUID.randomUUID().toString());

		jsonResponse.put("result", "success");
		jsonResponse.put("new-token", pe.getToken());
		jsonResponse.put("pad-count", PadsCollection.INSTANCE.getSize());
	}

}
