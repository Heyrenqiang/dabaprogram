package name.lxm.targets.wireless.data;

/**
 * Base class for data frame from router to PC.
 * 
 * @author Xiaoming Mar 20, 2017
 *
 */
public class ResponseFrame {
	
	public static final byte FRAME_HEADER = 0x7E;
	

	protected byte[] dataframe;
	
	/**
	 * 
	 * @param data
	 */
	public ResponseFrame(byte[] data)
	{
		dataframe = data;
	}
	
	/**
	 * Header, should be 0x7E.
	 * @return
	 */
	public byte getHeader()
	{
		return dataframe[0];
	}
	
	/**
	 * length of this frame.
	 * 
	 * @return
	 */
	public int getLength()
	{
		return (dataframe[1] & 0x00ff) * 256 + (dataframe[2] & 0x00ff);
	}
	
	/**
	 * 检查数据帧格式是否合法
	 * 
	 * @return
	 */
	public boolean isValid()
	{
		return dataframe[0] == 0x7E; 
	}
	
	/**
	 * get frame type.
	 * @return
	 */
	public byte getFrameType()
	{
		return dataframe[15];
	}

}
