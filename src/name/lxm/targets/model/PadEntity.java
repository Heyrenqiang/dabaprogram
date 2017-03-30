package name.lxm.targets.model;

import java.net.Socket;

/**
 * 该对象用来描述平板（控制器）在服务器内的代理实体。
 * 
 * @author Xiaoming Li Mar 12, 2017
 *
 */
public class PadEntity {
	/**
	 * ID of the pad. 0 means it has not been assigned a valid one.
	 */
	private int id;
	private String name;
	private int state; //0:offline 1:online
	private Socket socket;
	private long lastVisit;
	private String token;
	
	/**
	 * Construct a pad entity.
	 * 
	 * @param id pad-id
	 * @param name name for the pad
	 * @param state state of the pad, 0: offline; 1:online
	 * @param visit time-stamp for last visiting.
	 */
	public PadEntity(int id, String name, int state, long visit)
	{
		this.id = id;
		this.name = name;
		this.state = state;
		this.lastVisit = visit;
	}
	
	public void setToken(String token)
	{
		this.token = token;
	}
	
	public String getToken()
	{
		return token;
	}

	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public int getState() {
		return state;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public long getLastVisit() {
		return lastVisit;
	}

	/**
	 * 设置最后访问时间
	 * @param lastVisit
	 */
	public void setLastVisit(long lastVisit) {
		this.lastVisit = lastVisit;
	}

	/**
	 * Set the state of the pad controller
	 * 
	 * @param state 0:offline, 1:online
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	
}
