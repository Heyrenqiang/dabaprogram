package name.lxm.targets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

public class ConfDoc {

	private String confpath = "config.json";
	private JSONObject jconf;
	
	
	public static ConfDoc INSTANCE = new ConfDoc();
	
	private ConfDoc()
	{
		File f = new File(confpath);
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			String contents = "";
			while((line = br.readLine()) != null){
				contents = contents + line;
			}
			br.close();
			jconf = new JSONObject(contents);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Object getDefaultToken() {
		
		return jconf.optString("default-token");
	}

	public int getServerPort() {
		int port = jconf.optInt("server_port", 4444);
		
		return port;
	}

	public String getRouterAddress() {
		return jconf.optString("router_ip", "192.168.1.1");
	}
	
	public int getRouterPort()
	{
		return jconf.optInt("router_port", 3128);
	}
	
}
