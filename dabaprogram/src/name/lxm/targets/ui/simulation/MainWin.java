package name.lxm.targets.ui.simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import name.lxm.targets.ui.UIEventListener;

public class MainWin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private UIEventListener listener = null;
	
	public MainWin(String title)
	{
		super(title);
		setPreferredSize(new Dimension(600, 400));
		mainPanel  = new JPanel();
		JButton btnStand = new JButton("Stand");
		btnStand.addActionListener(this);
		JButton btnSit = new JButton("Sit");
		btnSit.addActionListener(this);
		JButton btnLightOn = new JButton("Light");
		btnLightOn.addActionListener(this);
		JButton btnInit = new JButton("Init");
		btnInit.addActionListener(this);
		JButton btnAssignID = new JButton("id");
		btnAssignID.addActionListener(this);
		JButton btnStatus = new JButton("Status");
		btnStatus.addActionListener(this);
		
		mainPanel.add(btnStand);
		mainPanel.add(btnSit);
		mainPanel.add(btnLightOn);
		mainPanel.add(btnInit);
		mainPanel.add(btnAssignID);
		mainPanel.add(btnStatus);
		this.setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel, BorderLayout.NORTH);
		
		JTextField msgbox = new JTextField();
		msgbox.setEditable(false);
		this.getContentPane().add(msgbox, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public void updateData()
	{
		
	}
	
	
	public void setUIEventListener(UIEventListener listener)
	{
		this.listener= listener;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String cmd = ae.getActionCommand();
		if("Stand".equals(cmd)){
			listener.processStandEvent(null);
		}
		if("Sit".equals(cmd)){
			listener.processSitEvent(null);
		}
		if("Light".equals(cmd)){
			listener.processLightEvent(null);
		}
		if("Init".equals(cmd)){
			listener.processInitEvent(null);
		}
		if("id".equals(cmd)){
			listener.processIDEvent(null);
		}
		if("Status".equals(cmd)){
			listener.processStatusEvent(null);
		}
	}
}
