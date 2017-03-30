package name.lxm.targets.ui;

public class Bazhi extends Tubiao{
	private int bianhao;//编号
	private int seqbianhao;//靶序列编号
	private int group;//所属组
	private double temprature;//温度
	private int light;//灯光
	private String alert;//报警信息
	private byte pose;//姿态
	private int hit;//成绩
	private String debug;//调试信息
	private byte[] chengji;//成绩
	private int zuhao;//组号
	private double qiya;//气压
	private double dianliang;//电量
	private int suodingyf;
	private boolean isdongzuo;
	private boolean isxuanzhong;
	private boolean isweidazhong;
	private boolean isdazhong;
	private boolean isdongzuoenabled;
	
	public boolean isIsdongzuoenabled() {
		return isdongzuoenabled;
	}
	public void setIsdongzuoenabled(boolean isdongzuoenabled) {
		this.isdongzuoenabled = isdongzuoenabled;
	}
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
	public double getTemprature() {
		return temprature;
	}
	public void setTemprature(double d) {
		this.temprature = d;
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
	public byte getPose() {
		return pose;
	}
	public void setPose(byte b) {
		this.pose = b;
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
	public Bazhi(int bianhao, byte[] chengji, int zuhao, int suodingyf, boolean isdongzuo, boolean isxuanzhong,
			boolean isweidazhong, boolean isdazhong, double qiya, double dianliang) {
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
	
	public Bazhi(int i, int j, int k, int l, boolean b, boolean c, boolean d, boolean e, String string,
			String string2) {
		// TODO 自动生成的构造函数存根
		
	}
	public byte[] getChengji() {
		return chengji;
	}
	public void setChengji(byte[] bs) {
		this.chengji = bs;
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
	public double getQiya() {
		return qiya;
	}
	public void setQiya(double d) {
		this.qiya = d;
	}
	public double getDianliang() {
		return dianliang;
	}
	public void setDianliang(double d) {
		this.dianliang = d;
	}
	public void setstateDazhong(){
		this.isdazhong=true;
		this.isweidazhong=false;
		this.isxuanzhong=false;
		this.isdongzuo=false;
	}
	public void setstateWeidazhong(){
		this.isdazhong=false;
		this.isweidazhong=true;
		this.isxuanzhong=false;
		this.isdongzuo=false;
	}
	public void setstateXuanzhong(){
		this.isdazhong=false;
		this.isweidazhong=false;
		this.isxuanzhong=true;
		this.isdongzuo=false;
	}
	public void setstateDongzuo(){
		this.isdazhong=false;
		this.isweidazhong=false;
		this.isxuanzhong=false;
		this.isdongzuo=true;
	}
	public void setstateDefault(){
		this.isdazhong=false;
		this.isweidazhong=false;
		this.isxuanzhong=false;
		this.isdongzuo=false;
	}
}
