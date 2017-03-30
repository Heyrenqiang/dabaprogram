package name.lxm.targets.http;

import name.lxm.targets.model.JsonMessage;

public class DefaultHandler implements Handler {
	
	public static DefaultHandler INSTANCE = new DefaultHandler();
	private DefaultHandler(){}

	@Override
	public String doProcess(String get) {
		JsonMessage msg = new JsonMessage(402);
		return msg.toJSONString();
	}

}
