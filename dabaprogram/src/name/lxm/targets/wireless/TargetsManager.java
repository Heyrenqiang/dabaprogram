package name.lxm.targets.wireless;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import name.lxm.targets.ConfDoc;
import name.lxm.targets.Utility;
import name.lxm.targets.exception.SetNodeIDException;
import name.lxm.targets.exception.WrongFrameFormatException;
import name.lxm.targets.model.TargetEntity;
import name.lxm.targets.model.TargetsCollection;
import name.lxm.targets.wireless.data.CommandFrame;
import name.lxm.targets.wireless.data.IDConfigResponseFrame;
import name.lxm.targets.wireless.data.InitResponseFrame;
import name.lxm.targets.wireless.data.ResponseFrame;
import name.lxm.targets.wireless.data.RouterDataPackage;
import name.lxm.targets.wireless.data.TargetStatusFrame;

/**
 * <p>靶网管理器，用于初始化/获取靶网的数据。</p>
 * 
 * <p>This is a task level interface.</p>
 * 
 * 
 * @author Xiaoming Li Mar 5, 2017
 *
 */
public class TargetsManager {
	
	private String routerIP;
	private int routerPort;
	private InputStream inStream;
	private OutputStream outStream;
	private TMWireless wireless;
	private Socket socket;
	private ReadThread readThread;
	private ITargetsEventListener telistener = null;
	
	/**
	 * 初始化函数
	 */
	public TargetsManager()
	{
		routerIP = ConfDoc.INSTANCE.getRouterAddress();
		routerPort = ConfDoc.INSTANCE.getRouterPort();
	}
	
	/**
	 * 联接到路由器，执行任何其他网络操作前首先调用该函数。
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void connect2Router() throws UnknownHostException, IOException
	{
		socket = new Socket(routerIP, routerPort);
		socket.setSoTimeout(5000);
		inStream = socket.getInputStream();
		outStream = socket.getOutputStream();
		wireless = new TMWireless(inStream, outStream);
		readThread = new ReadThread();
		readThread.start();
	}
	
	/**
	 * 从路由器上断开。断开后大部分的靶网控制功能将无法使用，必须要重新连接才行。
	 * 
	 * @see connect2Router
	 * @throws IOException
	 */
	public void disconnect() throws IOException
	{
		inStream.close();
		outStream.close();
		socket.close();
		inStream = null;
		outStream = null;
		readThread.exit();
	}

	/**
	 * 路由的IP和端口将从配置文件中获取，该函数一般不用。
	 * @param routerIP
	 */
	public void setRouterIP(String routerIP) {
		this.routerIP = routerIP;
	}

	/**
	 * 路由的IP和端口将从配置文件中获取，该函数一般不用。
	 * @param routerPort
	 */
	public void setRouterPort(int routerPort) {
		this.routerPort = routerPort;
	}
	
