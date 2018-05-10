package bridge.GuardList;

import global.structure.Guard;
import global.structure.GuardValue;
import global.structure.TransitionSystem;

import java.util.ArrayList;
import java.util.List;

import bridge.Tools;

public class GuardLogics {

	//Attributes
	private Tools tools;
	private StateObjectDictionary stateObjectDictionary;
	
	//Constructor
	public GuardLogics () {
		tools = new Tools();
		stateObjectDictionary = StateObjectDictionary.getInstance();
	}
	
//--Public Methods-----------------------------------------------------------------------------------------------------------------------------------------//
	public String listGuardStates (TransitionSystem root) {
		String output = "";
		if (tools.getGuards(root) == null) return output;
		for (Guard guard : tools.getGuards(root)) {
			output = output + "\n" + guard.getName() + ":=case\n";
			initStateObjectDictionary(guard, root);
			for (StateObject stateObject : stateObjectDictionary.getStateObjectMap().values()) {
				int counter = 0;
				output = output + "    State=" + stateObject.getStateName() + ": {";
				for (GuardValue guardValue : stateObject.getGuardValues()) {
					counter ++;
					output = output + guardValue.toString().toLowerCase();
					if (counter < stateObject.getGuardValues().size()) {
						output = output + ", ";
					}
				}
				output = output + "};\n";
			}
			output = output + "\nTRUE: {dc, true, false};\n";
			output = output + "esac;\n";
			stateObjectDictionary.reset();
		}
		return output;
	}

//--Private Methods----------------------------------------------------------------------------------------------------------------------------------------//
	private void initStateObjectDictionary (Guard guard, TransitionSystem root) {	
		for (TransitionSystem state : tools.getAllStates(root)) {
			StateObject stateObject = new StateObject();
			stateObject.setStateName(state.getState().getMessage().getContent());
			stateObject.addGuardValue(getThisGuardValue(guard, state));
			for (GuardValue guardValue : getNextGuardValues(guard, state)) {
				stateObject.addGuardValue(guardValue);
			}
			stateObjectDictionary.addStateObject(stateObject);
		}
	}

	private GuardValue getThisGuardValue (Guard guard, TransitionSystem state) {
		for (Guard stateGuard : state.getState().getGuards()) {
			if (guard.getName().equals(stateGuard.getName())) {
				return stateGuard.getValue();
			}
		}
		return null;
	}
	
	private List<GuardValue> getNextGuardValues (Guard guard, TransitionSystem state) {
		List<GuardValue> guardValues = new ArrayList<GuardValue>();
		for (TransitionSystem next : state.getNext()) {
			GuardValue nextGuardValue = getThisGuardValue(guard, next);
			guardValues.add(nextGuardValue);
		}
		return guardValues;
	}
	
}
