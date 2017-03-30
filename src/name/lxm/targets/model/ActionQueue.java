package name.lxm.targets.model;

import java.util.Collection;
import java.util.Vector;

public class ActionQueue {
	private Vector<ActionCommand> queue = new Vector<>();
	
	public static ActionQueue INSTANCE = new ActionQueue();
	
	private ActionQueue(){}
	
	public ActionCommand pop()
	{
		if(queue.isEmpty()) return null;
		
		return queue.remove(0);
	}
	
	public void push(ActionCommand ac)
	{
		queue.addElement(ac);
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public Collection<ActionCommand> getActionCollection() {
		
		return queue;
	}
}
