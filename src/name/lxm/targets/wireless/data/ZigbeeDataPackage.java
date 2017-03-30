package name.lxm.targets.wireless.data;

import name.lxm.targets.Utility;
import name.lxm.targets.exception.WrongFrameFormatException;

/**
 * 该类是zigbee网络数据的下行数据封装类，即从PC到中继。<br />
 * 
 * <p>可以设置的内容包括（1）数据长度（2）发送内容（3）发送MAC目标地址和网络地址（4）帧组别（5）校验位</p>
 * 
 * <p>校验位暂时不用</p>
 * 
 * @author Xiaoming Li Mar 19, 2017
 *
 */
public class ZigbeeDataPackage {
	private byte package_head = 0x7e; /*fixed value*/
	private int package_size; /* 2 bytes, unsigned */
	private byte package_cat = 0x10; /* reserved */
	private int package_group_id; /* 1 byte, 0x01 - 0xff */
	private byte[] package_dest_mac = new byte[8]; /* 8 bytes, 0x0000ffff is broadcasting address */
	private int package_dest_id; /* 2 bytes, 0x0001-0xffff*/
	private byte package_br_area; /* broadcasting area */
	private byte package_reserved = 0; /*no use*/
	private byte[] package_data; /* to be determined by contents */
	private byte package_checksum; /*1 byte, checksum */
	
	/**
	 * 该对象自身的字节化数据
	 */
	private byte[] dataFrame = null;
	
//	public static void main(String[] args)
//	{
//		test();
//	}

	/**
	 * 测试函数
	 */
	@SuppressWarnings("unused")
	private static void test() {
//		CommandFrame f = new CommandFrame(CommandFrame.CMDCODE_ASSIGNID);
//		f.setTimeStamp();
//		ZigbeeDataPackage zf = new ZigbeeDataPackage(f);
//		zf.setDestNetID(1);
//		zf.setDestMacAddress(new byte[]{2,2,2,2,2,2,2,2});
//		zf.setGroupID(0);
//		byte[] buf = zf.toByteArray();
//		System.out.println(Arrays.toString(buf));
//		
//		try {
//			ZigbeeDataPackage zf2 = new ZigbeeDataPackage(buf);
//			System.out.println(zf2.getFrameSize());
//			System.out.println(Arrays.toString(zf2.getFrameData()));
//		} catch (WrongFrameFormatException e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * 从具体帧数据中生成zigbee数据包
	 * @param frame
	 */
	public ZigbeeDataPackage(RequestDataFrame frame)
	{
		package_data = frame.toByteArray();
		//18:Start from 0x7E to checksum byte, excluding the frame data
		package_size = 14 + package_data.length; 

		package_group_id = 0;
		package_checksum = 0;
		dataFrame = new byte[package_size + 4];
	}
	
	/**
	 * 设置发送内容及帧长度（自动计算）。
	 * 
	 * @param frame
	 */
	public void setDataFrame(RequestDataFrame frame)
	{
		package_data = frame.toByteArray();
		//18:Start from 0x7E to checksum byte, excluding the frame data
		package_size = 14 + package_data.length;
	}
	
	/**
	 * Set the group id. note the group id here is used for zigbee communication. 
	 * 
	 * @param groupid zigbee network group id parameter.
	 */
	public void setGroupID(int groupid)
	{
		package_group_id = groupid;
	}
	
	/**
	 * 设置目标物理地址
	 * 
	 * @param mac
	 */
	public void setDestMacAddress(byte[] mac)
	{
		System.arraycopy(mac, 0, package_dest_mac, 0, 8);
	}
	
	/**
	 * 设置目标网络地址
	 * 
	 * @param id
	 */
	public void setDestNetID(int id)
	{
		package_dest_id = id;
	}
	
	/**
	 * 将对象转换成字节数组，用于发送。Double check this when protocol is changed.
	 * 
	 * last update: 3.23.2017
	 * 
	 * @return byte[] An byte array.
	 */
	public byte[] toByteArray()
	{
		dataFrame[0] = package_head; //帧头
		dataFrame[1] = (byte) ((package_size>>8) & 0x00ff); //长度高位
		dataFrame[2] = (byte) (package_size & 0x00ff); //长度低位
		dataFrame[3] = package_cat; //固定
		dataFrame[4] = (byte) package_group_id; //组编号
		System.arraycopy(package_dest_mac, 0, dataFrame, 5, 8); //MAC地址
		dataFrame[13] = (byte) (package_dest_id >> 8 & 0xff); //分配的地址高位
		dataFrame[14] = (byte) (package_dest_id & 0xff); //网络地址低位
		dataFrame[15] = package_br_area;
		dataFrame[16] = package_reserved; //不用
		System.arraycopy(package_data, 0, dataFrame, 17, package_data.length);
		dataFrame[17+package_data.length] = Utility.calculateZigbeeChecksumByte(dataFrame, 3, dataFrame.length);
		
		return dataFrame;
	}
	
	/**
	 * 从字节集合中创建包对象。
	 * 
	 * @param d
	 * @throws WrongFrameFormatException
	 */
//	public ZigbeeDataPackage(byte[] d) throws WrongFrameFormatException
//	{
//		dataFrame = d;
//		parse();
//	}

	/**
	 * 解析字节包
	 * 
	 * @throws WrongFrameFormatException
	 */
//	private void parse() throws WrongFrameFormatException {
//		if(dataFrame[0] != 0x7e) throw new WrongFrameFormatException();
//		package_size = (dataFrame[1] & 0x00ff) * 256 + dataFrame[2] & 0x00ff;
//		if(package_size != dataFrame.length) throw new WrongFrameFormatException();
//		
//		package_cat = dataFrame[3];
//		package_group_id = dataFrame[4] & 0x00ff;
//		System.arraycopy(dataFrame, 5, package_dest_mac, 0, 8);
//		package_dest_id = dataFrame[13] & 0x00ff * 256 + dataFrame[14] & 0x00ff;
//		//System.arraycopy(dataFrame, 16, frame_data, 0, frame_size - 17);
//		package_data = Arrays.copyOfRange(dataFrame, 16, package_size - 1);
//		package_checksum = dataFrame[dataFrame.length-1];
//	}
	
	public int getFrameSize()
	{
		return package_size;
	}
	
	public byte[] getFrameDestMac()
	{
		return package_dest_mac;
	}
	
	public byte getFrameType()
	{
		return package_cat;
	}
	
	public int getFrameDestID()
	{
		return package_dest_id;
	}
	
	public int getFrameGroupID()
	{
		return package_group_id;
	}
	
	public byte[] getFrameData()
	{
		return package_data;
	}
	
	public byte getFrameCheckSum()
	{
		return package_checksum;
	}

	/**
	 * 从字节包中恢复对象。
	 * 
	 * @param d
	 * @throws WrongFrameFormatException
	 */
	public void fromByteArray(byte[] d) throws WrongFrameFormatException {
//		dataFrame = d;
//		parse();
	}
}

