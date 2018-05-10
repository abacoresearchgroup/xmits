package global.logics;

import global.structure.TransitionSystem;

public class Builder {

	//Attributes
	private static Builder uniqueInstance;
	private TransitionSystem root;
	private TransitionSystem position;
	private BuilderTools builderTools;
	
	//Constructor
	private Builder () {
		builderTools = new BuilderTools();
		position = builderTools.createRoot();
		root = position;
	}
	
	//Static Methods
	public static Builder getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new Builder();
		}
		return uniqueInstance;
	}

	//Public Methods
	public void reset () {
		position = builderTools.createRoot();
		root = position;
	}
	
	public TransitionSystem getRoot () {
		return root;
	}
	
	public TransitionSystem getPosition () {
		return position;
	}
	
	public void addChild (TransitionSystem child) {
		position.addNext(child);
	}
		
	public void setPosition (TransitionSystem position) {
		this.position = position;
	}
	
}
