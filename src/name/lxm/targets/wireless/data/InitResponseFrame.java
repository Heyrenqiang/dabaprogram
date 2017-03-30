package name.lxm.targets.wireless.data;

import java.util.Arrays;

import name.lxm.targets.Utility;

/**
 * 该数据帧是zigbee节点接收到初始化命令的时候返回的数据。
 * 
 * @author Xiaoming Li
 *
 */
public class InitResponseFrame extends ResponseFrame{

	/**
	 * 帧数据类型
	 * @see RouterDataPackage
	 */
	public static final byte FRAME_TYPE = 0x01;
	public final static int FRAME_SIZE = 80;
	
	/**
	 * 
	 * @param data
	 */
	public InitResponseFrame(byte[] data) {
		super(data);
	}
	
	
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return super.isValid() && dataframe[15] == FRAME_TYPE;
	}

	/**
	 * 获取节点的MAC地址，共8字节，从ASCII码标识的16进制字符串转换而来。
	 * 
	 * @return
	 */
	public byte[] getNodeMacAddress()
	{
		byte[] mac = Arrays.copyOfRange(dataframe, 16, 32);
		String s = new String(mac);
		return Utility.HexString2Bytes(s);
	}

	/**
	 * 获取节点的GPS数据，包括定位状态，维度，经度和高程，共30字节
	 * 
	 * @return
	 */
	public byte[] getGPSData()
	{
		return Arrays.copyOfRange(dataframe, 32, 62);
	}
	
	/**
	 * 获取靶的状态：立或倒
	 * 
	 * @return
	 *     0 - 立靶     
	 *     1 - 倒靶
	 */
	public byte getTargetState()
	{
		return dataframe[62];
	}
	
	/**
	 * 获取靶纸信息
	 * 
	 * 
	 * @return
	 *     byte[0] - 区域信息：0x00/0x0A/0x0B/0x0C/0x0D
	 *     byte[1] - 环数信息：00/01/02/03/04/05/06
	 */
	public byte[] getGradeData()
	{
		return Arrays.copyOfRange(dataframe, 63, 65);
	}
	
	/**
	 * 获取温度信息
	 * 
	 * @return 2个字节
	 */
	public double getTemprature()
	{
		return Utility.decodeTemprature(dataframe[65], dataframe[66]);
	}
	
	/**
	 * 获取气压信息
	 * 
	 * @return 2个字节
	 */
	public double getPressure()
	{
		return ((dataframe[67] & 0x00ff) << 8 + dataframe[68] & 0x00ff) / 10.0;
	}
	
	/**
	 * 获取蓄电池电量信息
	 * 
	 * @return 2个字节
	 */
	public double getBatteryInfo()
	{
		return ((dataframe[69] & 0x00ff) << 8 + dataframe[70] & 0x00ff) / 10.0;
	}
	
	/**
	 * 获取时间戳
	 * 
	 * @return 6个字节
	 */
	public byte[] getTimeStamp()
	{
		return Arrays.copyOfRange(dataframe, 73, 79);
		
	}
	
	/**
	 * 获得校验字节
	 * 
	 * @return
	 */
	public byte getCheckSum()
	{
		return dataframe[79];
	}
}
