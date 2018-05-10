package tuts.logics.rules;

import tuts.interfaces.Rule;
import tuts.logics.Lane;
import tuts.logics.Track;

public class FinalRule implements Rule{

	@Override
	public boolean applyRule(Track track) {
		for (Lane lane : track.getActiveLanes()) {
			lane.setCheckeredFlag();
		}
		return true;
	}

}
