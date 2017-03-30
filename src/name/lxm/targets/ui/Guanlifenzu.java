package name.lxm.targets.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Guanlifenzu {
	private static int zushu = 0;
	private static int[] groupedbazhis = new int[1000];
	private static int groupedbazhinum;
	private static DefineJpanel[] defineJpanels = new DefineJpanel[1000];
	private static Bazhiz[] bazhizs = new Bazhiz[100];

	public static int[] getGroupedbazhis() {
		return groupedbazhis;
	}

	public static void setGroupedbazhis(int[] groupedbazhis) {
		Guanlifenzu.groupedbazhis = groupedbazhis;
	}

	public static int getGroupedbazhinum() {
		return groupedbazhinum;
	}

	public static void setGroupedbazhinum(int groupedbazhinum) {
		Guanlifenzu.groupedbazhinum = groupedbazhinum;
	}

	public static Bazhiz[] getBazhizs() {
		return bazhizs;
	}

	public static void setBazhizs(Bazhiz[] bazhizs) {
		Guanlifenzu.bazhizs = bazhizs;
	}

	public static void creatnewpanel() {

		defineJpanels[zushu] = new DefineJpanel(zushu, bazhizs[zushu]);
		defineJpanels[zushu].getButton_1()
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						int i;
						for (i = 0; i < zushu; i++) {
							if (defineJpanels[i + 1].getButton_1() == e
									.getSource()) {
								break;
							}
						}
						//System.out.println(i);
						Guanlifenzu.deletefenzhu(i);
						Guanlifenzu.deletepanel(i);
						Guanlifenzu.showpanel();
					}
				});
	}

	public static void updatepanel(int a) {
		defineJpanels[a] = new DefineJpanel(a, bazhizs[a]);
		defineJpanels[a].getButton_1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int i;
				for (i = 0; i < zushu; i++) {
					if (defineJpanels[i + 1].getButton_1() == e.getSource()) {
						break;
					}
				}
				System.out.println(i);
				Guanlifenzu.deletefenzhu(i);
				Guanlifenzu.deletepanel(i);
				// Guanlifenzu.deletepanel(Integer.parseInt(e.getActionCommand()));
				Guanlifenzu.showpanel();
			}
		});

	}

	public static void creatfenzu(Bazhi[] bazhis) {
		Definedata definedata;
		definedata = Toolsfunction.splitGroupedornot(bazhis);
		int yesorno = 10;
		System.out.println(definedata.getExistedbazhi().length);
		if (definedata.getBazhis4().length > 0) {
			System.out.println(definedata.getExistedbazhi().length);
			String string = Toolsfunction.num2str(definedata.getExistedbazhi());
			yesorno = JOptionPane.showConfirmDialog(null,
					string + "已经被分组,是否继续？", "", JOptionPane.YES_NO_OPTION);
		}
		// System.out.println(yesorno);
		if (yesorno == 0 || definedata.getBazhis4().length == 0) {
			// 点击messagebox的确定或者没有选择到重复的靶子时
			// System.out.println("chuasdhua");
			bazhizs[zushu + 1] = new Bazhiz(zushu + 1, definedata.getBazhis3());
			for (int i = 0; i < definedata.getBazhis3().length; i++) {
				definedata.getBazhis3()[i].setGroup(zushu + 1);
			}
			zushu++;
			creatnewpanel();
		} else {

		}
	}

	public static void tuichuFenzhu(Bazhi[] bazhis) {
		int yesorno = 10;
		Definedata definedata;
		definedata = Toolsfunction.splitGroupedornot(bazhis);
		if (definedata.getBazhis3().length > 0) {
			String string = Toolsfunction
					.num2str(definedata.getNotexistedbazhi());
			yesorno = JOptionPane.showConfirmDialog(null,
					string + "尚未被分组,是否继续？", "", JOptionPane.YES_NO_OPTION);
		}
		if (yesorno == 0 || definedata.getBazhis3().length == 0) {
			int []z=new int[definedata.getBazhis4().length];
			for(int i=0;i<z.length;i++){
				z[i]=definedata.getBazhis4()[i].getBianhao();
			}
			deledebazhihao(z);
			for (int i = 0; i < definedata.getBazhis4().length; i++) {
				bazhizs[definedata.getBazhis4()[i].getGroup()]
						.deleteBazhis(definedata.getBazhis4()[i]);
				System.out.println("test");
				System.out.println(definedata.getBazhis4().length);
				System.out.println(definedata.getBazhis4()[i].getGroup());
				updatepanel(definedata.getBazhis4()[i].getGroup());
				definedata.getBazhis4()[i].setGroup(0);
			}
		} else {

		}
	}

	public static void tianjiaDaozu(int a, Bazhi[] bazhis) {
		Definedata definedata;
		definedata = Toolsfunction.splitGroupedornot(bazhis);
		int yesorno = 10;
		System.out.println(definedata.getBazhis4().length);
		System.out.println(definedata.getExistedbazhi().length);
		if (definedata.getBazhis4().length > 0) {
			String string = Toolsfunction.num2str(definedata.getExistedbazhi());
			yesorno = JOptionPane.showConfirmDialog(null,
					string + "已经被分组,是否继续？", "", JOptionPane.YES_NO_OPTION);
		}
		if (yesorno == 0 || definedata.getBazhis4().length == 0) {
			bazhizs[a].addBazhis(definedata.getBazhis3());
			for (int i = 0; i < definedata.getBazhis3().length; i++) {
				definedata.getBazhis3()[i].setGroup(a);
			}
			updatepanel(a);
		} else {

		}
	}

	public static void deletefenzhu(int a) {
		/// 注意：bazhizs[]是从bazhizs[1]开始的
		int[] z = new int[bazhizs[a + 1].getBazhis().length];
		for (int i = 0; i < z.length; i++) {
			z[i] = bazhizs[a + 1].getBazhis()[i].getBianhao();
		}
		deledebazhihao(z);
		for (int i = a+1; i < zushu; i++) {
			bazhizs[i] = bazhizs[i + 1];
		}
		bazhizs[zushu] = null;
		zushu--;
	}

	public static void deledebazhihao(int[] a) {
		boolean existed = false;
		int w = 0;
		for (int k = 0; k < groupedbazhinum; k++) {
			for (int j = 0; j < a.length; j++) {
				if (groupedbazhis[k] == a[j]) {
					existed = true;
					break;
				} else {

				}
			}
			if (existed) {
				existed = false;
			} else {
				groupedbazhis[w] = groupedbazhis[k];
				w++;
			}
		}
		groupedbazhinum = w;
	}

	public static int getZushu() {
		return zushu;
	}

	public static void setZushu(int zushu) {
		Guanlifenzu.zushu = zushu;
	}

	public static String num2str(int[] a, int b) {
		String str = "";
		for (int i = 0; i < b; i++) {
			str = str + String.valueOf(a[i]) + ",";
		}
		return str.substring(0, str.length() - 1);
	}

	public static void showpanel() {
		Mainframe.tmuiControlPanel.getPanel_7().removeAll();
		Mainframe.tmuiControlPanel.getPanel_7().repaint();
		for (int i = 0; i < zushu; i++) {
			Mainframe.tmuiControlPanel.getPanel_7()
					.setPreferredSize(new Dimension(0, (zushu) * 63 + 5));
			Mainframe.tmuiControlPanel.getPanel_7().add(defineJpanels[i + 1]);
			Mainframe.tmuiControlPanel.getScrollPane()
					.setViewportView(Mainframe.tmuiControlPanel.getPanel_7());
		}
		Mainframe.tmuiControlPanel.getPanel_7().repaint();

	}

	public static void deletepanel(int a) {
		for (int i = a+1; i < zushu + 1; i++) {
			defineJpanels[i] = defineJpanels[i + 1];
			defineJpanels[i].getLabel().setText("组" + String.valueOf(i) + ":");
			defineJpanels[i].repaint();
		}
		defineJpanels[zushu + 1] = null;
	}

	// @Override
	public static void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		int i;
		for (i = 0; i < zushu; i++) {
			if (defineJpanels[i + 1].getButton() == e.getSource()) {
				if(Mainframe.yunxinstate){
				for (int j = 0; j < bazhizs[i + 1].getBazhis().length; j++) {
					if (bazhizs[i + 1].getBazhis()[j].getSuodingyf() != 0) {
						bazhizs[i + 1].getBazhis()[j].setIsdongzuo(true);
						bazhizs[i + 1].getBazhis()[j].setIsweidazhong(false);
						bazhizs[i + 1].getBazhis()[j].setIsxuanzhong(false);
						bazhizs[i + 1].getBazhis()[j].setIsdazhong(false);
						bazhizs[i + 1].getBazhis()[j].setSuodingyf(0);
					}
				}
				Mainframe.huitupanel.displayzhengti(Mainframe.tubiaos, Mainframe.pointnum);
				}
				break;
			}
		}
	}
}
