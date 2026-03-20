package dabaxunlian;

public interface CommandlistenerInterface {
	public void groupinfoUpdate(Bazhiz[] bazhizs);//分组信息，参数为各组靶子组数组，每一个靶子组包含了组里个靶子信息
	public void systemInitialize();//系统初始化
	public void systemStart();//系统开始运行
	public void syatemStop();//系统停止运行
	public void bazhiAction(Bazhi bazhi);//靶子动作的命令,参数为一个靶子
	public void bazhisAction(Bazhi[] bazhis);//一组靶子动作的命令，参数为靶子组
}
