package name.lxm.targets.ui;

import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Dimension;

public class Tianjiadaozumsgbox extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6179630837536887551L;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public void setBtnNewButton_1(JButton btnNewButton_1) {
		this.btnNewButton_1 = btnNewButton_1;
	}

	private JButton btnNewButton_1;
	private JComboBox<Object> comboBox;
	private JPanel panel_2;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	
	public JComboBox<Object> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<Object> comboBox) {
		this.comboBox = comboBox;
	}

	public Tianjiadaozumsgbox(int a,String string) {
		setSize(360, 204);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;
		setLocation(w, h);
		getContentPane().setLayout(new GridLayout(3, 0, 0, 0));
		
		panel_2 = new JPanel();
		panel_2.setSize(new Dimension(54, 0));
		panel_2.setPreferredSize(new Dimension(100, 10));
		getContentPane().add(panel_2);
		
		lblNewLabel_1 = new JLabel("\u4F60\u9009\u4E2D\u7684\u9776\u5B50\uFF1A");
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setText(string);
		textField.setPreferredSize(new Dimension(300, 21));
		panel_2.add(textField);
		
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		
		lblNewLabel = new JLabel("\u8BF7\u9009\u62E9\u8981\u6DFB\u52A0\u5230\u54EA\u4E2A\u7EC4\uFF1A");
		panel_1.add(lblNewLabel);
		
		comboBox = new JComboBox<Object>();
//		comboBox.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		for(int i=0;i<a;i++){
			comboBox.addItem(i+1);
		}
		panel_1.add(comboBox);
		//textField.setColumns(10);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(50);
		getContentPane().add(panel);
		
		btnNewButton = new JButton("\u786E\u5B9A");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("\u53D6\u6D88");
//		btnNewButton_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		panel.add(btnNewButton_1);
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

}
