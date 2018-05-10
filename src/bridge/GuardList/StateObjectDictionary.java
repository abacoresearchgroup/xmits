package bridge.GuardList;

import global.structure.GuardValue;

import java.util.HashMap;

public class StateObjectDictionary {

	//Attributes
	private static StateObjectDictionary uniqueIntance;
	private HashMap<String, StateObject> stateObjectMap;
	
	//Constructor
	private StateObjectDictionary () {
		stateObjectMap = new HashMap<String, StateObject>();
	}
	
	//Static Methods
	public static StateObjectDictionary getInstance () {
		if (uniqueIntance == null) {
			uniqueIntance = new StateObjectDictionary();
		}
		return uniqueIntance;
	}
	
	//Public Methods
	public void addStateObject (StateObject stateObject) {
		if (stateObjectMap.get(stateObject.getStateName()) == null) {
			stateObjectMap.put(stateObject.getStateName(), stateObject);
		}else{
			for (GuardValue guardValue : stateObject.getGuardValues()) {
				stateObjectMap.get(stateObject.getStateName()).addGuardValue(guardValue);
			}
		}
	}
	
	public HashMap<String, StateObject> getStateObjectMap () {
		return stateObjectMap;
	}
	
	public void reset () {
		stateObjectMap = new HashMap<String, StateObject>();
	}
	
}
