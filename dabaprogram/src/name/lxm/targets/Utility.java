package name.lxm.targets;

import java.util.Arrays;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//import dabaxunlian.Commandsending;
//import dabaxunlian.Datatest;
import name.lxm.targets.exception.WrongFrameFormatException;
import name.lxm.targets.ui.Guanlifenzu;
import name.lxm.targets.ui.Mainframe;
import name.lxm.targets.ui.simulation.Communicationmoduletest;
import name.lxm.targets.ui.simulation.Dataupdate;
import name.lxm.targets.wireless.data.GPSData;

/**
 * 辅助类。
 * 
 * @author Xiaoming Li
 *
 */
public class Utility {

	private static int targetIDBank = 0;
	private static int padIDBank = 0;
	private static Mainframe mainframe;

	/**
	 * 生成靶ID,数据从1开始递增。
	 * 
	 * @return
	 */
	public static int generateTargetID() {
		return ++targetIDBank;
		// left the 0 intentionally.
		// id == 0 means the entity has not been assigned yet.
	}

	/**
	 * 组控制器ID，数据从1开始递增。
	 * 
	 * @return
	 */
	public static int generatePadID() {
		return ++padIDBank;
		// left the 0 intentionally.
		// id == 0 means the pad has not been assigned yet.
	}

	/**
	 * 将字节码转换成GPS数据。
	 * 
	 * @param data
	 * @return
	 * @throws WrongFrameFormatException
	 */
	public static GPSData decodeGPS(byte[] data) throws WrongFrameFormatException {
		if (data == null)
			throw new WrongFrameFormatException();
		if (data.length != 30)
			throw new WrongFrameFormatException();

		boolean bGpsValid = data[0] == 'A';
		if(bGpsValid){
			double lat = Double.parseDouble(new String(Arrays.copyOfRange(data, 1, 10)));
			if (data[10] == 'S')
				lat = -1.0 * lat;
			double lon = Double.parseDouble(new String(Arrays.copyOfRange(data, 11, 21)));
			if (data[21] == 'W')
				lon = -1.0 * lon;
			double att = Double.parseDouble(new String(Arrays.copyOfRange(data, 22, 30)));

			return new GPSData(bGpsValid, lat, lon, att);
		}else
			return new GPSData(bGpsValid, 0, 0, 0);

	}

	/**
	 * 将字节码转换成温度数据
	 * 
	 * @param data
	 * @return
	 */
	public static double decodeTemprature(byte data0, byte data1) {
		int high = data0 & 0x00ff;
		int low = data1 & 0x00ff;

		if ((high & 0x0008) == 0) {
			return (high * 256 + low) * 0.0625;
		} else {
			return ((high & 0x0007) * 256 + low) * 0.0625 - 128.0;
		}
	}

	/**
	 * 将字符串转换为JSON中的字符串值（即带引号）。
	 * 
	 * @param s
	 * @return
	 */
	public static String toJSONStringType(String s) {
		return "\"" + s + "\"";
	}

	public static GPSData fakeGPS() {
		double A, B, C;
		A = 100.200;
		B = 100.201;
		C = B - A;

		GPSData gps = new GPSData(true, Math.random() * C + A, Math.random() * C + A, 0.0);

		return gps;
	}

	public static void main(String[] args) {
		//MainWin mainWin=new MainWin("dasd");
		//mainWin.setVisible(true);
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		mainframe=new Mainframe("智能目标显示系统");
		
		Dataupdate dataupdate=new Dataupdate();
		Communicationmoduletest communicationmoduletest=new Communicationmoduletest();
		mainframe.addCommandstateListener(communicationmoduletest);
		dataupdate.addActionListener(mainframe);
		Guanlifenzu.adduiEventListener(TMApplication.INSTANCE);
		dataupdate.start();
		
		GPSData gps = fakeGPS();
		System.out.println(gps);

		byte a = (byte) 0xff;
		int b = a & 0x00ff;
		int c = ((int) a) << 8;

		System.out.println("a=" + a + " b=" + b + " c=" + c);

	}

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 })).byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
	 * 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return byte[]
	 */
	public static byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[8];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < 8; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}
	
	/**
	 * calculate the checksum byte of zigbee package. Be
	 * sure to assign the checksum to 0 in the given byte array.
	 * 
	 * @param buf
	 * @param start
	 * @param end
	 * @return
	 */
	public static byte calculateZigbeeChecksumByte(byte[] buf, int start, int end)
	{
		int sum = 0;
		if(end > buf.length) end = buf.length;
		for(int i=start; i<end; i++){
			sum += buf[i];
		}
		return (byte) (0x00ff - (sum & 0x00ff));
	}
}
