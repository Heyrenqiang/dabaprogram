package dabaxunlian;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	private static Daba1 mainform;

	public static Daba1 getMainform() {
		return mainform;
	}

	public static void setMainform(Daba1 mainform) {
		Main.mainform = mainform;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		mainform =new Daba1("打靶控制界面");
		Datatest datatest=new Datatest();
		Commandsending commandsending=new Commandsending();
		mainform.addlistener(commandsending);
		datatest.setActionListener(mainform);
	}

}
