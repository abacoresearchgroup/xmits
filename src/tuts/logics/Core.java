package tuts.logics;

import java.util.ArrayList;
import java.util.List;

import global.logics.Builder;
import global.structure.TransitionSystem;
import global.tools.IdGenerator;
import tuts.dictionaries.TransitionSystemDictionary;
import tuts.tools.GuardTools;

public class Core {

	//Attributes
	private TransitionSystemDictionary transitionSystemDictionary;
	private GuardTools guardTools;
	private Builder builder;
	private Grid grid;
	private Synch synch;
	private IdGenerator idGenerator;
	
	//Constructor
	public Core () {
		transitionSystemDictionary = TransitionSystemDictionary.getInstance();
		guardTools = new GuardTools();
		builder = Builder.getInstance();
		grid = new Grid();
		synch = Synch.getInstance();
		idGenerator = IdGenerator.getInstance();
	}
	
//--Public Methods----------------------------------------------------------------------------------------------------------------------------------------------//
	public void begin() {
		builder.reset();
		guardTools.collectGuards();
		List<Lane> lanes = createLaneList(transitionSystemDictionary.getTsList());
		grid.startRace(builder.getPosition(), lanes);
		synch.rest();
	}

//--Private Methods---------------------------------------------------------------------------------------------------------------------------------------------//
	private List<Lane> createLaneList (List<TransitionSystem> tsList) {
		List<Lane> lanes = new ArrayList<Lane>();
		for (TransitionSystem ts : tsList) {
			lanes.add(createLane(ts));
		}
		return lanes;
	}
	
	private Lane createLane (TransitionSystem ts) {
		Lane lane = new Lane();
		lane.setId(idGenerator.getIntegerId());
		lane.setTransitionSystem(ts);
		return lane;
	}
	
}
