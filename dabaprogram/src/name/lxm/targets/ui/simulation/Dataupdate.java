package name.lxm.targets.ui.simulation;

import name.lxm.targets.interfaces.WirelessListener;

public class Dataupdate {
	public Dataupdate(){
	}
	private WirelessListener wirelessListener;
	public void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					wirelessListener.onDataReceived();
				}
			}
		}).start();
	}

	public void addActionListener(WirelessListener listener){
		this.wirelessListener=listener;
	}
}
