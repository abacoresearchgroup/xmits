package tuts.logics.rules;

import tuts.interfaces.Rule;
import tuts.logics.Lane;
import tuts.logics.Lap;
import tuts.logics.Track;
import tuts.tools.RulesTools;

public class SecondRule implements Rule{
	
	//Attributes
	private RulesTools rulesTools;
	private Lap lap;
	
	//Constructor
	public SecondRule () {
		rulesTools = new RulesTools();
		lap = new Lap();
	}
	
	@Override
	public boolean applyRule(Track track) {
		for (Lane lane : track.getActiveLanes()) {
			if (rulesTools.laneNextIsEqual(lane)) {
				lap.secondRuleLap(track);
				return true;
			}
		}
		return false;
	}

}
