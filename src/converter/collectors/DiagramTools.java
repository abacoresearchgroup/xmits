package converter.collectors;

import java.util.List;

import reader.list.Attribute;
import reader.list.Node;
import global.exceptions.converter.InvalidXMIException;
import global.exceptions.converter.NotSupportedDiagramException;
import global.structure.Diagram;

public class DiagramTools {

	//Methods
	public Diagram identifyDiagram (List<Node> xmi) throws InvalidXMIException, NotSupportedDiagramException {
		try {
			for (Node node : xmi) {
				if (node.getContent().getName().equals("packagedElement")) {
					for (Attribute attribute : node.getContent().getAttributes()) {
						if (attribute.getName().equals("xmi:type")) {
							switch (attribute.getValue()) {
							case "uml:Activity":
								return Diagram.Activity;
							case "uml:Interaction":
								return Diagram.Sequence;
							case "uml:StateMachine":
								return Diagram.SMD;
							default :
								throw new NotSupportedDiagramException(attribute.getValue());
							}
						}
					}
				}
			}
			throw new InvalidXMIException(); //Extreme rare case of trolling
		}catch (NullPointerException e) {
			throw new InvalidXMIException();
		}
	}
	
}
