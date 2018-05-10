package tuts.logics;

import global.structure.TransitionSystem;

import java.util.List;

public class Grid {

	//Attributes
	private Synch synch;
	
	//Constructor
	public Grid () {
		synch = Synch.getInstance();
	}
	
	//Public Methods
	public synchronized void startRace (TransitionSystem position, List<Lane> lanes) {
		Track track = new Track();
		for (Lane lane : lanes) {
			track.addLane(lane);
			track.setPosition(position);
			track.setYellowFlag(true);
		}
		synch.increaseCounter();
		new Thread(new Race(track)).run();
	}
	
}
