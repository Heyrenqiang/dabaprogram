package name.lxm.targets.ui;

import name.lxm.targets.model.TargetEntity;

public interface UIEventListener {

	/**
	 * 立靶动作。参数如果为NULL，则指全部
	 * Let the target stand. If no target specified, all targets will stand.
	 * 
	 * @param te Target. If null then all the targets.
	 */
	void processStandEvent(TargetEntity te);

	/**
	 * Let the target sit. If no target specified, all targets will sit.
	 * 
	 * @param te Target. If null then all the targets.
	 */
	void processSitEvent(TargetEntity te);
	
	
	/**
	 * Let the light on on specified target.
	 * 
	 * @param te target. if null then all the targets.
	 */
	void processLightEvent(TargetEntity te);

	void processInitEvent(TargetEntity te);

	void processIDEvent(TargetEntity te);

	void processStatusEvent(TargetEntity te);

}
