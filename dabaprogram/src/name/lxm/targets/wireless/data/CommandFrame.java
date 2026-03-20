package name.lxm.targets.wireless.data;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * <p>命令类的数据帧定义与操作。命令类的数据是从计算机发往zigbee网络的。</p>
 * 
 * <p>该数据为下行数据，即从PC-->Router. </p>
 * 
 * <p>需要设置的内容为：（1）命令和参数（2）时间戳</p>
 * 
 * @author Xiaoming Li
 *
 */
public class CommandFrame extends RequestDataFrame {

	/** 广播类消息，系统初始化，复位 */
	public static final byte CMDCODE_RESET = 0x01;
	/** 靶位ID配置命令 */
	public static final byte CMDCODE_ASSIGNID = 0x02;
	/** 立靶测试 */
	public static final byte CMDCODE_TARGET_UP = 0x03;
	/** 倒靶测试 */
	public static final byte CMDCODE_TARGET_DOWN = 0x04;
	/** 点灯 ，点亮照明系统 */
	public static final byte CMDCODE_LIGHT_ON = 0x05;
	/** 靶位终端信息读取命令 */
	public static final byte CMDCODE_TARGET_STATUS = 0x06;
	
	public static final byte FRAME_HEAD = 0x55;

	private int cmdCode; /* 命令代码, 1个字节 */
	private byte[] param = new byte[10]; /* 10个字节，不同命令有不同的含义 */
	private byte[] timestamp = new byte[6]; /* 6个字节，时间戳 */

	/**
	 * 通用构造函数，需提供命令种类。
	 * 
	 * @param cmdCode command send by PC.
	 */
	public CommandFrame(byte cmdCode) {
		this.cmdCode = cmdCode;
		Arrays.fill(param, (byte) 0);
		if(cmdCode == CMDCODE_RESET){
			System.arraycopy(new byte[]{0,0,0,0,0,0,(byte) 0xff,(byte) 0xff}, 0, param, 0, 8);
		}
	}

	/**
	 * 配置靶ID时使用的构造函数，此时命令必须为2.
	 * 
	 * @param cmdCode 命令，应该为2
	 * @param id 靶子的ID
	 */
	public CommandFrame(byte cmdCode, int id) {
		this(cmdCode);
		param[0] = (byte) (id & 0x00ff);
	}

	/**
	 * 设置时间戳。
	 */
	public void setTimeStamp() {
		Date date = new Date();
		DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
		String s = df.format(date);
		s = s.replaceAll(":", "");
		if (s.length() < 6) {
			s = "0" + s;
		}
		timestamp[0] = (byte) s.charAt(0);
		timestamp[1] = (byte) s.charAt(1);
		timestamp[2] = (byte) s.charAt(2);
		timestamp[3] = (byte) s.charAt(3);
		timestamp[4] = (byte) s.charAt(4);
		timestamp[5] = (byte) s.charAt(5);
	}
	
	/**
	 * 转化成字节数组。
	 */
	public byte[] toByteArray()
	{
		dataframe = new byte[18];
		dataframe[0] = FRAME_HEAD;
		dataframe[1] = (byte) cmdCode;
		System.arraycopy(param, 0, dataframe, 2, param.length);
		System.arraycopy(timestamp, 0, dataframe, 12, timestamp.length);
		return dataframe;
	}
}
