package name.lxm.targets.model;

import name.lxm.targets.wireless.data.GPSData;

/**
 * 训练人员信息
 * @author lxm Mar 18, 2017
 *
 */
public class TraineeEntity {
	
	private int ID;
	private String name;
	private GPSData gps;
	private double battery;
	private byte[] SN;
	private byte[] timestamp;
	private long lastUpdate;
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GPSData getGps() {
		return gps;
	}
	public void setGps(GPSData gps) {
		this.gps = gps;
	}
	public double getBattery() {
		return battery;
	}
	public void setBattery(double battery) {
		this.battery = battery;
	}
	public byte[] getSN() {
		return SN;
	}
	public void setSN(byte[] sN) {
		SN = sN;
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
	
	
	
	

}
