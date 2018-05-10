package tuts.logics;

import global.structure.TransitionSystem;

import java.util.ArrayList;
import java.util.List;

public class Track {

	//Attributes
	private List<Lane> lanes;
	private boolean yellowFlag;
	private TransitionSystem position;
	
	//Constructor
	public Track () {
		lanes = new ArrayList<Lane> ();
		yellowFlag = false;
	}
	
	//Methods
	public void addLane (Lane lane) {
		lanes.add(lane);
	}
	
	public List<Lane> getAllLanes () {
		return lanes;
	}
	
	public List<Lane> getActiveLanes () {
		List<Lane> activeLanes = new ArrayList<Lane>();
		for (Lane lane : lanes) {
			if (!lane.getCheckeredFlag()) {
				activeLanes.add(lane);
			}
		}
		return activeLanes;
	}
	
	public void setYellowFlag (boolean value) {
		yellowFlag = value;
	}
	
	public boolean getYellowFlag () {
		return yellowFlag;
	}
	
	public int getScore () {
		int score = 0;
		for (Lane lane : lanes) {
			if (!lane.getCheckeredFlag()) {
				score ++;
			}
		}
		return score;
	}
	
	public void setPosition (TransitionSystem position) {
		this.position = position;
	}
	
	public TransitionSystem getPosition () {
		return position;
	}
	
}
