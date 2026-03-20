package name.lxm.targets.ui;

public class Bazhiz {
	private int groupID;
	private int[] bazhisID;
	private Bazhi[] bazhis;
	private int bazhishu;
	private int padID;
	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getPadID() {
		return padID;
	}

	public void setPadID(int padID) {
		this.padID = padID;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int pingbanhao) {
		this.groupID = pingbanhao;
	}

	public Bazhiz() {

	}

	public void deleteBazhis(Bazhi[] bazhis) {

	}

	public void updateBazhis(Bazhi[] bazhis) {

	}

	public void addBazhis(Bazhi bazhi) {

	}

	public void updateBazhis(Bazhi bazhi) {

	}

	public int[] getBazhisID() {
		return bazhisID;
	}

	public void setBazhisID(int[] bazhisnum) {
		this.bazhisID = bazhisnum;
	}

	public Bazhi[] getBazhis() {
		return bazhis;
	}

	public void setBazhis(Bazhi[] bazhis) {
		this.bazhis = bazhis;
	}

	public Bazhiz(int groupid, Bazhi[] bazhis) {
		this.groupID = groupid;
		this.padID = groupid;
		this.groupName = String.valueOf(groupid);
		this.bazhis = bazhis;
		this.bazhishu = bazhis.length;
		bazhisID = new int[bazhis.length];
		for (int i = 0; i < bazhis.length; i++) {
			bazhisID[i] = bazhis[i].getBianhao();
		}
	}

	public void addBazhis(Bazhi[] bazhis) {
		int w = this.bazhishu + bazhis.length;
		Bazhi[] www = new Bazhi[w];
		for (int i = 0; i < this.bazhishu; i++) {
			www[i] = this.bazhis[i];
		}
		for (int i = 0; i < bazhis.length; i++) {
			www[this.bazhishu + i] = bazhis[i];
		}
		this.bazhis = www;
		this.bazhishu = w;
		this.bazhisID = new int[this.bazhis.length];
		for (int i = 0; i < this.bazhis.length; i++) {
			bazhisID[i] = this.bazhis[i].getBianhao();
		}
	}

	public void deleteBazhis(Bazhi bazhi) {
		// bazhi.setGroup(0);
		int w = this.bazhis.length - 1;
		Bazhi[] www = new Bazhi[w];
		int k = 0;
		for (int i = 0; i < this.bazhis.length; i++) {
			if (this.bazhis[i].equals(bazhi)) {

			} else {
				www[k] = this.bazhis[i];
				k++;
			}
		}
		this.bazhis = www;
		this.bazhishu = w;
		this.bazhisID = new int[w];
		for (int i = 0; i < w; i++) {
			bazhisID[i] = this.bazhis[i].getBianhao();
		}
	}

}
