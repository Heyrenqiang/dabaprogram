package name.lxm.targets.model;

import java.util.Collection;
import java.util.Hashtable;

public class TraineeEntityCollection {
	
	/**
	 * Global instance of this class.
	 */
	public static TraineeEntityCollection INSTANCE = new TraineeEntityCollection();
	
	

	
	
	private Hashtable<Integer,TraineeEntity> collection = new Hashtable<>();
	
	
	private TraineeEntityCollection(){}
	
	
	public void put(TraineeEntity te)
	{
		if(te != null) collection.put(te.getID(), te);
	}
	
	public TraineeEntity get(int id)
	{
		return collection.get(id);
	}
	
	public int getSize()
	{
		return collection.size();
	}
	
	public Collection<TraineeEntity> getCollection()
	{
		return collection.values();
	}
	
	
	
	

}
