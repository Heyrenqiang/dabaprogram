package name.lxm.targets.wireless.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import name.lxm.targets.exception.WrongFrameFormatException;

/**
 * <p>这个类描述的是从中继到PC的数据包格式，即所谓的上行数据。上行数据较为复杂，且多为
 * 集合类数据，因此要格外注意，这个类的数据不用于生成，主要用于解析。</p>
 * 
 */
public class RouterDataPackage {
	public static final byte PACKAGE_HEAD_HIGH = 0x55;
	public static final int PACKAGE_HEAD_LOW = 0x00AA;
	
	public static final byte PACKAGE_DATATYPE_INITRESP = 0x01;
	public static final byte PACKAGE_DATATYPE_CONFRESP = 0x02;
	public static final byte PACKAGE_DATATYPE_STATUS = 0x03;
	
	/**
	 * 需要解析的字节数组。
	 */
	private byte[] data = null;	
	
	/**
	 * 数据帧长度
	 */
	private int size;
	
	/**
	 * 从字节缓冲区中创建对象。
	 * 
	 * @param d
	 * @throws WrongFrameFormatException
	 */
	public RouterDataPackage(byte[] d) throws WrongFrameFormatException
	{
		this.data = d;
		size = data.length;
		//double check the format
		if(data[0] != PACKAGE_HEAD_HIGH || data[1] != PACKAGE_HEAD_LOW){
			throw new WrongFrameFormatException();
		}
	}
	
	/**
	 * 构造函数，从数据流中直接构造出数据包。
	 * 
	 * @param is 输入数据流，应该是字节流。
	 * @throws IOException IO错误
	 * @throws WrongFrameFormatException  格式错误
	 */
	public RouterDataPackage(InputStream is) throws IOException, WrongFrameFormatException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if(is.read() == PACKAGE_HEAD_HIGH && is.read() == PACKAGE_HEAD_LOW){
			baos.write(PACKAGE_HEAD_HIGH);
			baos.write(PACKAGE_HEAD_LOW);
			int len1 = is.read();
			int len2 = is.read();
			size = len1*256+len2;
			baos.write(len1);
			baos.write(len2);
			int c = size - 4;
			while( c >  0)
			{
				baos.write(is.read());
				c--;
			}
			data = baos.toByteArray();
		}else{
			throw new WrongFrameFormatException();
		}
	}
	
	/**
	 * 获取数据包总字节数。
	 * 
	 * @return
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * 返回此数据包里的数据帧类型
	 * 
	 * @return
	 */
	public byte getType()
	{
		return data[4];
	}
	
	/**
	 * 获取CRC校验数据，2个字节。
	 * 
	 * @return 2个字节
	 */
	public byte[] getCRC()
	{
		return Arrays.copyOfRange(data, getSize()-2, getSize());
	}
	
	/**
	 * 获取封装好了的zigbee上行数据包，包含多个靶子信息。<br /> 
	 * Get the ResponseFrame collection from the data frame of the package.
	 * 
	 * @return
	 *   An array of response frames. The class of the objects in the array
	 *   depends on the content of the frame. It should be one of the sub-class
	 *   of ResponseFrame. User of this method should use <code>instanceof</code>
	 *   to determine which class it is. 
	 */
	public ResponseFrame[] getResponseFrames()
	{
		int count = 0;
		int framesize;
		switch(getType()){
		case InitResponseFrame.FRAME_TYPE:
			framesize = InitResponseFrame.FRAME_SIZE;
			count = (size - 5) / framesize;			
			break;
		case TargetStatusFrame.FRAME_TYPE:
			framesize = TargetStatusFrame.FRAME_SIZE;
			count = (size - 5) / framesize;
			break;
		case IDConfigResponseFrame.FRAME_TYPE:
			framesize = IDConfigResponseFrame.FRAME_SIZE;
			count = (size - 5) / framesize;
			break;
		default:
			return null;
		}
		
		ResponseFrame[] frames  = new ResponseFrame[count];
		
		switch(getType()){
		case InitResponseFrame.FRAME_TYPE:
			for(int i=0; i<frames.length; i++)
			{
				//extract a segment from the data
				byte[] b = Arrays.copyOfRange(data, 5+i*framesize, 5+i*framesize+framesize);
				frames[i] = new InitResponseFrame(b);
			}
			break;
		case TargetStatusFrame.FRAME_TYPE:
			for(int i=0; i<frames.length; i++)
			{
				//extract a segment from the data
				byte[] b = Arrays.copyOfRange(data, 5+i*framesize, 5+i*framesize+framesize);
				frames[i] = new TargetStatusFrame(b);
			}
			break;
		case IDConfigResponseFrame.FRAME_TYPE:
			for(int i=0; i<frames.length; i++)
			{
				//extract a segment from the data
				byte[] b = Arrays.copyOfRange(data, 5+i*framesize, 5+i*framesize+framesize);
				frames[i] = new IDConfigResponseFrame(b);
			}
			break;
		default:
			return null;		
		}

		return frames;
	}

	
}
