package converter.collectors.diagrams.activity;

import java.util.List;

import reader.list.Attribute;
import reader.list.Body;
import reader.list.Node;
import global.exceptions.converter.InvalidGuardException;
import global.structure.Guard;
import global.structure.GuardValue;
import converter.collectors.guards.GuardDictionary;
import converter.collectors.guards.GuardHandler;
import converter.logics.dictionaries.ElementDictionary;
import converter.logics.structures.Element;
import converter.logics.structures.Transition;

public class ActivityGuardCollector {
	
	//Attributes
	private ElementDictionary elementDictionary;
	private GuardDictionary guardDictionary;
	private GuardHandler guardHandler;
	
	//Constructor
	public ActivityGuardCollector () {
		elementDictionary = ElementDictionary.getInstance();
		guardDictionary = GuardDictionary.getInstance();
		guardHandler = new GuardHandler();
	}

//--Public Methods---------------------------------------------------------------------------------------------------------------------------------------------//
	public void collect(List<Node> xmi) {
		boolean guard = false;
		boolean body = false;
		String lastIncommingId = "";
		String lastTransitionId = "";
		for (Node node : xmi) {
			if (node.getContent().getName() != null) {
				switch (node.getContent().getName()) {
				case "edge":
					lastIncommingId = getIncommingID(node);
					lastTransitionId = getTransitionID(node);
					guard = false;
					body = false;
					break;
				case "guard":
					guard = true;
					body = false;
					break;
				case "body":
					if (guard) {
						body = true;
					}
				}
			}
			if (node.getContent() instanceof Body) {
				if (guard && body) {
					createGuard (node, lastIncommingId, lastTransitionId);
					guard = false;
					body = false;
				}
			}
		}
		
	}

	//--Private Methods---------------------------------------------------------------------------------------------------------------------------------------------//
	private String getIncommingID(Node node) {
		String incommingId = "";
		for (Attribute attribute : node.getContent().getAttributes()) {
			if (attribute.getName().equals("source")) {
				incommingId =  attribute.getValue();
				break;
			}
		}
		return incommingId;
	}
	
	private String getTransitionID(Node node) {
		String transitionId = "";
		for (Attribute attribute : node.getContent().getAttributes()) {
			if (attribute.getName().equals("xmi:id")) {
				transitionId =  attribute.getValue();
				break;
			}
		}
		return transitionId;
	}

	private void createGuard(Node node, String lastIncommingId, String lastTransitionId) {
		Guard guard = new Guard();
		try {
			guard = guardHandler.getGuard(node.getContent().getContent());
		} catch (InvalidGuardException e) {
			e.printStackTrace();
		}
		if (classifyGuard(lastIncommingId,lastTransitionId, guard)) {
			guard.setValue(GuardValue.DC);
			guardDictionary.addGuard(lastIncommingId, guard);
		}
	}

	private boolean classifyGuard (String lastIncommingId, String lastTransitionId, Guard guard) {
		setTransitionValue(lastIncommingId, lastTransitionId, guard);
		if (guard.getName() == null) {
			return false;
		}
		return true;
	}

	private void setTransitionValue (String lastIncommingId, String lastTransitionId, Guard guard) {
		Element decision = elementDictionary.getElement(lastIncommingId);
		for (Transition transition : decision.getContent().getOutgoingTransitions()) {
			if (transition.getId().equals(lastTransitionId)) {
				transition.setGuardValue(guard.getValue());
				break;
			}
		}
	}
	
}
