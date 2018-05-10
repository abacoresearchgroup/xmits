package tuts.logics;

import global.structure.Guard;

import java.util.ArrayList;
import java.util.List;

import tuts.logics.qualifying.Qualifying;
import tuts.tools.GuardTools;
import tuts.tools.RulesTools;

public class Lap {
	
	//Attributes
	private RulesTools rulesTools;
	private Qualifying qualifying;
	private GuardTools guardTools;
	
	//Constructor
	public Lap () {
		rulesTools = new RulesTools();
		qualifying = new Qualifying();
		guardTools = new GuardTools();
	}

//--Public Methods-------------------------------------------------------------------------------------------------------------------------------------------//
	public void firstRuleLap(Track track) {
		for (Lane lane : track.getActiveLanes()) {
			if (nextIsEmpty(lane)) {
				outOfRace(lane, track);
			}else{
				advanceOnePosition(lane);
			}
		}
	}

	public void secondRuleLap(Track track) {
		for (Lane lane : track.getActiveLanes()) {
			if (!nextIsEmpty(lane)) {
				if (rulesTools.laneNextIsEqual(lane)) {
					advanceOnePosition(lane);
				}
			}
		}
	}

	public void thirdRuleLap(Track track) {
		List<Lane> nextsAreEqual = new ArrayList<Lane>();
		List<Lane> nextsAreDifferent = new ArrayList<Lane>();
		for (Lane lane : track.getActiveLanes()) {
			if (nextIsEmpty(lane)) {
				outOfRace(lane, track);
			}else{
				if (nextsAreEqual(lane) && lane.getTransitionSystem().getNext().size() > 1) {
					nextsAreEqual.add(lane);
				}else{
					nextsAreDifferent.add(lane);
				}
			}
		}
		if (!nextsAreEqual.isEmpty() || !nextsAreDifferent.isEmpty()) {
			qualifying.buildGrid(track, nextsAreEqual, nextsAreDifferent);
		}
	}

//--Private Methods-----------------------------------------------------------------------------------------------------------------------------------------//
	private void outOfRace (Lane lane, Track track) {
		lane.setCheckeredFlag();
	}
	
	private void advanceOnePosition (Lane lane) {
		lane.setTransitionSystem(lane.getTransitionSystem().getNext().get(0));
	}
	
	public boolean nextIsEmpty (Lane lane) {
		return lane.getTransitionSystem().getNext().isEmpty();
	}
	
	private boolean nextsAreEqual(Lane lane) {
		List<Guard> firstGuards = guardTools.addMissingGuards(lane.getTransitionSystem().getNext().get(0).getState().getGuards());
		for (int i = 1; i < lane.getTransitionSystem().getNext().size(); i++) {
			List<Guard> secondGuards = guardTools.addMissingGuards(lane.getTransitionSystem().getNext().get(i).getState().getGuards());
			if (!guardTools.allGuardsAreEqual(firstGuards, secondGuards)) {
				return false;
			}
		}
		return true;
	}
	
}
