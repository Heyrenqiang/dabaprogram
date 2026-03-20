package name.lxm.targets.ui.simulation;

import name.lxm.targets.interfaces.Commandstatelistener;
import name.lxm.targets.interfaces.Groupinfoupdatelistener;
import name.lxm.targets.interfaces.UIEventListener;
import name.lxm.targets.model.TargetEntity;

public class Communicationmoduletest implements Commandstatelistener,Groupinfoupdatelistener,UIEventListener{


	@Override
	public void groupinfoUpdate() {
		// TODO 自动生成的方法存根
		System.out.println("分组信息已经更新");
	}

	@Override
	public void sysInitialize() {
		// TODO 自动生成的方法存根
		System.out.println("系统初始化");
	}

	@Override
	public void sysReady() {
		// TODO 自动生成的方法存根
		System.out.println("系统已就绪");
	}

	@Override
	public void sysSleep() {
		// TODO 自动生成的方法存根
		System.out.println("系统进入睡眠");
	}

	@Override
	public void sysStart() {
		// TODO 自动生成的方法存根
		System.out.println("系统开始运行");
	}

	@Override
	public void sysStop() {
		// TODO 自动生成的方法存根
		System.out.println("系统停止");
	}

	@Override
	public void sysReset() {
		// TODO 自动生成的方法存根
		System.out.println("系统复位");
	}

	@Override
	public void processStandEvent(TargetEntity te) {
		// TODO 自动生成的方法存根
		System.out.println("靶子起立");
	}

	@Override
	public void processSitEvent(TargetEntity te) {
		// TODO 自动生成的方法存根
		System.out.println("靶子倒下");
	}

	@Override
	public void processLightEvent(TargetEntity te) {
		// TODO 自动生成的方法存根
		System.out.println("靶子点灯");
	}

	@Override
	public void processInitEvent(TargetEntity te) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void processIDEvent(TargetEntity te) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void processStatusEvent(TargetEntity te) {
		// TODO 自动生成的方法存根
		
	}

}
