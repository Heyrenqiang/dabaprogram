package name.lxm.targets.wireless.data;

/**
 * 通用的数据帧单元对象，方便对其进行进一步的打包。
 * 该数据帧单元包括命令数据帧，以及靶网发过来的应答数据。
 * 
 * @author Xiaoming Li
 *
 */
public abstract class RequestDataFrame {
	protected byte[] dataframe;
	
	/**
	 * 将对象转换成字节码。
	 * @return
	 */
	public abstract byte[] toByteArray();

}
