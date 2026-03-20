package name.lxm.targets.model;

import name.lxm.targets.wireless.data.GPSData;


/**
 * 靶子的实体类
 * 
 * @author Xiaoming Li
 *
 */
public class TargetEntity {
	public static final int CODE = 0;
	/**
	 * 尚未使用，以后可能会删除。
	 */
	private int code ; //对象码
	/**
	 * 靶编号。0代表着尚未分配合法的编号。
	 */
	private int ID; //靶编号
	/**
	 * 靶物理地址
	 */
	private byte[] SN; //靶物理号，即MAC地址
	/**
	 * 所属组编号，一个靶只能属于一个组。
	 */
	private int group; //组编号
	/**
	 * GPS数据，共三个，分别是lat，lan, att;
	 */
	private GPSData gps; //gps
	/**
	 * 靶温度数据
	 */
	private double temprature; //靶温度
	/**
	 * 靶电池电压
	 */
	private double voltage; //靶电池电压
	/**
	 * 靶气源压力
	 */
	private double presure; //靶气源压力
	/**
	 * 靶成绩数据，两个字节，前者是区域数据，后者是环数。
	 */
	private byte[] grade; //靶成绩
	
	/**
	 * 时间戳，6个字节，对应于“HHMMSS”
	 */
	private byte[] timestamp; 
	
	/**
	 * 靶子的状态：立或倒
	 */
	private byte status;
	
	
	/**
	 * 内部使用，最后访问时间
	 */
	private long lastUpdate;
	private boolean initialized;
	
	
	public boolean isInitialized() {
		return initialized;
	}

	public GPSData getGps() {
		return gps;
	}

	public void setGps(GPSData gps) {
		this.gps = gps;
	}

	public double getTemprature() {
		return temprature;
	}

	public void setTemprature(double temprature) {
		this.temprature = temprature;
	}

	public double getVoltage() {
		return voltage;
	}

	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}

	public double getPresure() {
		return presure;
	}

	public void setPresure(double presure) {
		this.presure = presure;
	}

	public byte[] getGrade() {
		return grade;
	}

	public void setGrade(byte[] grade) {
		this.grade = grade;
	}

	public TargetEntity()
	{
		SN = new byte[8];
		group = 0;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public byte[] getSN() {
		return SN;
	}

	public void setSN(byte[] sN) {
		System.arraycopy(sN, 0, SN, 0, 8);
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}
	
	/**
	 * 构造函数，根据靶子的基本数据。
	 * 
	 * @param code 对象编码，暂时不用
	 * @param id 靶编号
	 * @param sn 靶物理地址
	 * @param group 靶分组，默认0
	 * @param lastUpdate 更新时间戳
	 */
	public TargetEntity(int code, int id, byte[] sn, int group, long lastUpdate)
	{
		this();
		this.code = code;
		this.ID = id;
		this.group = group;
		System.arraycopy(sn, 0, SN, 0, 8);
		this.lastUpdate = lastUpdate;
	}

	public byte[] getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(byte[] timestamp) {
		this.timestamp = timestamp;
	}

	public long getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setStatus(byte state)
	{
		status = state;
	}
	
	public byte getStatus()
	{
		return status;
	}

	public void setInitialized(boolean b) {
		initialized = b;
	}
}
