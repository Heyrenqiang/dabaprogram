package name.lxm.targets.wireless.data;

public class GPSData {
	private double lat;
	private double lon;
	private double att;
	private boolean valid;
	
	public GPSData(boolean valid, double lat, double lon, double att)
	{
		this.valid = valid;
		this.lat = lat;
		this.lon = lon;
		this.att = att;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public double getAtt() {
		return att;
	}

	public boolean isValid() {
		return valid;
	}
	
	public String toString()
	{
		return Double.toString(lat) + "," + Double.toString(lon) + "," + Double.toString(att)
				+ "," + valid;
	}
	
}
