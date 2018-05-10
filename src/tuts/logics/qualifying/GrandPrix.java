package tuts.logics.qualifying;

import java.util.ArrayList;
import java.util.List;

import tuts.logics.Grid;
import tuts.logics.Lane;
import tuts.logics.Track;

public class GrandPrix {

	//Attributes
	private Grid grid;
	
	//Constructor
	public GrandPrix () {
		grid = new Grid();
	}
	
	//Methods
	public void startRace (Track track, List<List<Lane>> classifiedLanesWithDifferentNext, List<List<Lane>> classifiedLanesWithEqualNexts) {
		if (classifiedLanesWithEqualNexts.isEmpty() && !classifiedLanesWithDifferentNext.isEmpty()) {
			for (List<Lane> classifiedLanes : classifiedLanesWithDifferentNext) {
				createThread(track, classifiedLanes);
			}
		}
		if (!classifiedLanesWithEqualNexts.isEmpty() && classifiedLanesWithDifferentNext.isEmpty()) {
			for (List<Lane> lanesWithEqualNext : classifiedLanesWithEqualNexts) {
				createThread(track, lanesWithEqualNext);
			}
		}
		if (!classifiedLanesWithEqualNexts.isEmpty() && !classifiedLanesWithDifferentNext.isEmpty()) {
			for (List<Lane> lanesWithEqualNext : classifiedLanesWithEqualNexts) {
				for (List<Lane> lanesWithDifferentNext : classifiedLanesWithDifferentNext) {
					List<Lane> qualifiedLanes = joinLaneLists(lanesWithEqualNext, lanesWithDifferentNext);
					createThread(track, qualifiedLanes);
				}
			}
		}
		for (Lane lane : track.getActiveLanes()) {
			lane.setCheckeredFlag();
		}
	}

//--Private Methods-------------------------------------------------------------------------------------------------------------------------------------------------------//	
	private void createThread (Track track, List<Lane> qualifiedLanes) {
		List<Integer> laneIDs = getLaneIDs(track);
		List<Lane> gridList = new ArrayList<Lane>();
		for (Integer id : laneIDs) {
			if (laneIsThere(id, qualifiedLanes)) {
				gridList.add(getLaneById(id, qualifiedLanes));
			}else{
				Lane outOfRace = getLaneById(id, track.getAllLanes());
				outOfRace.getTransitionSystem().getNext().clear();
				gridList.add(outOfRace);
			}
		}
		grid.startRace(track.getPosition(), gridList);
	}
	
	private Lane getLaneById (Integer id, List<Lane> lanes) {
		for (Lane lane : lanes) {
			if (lane.getId() == id) {
				return lane.getClone();
			}
		}
	return null;
}

	private boolean laneIsThere (Integer id, List<Lane> lanes) {
		for (Lane lane : lanes) {
			if (lane.getId() == id) {
				return true;
			}
		}
	return false;
}

	private List<Integer> getLaneIDs (Track track) {
		List<Integer> laneIDs = new ArrayList<Integer>();
		for (Lane lane : track.getAllLanes()) {
			laneIDs.add(lane.getId());
		}
		return laneIDs;
	}
	
	private List<Lane> joinLaneLists (List<Lane> lanesWithEqualNext, List<Lane> lanesWithDifferentNext) {
		List<Lane> qualifiedLanes = new ArrayList<Lane>();
		for (Lane lane : lanesWithEqualNext) {
			qualifiedLanes.add(lane);
		}
		for (Lane lane : lanesWithDifferentNext) {
			qualifiedLanes.add(lane);
		}
		return qualifiedLanes;
	}
	
}
