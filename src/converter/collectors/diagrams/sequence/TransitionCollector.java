package converter.collectors.diagrams.sequence;

import global.tools.IdGenerator;

import java.util.List;

import converter.collectors.transitions.TransitionDictionary;
import converter.logics.structures.Transition;
import reader.list.Node;

public class TransitionCollector {
	
	//Attributes
	private TransitionDictionary transitionDictionary;
	private SequenceTools sequenceTools;
	private IdGenerator idGenerator;
	private List<Node> xmi;
	private boolean message;
	private int iterator;
	
	//Constructor
	public TransitionCollector () {
		transitionDictionary = TransitionDictionary.getInstance();
		sequenceTools = new SequenceTools();
		idGenerator = IdGenerator.getInstance();
		message = true;
		iterator = 0;
	}

//--Public Methods---------------------------------------------------------------------------------------------------------------------------------------------------//
	public void collect (List<Node> xmi) {
		this.xmi = xmi;
		iterateXmi();
		reset();
	}

	//--Private Methods-------------------------------------------------------------------------------------------------------------------------------------------------//
	private void iterateXmi () {
		String lastId = sequenceTools.getStartId(xmi);
		while (iterator < xmi.size()) {
			if (sequenceTools.isFragment(xmi.get(iterator))) {
				lastId = processFragment(lastId);
			}else{
				iterator ++;
			}
		}
		String finalId = sequenceTools.getFinalId(xmi);
		createTransition(lastId, finalId);
	}

	private String processFragment(String lastId) {
		switch (sequenceTools.getFragmentType(xmi.get(iterator))) {
		case "uml:MessageOccurrenceSpecification":
			if (message) {
				lastId = collectMessage(lastId);
				message = false;
			}else{
				iterator ++;
				message = true;
			}
			break;
		case "uml:CombinedFragment":
			lastId = collectIteractionFragment(lastId);
			break;
		default:
			iterator ++;
		}
		return lastId;
	}

	private String collectIteractionFragment(String lastId) {
		String combinedFragmentId = sequenceTools.getFragmentId(xmi.get(iterator));
		String outputId = sequenceTools.getFirstOperandId(iterator, xmi);
		createTransition(lastId, combinedFragmentId);
		lastId = combinedFragmentId;
		
		int operands = 0;
		int level = xmi.get(iterator).getLevel();
		iterator ++;
		
		while (iterator < xmi.size()) {
			if (xmi.get(iterator).getLevel() == level) {
				break;
			}
			switch (sequenceTools.getNodeName(xmi.get(iterator))) {
			case "fragment":
				lastId = processFragment(lastId);
				break;
			case "operand":
				if (sequenceTools.isClose(xmi.get(iterator))) {
					createTransition(lastId, outputId);
					lastId = combinedFragmentId;
					operands ++;
				}
				iterator ++;
				break;
			default:
				iterator ++;
			}
		}
		
		if (operands == 1) {
			createTransition(combinedFragmentId, outputId);
		}
		
		return outputId;
	}

	private String collectMessage(String lastId) {
		String messageId = sequenceTools.getMessageId(xmi.get(iterator));
		createTransition(lastId, messageId);
		iterator ++;
		return messageId;
	}
	
	private void createTransition (String incomming, String outgoing) {
		Transition transition = new Transition();
		transition.setId(idGenerator.getStringId());
		transition.setIncommingId(incomming);
		transition.setOutgoingId(outgoing);
		transitionDictionary.addTransition(transition);
	}
	
	private void reset() {
		message = true;
		iterator = 0;
	}
	
}
