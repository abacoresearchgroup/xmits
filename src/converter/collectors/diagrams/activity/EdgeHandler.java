package converter.collectors.diagrams.activity;

import converter.collectors.transitions.TransitionDictionary;
import converter.logics.structures.Transition;
import reader.list.Attribute;
import reader.list.Node;

public class EdgeHandler {

	//Attributes
	private TransitionDictionary transitionDictionary;
	
	//Constructor
	public EdgeHandler () {
		transitionDictionary = TransitionDictionary.getInstance();
	}
	
	public void process (Node node) {
		Transition transition = new Transition();
		for (Attribute attribute : node.getContent().getAttributes()) {
			switch (attribute.getName()) {
			case "xmi:id":
				transition.setId(attribute.getValue());
				break;
			case "source":
				transition.setIncommingId(attribute.getValue());
				break;
			case "target":
				transition.setOutgoingId(attribute.getValue());
				break;
			}
		}
		transitionDictionary.addTransition(transition);
	}

}
