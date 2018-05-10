package bridge;

import global.structure.Guard;
import global.structure.TransitionSystem;

import java.util.ArrayList;
import java.util.List;

public class Tools {

//--Public Methods-----------------------------------------------------------------------------------------------------------------------------------------//
	public List<TransitionSystem> getAllStates (TransitionSystem transitionSystem) {
		List<TransitionSystem> output = new ArrayList<TransitionSystem>();
		if (transitionSystem.getState().getMessage().getContent() != null) {
			output.add(transitionSystem);
		}
		for (TransitionSystem child : transitionSystem.getNext()) {
			output = joinTransitionSystemList(output, getAllStates(child));
		}
		return output;
	}
	
	public List<Guard> getGuards (TransitionSystem transitionSystem) {
		for (TransitionSystem state : getAllStates(transitionSystem)) {
			if (!state.getState().getGuards().isEmpty()) {
				return state.getState().getGuards();
			}
		}
		return null;
	}
	
	public List<TransitionSystem> getStateSet (List<TransitionSystem> allStates) {
		List<TransitionSystem> stateSet = new ArrayList<TransitionSystem>();
		for (TransitionSystem state : allStates) {
			if (!stateIsThere(state, stateSet)) {
				stateSet.add(state);
			}
		}
		return stateSet;
	}

//--Private Methods----------------------------------------------------------------------------------------------------------------------------------------//
	private List<TransitionSystem> joinTransitionSystemList(List<TransitionSystem> one, List<TransitionSystem> two) {
		for (TransitionSystem transitionSystem : two) {
			one.add(transitionSystem);
		}
		return one;
	}
	
	private boolean stateIsThere (TransitionSystem incomming, List<TransitionSystem> stateSet) {
		for (TransitionSystem stored : stateSet) {
			if (stored.getState().getMessage().getContent().equals(incomming.getState().getMessage().getContent())) {
				return true;
			}
		}
		return false;
	}

}
