package name.lxm.targets.model;

import java.util.Collection;
import java.util.Hashtable;

/**
 * 平板控制器集合
 * 
 * @author lxm Mar 12, 2017
 *
 */
public class PadsCollection {
	
	public static PadsCollection INSTANCE = new PadsCollection();
	
	private Hashtable<Integer, PadEntity> collection = new Hashtable<>();
	
	
	private PadsCollection(){}
	
	
	public void put(PadEntity pad)
	{
		collection.put(pad.getId(), pad);
	}
	
	public PadEntity get(int padid)
	{
		return collection.get(padid);
	}
	
	public Collection<PadEntity> getPadsCollection()
	{
		return collection.values();
	}
	
	public int getSize()
	{
		return collection.size();
	}
	
	public Integer[] getKeys()
	{
		return (Integer[]) collection.keySet().toArray();
	}

	/**
	 * check the token 
	 * @param id
	 * @param token 
	 * @return true: is token, false:not the token.
	 */
	public boolean checkToken(int id, String token) {
		PadEntity pe = collection.get(id);
		return (token != null) && (pe != null) && (token.equals(pe.getToken()));
	}


	public boolean isEmpty() {
		return collection.isEmpty();
	}
}
