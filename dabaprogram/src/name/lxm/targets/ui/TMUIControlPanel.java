package name.lxm.targets.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class TMUIControlPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Timeoutthread getTimeoutthread() {
		return timeoutthread;
	}

	public void setTimeoutthread(Timeoutthread timeoutthread) {
		this.timeoutthread = timeoutthread;
	}

	public JPanel panel_1 = new JPanel();
	private JPanel panel_7;
	private JScrollPane scrollPane;
	DefineJpanel defineJpanel;
	private Timeoutthread timeoutthread;
	private JTable table;
	private JTable table_1;
	private JButton savegroupButton;

	public JButton getSavegroupButton() {
		return savegroupButton;
	}

	public void setSavegroupButton(JButton savegroupButton) {
		this.savegroupButton = savegroupButton;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JPanel getPanel_7() {
		return panel_7;
	}

	public void setPanel_7(JPanel panel_7) {
		this.panel_7 = panel_7;
	}

	/**
	 * Create the panel.
	 */
	public TMUIControlPanel() {
		setBorder(BorderFactory.createLineBorder(Color.white));// 设置面板边框颜色
		setLayout(new BorderLayout(0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new LineBorder(Color.WHITE));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		add(tabbedPane, BorderLayout.CENTER);

		// tabbedPane.setMargin(new Insets(top, left, bottom, right));

		tabbedPane.addTab("    \u5206  \u7EC4    ", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

		panel_7 = new JPanel();
		scrollPane.setViewportView(panel_7);

		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8, BorderLayout.SOUTH);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));

		JButton tianjiafenzuBtn = new JButton("添加分组");
		panel_8.add(tianjiafenzuBtn);

		savegroupButton = new JButton("保存分组");
		// 点击保存分组按钮后将会做一下操作，系统进入就绪状态，更新分组信息到通讯模块
		savegroupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Mainframe.chushihuastate) {
					if (Mainframe.bazhishu == Guanlifenzu.getGroupedbazhinum()) {
						Mainframe.setsysstate(Mainframe.jiuxustaten);
						Guanlifenzu.groupinfoUpdate();// 更新分组信息到通讯模块
						Mainframe.qidongbutton.setEnabled(true);
						timeoutthread = new Timeoutthread();
						timeoutthread.start();
						// Mainframe.commandlistenerInterface.systemReady();
						Mainframe.commandstatelistener.sysReady();
						savegroupButton.setEnabled(false);// 保存按钮不可用
					}
				}
			}
		});
		panel_8.add(savegroupButton);
		// 当添加分组按钮按下时作以下操作，选择要添加分组的靶子并添加分组
		tianjiafenzuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Mainframe.chushihuastate) {
					Tianjiafenzumsgbox tianjiafenzumsgbox = new Tianjiafenzumsgbox(Guanlifenzu.getZushu());
					tianjiafenzumsgbox.setVisible(true);
					tianjiafenzumsgbox.getButton().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							tianjiafenzumsgbox.dispose();
						}
					});
					tianjiafenzumsgbox.getBtnNewButton().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (Mainframe.chushihuastate) {
								String[] mStrings = tianjiafenzumsgbox.getTextField().getText().split(",");
								System.out.println(mStrings.length);
								if (!mStrings[0].equals("")) {
									int j = mStrings.length;
									int[] a = new int[mStrings.length];
									for (int i = 0; i < mStrings.length; i++) {
										a[i] = Integer.parseInt(mStrings[i]);
									}
									Bazhi[] bazhiijk = new Bazhi[j];
									for (int i = 0; i < j; i++) {
										bazhiijk[i] = Mainframe.bazhis[a[i] - 1];
									}
									Guanlifenzu.creatfenzu(bazhiijk);
									// Guanlifenzu.creatnewpanel();
									Guanlifenzu.showpanel();
								} else {
									Bazhi[] bazhikji = new Bazhi[0];
									Guanlifenzu.creatfenzu(bazhikji);
									// Guanlifenzu.creatnewpanel();
									Guanlifenzu.showpanel();
								}
								tianjiafenzumsgbox.dispose();
							}
						}
					});
				}
			}
		});
		// pa

		JPanel panel = new JPanel();
		tabbedPane.addTab("    \u4EBA  \u5458    ", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		panel.add(scrollPane_2, BorderLayout.CENTER);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, },
				new String[] { "New column", "New column" }));
		scrollPane_2.setViewportView(table_1);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("    \u9776  \u5B50    ", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, },
				new String[] { "New column", "New column", "New column" }));
		scrollPane_1.setViewportView(table);

		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.WEST);

		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.NORTH);

		JPanel panel_5 = new JPanel();
		add(panel_5, BorderLayout.SOUTH);

		JPanel panel_6 = new JPanel();
		add(panel_6, BorderLayout.EAST);

	}
}

class Timeoutthread extends Thread {
	public Timeoutthread() {
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			System.out.println("进入就绪状态请启动");
			try {
				Thread.sleep(300000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				System.out.println("退出就绪状态进入运行状态");
				break;
			}
			Mainframe.setsysstate(Mainframe.shuimianstaten);
			Mainframe.qidongbutton.setText("激活");
			System.out.println("系统进入睡眠状态，请激活");
			// Mainframe.commandlistenerInterface.systemSleep();
			Mainframe.commandstatelistener.sysSleep();
			break;
		}
	}
}
