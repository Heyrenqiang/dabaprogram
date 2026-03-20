package name.lxm.targets.ui;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class Tianjiafenzumsgbox extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6591342574774172118L;
	private JTextField textField;
	private JButton btnNewButton = new JButton("\u786E\u5B9A");
	private JButton button = new JButton("\u53D6\u6D88");
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}
	public JButton getButton() {
		return button;
	}
	public void setButton(JButton button) {
		this.button = button;
	}
	public Tianjiafenzumsgbox(int a) {
		setSize(399, 141);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;
		setLocation(w, h);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, -122, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 4;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		getContentPane().add(panel_1, gbc_panel_1);
		
		String string="添加到靶子组"+String.valueOf(a);
		JLabel lblNewLabel = new JLabel(string);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u9009\u62E9\u9776\u5B50\uFF1A");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 3;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		getContentPane().add(panel_2, gbc_panel_2);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		
		
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("确定");
//			}
//		});
		panel.add(btnNewButton);
		
		
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//			}
//		});
		panel.add(button);
	}
	

}
