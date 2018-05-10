package tuts.logics.qualifying;

import global.structure.Guard;
import global.structure.TransitionSystem;

import java.util.ArrayList;
import java.util.List;

import tuts.logics.Lane;
import tuts.tools.GuardTools;

public class QualifyingTools {

	//Attributes
	private GuardTools guardTools;
		
	//Constructor
	public QualifyingTools () {
		guardTools = new GuardTools();
	}
	
//--Public Methods---------------------------------------------------------------------------------------------------------------------------------------------------//
	public List<Lane> createLanesWithOneNext (List<Lane> lanesWithNexts) {
		List<Lane> listWithOneNext = new ArrayList<Lane>();
		for (Lane lane : lanesWithNexts) {
			for (Lane neoLane : getOneLaneForEachNext(lane)) {
				listWithOneNext.add(neoLane);
			}
		}
		return listWithOneNext;
	}
	
	public List<Lane> getOneLaneForEachNext (Lane lane) {
		List<Lane> lanesForEachNext = new ArrayList<Lane>();
		for (TransitionSystem next : lane.getTransitionSystem().getNext()) {
			Lane neoLane = new Lane();
			neoLane.setId(lane.getId());
			neoLane.setTransitionSystem(lane.getTransitionSystem().getClone());
			neoLane.getTransitionSystem().getNext().clear();
			neoLane.getTransitionSystem().addNext(next);
			lanesForEachNext.add(neoLane);
		}
		return lanesForEachNext;
	}
	
	public List<List<Guard>> createSampleList (List<Lane> lanesWithOneNext) {
		List<List<Guard>> sampleList = new ArrayList<List<Guard>>();
		for (Lane lane : lanesWithOneNext) {
			List<Guard> nextGuards = guardTools.addMissingGuards(lane.getTransitionSystem().getNext().get(0).getState().getGuards());
			if (!guardIsThere(sampleList, nextGuards)) {
				sampleList.add(nextGuards);
			}
		}
		return sampleList;
	}
	
//..Equal Next Functions.............................................................................................................................................//
	public List<List<Lane>> splitLanesWithSameNext (List<Lane> nextsAreEqual) {
		List<List<Lane>> lanesWithSameNext = new ArrayList<List<Lane>>();
		for (Lane lane : nextsAreEqual) {
			lanesWithSameNext.add(getOneLaneForEachNext(lane));
		}
		return lanesWithSameNext;
	}
	
	public List<Iterator> createIteratorList(int size) {
		List<Iterator> iteratorList = new ArrayList<Iterator>();
		for (int i = 0; i < size; i++) {
			iteratorList.add(new Iterator());
		}
		return iteratorList;
	}
	
	public boolean ietarteList(List<Iterator> iteratorList, List<List<Lane>> lanesWithSameNext) {
		for (int i = 0; i < iteratorList.size(); i++) {
			iteratorList.get(i).iterate();
			if (iteratorList.get(i).getIterator() == lanesWithSameNext.get(i).size()) {
				iteratorList.get(i).reset();
			}else{
				break;
			}
		}
		if (allIsZero(iteratorList)) {
			return true;
		}
		return false;
	}

	//--Private Methods--------------------------------------------------------------------------------------------------------------------------------------------------//
	private boolean guardIsThere (List<List<Guard>> sampleList, List<Guard> guards) {
		for (List<Guard> sampleGuard : sampleList) {
			if (guardTools.allGuardsAreEqual(sampleGuard, guards)) {
				return true;
			}
		}
		return false;
	}

	private boolean allIsZero(List<Iterator> iteratorList) {
		for (Iterator iterator : iteratorList) {
			if (iterator.getIterator() != 0) {
				return false;
			}
		}
		return true;
	}
	
}
