package name.lxm.targets.ui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class TMUITargetStatusPanel extends JPanel {
	private JScrollPane scrollPane;
	private JList<String> list;
	private JScrollPane scrollPane_1;
	private JTable table;
	public ArrayList<String> arraylist=new ArrayList<String>();
	private Statusmodel statusmodel;
	public ArrayList<String> getArraylist() {
		return arraylist;
	}
	public void setArraylist(ArrayList<String> arraylist) {
		this.arraylist = arraylist;
	}
	public Statusmodel getStatusmodel() {
		return statusmodel;
	}
	public void setStatusmodel(Statusmodel statusmodel) {
		this.statusmodel = statusmodel;
	}
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
		
		list = new JList<String>();
		list.setEnabled(false);
		list.setFont(new Font("宋体", Font.PLAIN, 18));
		scrollPane.setViewportView(list);
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
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(111);
		table.getColumnModel().getColumn(1).setPreferredWidth(162);
		scrollPane_1.setViewportView(table);
	}
	public JList<String> getList() {
		return list;
	}
	public void setList(JList<String> list) {
		this.list = list;
	}
	public void statusChanged(String string){
		this.arraylist.add(string);
		statusmodel=new Statusmodel();
		this.list.setModel(statusmodel);
	}
	public void dataChanged(){
		
	}
	class Statusmodel extends AbstractListModel<String>{
		@Override  
        public int getSize() {// 获取数组大小  
            return arraylist.size();  
        }     
        @Override  
        public String getElementAt(int index) {// 获取指定索引元素  
            return arraylist.get(index);  
        }
	}
}
