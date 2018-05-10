package tuts.logics.qualifying;

import global.structure.Guard;

import java.util.ArrayList;
import java.util.List;

import tuts.logics.Lane;
import tuts.logics.Track;
import tuts.tools.GuardTools;

public class Qualifying {
	
	//Attributes
	private GuardTools guardTools;
	private QualifyingTools qualifyingTools;
	private GrandPrix grandPrix;
	
	//Constructor
	public Qualifying () {
		guardTools = new GuardTools();
		qualifyingTools = new QualifyingTools();
		grandPrix = new GrandPrix();
	}

//--Public Methods---------------------------------------------------------------------------------------------------------------------------------------------------//
	//Methods
	public void buildGrid (Track track, List<Lane> nextsAreEqual, List<Lane> nextsAreDifferent) {
		if (!nextsAreEqual.isEmpty() && !nextsAreDifferent.isEmpty()) {		
			List<List<Lane>> classifiedLanesWithEqualNexts = classifyLanesWithEqualNexts(nextsAreEqual);
			List<List<Lane>> groupedLanesWithDifferentNext = groupLanesWithDifferentNexts(nextsAreDifferent);
			grandPrix.startRace(track, groupedLanesWithDifferentNext, classifiedLanesWithEqualNexts);
		}else{		
			List<List<Lane>> classifiedLanesWithDifferentNext = classifyLanesWithDifferentNexts(nextsAreDifferent);
			List<List<Lane>> classifiedLanesWithEqualNexts = classifyLanesWithEqualNexts(nextsAreEqual);
			grandPrix.startRace(track, classifiedLanesWithDifferentNext, classifiedLanesWithEqualNexts);
		}
	}

	//--Private Methods--------------------------------------------------------------------------------------------------------------------------------------------------//
	private List<List<Lane>> groupLanesWithDifferentNexts (List<Lane> nextsAreDifferent) {
		List<List<Lane>> groupedLanesWithDifferentNext = new ArrayList<List<Lane>>();
		groupedLanesWithDifferentNext.add(nextsAreDifferent);
		return groupedLanesWithDifferentNext;
	}
	
	private List<List<Lane>> classifyLanesWithDifferentNexts (List<Lane> nextsAreDifferent) {
		List<Lane> lanesWithOneDifferentNext = qualifyingTools.createLanesWithOneNext(nextsAreDifferent);
		List<List<Guard>> sampleList = qualifyingTools.createSampleList(lanesWithOneDifferentNext);
		List<List<Lane>> lanesWithTheSameNext = new ArrayList<List<Lane>>();
		for (List<Guard> sampleGuards : sampleList) {
			List<Lane> equalLanes = new ArrayList<Lane>();
			for (Lane lane : lanesWithOneDifferentNext) {
				List<Guard> nextGuards = guardTools.addMissingGuards(lane.getTransitionSystem().getNext().get(0).getState().getGuards());
				if (guardTools.allGuardsAreEqual(sampleGuards, nextGuards)) {
					equalLanes.add(lane);
				}
			}
			lanesWithTheSameNext.add(equalLanes);
		}
		return lanesWithTheSameNext;
	}
	
	private List<List<Lane>> classifyLanesWithEqualNexts (List<Lane> nextsAreEqual) {
		List<List<Lane>> classifiedLanesWithEqualNexts = new ArrayList<List<Lane>>();
		List<List<Lane>> lanesWithSameNext = qualifyingTools.splitLanesWithSameNext(nextsAreEqual);
		List<Iterator> iteratorList = qualifyingTools.createIteratorList(lanesWithSameNext.size());
		while (true) {
			List<Lane> laneGroup = new ArrayList<Lane>();
			for (int i = 0; i < lanesWithSameNext.size(); i++) {
				laneGroup.add(lanesWithSameNext.get(i).get(iteratorList.get(i).getIterator()));
			}
			classifiedLanesWithEqualNexts.add(laneGroup);
			if (qualifyingTools.ietarteList(iteratorList, lanesWithSameNext)) {
				break;
			}
		}
		return classifiedLanesWithEqualNexts;
	}
	
}
