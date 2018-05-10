package converter.collectors.diagrams.smd;

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
	private SmdTools smdTools;
	private Core core;
	
	//Constructor
	public ElementCollector () {
		elementDictionary = ElementDictionary.getInstance();
		smdTools = new SmdTools();
		core = Core.getInstance();
	}
	
//--Public Methods----------------------------------------------------------------------------------------------------------------------------------------------//
	public void colect(List<Node> xmi) {
		for (Node node : xmi) {
			if (smdTools.nodeNameEquals(node, "subvertex")) {
				collectSubvertex(node);
				continue;
			}
			if (smdTools.nodeNameEquals(node, "connectionPoint")) {
				collectConnectionPoint(node);
			}
		}
	}

//--Private Methods--------------------------------------------------------------------------------------------------------------------------------------------//
	private void collectConnectionPoint(Node node) {
		Element connectionPoint = createElement(node);
		connectionPoint.setType(Type.Connection);
		elementDictionary.addElement(connectionPoint.getContent().getId(), connectionPoint);
	}
	
	private void collectSubvertex(Node node) {
		switch (smdTools.getAttributeValue(node, "xmi:type")) {
		case "uml:State":
			collectState(node);
			break;
		case "uml:Pseudostate":
			collectPseudostate(node);
			break;
		case "uml:FinalState":
			collectFinalState(node);
		}
	}

	private void collectState(Node node) {
		Element state = createElement(node);
		state.setType(Type.State);		
		elementDictionary.addElement(state.getContent().getId(), state);
	}

	private void collectPseudostate(Node node) {
		Element pseudoState = createElement(node);
		
		switch (smdTools.getAttributeValue(node, "kind")) {
		case "fork":
			pseudoState.setType(Type.Fork);
			break;
		case "join":
			pseudoState.setType(Type.Join);
			break;
		case "choice":
			pseudoState.setType(Type.Decision);
			break;
		case "junction":
			pseudoState.setType(Type.Connection);
			break;
		case "terminate":
			pseudoState.setType(Type.Default);
			break;
		default:
			pseudoState.setType(Type.Default);
			core.setStartElement(pseudoState);
		}
		
		elementDictionary.addElement(pseudoState.getContent().getId(), pseudoState);
	}

	private void collectFinalState(Node node) {
		Element finalState = createElement(node);
		finalState.setType(Type.Default);
		elementDictionary.addElement(finalState.getContent().getId(), finalState);
	}
	
	private Element createElement (Node node) {
		Content content = new Content();
		content.setDiagram(Diagram.SMD);
		content.setId(smdTools.getAttributeValue(node, "xmi:id"));
		content.setName(smdTools.getAttributeValue(node, "name"));
		
		Element element = new Element();
		element.setContent(content);
		
		return element;
	}

}
