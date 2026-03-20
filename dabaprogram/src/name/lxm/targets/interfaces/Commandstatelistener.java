package name.lxm.targets.interfaces;

/**
 * @author Administrator
 * 用于发送系统状态，主界面调用接口函数，通讯模块实现接口函数
 *
 */
public interface Commandstatelistener {
	public void sysInitialize();//系统初始化
	public void sysReady();//就绪
	public void sysSleep();//睡眠
	public void sysStart();//系统开始运行
	public void sysStop();//系统停止运行
	public void sysReset();//系统复位
	

	/**
	 * 后面三个函数不用
	 */
//	public void bazhiAction(Bazhi bazhi);//靶子动作的命令,参数为一个靶子
//	public void bazhisAction(Bazhi[] bazhis);//一组靶子动作的命令，参数为靶子组
//	public void groupinfoUpdate(Bazhiz[] bazhizs);//分组信息，参数为各组靶子组数组，每一个靶子组包含了组里个靶子信息
}
