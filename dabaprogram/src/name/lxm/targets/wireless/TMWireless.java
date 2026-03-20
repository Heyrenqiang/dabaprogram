/*
 * 
 */
package name.lxm.targets.wireless;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import name.lxm.targets.exception.WrongFrameFormatException;
import name.lxm.targets.model.TargetEntity;
import name.lxm.targets.wireless.data.CommandFrame;
import name.lxm.targets.wireless.data.ResponseFrame;
import name.lxm.targets.wireless.data.RouterDataPackage;
import name.lxm.targets.wireless.data.ZigbeeDataPackage;

/**
 * <p>This is a super top level interface to the zigbee network. 
 * The class provides the synchronous methods to write to and read from the network,
 * you need to pay attention to the delay and blocking of the thread. The timeout setting 
 * by the socket can be used to make the communication throw a SocketTimeOutException. It is
 * useful for reading from the networking not fearing the program will be blocked for ever.</p>
 * 
 * <p>Another job of this class is used to generate required protocol data. </p>
 * 
 * @author Xiaoming Li Mar 19, 2017
 *
 */
public class TMWireless {
	
	private InputStream in;
	private OutputStream out;
	Logger loger = null;
	
	/**
	 * Default constructor. 
	 * 
	 * @param inStream
	 * @param outStream
	 */
	public TMWireless(InputStream inStream, OutputStream outStream)
	{
		in = inStream;
		out = outStream;
		loger = Logger.getLogger(this.getClass().getName());
		loger.setLevel(Level.ALL);
	}
	
	/**
	 * 发送网络初始化命令，该命令将群发至各个节点，节点返回初始化后的数据。
	 * 
	 * @throws IOException
	 */
	public synchronized void sendInitResetCommand() throws IOException
	{
		//Create the data frame of initializing the nodes
		CommandFrame cf = new CommandFrame(CommandFrame.CMDCODE_RESET);
		//set the timestamp
		cf.setTimeStamp();
		//create the encapsulated package
		ZigbeeDataPackage zdp = new ZigbeeDataPackage(cf);
		zdp.setDestNetID(0xfffe);
		zdp.setDestMacAddress(new byte[]{0,0,0,0,0,0,(byte) 0xff,(byte) 0xff});
		zdp.setGroupID(1);
		out.write(zdp.toByteArray());
	}

	/**
	 * 从路由中读取一个数据包
	 * 
	 * @return
	 * @throws IOException
	 * @throws WrongFrameFormatException
	 */
	public synchronized RouterDataPackage readRouterPackage() throws IOException, WrongFrameFormatException
	{
		return new RouterDataPackage(in);
	}
	
	/**
	 * Extract response frames from the router package.
	 * 
	 * @param rdp Router data package read from network.
	 * @return An array of frames containing the response object.
	 */
	public ResponseFrame[] getResponseFrames(RouterDataPackage rdp)
	{
		return rdp.getResponseFrames();
	}

	/**
	 * Read Response Frames from the network. <br />
	 * It is the combination of <code> getResponseFrames </code> and <code> readRouterPackage </code>
	 * 
	 * @return an array of frames containing the response objets. 
	 * @throws WrongFrameFormatException
	 * @throws IOException
	 */
	public ResponseFrame[] readResponseFrame() throws WrongFrameFormatException, IOException
	{
		return getResponseFrames(readRouterPackage());
	}
	
	
	/**
	 * 发送配置id命令。
	 * 
	 * @param tar
	 * @throws IOException
	 */
	public synchronized void sendConfigCommand(TargetEntity tar) throws IOException {
		CommandFrame cf = new CommandFrame(CommandFrame.CMDCODE_ASSIGNID);
		cf.setTimeStamp();
		ZigbeeDataPackage zdp = new ZigbeeDataPackage(cf);
		zdp.setDestNetID(0xfffe);
		zdp.setDestMacAddress(tar.getSN());
		zdp.setGroupID(1);
		out.write(zdp.toByteArray());
	}
	
	/**
	 * 发送状态请求命令，目前是群发。
	 * 
	 * @throws IOException
	 */
	public synchronized void sendStatusCommand() throws IOException
	{
		CommandFrame cf = new CommandFrame(CommandFrame.CMDCODE_TARGET_STATUS);
		cf.setTimeStamp();
		ZigbeeDataPackage zdp = new ZigbeeDataPackage(cf);
		zdp.setDestNetID(0xfffe);
		zdp.setDestMacAddress(new byte[]{0,0,0,0,0,0,(byte) 0xff,(byte) 0xff});
		zdp.setGroupID(1);
		out.write(zdp.toByteArray());
	}
	
	/**
	 * 发送其他单独控制命令，点对点通讯，例如起靶，倒靶，单等，单独状态查询等。
	 * 
	 * @param tar
	 * @param cmd
	 * @throws IOException
	 */
	public synchronized void sendSingleOPCommand(TargetEntity tar, byte cmd) throws IOException
	{
		CommandFrame cf = new CommandFrame(cmd);
		cf.setTimeStamp();
		ZigbeeDataPackage zdp = new ZigbeeDataPackage(cf);
		zdp.setDestNetID(0xfffe);
		zdp.setDestMacAddress(tar.getSN());
		zdp.setGroupID(1);
		out.write(zdp.toByteArray());
	}
	
	
	/**
	 * Testing method. DO NOT USE IT WHEN DEPLOYING THE PROGRAM.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		
	}

	/**
	 * Testing if there is data available
	 * @return
	 * @throws IOException
	 */
	public boolean hasIncomingData() throws IOException {		
		return in.available() >0;
	}
}