	/**
	 * 初始化整个物联网络：首先向该网络发送初始化命令，然后
	 * 等待物联网络发回各自的基础信息。之后向每个物联网节点
	 * 发送注册后的ID，并确认发回数据。
	 * 
	 * @throws IOException 
	 * @throws WrongFrameFormatException 
	 * @throws SetNodeIDException 
	 */
	public void initResetNetwork() throws IOException, WrongFrameFormatException, SetNodeIDException
	{
		//发送初始化命令
		wireless.sendInitResetCommand();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assignNodeID();
		
		//发送注册的节点编号
		for(TargetEntity te : TargetsCollection.INSTANCE.getTargetsCollection()){
			wireless.sendConfigCommand(te);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 为每个节点设置NetID，目前的方案是直接为每个节点随机赋予一个ID，
	 * 今后可以考虑按照一定的策略进行分配。
	 */
	private void assignNodeID() {
		//TODO 设计新的ID分配方案
		return;
	}

	/**
	 * Update the data from the wireless network
	 */
	public void updateData() {
		//1 send command to the wireless
		try {
			wireless.sendStatusCommand();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void standTarget(TargetEntity te) {
		try {
			wireless.sendSingleOPCommand(te, CommandFrame.CMDCODE_TARGET_UP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sitTarget(TargetEntity te) {
		try {
			wireless.sendSingleOPCommand(te, CommandFrame.CMDCODE_TARGET_DOWN);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void lightOnOff(TargetEntity te) {
		try {
			wireless.sendSingleOPCommand(te, CommandFrame.CMDCODE_LIGHT_ON);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Thread that used to read from socket.
	 * 
	 * @author Xiaoming Mar 24, 2017
	 *
	 */
	private class ReadThread extends Thread
	{
		
		private boolean bRun = true;

		public ReadThread()
		{
		}

		@Override
		public void run() {
			while(bRun){
				try {
					if(wireless.hasIncomingData()){
						RouterDataPackage rdp = wireless.readRouterPackage();
						handleResponseMessage(rdp);
					}else{
						Thread.sleep(1000);
					}
				} catch (WrongFrameFormatException | IOException | InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		public void exit()
		{
			bRun = false;
		}
		
	}
	
	/**
	 * Method used to handle the incoming packages from the router.
	 * 
	 * @param rdp router data package
	 * @throws WrongFrameFormatException
	 */
	private void handleResponseMessage(RouterDataPackage rdp) throws WrongFrameFormatException
	{
		ResponseFrame[] fa = rdp.getResponseFrames();
		if(fa != null){
			for(ResponseFrame fi : fa){
				//if it is a init response message
				if(fi instanceof InitResponseFrame){
					InitResponseFrame irf = (InitResponseFrame) fi;
					TargetEntity te = new TargetEntity();
					te.setCode(0);
					te.setGps(Utility.decodeGPS(irf.getGPSData()));
					te.setGrade(irf.getGradeData());
					te.setGroup(0);
					te.setID(Utility.generateTargetID());
					te.setLastUpdate(System.currentTimeMillis());
					te.setPresure(irf.getPressure());
					te.setSN(irf.getNodeMacAddress());
					te.setStatus(irf.getTargetState());
					te.setTemprature(irf.getTemprature());
					te.setTimestamp(irf.getTimeStamp());
					te.setVoltage(irf.getBatteryInfo());
					te.setInitialized(false);
					//save the node information
					TargetsCollection.INSTANCE.put(te);
				}
				//if it ID configuration response message
				if(fi instanceof IDConfigResponseFrame){
					IDConfigResponseFrame irf = (IDConfigResponseFrame) fi;
					TargetEntity te = TargetsCollection.INSTANCE.get(irf.getLocalID());
					if(te != null){
						if(Arrays.equals(te.getSN(), irf.getNodeMacAddress())){
							//set the node state to initialized.
							te.setInitialized(true);
							te.setLastUpdate(System.currentTimeMillis());
						}
					}
				}
				//if it is the status response message
				if(fi instanceof TargetStatusFrame){
					TargetStatusFrame tsf = (TargetStatusFrame) fi;
					TargetEntity te = TargetsCollection.INSTANCE.get(tsf.getLocalID());
					//update the node information.
					if(te != null){
						te.setGrade(tsf.getGradeData());
						te.setPresure(tsf.getPressure());
						te.setStatus(tsf.getTargetStatus());
						te.setVoltage(tsf.getBatteryStatus());
						te.setTemprature(tsf.getTemprature());
						te.setTimestamp(tsf.getTimeStamp());
						te.setLastUpdate(System.currentTimeMillis());
					}
				}
			}
			if(telistener != null) telistener.updateData();
			
		}
	}

	public void setNodeID(TargetEntity te) {
		try {
			wireless.sendConfigCommand(te);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getNodeStatus(TargetEntity te){
		try {
			wireless.sendStatusCommand();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param l
	 */
	public void setTargetUpdateListener(ITargetsEventListener l)
	{
		telistener = l;
	}
}
