package name.lxm.targets.wireless.data;

import java.util.Arrays;

import name.lxm.targets.Utility;

public class TargetStatusFrame extends ResponseFrame {
	public static final byte FRAME_TYPE = 0x03;
	public static final int FRAME_SIZE = 35;
	
	/**
	 * 构造函数
	 * 
	 * @param data 数据帧数组
	 */
	public TargetStatusFrame(byte[] data) {
		super(data);
	}
	
	
	@Override
	public boolean isValid() {
		return super.isValid() && getFrameType() == FRAME_TYPE;
	}


	/**
	 * Get the ID of the target
	 * 
	 * @return
	 */
	public int getLocalID()
	{
		return dataframe[16] & 0x00ff;
	}
	
	/**
	 * 获取靶子状态
	 * @return 0 - 立，1 - 倒 
	 */
	public byte getTargetStatus()
	{
		return dataframe[17];
	}
	
	/**
	 * 获取靶纸信息
	 *     byte[0] - 区域信息：0x00/0x0A/0x0B/0x0C/0x0D
	 *     byte[1] - 环数信息：00/01/02/03/04/05/06 
	 *     
	 * @return
	 */
	public byte[] getGradeData()
	{
		return Arrays.copyOfRange(dataframe, 18, 20);
	}
	
	/**
	 * 获取温度信息
	 * 
	 * @return
	 */
	public double getTemprature()
	{
		return Utility.decodeTemprature(dataframe[20], dataframe[21]);
	}
	
	/**
	 * 获取压力信息
	 * 
	 * @return
	 */
	public double getPressure()
	{
		return ((dataframe[22] & 0x00ff) << 8 + dataframe[23] & 0x00ff) / 10.0;
	}
	
	/**
	 * 获取电池信息
	 * 
	 * @return
	 */
	public double getBatteryStatus()
	{
		return ((dataframe[24] & 0x00ff) << 8 + dataframe[25] & 0x00ff) / 10.0;

	}
	
	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public byte[] getTimeStamp()
	{
		return Arrays.copyOfRange(dataframe, 28, 34);
	}
	
	/**
	 * Get the checksum. No use in this program.
	 * @return
	 */
	public byte getCheckSum()
	{
		return dataframe[34];
	}

}
