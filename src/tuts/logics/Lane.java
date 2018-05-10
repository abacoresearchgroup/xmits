package tuts.logics;

import global.structure.TransitionSystem;

public class Lane {
	
	//Attributes
	public int id;
	public TransitionSystem transitionSystem;
	public boolean checkeredFlag; 
	
	//Constructor
	public Lane () {
		checkeredFlag = false;
	}
	
	//Methods
	public void setId (int id) {
		this.id = id;
	}
	
	public int getId () {
		return id;
	}
	
	public void setTransitionSystem (TransitionSystem transitionSystem) {
		this.transitionSystem = transitionSystem;
	}
	
	public TransitionSystem getTransitionSystem () {
		return transitionSystem;
	}
	
	public void setCheckeredFlag () {
		checkeredFlag = true;
	}
	
	public boolean getCheckeredFlag () {
		return checkeredFlag; 
	}

	public Lane getClone() {
		Lane clone = new Lane();
		clone.setId(id);
		clone.setTransitionSystem(transitionSystem.getClone());
		if (checkeredFlag) {
			clone.setCheckeredFlag();
		}
		return clone;
	}
	
}
