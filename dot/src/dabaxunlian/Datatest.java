package dabaxunlian;

public class Datatest {
	public Datatest(){
	}
	private Tubiao[] tubiaos;
	private Datainterface datainterface;
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
					tubiaos=new Tubiao[1];
					datainterface.dataUpdate(tubiaos);
				}
			}
		}).start();
	}
	public void setActionListener(Datainterface datainterface){
		this.datainterface=datainterface;
	}
}
