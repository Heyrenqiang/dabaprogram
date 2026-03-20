package dabaxunlian;

public class Bazhi extends Tubiao{
	private int bianhao;//编号
	private int seqbianhao;//靶序列编号
	private int group;//所属组
	private int temprature;//温度
	private int light;//灯光
	private String alert;//报警信息
	private String pose;//姿态
	private int hit;//成绩
	private String debug;//调试信息
	private int chengji;//成绩
	private int zuhao;//组号
	private String qiya;//气压
	private String dianliang;//电量
	private int suodingyf;
	private boolean isdongzuo;
	private boolean isxuanzhong;
	private boolean isweidazhong;
	private boolean isdazhong;
	
	public int getSeqbianhao() {
		return seqbianhao;
	}
	public void setSeqbianhao(int seqbianhao) {
		this.seqbianhao = seqbianhao;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public int getTemprature() {
		return temprature;
	}
	public void setTemprature(int temprature) {
		this.temprature = temprature;
	}
	public int getLight() {
		return light;
	}
	public void setLight(int light) {
		this.light = light;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getPose() {
		return pose;
	}
	public void setPose(String pose) {
		this.pose = pose;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getDebug() {
		return debug;
	}
	public void setDebug(String debug) {
		this.debug = debug;
	}
	public int getBianhao() {
		return bianhao;
	}
	public void setBianhao(int bianhao) {
		this.bianhao = bianhao;
	}
	public Bazhi(){
		
	}
	public Bazhi(int bianhao, int chengji, int zuhao, int suodingyf, boolean isdongzuo, boolean isxuanzhong,
			boolean isweidazhong, boolean isdazhong, String qiya, String dianliang) {
		super();
		this.bianhao = bianhao;
		this.chengji = chengji;
		this.zuhao = zuhao;
		this.suodingyf = suodingyf;
		this.isdongzuo = isdongzuo;
		this.isxuanzhong = isxuanzhong;
		this.isweidazhong = isweidazhong;
		this.isdazhong = isdazhong;
		this.qiya = qiya;
		this.dianliang = dianliang;
	}
	
	public int getChengji() {
		return chengji;
	}
	public void setChengji(int chengji) {
		this.chengji = chengji;
	}
	public int getZuhao() {
		return zuhao;
	}
	public void setZuhao(int zuhao) {
		this.zuhao = zuhao;
	}
	public int getSuodingyf() {
		return suodingyf;
	}
	public void setSuodingyf(int suodingyf) {
		this.suodingyf = suodingyf;
	}
	public boolean isIsdongzuo() {
		return isdongzuo;
	}
	public void setIsdongzuo(boolean isdongzuo) {
		this.isdongzuo = isdongzuo;
	}
	public boolean isIsxuanzhong() {
		return isxuanzhong;
	}
	public void setIsxuanzhong(boolean isxuanzhong) {
		this.isxuanzhong = isxuanzhong;
	}
	public boolean isIsweidazhong() {
		return isweidazhong;
	}
	public void setIsweidazhong(boolean isweidazhong) {
		this.isweidazhong = isweidazhong;
	}
	public boolean isIsdazhong() {
		return isdazhong;
	}
	public void setIsdazhong(boolean isdazhong) {
		this.isdazhong = isdazhong;
	}
	public String getQiya() {
		return qiya;
	}
	public void setQiya(String qiya) {
		this.qiya = qiya;
	}
	public String getDianliang() {
		return dianliang;
	}
	public void setDianliang(String dianliang) {
		this.dianliang = dianliang;
	}
}
