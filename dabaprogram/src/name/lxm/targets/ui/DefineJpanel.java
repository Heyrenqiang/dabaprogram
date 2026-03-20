package name.lxm.targets.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
//package 自定义组件;
public class DefineJpanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_2 = new JTextField();
	private JButton button = new JButton();
	private int zushu;
	private int pingbanh;
	private int[] bazhi=new int[100];
	private String strzushu;
	private String strpingbanh;
	private String strbazhi;
	private final JToolBar toolBar = new JToolBar();
	private final JButton btnNewButton = new JButton("");
	private final JButton button_1 = new JButton("");
	private JLabel label = new JLabel();
	private JTextField textField_1 = new JTextField();
	public int getZushu() {
		return zushu;
	}
	public void setZushu(int zushu) {
		this.zushu = zushu;
	}
	public int getPingbanh() {
		return pingbanh;
	}
	public void setPingbanh(int pingbanh) {
		this.pingbanh = pingbanh;
	}
	public int[] getBazhi() {
		return bazhi;
	}
	public void setBazhi(int[] bazhi) {
		this.bazhi = bazhi;
	}
	
	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	public JButton getButton_1() {
		return button_1;
	}
	private final JPanel panel = new JPanel();
	private int bazhinum;
	private int[] bazhis;
	private  void canshu2str(int zushu,int pingbanh,int[] bazhi,int bazhinum){
		String str="";
		strzushu=" 组"+String.valueOf(zushu)+":";
		strpingbanh="控制平板#"+String.valueOf(pingbanh);
		bazhis=new int[bazhinum];
		for(int i=0;i<bazhinum;i++){
			bazhis[i]=bazhi[i];
		}
		Arrays.sort(bazhis);
		for(int i=0;i<bazhinum;i++){
			//System.out.println(bazhis[i]);
		}
		for(int i=0;i<bazhinum;i++){
			str =str +String.valueOf(bazhis[i])+",";
		}
		//str.substring(0,str.length()-1);
		if(bazhinum >0){
		strbazhi="   靶#:"+str.substring(0,str.length()-1);
		}else{
			strbazhi="   靶#:";
		}
	}
	public int[] getBazhis() {
		return bazhis;
	}
	public void setBazhis(int[] bazhis) {
		this.bazhis = bazhis;
	}
	public int getBazhinum() {
		return bazhinum;
	}
	public void setBazhinum(int bazhinum) {
		this.bazhinum = bazhinum;
	}
	public DefineJpanel(int zushu,Bazhiz bazhiz) {	
		this.zushu=zushu;
		this.pingbanh=bazhiz.getGroupID();
		this.bazhi=bazhiz.getBazhisID();
		canshu2str(zushu,pingbanh,bazhi,bazhi.length);
		this.setPreferredSize(new Dimension(260, 58));
		this.setBorder(new LineBorder(Color.LIGHT_GRAY));
		//Container panel_1;
		//panel_1.add(this);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{42, 142, 0, 0, 20, 0};
		gbl_panel_7.rowHeights = new int[]{29, 29, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_panel_7);
		label.setText(strzushu);
		label.setOpaque(true);
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(5, 5, 0, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		this.add(label, gbc_label);
		
		//JTextField textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setEditable(false);
		textField_1.setText(strpingbanh);
		textField_1.setColumns(10);
		textField_1.setCaretPosition(0);
		textField_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.insets = new Insets(5, 0, 0, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 0;
		this.add(textField_1, gbc_textField_1);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 4;
		gbc_toolBar.gridy = 0;
		toolBar.setBorder(null);
		add(toolBar, gbc_toolBar);
		btnNewButton.setIcon(new ImageIcon(DefineJpanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/close-pressed.gif")));
		toolBar.add(btnNewButton);
		button_1.setIcon(new ImageIcon(DefineJpanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		
		toolBar.add(button_1);
		
		textField_2.setBackground(Color.WHITE);
		textField_2.setEditable(false);
		textField_2.setText(strbazhi);
		textField_2.setColumns(10);
		textField_2.setCaretPosition(0);
		textField_2.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(192, 192, 192)));
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.insets = new Insets(0, 5, 5, 0);
		gbc_textField_2.gridx = 0;
		gbc_textField_2.gridy = 1;
		this.add(textField_2, gbc_textField_2);
		button.setText("\u52A8\u4F5C");
		
		button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridwidth = 3;
		gbc_button.insets = new Insets(0, 5, 0, 0);
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.gridx = 2;
		gbc_button.gridy = 1;
		this.add(button, gbc_button);
		button.addActionListener(this);
		btnNewButton.addActionListener(this);
		button_1.addActionListener(this);
	}
	public String getStrzushu() {
		return strzushu;
	}
	public void setStrzushu(String strzushu) {
		this.strzushu = strzushu;
	}
	public String getStrpingbanh() {
		return strpingbanh;
	}
	public void setStrpingbanh(String strpingbanh) {
		this.strpingbanh = strpingbanh;
	}
	public String getStrbazhi() {
		return strbazhi;
	}
	public void setStrbazhi(String strbazhi) {
		this.strbazhi = strbazhi;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}
	public void setTextField_2(JTextField textField_2) {
		this.textField_2 = textField_2;
	}
	public JButton getButton() {
		return button;
	}
	public void setButton(JButton button) {
		this.button = button;
	}
	public void actionPerformed(ActionEvent e){
		Guanlifenzu.actionPerformed(e);
	}
}
