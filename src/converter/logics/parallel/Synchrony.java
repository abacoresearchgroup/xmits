package converter.logics.parallel;

import java.util.ArrayList;
import java.util.List;

import converter.logics.LogicTools;
import converter.logics.structures.CheckPoint;
import converter.logics.structures.Transition;

public class Synchrony {
	
	//Attributes
	private static Synchrony uniqueInstance;
	private List<CheckPoint> checkPoints;
	private LogicTools logicTools;
	
	//Constructor
	private Synchrony () {
		checkPoints = new ArrayList<CheckPoint>();
		logicTools = new LogicTools();
	}
	
//--Private Methods-----------------------------------------------------------------------------------------------------------------------------------------------//
	public static Synchrony getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new Synchrony();
		}
		return uniqueInstance;
	}

//--Public Methods------------------------------------------------------------------------------------------------------------------------------------------------//
	public void addCheckPoint (Transition lastTransition, List<Integer> path) {
		CheckPoint checkPoint = new CheckPoint();
		checkPoint.setId(lastTransition.getId());
		checkPoint.setPath(logicTools.integerListClone(path));
		checkPoints.add(checkPoint);		
	}
	
	public boolean existis(Transition incommingTransition, List<Integer> path) {
		
		CheckPoint incommingCheckPoint = new CheckPoint();
		incommingCheckPoint.setId(incommingTransition.getId());
		incommingCheckPoint.setPath(logicTools.integerListClone(path));
		
		for (CheckPoint storedCheckPoint : checkPoints) {
			if (fromSamePath(storedCheckPoint, incommingCheckPoint)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void reset () {
		checkPoints = new ArrayList<CheckPoint>();
	}

//--Private Methods----------------------------------------------------------------------------------------------------------------------------------------------//
	private boolean fromSamePath(CheckPoint storedCheckPoint, CheckPoint incommingCheckPoint) {
		if (storedCheckPoint.getId().equals(incommingCheckPoint.getId())) {
			if (isThere(storedCheckPoint.getPath(), incommingCheckPoint.getPath())) {
				return true;
			}
		}
		return false;
	}

	private boolean isThere (List<Integer> storedPath, List<Integer> incommingPath) {
		
		if (storedPath.size() > incommingPath.size()) {
			return false;
		}
		
		for (int i = 0; i < storedPath.size(); i++) {
			if (storedPath.get(i) != incommingPath.get(i)) {
				return false;
			}
		}
		
		return true;
	}

}
