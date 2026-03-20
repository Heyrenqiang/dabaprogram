package name.lxm.targets.wireless;

/**
 * A default implementation of the {@link ITargetsEventListener}
 * 
 * @author lxm Mar 30, 2017
 *
 */
public class DefaultTargetEventsListener implements ITargetsEventListener {

	@Override
	public void updateData(int datatype) {

	}

	@Override
	public void updateData() {
		updateData(DATATYPE_NOTSPECIFIED);
	}

}
