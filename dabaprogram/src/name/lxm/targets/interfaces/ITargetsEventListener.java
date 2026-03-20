package name.lxm.targets.interfaces;

/**
 * Interface to Target event listener
 * 
 * @author lxm Mar 30, 2017
 *
 */
public interface ITargetsEventListener {
	public static final int DATATYPE_TARGET = 1;
	public static final int DATATYPE_TRAINEE = 2;
	public static final int DATATYPE_NOTSPECIFIED = 0;

	public void updateData(int datatype);
	public void updateData();
}
