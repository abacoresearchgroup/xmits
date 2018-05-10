package converter.collectors.transitions;

import java.util.ArrayList;
import java.util.List;

import converter.logics.structures.Transition;

public class TransitionDictionary {
	
	//Attributes
	private static TransitionDictionary uniqueInstance;
	private List<Transition> transitions;
	
	//Constructor
	private TransitionDictionary () {
		transitions = new ArrayList<Transition>();
	}
	
	//Static Methods
	public static TransitionDictionary getInstance () {
		if (uniqueInstance == null) {
			uniqueInstance = new TransitionDictionary();
		}
		return uniqueInstance;
	}
	
	//Public Methods
	public void addTransition (Transition transition) {
		transitions.add(transition);
	}
	
	public List<Transition> getTransitions () {
		return transitions;
	}

	public void reset () {
		transitions = new ArrayList<Transition>();
	}
	
}
