package tuts.logics.rules;

import tuts.interfaces.Rule;
import tuts.logics.Lap;
import tuts.logics.Track;
import tuts.tools.RulesTools;

public class ThirdRule implements Rule{

	//Attributes
	private RulesTools rulesTools;
	private Lap lap;
	
	public ThirdRule () {
		rulesTools = new RulesTools();
		lap = new Lap();
	}
	
	//Methods
	@Override
	public boolean applyRule(Track track) {
		if (rulesTools.getAllNexts(track.getActiveLanes()).isEmpty()) {
			return false;
		}
		lap.thirdRuleLap(track);
		return true;
	}

}
