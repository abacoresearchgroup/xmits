package tuts.dictionaries;

import global.structure.Guard;

import java.util.LinkedList;
import java.util.List;

public class GuardDictionary {

	//Attributes
	private static GuardDictionary uniqueInstance;
	private List<Guard> guardList;

	//Constructor
	private GuardDictionary () {
		guardList = new LinkedList<Guard>();
	}

	//Static Methods
	public static GuardDictionary getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new GuardDictionary();
		}
		return uniqueInstance;
	}

	//Public Methods
	public List<Guard> getGuardList () {
		return guardList;
	}

	public void addGuard(Guard guard) {
		guardList.add(guard);
	}

	public void reset () {
		guardList = new LinkedList<Guard>();
	}

}
