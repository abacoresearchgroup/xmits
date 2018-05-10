package converter.logics.parallel;

import global.exceptions.converter.ForkException;
import global.exceptions.converter.TransitionException;

import java.util.ArrayList;
import java.util.List;

import converter.logics.dictionaries.ElementDictionary;
import converter.logics.structures.Element;
import converter.logics.structures.Transition;

public class NextSolver {
	
	//Attributes
	private ElementDictionary elementDictionary;
	
	//Constructor
	public NextSolver () {
		elementDictionary = ElementDictionary.getInstance();
	}

	//Methods
	public List<Element> getNexts (List<Transition> transitions) throws TransitionException {
		
		List<Element> output = new ArrayList<Element>();
		ParallelTools parallelTools = new ParallelTools();
		
		for (Transition transition : transitions) {		
			Element element = elementDictionary.getElement(transition.getOutgoingId());
			if (element != null) {
				switch (element.getType()) {
				case State :
					output.add(element.getClone());
					break;
				case Fork:
					try {
						Element fork = parallelTools.solveFork(element.getContent().getOutgoingTransitions());
						for (Element forkElement : fork.getMultiElement().getElements()) {
							output.add(forkElement);
						}
					} catch (ForkException e) {
						e.printStackTrace();
					}
					break;
				case Join:
					element.getContent().setLastTransition(transition);
					output.add(element.getClone());
					break;
				case Decision:
					//Decision nodes inside parallels are not supported yet
					break;
				case Connection:
					for (Element connectionElement : getNexts(element.getContent().getOutgoingTransitions())) {
						output.add(connectionElement);
					}
					break;
				default:
					//Do nothing
				}
			}else{
				throw new TransitionException();
			}
		}
		
		return output;
	}

}
