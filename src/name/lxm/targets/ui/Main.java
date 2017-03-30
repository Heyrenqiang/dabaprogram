package name.lxm.targets.ui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	private static Mainframe mainform;

	public static Mainframe getMainform() {
		return mainform;
	}

	public static void setMainform(Mainframe mainform) {
		Main.mainform = mainform;
	}

	public static void main(String[] args) {
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		mainform =new Mainframe("智能显示模块");
	}
}
