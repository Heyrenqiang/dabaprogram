package name.lxm.targets.wireless.data;

import java.util.Arrays;

import name.lxm.targets.Utility;

public class IDConfigResponseFrame extends ResponseFrame {

	public final static byte FRAME_TYPE = 0x02;
	public final static int FRAME_SIZE = 72;
	
	public IDConfigResponseFrame(byte[] d)
	{
		super(d);
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return super.isValid() && dataframe[15] == FRAME_TYPE;
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
	 * 获取发送数据的节点MAC地址
	 * 
	 * @return 8个字节
	 */
	public byte[] getNodeMacAddress()
	{
		return Utility.HexString2Bytes(new String(Arrays.copyOfRange(dataframe, 17, 33)));
	}
	
	/**
	 * 获取GPS数据
	 * 
	 * @return 30个字节
	 */
	public byte[] getGPSData()
	{
		return Arrays.copyOfRange(dataframe, 33, 63);
	}
	
	/**
	 * 获取时间戳
	 * 
	 * @return 6个字节
	 */
	public byte[] getTimeStamp()
	{
		return Arrays.copyOfRange(dataframe, 65, 71);
	}
	
	/**
	 * Get the checksum. NO use here.
	 * 
	 * @return
	 */
	public byte getCheckSum()
	{
		return dataframe[71];
	}
}
