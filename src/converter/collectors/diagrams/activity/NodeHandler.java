package converter.collectors.diagrams.activity;

import global.structure.Diagram;
import converter.logics.Core;
import converter.logics.dictionaries.ElementDictionary;
import converter.logics.structures.Content;
import converter.logics.structures.Element;
import converter.logics.structures.Type;
import reader.list.Attribute;
import reader.list.Node;

public class NodeHandler {
	
	//Attributes
	private boolean isInitialNode;
	private ElementDictionary elementDictionary;
	private Core core;
	
	//Constructor
	public NodeHandler () {
		isInitialNode = false;
		elementDictionary = ElementDictionary.getInstance();
		core = Core.getInstance();
	}

//--Public Methods------------------------------------------------------------------------------------------------------------------------------------//	
	public void process (Node node) {
		Element element = new Element();
		Content content = new Content();
		for (Attribute attribute : node.getContent().getAttributes()) {
			switch (attribute.getName()) {
				case "xmi:type":
					element.setType(getNodeType(attribute.getValue()));
					break;
				case "xmi:id":
					content.setId(attribute.getValue());
					break;
				case "name":
					content.setName(attribute.getValue());
					break;
			}
		}
		content.setDiagram(Diagram.Activity);
		element.setContent(content);
		elementDictionary.addElement(element.getContent().getId(), element);
		if (isInitialNode) {
			core.setStartElement(element);
			isInitialNode = false;
		}
	}
	
//--Private Methods------------------------------------------------------------------------------------------------------------------------------------//
	private Type getNodeType (String type) {
		switch (type) {
		case "uml:InitialNode":
			isInitialNode = true;
			return Type.Default;
		case "uml:OpaqueAction":
			return Type.State;
		case "uml:DecisionNode":
			return Type.Decision;
		case "uml:MergeNode":
			return Type.Connection;
		case "uml:ForkNode":
			return Type.Fork;
		case "uml:JoinNode":
			return Type.Join;
		default:
			return Type.Default;
		}
	}
	
}
