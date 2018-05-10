package tuts.tools;

import global.structure.Guard;
import global.structure.TransitionSystem;

import java.util.ArrayList;
import java.util.List;

import tuts.logics.Lane;

public class RulesTools {

	//Attributes
	private GuardTools guardTools;
	
	//Constructor
	public RulesTools () {
		guardTools = new GuardTools();
	}
	
//--Public Methods-------------------------------------------------------------------------------------------------------------------------------------------//
	public boolean isThereAnyDivision (List<Lane> lanes) {
		for (Lane lane : lanes) {
			if (isNextADivision(lane)) {
				return true;
			}
		}
		return false;
	}

	public List<TransitionSystem> getAllNexts(List<Lane> lanes) {
		List<TransitionSystem> allNexts = new ArrayList<TransitionSystem>();
		for (Lane lane : lanes) {
			for (TransitionSystem next : lane.getTransitionSystem().getNext()) {
				allNexts.add(next);
			}
		}
		return allNexts;
	}

	public boolean laneNextIsEqual(Lane lane) {
		if (!lane.getTransitionSystem().getNext().isEmpty() && !isNextADivision(lane)) {
			List<Guard> guardsOne = lane.getTransitionSystem().getState().getGuards();
			List<Guard> guardsTwo = lane.getTransitionSystem().getNext().get(0).getState().getGuards();
			guardsOne = guardTools.addMissingGuards(guardsOne);
			guardsTwo = guardTools.addMissingGuards(guardsTwo);
			if (guardTools.allGuardsAreEqual(guardsOne, guardsTwo)) {
				return true;
			}
		}
		return false;
	}

//--Private Methods-----------------------------------------------------------------------------------------------------------------------------------------//
	private boolean isNextADivision (Lane lane) {
		return lane.getTransitionSystem().getNext().size() > 1;
	}
	
}
