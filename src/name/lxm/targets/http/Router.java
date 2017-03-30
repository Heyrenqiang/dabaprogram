package name.lxm.targets.http;

import java.util.Hashtable;

public class Router {
	
	private Hashtable<String, Handler> routeTable = new Hashtable<>();
	
	public Router()
	{
		routeTable.put("/login", new LoginHandler());
		routeTable.put("/update", new UpdateHandler());
		routeTable.put("/action", new ActionHandler());
		routeTable.put("/show", new ShowObjectsHandler());
	}

	public Handler findActionHandler(String string) 
	{
		int start = string.indexOf('/'); 
		int end = string.lastIndexOf('?');
		String key = string.substring(start, end==-1?string.length():end);
		Handler handler = routeTable.get(key);
		if(handler != null) return handler;
		return DefaultHandler.INSTANCE;
	}

}