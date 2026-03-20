package dabaxunlian;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TMUIControlPanel extends JPanel {
	public JPanel panel_1 = new JPanel();
	private JPanel panel_7;
	private JScrollPane scrollPane;
	DefineJpanel defineJpanel;
	private JTable table;
	private JTable table_1;
	private Tubiao[] tubiaos;
	private Bazhi[] bazhis;
	private Dabarenyuan[] dabarenyuans;

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

		JButton btnNewButton_1 = new JButton("\u6DFB\u52A0\u5206\u7EC4");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Defineframe defineframe = new Defineframe();
				defineframe.setVisible(true);
				defineframe.getButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						defineframe.dispose();
						// System.out.println("guangbudiao");
						// dispose();
					}
				});
				defineframe.getBtnNewButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("确定");

						String[] mStrings = defineframe.getTextField().getText().split(",");
						System.out.println(mStrings.length);
						if (!mStrings[0].equals("")) {
							int j = mStrings.length;
							int[] a = new int[mStrings.length];
							for (int i = 0; i < mStrings.length; i++) {
								a[i] = Integer.parseInt(mStrings[i]);
							}
							Bazhi[] bazhiijk = new Bazhi[j];
							for (int i = 0; i < j; i++) {
								bazhiijk[i] = Daba1.bazhis[a[i] - 1];
							}
							Guanlifenzu.creatfenzu(bazhiijk);
							//Guanlifenzu.creatnewpanel();
							Guanlifenzu.showpanel();
						} else {
							Bazhi[] bazhikji = new Bazhi[0];
							Guanlifenzu.creatfenzu(bazhikji);
							//Guanlifenzu.creatnewpanel();
							Guanlifenzu.showpanel();
						}
						defineframe.dispose();
					}
				});
			}
		});
		panel_1.add(btnNewButton_1, BorderLayout.SOUTH);
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
