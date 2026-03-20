package dabaxunlian;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;

public class TMUITargetStatusPanel extends JPanel {
	private JScrollPane scrollPane;
	private JList list;
	private JScrollPane scrollPane_1;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public TMUITargetStatusPanel() {
		setBorder(BorderFactory.createLineBorder(Color.white));// 设置面板边框颜色
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(0, 150));
		JPanel jPanel=new JPanel();
		jPanel.setLayout(new GridLayout(0, 2, 0, 0));
		add(jPanel, BorderLayout.CENTER);
		
		
		JPanel panel = new JPanel();
		jPanel.add(panel);
		
		JPanel panel_1 = new JPanel();
		jPanel.add(panel_1);
		panel.setBorder(BorderFactory.createTitledBorder("系统消息"));
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
		panel.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList();
		list.setFont(new Font("宋体", Font.PLAIN, 20));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"8\uFF1A00", "9\uFF1A00", "10\uFF1A00", "11\uFF1A00", "12\uFF1A00", "8\uFF1A00", "9\uFF1A00", "10\uFF1A00", "11\uFF1A00", "12\uFF1A00"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
//		list.setCellRenderer(new DefaultListCellRenderer() {
//            public void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.setColor(Color.black);
//                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
//            }
//        });
		scrollPane.setViewportView(list);
		MatteBorder border=new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY);
		panel_1.setBorder(BorderFactory.createTitledBorder("成绩"));
		panel_1.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(111);
		table.getColumnModel().getColumn(1).setPreferredWidth(162);
		scrollPane_1.setViewportView(table);
		
		
	}

}
