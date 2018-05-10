package tuts.logics.rules;

import global.structure.Guard;
import global.structure.TransitionSystem;

import java.util.List;

import tuts.interfaces.Rule;
import tuts.logics.Lap;
import tuts.logics.Track;
import tuts.tools.GuardTools;
import tuts.tools.RulesTools;

public class FirstRule implements Rule{

	//Attributes
	private RulesTools rulesTools;
	private GuardTools guardTools;
	private Lap lap;
	
	//Constructor
	public FirstRule () {
		rulesTools = new RulesTools();
		guardTools = new GuardTools();
		lap = new Lap();
	}
	
	@Override
	public boolean applyRule(Track track) {
		if (rulesTools.isThereAnyDivision(track.getActiveLanes())) {
			return false;
		}else{
			List<TransitionSystem> nexts = rulesTools.getAllNexts(track.getActiveLanes());
			if (nexts.isEmpty()) {
				return false;
			}else{
				List<Guard> first = guardTools.addMissingGuards(nexts.get(0).getState().getGuards());
				for (int i = 1; i < nexts.size(); i++) {
					List<Guard> n = guardTools.addMissingGuards(nexts.get(i).getState().getGuards());
					if (!guardTools.allGuardsAreEqual(first, n)) {
						return false;
					}
				}
				lap.firstRuleLap(track);
			}
		}
		return true;
	}

}
