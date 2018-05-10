package converter.collectors.guards;

import global.structure.Guard;
import global.structure.GuardValue;

import java.util.Hashtable;

public class GuardDictionary {

	//Attributes
	private static GuardDictionary uniqueInstance;
	private Hashtable<String, Guard> guardList;
	
	//Constructor
	private GuardDictionary () {
		guardList = new Hashtable<String, Guard>();
	}
	
	//Static Methods
	public static GuardDictionary getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new GuardDictionary();
		}
		return uniqueInstance;
	}
	
	//Public Methods
	public void addGuard (String id, Guard guard) {
		if (guardList.get(id) == null) {
			guardList.put(id, guard);
		}
	}
	
	public Hashtable<String, Guard> getGuardList () {
		return guardList;
	}
	
	public Guard getGuardById (String id) {
		return guardList.get(id);
	}
	
	public Hashtable<String, Guard> getGuardListClone () {
		Hashtable<String, Guard> clone = new Hashtable<String, Guard>();
		for (String key : guardList.keySet()) {
			clone.put(key, guardList.get(key).getClone());
		}
		return clone;
	}
	
	public void rebuildGuardList (Hashtable<String, Guard> list) {
		guardList.clear();
		for (String key : list.keySet()) {
			guardList.put(key, list.get(key));
		}
	}
	
	public void setTrue (String guardId) {
		getGuardById(guardId).setValue(GuardValue.True);
	}
	
	public void setFalse (String guardId) {
		getGuardById(guardId).setValue(GuardValue.False);
	}
	
	public void reset () {
		guardList = new Hashtable<String, Guard>();
	}
	
}
