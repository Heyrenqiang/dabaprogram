package dabaxunlian;

public class Commandsending implements CommandlistenerInterface{

	@Override
	public void groupinfoUpdate(Bazhiz[] bazhizs) {
		// TODO 自动生成的方法存根
		System.out.println("分组信息更新");
	}

	@Override
	public void systemInitialize() {
		// TODO 自动生成的方法存根
		System.out.println("初始化");
	}

	@Override
	public void systemStart() {
		// TODO 自动生成的方法存根
		System.out.println("系统启动");
	}

	@Override
	public void syatemStop() {
		// TODO 自动生成的方法存根
		System.out.println("系统停止");
	}

	@Override
	public void bazhiAction(Bazhi bazhi) {
		// TODO 自动生成的方法存根
		System.out.println("一个靶子动作");
	}

	@Override
	public void bazhisAction(Bazhi[] bazhis) {
		// TODO 自动生成的方法存根
		System.out.println("多个靶子动作");
	}
}
