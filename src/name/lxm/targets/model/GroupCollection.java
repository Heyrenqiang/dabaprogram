package name.lxm.targets.model;

import java.util.Collection;
import java.util.Hashtable;

public class GroupCollection {
	public static GroupCollection INSTANCE = new GroupCollection();
	
	
	private Hashtable<Integer, GroupEntity> pool = new Hashtable<>();
	
	
	
	private GroupCollection(){}
	
	public void put(GroupEntity ge)
	{
		pool.put(ge.getGroupID(), ge);
	}

	public GroupEntity getGroup(int groupID)
	{
		return pool.get(groupID);
	}
	
	public int getSize()
	{
		return pool.size();
	}
	
	public Collection<GroupEntity> getCollection()
	{
		return pool.values();
	}

	public GroupEntity findGroupByPad(int padID) {
		for(GroupEntity ge : pool.values()){
			if(ge.getPadID() == padID) return ge;
		}
		return null;
	}
}
