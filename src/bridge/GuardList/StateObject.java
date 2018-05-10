package bridge.GuardList;

import global.structure.GuardValue;

import java.util.ArrayList;
import java.util.List;

public class StateObject {

	//Attributes
	private String stateName;
	private List<GuardValue> guardValues;
	
	//Constructor
	public StateObject () {
		guardValues = new ArrayList<GuardValue>();
	}
	
	//Public Methods
	public void setStateName (String stateName) {
		this.stateName = stateName;
	}
	
	public String getStateName () {
		return stateName;
	}
	
	public void addGuardValue (GuardValue guardValue) {
		if (!guardValueIsThere(guardValue)) {
			guardValues.add(guardValue);
		}
	}

	public List<GuardValue> getGuardValues () {
		return guardValues;
	}
	
	//PrivateMethods
	private boolean guardValueIsThere(GuardValue incomming) {
		for (GuardValue stored : guardValues) {
			if (stored == incomming) {
				return true;
			}
		}
		return false;
	}
}
