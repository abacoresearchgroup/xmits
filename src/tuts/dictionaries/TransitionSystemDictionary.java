package tuts.dictionaries;

import global.structure.TransitionSystem;

import java.util.LinkedList;
import java.util.List;

import tuts.tools.TransitionSystemTools;

public class TransitionSystemDictionary {

	//Attributes
	private static TransitionSystemDictionary uniqueInstance;
	private List<TransitionSystem> tsList;
	
	//Constructor
	private TransitionSystemDictionary () {
		tsList = new LinkedList<TransitionSystem>();
	}
	
	//Static Methods
	public static TransitionSystemDictionary getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TransitionSystemDictionary();
		}
		return uniqueInstance;
	}
	
	//Public Methods
	public void addTransitionSystem (TransitionSystem transitionSystem) {
		TransitionSystemTools tsTools = new TransitionSystemTools();
		TransitionSystem root = tsTools.copyTransitionSystemTree(transitionSystem);
		tsList.add(root);
	}
	
	public List<TransitionSystem> getTsList () {
		return tsList;
	}
	
	public void reset () {
		tsList = new LinkedList<TransitionSystem>();
	}
	
}
