package converter.collectors.transitions;

import converter.logics.dictionaries.ElementDictionary;
import converter.logics.structures.Transition;

public class TransitionTools {

	//Attributes
	private TransitionDictionary transitionDictionary;
	private ElementDictionary elementDictionary;
	
	//Constructor
	public TransitionTools () {
		transitionDictionary = TransitionDictionary.getInstance();
		elementDictionary = ElementDictionary.getInstance();
	}
	
	//Methods
	public void AddTransitions () {
		for (Transition transition : transitionDictionary.getTransitions()) {
			elementDictionary.getElement(transition.getIncommingId()).getContent().addOutgoingTransition(transition);
			elementDictionary.getElement(transition.getOutgoingId()).getContent().addIncommingTransition(transition);
		}
		transitionDictionary.reset();
	}
	
}
