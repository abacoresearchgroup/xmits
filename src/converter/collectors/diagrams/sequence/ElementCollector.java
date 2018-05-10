package converter.collectors.diagrams.sequence;

import global.structure.Diagram;

import java.util.List;

import converter.logics.Core;
import converter.logics.dictionaries.ElementDictionary;
import converter.logics.structures.Content;
import converter.logics.structures.Element;
import converter.logics.structures.Type;
import reader.list.Node;

public class ElementCollector {

	//Attributes
	private ElementDictionary elementDictionary;
	private MessageDictionary messageDictionary;
	private SequenceTools sequenceTools;
	private boolean message;
	private Core core;
	private List<Node> xmi;
	
	//Constructor
	public ElementCollector () {
		elementDictionary = ElementDictionary.getInstance();
		messageDictionary = MessageDictionary.getInstance();
		sequenceTools = new SequenceTools();
		message = true;
		core = Core.getInstance();
	}
	
	//Public Methods
	public void collect (List<Node> xmi) {
		this.xmi = xmi;
		createStart();
		createFinish();
		colectFragments();
	}

//--Private Methods-----------------------------------------------------------------------------------------------------------------------------------------//
	private void createStart () {
		Content content = new Content();
		content.setDiagram(Diagram.Sequence);
		content.setId(sequenceTools.getStartId(xmi));		
		Element startElement = new Element();
		startElement.setContent(content);
		startElement.setType(Type.Default);
		elementDictionary.addElement(startElement.getContent().getId(), startElement);
		core.setStartElement(startElement);
	}
	
	private void createFinish () {
		Content content = new Content();
		content.setDiagram(Diagram.Sequence);
		content.setId(sequenceTools.getFinalId(xmi));
		Element finishElement = new Element();
		finishElement.setContent(content);
		finishElement.setType(Type.Default);
		elementDictionary.addElement(finishElement.getContent().getId(), finishElement);
	}
	
	private void colectFragments () {
		int iterator = 0;
		for (Node node : xmi) {
			if (sequenceTools.getNodeName(node).equals("fragment")) {
				switch (sequenceTools.getFragmentType(node)) {
				case "uml:MessageOccurrenceSpecification":
					collectMessage(node);
					break;
				case "uml:CombinedFragment":
					collectCombinedtFragment(iterator);
				}
			}
			iterator ++;
		}
	}

	private void collectMessage (Node node) {
		if (message) {
			String messageId = sequenceTools.getMessageId(node);
			Content content = new Content();
			content.setDiagram(Diagram.Sequence);
			content.setId(messageId);
			content.setName(messageDictionary.getMessageById(messageId));
			Element element = new Element();
			element.setContent(content);
			element.setType(Type.State);
			elementDictionary.addElement(messageId, element);
			message = false;
		}else{
			message = true;
		}
	}

	private void collectCombinedtFragment (int iterator) {
		String interactionOperator = sequenceTools.getInteractionOperator(xmi.get(iterator));
		String combinedFragmentId = sequenceTools.getFragmentId(xmi.get(iterator));
		String operand = sequenceTools.getFirstOperandId(iterator, xmi);
		
		switch (interactionOperator) {
		case "opt":
			createDecision(combinedFragmentId);
			createConnection(operand);
			break;
		case "loop":
			createDecision(combinedFragmentId);
			createConnection(operand);
			break;
		case "alt":
			createDecision(combinedFragmentId);
			createConnection(operand);
			break;
		case "par":
			createFork(combinedFragmentId);
			createJoin(operand);
			break;
		default: 
			createConnection(combinedFragmentId);
			createConnection(operand);
		}
		
	}

	private void createJoin(String id) {
		Content content = createContent(id);
		Element join = new Element();
		join.setType(Type.Join);
		join.setContent(content);
		elementDictionary.addElement(id, join);
	}

	private void createConnection(String id) {
		Content content = createContent(id);
		Element connection = new Element();
		connection.setType(Type.Connection);
		connection.setContent(content);
		elementDictionary.addElement(id, connection);
	}

	private void createFork(String id) {
		Content content = createContent(id);
		Element fork = new Element();
		fork.setType(Type.Fork);
		fork.setContent(content);
		elementDictionary.addElement(id, fork);
	}

	private void createDecision(String id) {
		Content content = createContent(id);
		Element decision = new Element();
		decision.setType(Type.Decision);
		decision.setContent(content);
		elementDictionary.addElement(id, decision);
	}
	
	private Content createContent (String id) {
		Content content = new Content();
		content.setDiagram(Diagram.Sequence);
		content.setId(id);
		return content;
	}
	
}
