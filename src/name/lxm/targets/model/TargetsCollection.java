package name.lxm.targets.model;

import java.util.Collection;
import java.util.Hashtable;

/**
 * 靶子数据集和
 * 
 * @author lxm Mar 12, 2017
 *
 */
public class TargetsCollection {
	
	private Hashtable<Integer, TargetEntity> collection = new Hashtable<>();
	
	public static final TargetsCollection INSTANCE = new TargetsCollection();
	
	private TargetsCollection(){}
	
	
	public void put(TargetEntity target)
	{
		collection.put(target.getID(), target);
	}
	
	public TargetEntity get(int id)
	{
		return collection.get(id);
	}

	public int getCount()
	{
		return collection.size();
	}
	
	/**
	 * Get the collection of target entity.
	 * @return
	 */
	public Collection<TargetEntity> getTargetsCollection()
	{
		return collection.values();
	}
	
	public Integer[] toKeyArray()
	{
		return (Integer[]) collection.keySet().toArray();
	}


	public boolean isEmpty() {
		return collection.isEmpty();
	}
	
}
