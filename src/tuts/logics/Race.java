package tuts.logics;

import tuts.dictionaries.RulesDictionary;
import tuts.tools.OutputTools;
import tuts.interfaces.Rule;

public class Race implements Runnable{
	
	//Attributes
	private Track track;
	private Synch synch;
	private OutputTools outputTools;
	private RulesDictionary rulesDictionary;
	
	//Constructor
	public Race (Track track) {
		this.track = track;
		synch = Synch.getInstance();
		outputTools = new OutputTools();
		rulesDictionary = RulesDictionary.getInstance();
	}
	
	//Public Methods
	@Override
	public void run () {
		race();
	}

	//Private Methods
	private void race() {
		while (true) {

			if (track.getYellowFlag()) {
				track.setYellowFlag(false);
			}else{
				track.setPosition(outputTools.createTuts(track));
			}
			
			for (Rule rule : rulesDictionary.getRules()) {
				if (rule.applyRule(track)) {
					break;
				}
			}
			
			if (track.getScore() == 0) {
				synch.decreaseCounter();
				break;
			}
			
		}
	}

}
