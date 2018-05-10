package converter.collectors.diagrams.smd;

import java.util.List;

import converter.collectors.transitions.TransitionDictionary;
import converter.logics.dictionaries.ElementDictionary;
import converter.logics.structures.Element;
import converter.logics.structures.Transition;
import converter.logics.structures.Type;
import reader.list.Node;

public class TransitionCollector {
	
	//Attributes
	private TransitionDictionary transitionDictionary;
	private ElementDictionary elementDictionary;
	private SmdGuardCollector smdGuardCollector;
	private SmdTools smdTools;
	private Transition lastTransition;
	
	//Constructor
	public TransitionCollector () {
		transitionDictionary = TransitionDictionary.getInstance();
		elementDictionary = ElementDictionary.getInstance();
		smdGuardCollector = new SmdGuardCollector();
		smdTools = new SmdTools();
		lastTransition = new Transition();
	}

	//public Methods
	public void collect(List<Node> xmi) {
		int iterator = 0;
		while (iterator < xmi.size()) {
			if (smdTools.isTransition(xmi.get(iterator))) {
				collectTransition(xmi.get(iterator));
				collectTransitionGuard(xmi, iterator);
			}
			iterator ++;
		}
		lastTransition = new Transition();
	}


//--Private Methods--------------------------------------------------------------------------------------------------------------------------------------------------//
	private void collectTransition (Node node) {
		Transition transition = new Transition();
		transition.setId(smdTools.getAttributeValue(node, "xmi:id"));
		transition.setIncommingId(smdTools.getAttributeValue(node, "source"));
		transition.setOutgoingId(smdTools.getAttributeValue(node, "target"));
		transitionDictionary.addTransition(transition);
		lastTransition = transition;
	}

	private void collectTransitionGuard (List<Node> xmi, int iterator) {
		boolean ownedRule = false;
		for (int i = iterator + 1; i < xmi.size(); i++) {
			if (ownedRule) {
				Element source = elementDictionary.getElement(lastTransition.getIncommingId());
				if (source.getType().equals(Type.Decision)) {
					smdGuardCollector.collect(xmi.get(i), lastTransition);
				}else{
					lastTransition.setName(smdTools.getAttributeValue(xmi.get(i), "value"));
				}
				break;
			}else{
				if (smdTools.nodeNameEquals(xmi.get(i), "ownedRule")) {
					ownedRule = true;
				}else{
					break;
				}
			}
		}
	}
	
}
